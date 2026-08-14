package com.ronaldo.codex.api.structura;

import com.ronaldo.codex.api.nodo.Nodo;
import java.util.List;

/**
 *
 * @author ronaldo
 */
public class AtributosStructura extends Nodo {

    private List<AtributoStructura> atributos;

    public AtributosStructura(int fila, int columna) {
        super(fila, columna);
    }

    public List<AtributoStructura> getAtributos() {
        return atributos;
    }

    public void setAtributos(List<AtributoStructura> atributos) {
        this.atributos = atributos;
    }

}
