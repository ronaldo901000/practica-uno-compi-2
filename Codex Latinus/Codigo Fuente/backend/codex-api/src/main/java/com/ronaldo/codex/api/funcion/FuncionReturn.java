package com.ronaldo.codex.api.funcion;

import com.ronaldo.codex.api.condicional.Condicional;
import com.ronaldo.codex.api.condicional.ElseCondicion;
import com.ronaldo.codex.api.condicional.IfCondicional;
import com.ronaldo.codex.api.declaracion.Declaracion;
import com.ronaldo.codex.api.dto.entrada.error.analisis.ErrorSemantico;
import com.ronaldo.codex.api.enums.Categoria;
import com.ronaldo.codex.api.enums.Tipo;
import com.ronaldo.codex.api.exceptions.PilaException;
import com.ronaldo.codex.api.instruccion.Instruccion;
import com.ronaldo.codex.api.retorno.Retorno;
import com.ronaldo.codex.api.parametros.creacion.ParametroCreacion;
import com.ronaldo.codex.api.semantica.Llave;
import com.ronaldo.codex.api.semantica.Semantica;
import com.ronaldo.codex.api.semantica.Simbolo;
import com.ronaldo.codex.api.services.verificacion.VerificadorTipos;

import java.util.ArrayList;
import java.util.List;

public class FuncionReturn extends Funcion {

    private Tipo tipoRetorno;
    private String tipoRetornoString;

    public FuncionReturn(String tipoRetornoString, int fila, int columna, String id) {
        super(fila, columna, id);
        this.tipoRetornoString = tipoRetornoString;
        VerificadorTipos verificadorTipos = new VerificadorTipos();
        this.tipoRetorno = verificadorTipos.verificar(tipoRetornoString);

    }

    @Override
    public void realizarTraduccion(StringBuffer sb) {
        sb.append("atioray ");
        String tipoTraducido = (tipoRetorno != null) ? tipoRetorno.getText() : tipoRetornoString;
        sb.append(traductor.traducir(tipoTraducido)).append(" ");
        sb.append(traductor.traducir(id)).append("(");
        traducirParametros(sb);
        sb.append(") \n").append("{").append("\n");
        traducirSeccionVariables(sb);
        traducirInstruccionesInternas(sb);
        sb.append("} inisfay;\n");
    }

    @Override
    public void verificarSemantica(Semantica semantica) throws PilaException, Exception {

        if (semantica.getTablaSimbolos().buscar(this.id, semantica.getGLOBAL()) != null) {
            semantica.getErrores().add(new ErrorSemantico(
                    getFila(), getColumna(), this.id,
                    "La funcion '" + this.id + "' ya ha sido definida previamente."
            ));
            return;
        }

        Simbolo simboloFuncion = new Simbolo();
        simboloFuncion.setLlave(new Llave(this.id, semantica.getGLOBAL()));
        simboloFuncion.setCategoria(Categoria.FUNCION);
        simboloFuncion.setTipoRetorno(this.tipoRetorno != null ? this.tipoRetorno.ordinal() : 5);

        List<String> tiposParamsList = new ArrayList<>();
        if (this.parametros != null) {
            for (ParametroCreacion p : this.parametros) {
                if (p != null && p.getTipo() != null) {
                    tiposParamsList.add(p.getTipo().getText());
                }
            }
        }
        simboloFuncion.setListaParams(tiposParamsList);
        simboloFuncion.setNumeroParams(tiposParamsList.size());

        semantica.getTablaSimbolos().insertar(simboloFuncion);

        semantica.entrarAmbito(this.id);
        semantica.entrarFuncionConRetorno(this.tipoRetornoString);

        int erroresAntesDelCuerpo = semantica.getErrores().size();

        if (this.parametros != null) {
            for (ParametroCreacion param : this.parametros) {
                if (param != null) {
                    param.verificarSemantica(semantica);
                }
            }
        }

        if (this.variables != null) {
            for (Declaracion dec : this.variables) {
                if (dec != null) {
                    dec.verificarSemantica(semantica);
                }
            }
        }

        if (this.instrucciones != null) {
            for (Instruccion inst : this.instrucciones) {
                if (inst != null) {
                    inst.verificarSemantica(semantica);
                }
            }
        }

        verificarCodigoNoAlcanzable(this.instrucciones, semantica);

        int erroresActuales = semantica.getErrores().size();
        boolean huboErroresEnCuerpo = false;

        if (erroresActuales > erroresAntesDelCuerpo) {
            huboErroresEnCuerpo = true;
        }

        if (!huboErroresEnCuerpo) {
            if (!todosLosCaminosRetornan(this.instrucciones)) {
                semantica.getErrores().add(new ErrorSemantico(
                        getFila(), getColumna(), this.id,
                        "La funcion '" + this.id + "' no retorna un valor en todos los caminos posibles."
                ));
            }
        }

        semantica.salirFuncion();
        semantica.salirAmbito();
    }

