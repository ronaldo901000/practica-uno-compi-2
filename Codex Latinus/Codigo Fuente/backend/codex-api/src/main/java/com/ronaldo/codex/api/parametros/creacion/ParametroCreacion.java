package com.ronaldo.codex.api.parametros.creacion;

import com.ronaldo.codex.api.enums.Tipo;
import com.ronaldo.codex.api.nodo.Nodo;

/**
 *
 * @author ronaldo
 */
public class ParametroCreacion extends Nodo {

    private String id;
    private Tipo tipo;

    public ParametroCreacion(String id, Tipo tipo, int fila, int columna) {
        super(fila, columna);
        this.id = id;
        this.tipo = tipo;
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
