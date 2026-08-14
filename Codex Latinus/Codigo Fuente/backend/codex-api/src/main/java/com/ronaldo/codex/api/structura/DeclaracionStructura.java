package com.ronaldo.codex.api.structura;

import com.ronaldo.codex.api.nodo.Nodo;

/**
 *
 * @author ronaldo
 */
public class DeclaracionStructura extends Nodo {

    private String id;
    private AtributosStructura atributos;

    public DeclaracionStructura(String id, AtributosStructura atributos, int fila, int columna) {
        super(fila, columna);
        this.id = id;
        this.atributos = atributos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AtributosStructura getAtributos() {
        return atributos;
    }

    public void setAtributos(AtributosStructura atributos) {
        this.atributos = atributos;
    }

}
