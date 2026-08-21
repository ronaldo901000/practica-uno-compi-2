package com.ronaldo.codex.api.funcion;

import com.ronaldo.codex.api.declaracion.Declaracion;
import com.ronaldo.codex.api.dto.entrada.error.analisis.ErrorSemantico;
import com.ronaldo.codex.api.enums.Categoria;
import com.ronaldo.codex.api.enums.Tipo;
import com.ronaldo.codex.api.exceptions.PilaException;
import com.ronaldo.codex.api.instruccion.Instruccion;
import com.ronaldo.codex.api.parametros.creacion.ParametroCreacion;
import com.ronaldo.codex.api.semantica.Llave;
import com.ronaldo.codex.api.semantica.Semantica;
import com.ronaldo.codex.api.semantica.Simbolo;
import com.ronaldo.codex.api.services.verificacion.VerificadorTipos;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ronaldo
 */
public class FuncionReturn extends Funcion {

    private Tipo tipoRetorno;
    private String idRetorno;

    public FuncionReturn(String tipoRetornoString, String idRetorno,
            int fila, int columna, String id) {

        super(fila, columna, id);
        this.idRetorno = idRetorno;
        VerificadorTipos verificadorTipos = new VerificadorTipos();
        this.tipoRetorno = verificadorTipos.verificar(tipoRetornoString);

    }

    @Override
    public void realizarTraduccion(StringBuffer sb) {
        sb.append("atioray ");
        sb.append(traductor.traducir(tipoRetorno.getText())).append(" ");

        sb.append(traductor.traducir(id)).append("(");

        traducirParametros(sb);
        sb.append(") \n").append("{").append("\n");     
        traducirSeccionVariables(sb);
        traducirInstruccionesInternas(sb);
        sb.append("eddereray ").append(traductor.traducir(idRetorno));
        sb.append("} inisfay;\n");

    }

    @Override
    public void verificarSemantica(Semantica semantica) throws PilaException, Exception {

        //Verifica que no exista una funcion con el mismo ID en el ambito global
        if (semantica.getTablaSimbolos().buscar(this.id, semantica.getGLOBAL()) != null) {
            semantica.getErrores().add(new ErrorSemantico(
                    getFila(),
                    getColumna(),
                    this.id,
                    "La funcion '" + this.id + "' ya ha sido definida previamente."
            ));
            return;
        }

        //Registrar la funcion en la tabla de simbolos
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

        //Entrar al nuevo ambito
        semantica.entrarAmbito(this.id);

        //Procesar parametros
        if (this.parametros != null) {
            for (ParametroCreacion param : this.parametros) {
                if (param != null) {
                    param.verificarSemantica(semantica);
                }
            }
        }

        //Procesar declaraciones de variables
        if (this.variables != null) {
            for (Declaracion dec : this.variables) {
                if (dec != null) {
                    dec.verificarSemantica(semantica);
                }
            }
        }

        //Procesar instrucciones internas
        if (this.instrucciones != null) {
            for (Instruccion inst : this.instrucciones) {
                if (inst != null) {
                    inst.verificarSemantica(semantica);
                }
            }
        }

        //Validar el identificador de retorno y su tipo
        if (this.idRetorno != null) {

            Simbolo simRetorno = semantica.getTablaSimbolos().buscar(this.idRetorno, semantica.getAmbitoActual());

            if (simRetorno == null) {
                simRetorno = semantica.getTablaSimbolos().buscar(this.idRetorno, semantica.GLOBAL);
            }

            if (simRetorno == null) {
                semantica.getErrores().add(new ErrorSemantico(
                        getFila(),
                        getColumna(),
                        this.idRetorno,
                        "La variable de retorno '" + this.idRetorno + "' no esta declarada."
                ));
            } else {

                Tipo tipoVarRetorno = Tipo.values()[simRetorno.getIdTipo()];

                if (tipoVarRetorno != this.tipoRetorno) {
                    semantica.getErrores().add(new ErrorSemantico(
                            getFila(),
                            getColumna(),
                            this.idRetorno,
                            "Tipo de retorno incompatible en la funcion '" + this.id
                            + "'. Se esperaba '" + this.tipoRetorno + "' pero la variable '"
                            + this.idRetorno + "' es de tipo '" + tipoVarRetorno + "'"
                    ));
                }
            }
        } else {
            semantica.getErrores().add(new ErrorSemantico(
                    getFila(),
                    getColumna(),
                    this.id,
                    "La funcion '" + this.id
                    + "' requiere especificar un identificador de retorno valido."
            ));
        }

        semantica.salirAmbito();
    }

    public Tipo getTipoRetorno() {
        return tipoRetorno;
    }

    public void setTipoRetorno(Tipo tipoRetorno) {
        this.tipoRetorno = tipoRetorno;
    }

    public String getIdRetorno() {
        return idRetorno;
    }

    public void setIdRetorno(String idRetorno) {
        this.idRetorno = idRetorno;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ParametroCreacion> getParametros() {
        return parametros;
    }

    public void setParametros(List<ParametroCreacion> parametros) {
        this.parametros = parametros;
    }

    public List<Declaracion> getVariables() {
        return variables;
    }

    public void setVariables(List<Declaracion> variables) {
        this.variables = variables;
    }

    public List<Instruccion> getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(List<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
    }

}
