package com.ronaldo.codex.api.expresion;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ronaldo
 */
@Getter
@Setter
public class AccesoArray extends Expresion {

    private int posicion;
    private String id;

    public AccesoArray(int posicion, String id, int fila, int columna) {
        super(fila, columna);
        this.posicion = posicion;
        this.id = id;
    }

}
