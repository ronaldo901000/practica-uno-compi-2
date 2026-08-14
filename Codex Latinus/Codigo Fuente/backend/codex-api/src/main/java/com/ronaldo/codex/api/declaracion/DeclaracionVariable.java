package com.ronaldo.codex.api.declaracion;

import com.ronaldo.codex.api.enums.Tipo;
import com.ronaldo.codex.api.expresion.Expresion;

/**
 *
 * @author ronaldo
 */
public class DeclaracionVariable extends Declaracion {

    private Expresion valor;

    public DeclaracionVariable(Expresion valor, String id, String tipoString, int fila, int columna) {
        super(id, tipoString, fila, columna);
        this.valor = valor;
    }

    public Expresion getValor() {
        return valor;
    }

    public void setValor(Expresion valor) {
        this.valor = valor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

}
