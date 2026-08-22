package com.ronaldo.codex.api.expresion;

import com.ronaldo.codex.api.enums.Tipo;
import com.ronaldo.codex.api.nodo.Nodo;

/**
 * @author ronaldo
 */
public abstract class Expresion extends Nodo {

    protected Tipo tipoResultado = Tipo.ERROR;
    protected String nombreTipoEstructura; 

    public Expresion(int fila, int columna) {
        super(fila, columna);
    }

    public Tipo getTipoResultado() {
        return tipoResultado;
    }

    public void setTipoResultado(Tipo tipoResultado) {
        this.tipoResultado = tipoResultado;
    }

    public String getNombreTipoEstructura() {
        return nombreTipoEstructura;
    }

    public void setNombreTipoEstructura(String nombreTipoEstructura) {
        this.nombreTipoEstructura = nombreTipoEstructura;
    }
}