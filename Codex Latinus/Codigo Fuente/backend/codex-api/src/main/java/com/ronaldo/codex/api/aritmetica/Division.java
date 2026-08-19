package com.ronaldo.codex.api.aritmetica;

import com.ronaldo.codex.api.dto.entrada.error.analisis.ErrorSemantico;
import com.ronaldo.codex.api.enums.Tipo;
import com.ronaldo.codex.api.expresion.Expresion;
import com.ronaldo.codex.api.semantica.Semantica;

/**
 *
 * @author ronaldo
 */
public class Division extends Aritmetica {

    public Division(Expresion valorUno, Expresion valorDos, int fila, int columna) {
        super(valorUno, valorDos, fila, columna);
    }

    @Override
    public void verificarSemantica(Semantica semantica) throws Exception {

        valorUno.verificarSemantica(semantica);
        valorDos.verificarSemantica(semantica);

        Tipo tipoUno = valorUno.getTipoResultado();
        Tipo tipoDos = valorDos.getTipoResultado();

        if (tipoUno == Tipo.ERROR || tipoDos == Tipo.ERROR) {
            this.tipoResultado = Tipo.ERROR;
            return;
        }

        if (tipoUno == Tipo.TEXTUM || tipoDos == Tipo.TEXTUM) {
            this.tipoResultado = Tipo.ERROR;
            semantica.getErrores().add(new ErrorSemantico(
                    fila,
                    columna,
                    "/",
                    "El tipo TEXTUM no es compatible con la operación de división."
            ));
            return;
        }

       
        if (esCero(valorDos)) {
            this.tipoResultado = Tipo.ERROR;
            semantica.getErrores().add(new ErrorSemantico(
                    fila,
                    columna,
                    "/",
                    "Error Semántico: División entre cero."
            ));
            return;
        }


        int jerarquiaUno = obtenerNivelJerarquiaResMultiDiv(tipoUno);
        int jerarquiaDos = obtenerNivelJerarquiaResMultiDiv(tipoDos);

        if (jerarquiaUno == -1 || jerarquiaDos == -1) {
            this.tipoResultado = Tipo.ERROR;
            semantica.getErrores().add(new ErrorSemantico(
                    fila,
                    columna,
                    "/",
                    "No se pueden dividir estos tipos (" + tipoUno + " / " + tipoDos + ")."
            ));
            return;
        }

        if (jerarquiaUno >= jerarquiaDos) {
            this.tipoResultado = tipoUno;
        } else {
            this.tipoResultado = tipoDos;
        }
    }

    private boolean esCero(Expresion expr) {

        if (expr instanceof ElementoTerminal) {
            ElementoTerminal terminal = (ElementoTerminal) expr;
            String valor = (String) terminal.getValor();
            try {
                double num = Double.parseDouble(valor);
                return num == 0;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        return false;
    }

}
