package com.ronaldo.codex.api.aritmetica;

import com.ronaldo.codex.api.enums.Tipo;
import com.ronaldo.codex.api.expresion.Expresion;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ronaldo
 */
@Getter
@Setter
public class ElementoTerminal extends Expresion{
    private Tipo tipo;
    private Object valor;

    public ElementoTerminal(int fila, int columna, Object valor, Tipo tipo) {
        super(fila, columna);
        this.valor = valor;
        this.tipo = tipo;
    }

}
