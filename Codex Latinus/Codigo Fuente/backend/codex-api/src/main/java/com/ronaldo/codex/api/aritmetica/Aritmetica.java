package com.ronaldo.codex.api.aritmetica;

import com.ronaldo.codex.api.expresion.Expresion;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ronaldo
 */
@Getter
@Setter
public class Aritmetica extends Expresion {

    private Object valorUno;
    private Object valorDos;

    public Aritmetica(Object valorUno, Object valorDos, int fila, int columna) {
        super(fila, columna);
        this.valorUno = valorUno;
        this.valorDos = valorDos;
    }

    
    

}
