package com.ronaldo.codex.api.nodo;

import com.ronaldo.codex.api.interfaces.Visitable;

/**
 *
 * @author ronaldo
 */
public abstract class Nodo implements Visitable {

    protected int fila;
    protected int columna;

    public Nodo(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna + 1;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

}
