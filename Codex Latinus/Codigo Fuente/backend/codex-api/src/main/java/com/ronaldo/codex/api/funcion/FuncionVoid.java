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
    public void verificarSemantica(Semantica semantica) throws PilaException {

        if (semantica.getTablaSimbolos().buscar(this.id, "global") != null) {
            semantica.getErrores().add(new ErrorSemantico(
                    getFila(),
                    getColumna(),
                    this.id,
                    "La función o método '" + this.id + "' ya ha sido definido previamente."
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

        try {

            if (this.parametros != null) {
                for (ParametroCreacion param : this.parametros) {
                    if (param != null) {
                        try {
                            param.verificarSemantica(semantica);
                        } catch (Exception e) {

                        }
                    }
                }
            }

            if (this.variables != null) {
                for (Declaracion dec : this.variables) {
                    if (dec != null) {
                        try {
                            dec.verificarSemantica(semantica);
                        } catch (Exception e) {

                        }
                    }
                }
            }

            if (this.instrucciones != null) {
                for (Instruccion inst : this.instrucciones) {
                    if (inst != null) {
                        try {
                            inst.verificarSemantica(semantica);
                        } catch (Exception e) {

                        }
                    }
                }
            }

        } finally {
            semantica.salirAmbito();
        }
    }
}
