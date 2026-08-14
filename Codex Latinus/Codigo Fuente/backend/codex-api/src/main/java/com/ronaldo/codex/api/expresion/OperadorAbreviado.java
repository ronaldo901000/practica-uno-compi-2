package com.ronaldo.codex.api.expresion;

import com.ronaldo.codex.api.enums.TipoOperadorAbreviado;

/**
 *
 * @author ronaldo
 */
public class OperadorAbreviado extends Expresion {

    private TipoOperadorAbreviado tipo;
    private String id;

    public OperadorAbreviado(TipoOperadorAbreviado tipo, String id, int fila, int columna) {
        super(fila, columna);
        this.tipo = tipo;
        this.id = id;
    }

    public TipoOperadorAbreviado getTipo() {
        return tipo;
    }

    public void setTipo(TipoOperadorAbreviado tipo) {
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
