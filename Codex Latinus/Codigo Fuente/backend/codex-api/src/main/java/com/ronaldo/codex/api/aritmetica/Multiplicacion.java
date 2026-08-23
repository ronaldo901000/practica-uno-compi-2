package com.ronaldo.codex.api.aritmetica;

import com.ronaldo.codex.api.dto.entrada.error.analisis.ErrorSemantico;
import com.ronaldo.codex.api.enums.Tipo;
import com.ronaldo.codex.api.expresion.Expresion;
import com.ronaldo.codex.api.semantica.Semantica;

/**
 *
 * @author ronaldo
 */
public class Multiplicacion extends Aritmetica {

    public Multiplicacion(Expresion valorUno, Expresion valorDos, int fila, int columna) {
        super(valorUno, valorDos, fila, columna);
    }

    @Override
    public void realizarTraduccion(StringBuffer sb) {
        valorUno.realizarTraduccion(sb);
        sb.append(" * ");
        valorDos.realizarTraduccion(sb);
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
                    "*",
                    "El tipo TEXTUM no es compatible con la operacion de multiplicacion"
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
                    "*",
                    "No se pueden multiplicar tipos de datos no primitivos (" + tipoUno + " * " + tipoDos + ")."
            ));
            return;
        }

        if (jerarquiaUno >= jerarquiaDos) {
            this.tipoResultado = tipoUno;
        } else {
            this.tipoResultado = tipoDos;
        }
    }

    @Override
    protected String getOperador() {
        return "*";
    }

}
