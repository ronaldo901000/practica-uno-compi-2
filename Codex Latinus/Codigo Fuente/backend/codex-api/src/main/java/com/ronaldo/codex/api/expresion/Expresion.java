package com.ronaldo.codex.api.expresion;

import com.ronaldo.codex.api.enums.Tipo;
import com.ronaldo.codex.api.nodo.Nodo;

/**
 *
 * @author ronaldo
 */
public abstract class Expresion extends Nodo {

    protected Tipo tipoResultado = Tipo.ERROR;

    public Expresion(int fila, int columna) {
        super(fila, columna);
    }

    public Tipo getTipoResultado() {
        return tipoResultado;
    }

    public void setTipoResultado(Tipo tipoResultado) {
        this.tipoResultado = tipoResultado;
    }



}
