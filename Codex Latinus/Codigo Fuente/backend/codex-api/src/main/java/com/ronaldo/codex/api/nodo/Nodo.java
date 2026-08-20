package com.ronaldo.codex.api.nodo;

import com.ronaldo.codex.api.interfaces.Traducible;
import com.ronaldo.codex.api.interfaces.Visitable;
import com.ronaldo.codex.api.traductor.TraductorPigLatin;

/**
 *
 * @author ronaldo
 */
public abstract class Nodo implements Visitable, Traducible {

    protected int fila;
    protected int columna;
    protected TraductorPigLatin traductor;

    public Nodo(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.traductor = new TraductorPigLatin();
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
