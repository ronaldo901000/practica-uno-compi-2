package com.ronaldo.codex.api.expresion;

import com.ronaldo.codex.api.parametros.llamada.ParametrosLlamada;

/**
 *
 * @author ronaldo
 */
public class LlamadaFuncion extends Expresion {

    private String id;
    private ParametrosLlamada parametros;

    public LlamadaFuncion(String id, ParametrosLlamada parametros, int fila, int columna) {
        super(fila, columna);
        this.id = id;
        this.parametros = parametros;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ParametrosLlamada getParametros() {
        return parametros;
    }

    public void setParametros(ParametrosLlamada parametros) {
        this.parametros = parametros;
    }

}
