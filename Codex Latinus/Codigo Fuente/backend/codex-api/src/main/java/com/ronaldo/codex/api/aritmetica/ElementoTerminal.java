package com.ronaldo.codex.api.aritmetica;

import com.ronaldo.codex.api.enums.Tipo;
import com.ronaldo.codex.api.expresion.Expresion;

/**
 *
 * @author ronaldo
 */
public class ElementoTerminal extends Expresion {

    private Tipo tipo;
    private Object valor;

    public ElementoTerminal(int fila, int columna, Object valor, Tipo tipo) {
        super(fila, columna);
        this.valor = valor;
        this.tipo = tipo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

}
