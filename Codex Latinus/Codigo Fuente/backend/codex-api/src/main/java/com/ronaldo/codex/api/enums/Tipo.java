package com.ronaldo.codex.api.enums;

/**
 *
 * @author ronaldo
 */
public enum Tipo {
    NUMERUS("numerus"),
    DECIMALIS("decimalis"),
    TEXTUM("textum"),
    LITTERA("littera"),
    BOOL("bool"),
    VOID("void"),
    ARRAY("array"),
    STRUCTURA("structura"),
    IDENTIFICADOR("identificador"),
    ELEMENTO_ARRAY("elemento_array"),
    PARENTESIS("parentesis"),
    ERROR("error");

    private final String txt;

    Tipo(String txt) {
        this.txt = txt;
    }

    public String getText() {
        return txt;
    }
}
