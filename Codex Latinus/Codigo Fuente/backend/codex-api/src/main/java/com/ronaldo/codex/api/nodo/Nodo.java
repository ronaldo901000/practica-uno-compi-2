package com.ronaldo.codex.api.nodo;

import com.ronaldo.codex.api.interfaces.Visitable;

/**
 *
 * @author ronaldo
 */
public class Nodo implements Visitable {

    private int fila;
    private int columna;

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
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

}
