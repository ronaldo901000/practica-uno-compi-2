package com.ronaldo.codex.api.expresion;

import com.ronaldo.codex.api.expresion.Expresion;

/**
 *
 * @author ronaldo
 */
public class AccesoVariable extends Expresion{
    private String id;
    
    public AccesoVariable(int fila, int columna, String id) {
        super(fila, columna);
        this.id = id;
    }
    
}
