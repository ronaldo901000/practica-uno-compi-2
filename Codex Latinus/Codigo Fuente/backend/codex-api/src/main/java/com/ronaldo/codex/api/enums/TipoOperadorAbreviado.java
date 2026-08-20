package com.ronaldo.codex.api.enums;

/**
 *
 * @author ronaldo
 */
public enum TipoOperadorAbreviado {
    SUMAR("++"),
    RESTAR("--");

    private final String operador;

    TipoOperadorAbreviado(String operador) {
        this.operador = operador;
    }

    public String getOperador() {
        return operador;
    }
}
