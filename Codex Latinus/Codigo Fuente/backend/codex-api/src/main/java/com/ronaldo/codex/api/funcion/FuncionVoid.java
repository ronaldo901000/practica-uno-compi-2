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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ronaldo
 */
public class FuncionVoid extends Funcion {

    public FuncionVoid(int fila, int columna, String id) {
        super(fila, columna, id);
    }

    @Override
    public void realizarTraduccion(StringBuffer sb) {
        sb.append("actioway ");
        sb.append(traductor.traducir(id)).append("(");
        
        traducirParametros(sb);
        sb.append(") \n").append("{").append("\n");
        traducirSeccionVariables(sb);
        traducirInstruccionesInternas(sb);

        sb.append("} inisfay;\n");

    }

    @Override
    public void verificarSemantica(Semantica semantica) throws PilaException, Exception {

        if (semantica.getTablaSimbolos().buscar(this.id, semantica.GLOBAL) != null) {
            semantica.getErrores().add(new ErrorSemantico(
                    getFila(),
                    getColumna(),
                    this.id,
                    "La funcion '" + this.id + "' ya ha sido definida anteriormente."
            ));
            return;
        }

        Simbolo simboloFuncion = new Simbolo();
        simboloFuncion.setLlave(new Llave(this.id, semantica.getGLOBAL()));
        simboloFuncion.setCategoria(Categoria.FUNCION);
        simboloFuncion.setTipoRetorno(Tipo.VOID.ordinal());

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

        semantica.salirAmbito();
    }
}
