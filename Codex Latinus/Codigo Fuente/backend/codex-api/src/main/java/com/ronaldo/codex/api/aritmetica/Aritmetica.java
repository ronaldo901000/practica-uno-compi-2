package com.ronaldo.codex.api.aritmetica;

import com.ronaldo.codex.api.enums.Tipo;
import static com.ronaldo.codex.api.enums.Tipo.DECIMALIS;
import static com.ronaldo.codex.api.enums.Tipo.LITTERA;
import static com.ronaldo.codex.api.enums.Tipo.NUMERUS;
import com.ronaldo.codex.api.expresion.Expresion;
import static com.ronaldo.codex.api.enums.Tipo.BOOL;

/**
 *
 * @author ronaldo
 */
public abstract class Aritmetica extends Expresion {

    protected Expresion valorUno;
    protected Expresion valorDos;

    public Aritmetica(Expresion valorUno, Expresion valorDos, int fila, int columna) {
        super(fila, columna);
        this.valorUno = valorUno;
        this.valorDos = valorDos;
    }

    public Object getValorUno() {
        return valorUno;
    }

    public void setValorUno(Expresion valorUno) {
        this.valorUno = valorUno;
    }

    public Object getValorDos() {
        return valorDos;
    }

    public void setValorDos(Expresion valorDos) {
        this.valorDos = valorDos;
    }

    public int obtenerNivelJerarquiaResMultiDiv(Tipo tipo) {
        switch (tipo) {
            case DECIMALIS:
                return 4;
            case NUMERUS:
                return 3;
            case LITTERA:
                return 2;
            case BOOL:
                return 1;
            default:
                return -1;
        }
    }

}
