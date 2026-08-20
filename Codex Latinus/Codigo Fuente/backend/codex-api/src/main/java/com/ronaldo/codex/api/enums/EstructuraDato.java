package com.ronaldo.codex.api.enums;

/**
 *
 * @author ronaldo
 */
public enum EstructuraDato {
    ESTO("esto"),
    SERIES("series");

    private final String nombre;

    EstructuraDato(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreEnMinusculas() {
        return nombre;
    }
}
