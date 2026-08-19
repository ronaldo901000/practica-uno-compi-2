package com.ronaldo.codex.api.aritmetica;

import com.ronaldo.codex.api.dto.entrada.error.analisis.ErrorSemantico;
import com.ronaldo.codex.api.enums.Tipo;
import static com.ronaldo.codex.api.enums.Tipo.BOOLEANO;
import static com.ronaldo.codex.api.enums.Tipo.DECIMALIS;
import static com.ronaldo.codex.api.enums.Tipo.LITTERA;
import static com.ronaldo.codex.api.enums.Tipo.NUMERUS;
import static com.ronaldo.codex.api.enums.Tipo.TEXTUM;
import com.ronaldo.codex.api.expresion.Expresion;
import com.ronaldo.codex.api.semantica.Semantica;

/**
 *
 * @author ronaldo
 */
public class Suma extends Aritmetica {

    public Suma(Expresion valorUno, Expresion valorDos, int fila, int columna) {
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
            this.tipoResultado = Tipo.TEXTUM;
            return;
        }

        int jerarquiaUno = obtenerNivelJerarquia(tipoUno);
        int jerarquiaDos = obtenerNivelJerarquia(tipoDos);

        if (jerarquiaUno == -1 || jerarquiaDos == -1) {
            this.tipoResultado = Tipo.ERROR;
            semantica.getErrores().add(new ErrorSemantico(
                    fila,
                    columna,
                    "+",
                    "No se pueden operar estos tipos (" + tipoUno + " + " + tipoDos + ")."
            ));
            return;
        }

        if (jerarquiaUno >= jerarquiaDos) {
            this.tipoResultado = tipoUno;
        } else {
            this.tipoResultado = tipoDos;
        }
    }

    public int obtenerNivelJerarquia(Tipo tipo) {
        return switch (tipo) {
            case TEXTUM ->
                5;
            case DECIMALIS ->
                4;
            case NUMERUS ->
                3;
            case LITTERA ->
                2;
            case BOOLEANO ->
                1;
            default ->
                -1;
        };
    }

}
