package com.ronaldo.codex.api.enums;

/**
 *
 * @author ronaldo
 */
public enum TipoCondicion {
    IGUAL("=="),
    DIFERENTE("!="),
    MAYOR_IGUAL(">="),
    MAYOR(">"),
    MENOR_IGUAL("<="),
    MENOR("<"),
    AND("&&"),
    OR("||"),
    NOT("non"),
    EXPRESION("");
    ;

    private final String operador;

    TipoCondicion(String operador) {
        this.operador = operador;
    }

    public String getOperador() {
        return operador;
    }
}