    private void verificarCodigoNoAlcanzable(List<Instruccion> instrucciones, Semantica semantica) {
        if (instrucciones == null) {
            return;
        }

        boolean yaRetorno = false;

        for (Instruccion inst : instrucciones) {
            if (inst == null) {
                continue;
            }

            if (yaRetorno) {
                semantica.getErrores().add(new ErrorSemantico(
                        inst.getFila(), inst.getColumna(), "",
                        "Codigo no alcanzable: ya se habia retornado un valor previamente."
                ));
                break;
            }

            if (inst instanceof Retorno) {
                yaRetorno = true;
            } else if (inst instanceof Condicional) {
                List<Instruccion> instruccionUnica = new ArrayList<>();
                instruccionUnica.add(inst);

                if (todosLosCaminosRetornan(instruccionUnica)) {
                    yaRetorno = true;
                }
            }
        }
    }

    private boolean todosLosCaminosRetornan(List<Instruccion> instrucciones) {
        if (instrucciones == null) {
            return false;
        }

        for (Instruccion inst : instrucciones) {
            if (inst == null) {
                continue;
            }

            if (inst instanceof Retorno) {
                return true;
            }

            if (inst instanceof IfCondicional) {
                IfCondicional ifCond = (IfCondicional) inst;
                List<Condicional> bifurcaciones = ifCond.getBifurcaciones();

                if (bifurcaciones == null || bifurcaciones.isEmpty()) {
                    continue;
                }

                boolean ramaSiRetorna = todosLosCaminosRetornan(ifCond.getInstruccionesInternas());

                if (!ramaSiRetorna) {
                    continue;
                }

                Condicional ultimaBif = bifurcaciones.get(bifurcaciones.size() - 1);
                boolean tieneElseFinal = (ultimaBif instanceof ElseCondicion);

                if (!tieneElseFinal) {
                    continue;
                }

                boolean todasBifRetornan = true;
                for (int i = 0; i < bifurcaciones.size(); i++) {
                    Condicional bif = bifurcaciones.get(i);
                    boolean ret = todosLosCaminosRetornan(bif.getInstruccionesInternas());
                    if (!ret) {
                        todasBifRetornan = false;
                        break;
                    }
                }

                if (todasBifRetornan) {
                    return true;
                }
            }
        }
        return false;
    }

    public Tipo getTipoRetorno() {
        return tipoRetorno;
    }

    public void setTipoRetorno(Tipo tipoRetorno) {
        this.tipoRetorno = tipoRetorno;
    }

    public String getTipoRetornoString() {
        return tipoRetornoString;
    }

    public void setTipoRetornoString(String tipoRetornoString) {
        this.tipoRetornoString = tipoRetornoString;
    }

    @Override
    public void generarDot(StringBuffer sb) {
        String tipoRet = (this.tipoRetorno != null) ? this.tipoRetorno.getText() : this.tipoRetornoString;

        sb.append("  nodo").append(idNodo)
                .append(" [label=\"Funcion Con Retorno:\\n").append(this.id)
                .append("\\nTipo: ").append(tipoRet)
                .append("\", fillcolor=\"white\"];\n");

        if (this.parametros != null) {
            for (ParametroCreacion param : this.parametros) {
                if (param != null) {
                    param.generarDot(sb);
                    sb.append("  nodo").append(idNodo)
                            .append(" -> nodo").append(param.getIdNodo())
                            .append(" [label=\"parametro\"];\n");
                }
            }
        }

        if (this.variables != null) {
            for (Declaracion dec : this.variables) {
                if (dec != null) {
                    dec.generarDot(sb);
                    sb.append("  nodo").append(idNodo)
                            .append(" -> nodo").append(dec.getIdNodo())
                            .append(" [label=\"variable\"];\n");
                }
            }
        }

        if (this.instrucciones != null) {
            for (Instruccion inst : this.instrucciones) {
                if (inst != null) {
                    inst.generarDot(sb);
                    sb.append("  nodo").append(idNodo)
                            .append(" -> nodo").append(inst.getIdNodo())
                            .append(" [label=\"instruccion\"];\n");
                }
            }
        }
    }
}
