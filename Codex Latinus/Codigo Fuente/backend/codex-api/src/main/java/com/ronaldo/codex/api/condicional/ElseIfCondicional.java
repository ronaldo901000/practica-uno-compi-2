package com.ronaldo.codex.api.condicional;

import com.ronaldo.codex.api.dto.entrada.error.analisis.ErrorSemantico;
import com.ronaldo.codex.api.enums.Tipo;
import com.ronaldo.codex.api.instruccion.Instruccion;
import com.ronaldo.codex.api.semantica.Semantica;

/**
 *
 * @author ronaldo
 */
public class ElseIfCondicional extends Condicional {

    public ElseIfCondicional(int fila, int columna) {
        super(fila, columna);
    }

    @Override
    public void verificarSemantica(Semantica semantica) throws Exception {

        if (this.condicion != null) {
            this.condicion.verificarSemantica(semantica);

            Tipo tipoCondicion = this.condicion.getTipoResultado();

            if (tipoCondicion != Tipo.ERROR && tipoCondicion != Tipo.BOOLEANO) {
                semantica.getErrores().add(new ErrorSemantico(
                        getFila(),
                        getColumna(),
                        "aliter con condicion",
                        "La condición del 'aliter con condicion' debe ser de "
                        + "tipo BOOLEANO, pero se obtuvo " + tipoCondicion
                ));
            }
        }

        if (this.instruccionesInternas != null) {
            for (Instruccion instruccion : this.instruccionesInternas) {
                if (instruccion != null) {
                    instruccion.verificarSemantica(semantica);
                }
            }
        }
    }

}
