package com.ronaldo.codex.api.asignacion;

import com.ronaldo.codex.api.expresion.Expresion;
import com.ronaldo.codex.api.instruccion.Instruccion;

/**
 *
 * @author ronaldo
 */
public class Asignacion extends Instruccion {

    private String id;
    private Expresion expresion;

    public Asignacion(String id, Expresion expresion, int fila, int columna) {
        super(fila, columna);
        this.id = id;
        this.expresion = expresion;
    }

}
