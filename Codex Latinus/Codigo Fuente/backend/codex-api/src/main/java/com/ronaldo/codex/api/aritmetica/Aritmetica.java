package com.ronaldo.codex.api.aritmetica;

import com.ronaldo.codex.api.expresion.Expresion;

/**
 *
 * @author ronaldo
 */
public class Aritmetica extends Expresion {

    private Object valorUno;
    private Object valorDos;

    public Aritmetica(Object valorUno, Object valorDos, int fila, int columna) {
        super(fila, columna);
        this.valorUno = valorUno;
        this.valorDos = valorDos;
    }

    public Object getValorUno() {
        return valorUno;
    }

    public void setValorUno(Object valorUno) {
        this.valorUno = valorUno;
    }

    public Object getValorDos() {
        return valorDos;
    }

    public void setValorDos(Object valorDos) {
        this.valorDos = valorDos;
    }

}
