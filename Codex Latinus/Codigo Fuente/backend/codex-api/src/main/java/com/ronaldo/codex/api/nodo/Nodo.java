package com.ronaldo.codex.api.nodo;

import com.ronaldo.codex.api.interfaces.Visitable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ronaldo
 */

@Getter
@Setter
public class Nodo implements Visitable{
    private int fila;
    private int columna;

    public Nodo(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }
    
  
}
