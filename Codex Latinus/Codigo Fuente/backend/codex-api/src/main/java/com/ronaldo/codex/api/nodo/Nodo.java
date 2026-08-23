package com.ronaldo.codex.api.nodo;

import com.ronaldo.codex.api.interfaces.Dibujable;
import com.ronaldo.codex.api.interfaces.Traducible;
import com.ronaldo.codex.api.interfaces.Visitable;
import com.ronaldo.codex.api.traductor.TraductorPigLatin;

/**
 *
 * @author ronaldo
 */
public abstract class Nodo implements Visitable, Traducible, Dibujable {

    protected static int contadorNodos = 0;
    protected int idNodo;
    protected int fila;
    protected int columna;
    protected TraductorPigLatin traductor;

    public Nodo(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.traductor = new TraductorPigLatin();
        contadorNodos++;     
        this.idNodo = contadorNodos;
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

    public int getContadorNodos() {
        return contadorNodos;
    }

    public void setContadorNodos(int contadorNodos) {
        this.contadorNodos = contadorNodos;
    }

    public int getIdNodo() {
        return idNodo;
    }

    public void setIdNodo(int idNodo) {
        this.idNodo = idNodo;
    }

    public TraductorPigLatin getTraductor() {
        return traductor;
    }

    public void setTraductor(TraductorPigLatin traductor) {
        this.traductor = traductor;
    }

}
