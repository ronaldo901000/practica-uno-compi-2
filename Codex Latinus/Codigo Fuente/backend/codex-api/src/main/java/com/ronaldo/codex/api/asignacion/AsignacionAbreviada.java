package com.ronaldo.codex.api.asignacion;

import com.ronaldo.codex.api.expresion.OperadorAbreviado;
import com.ronaldo.codex.api.instruccion.Instruccion;

/**
 *
 * @author ronaldo
 */
public class AsignacionAbreviada extends Instruccion {

    private OperadorAbreviado operador;

    public AsignacionAbreviada(OperadorAbreviado operador, int fila, int columna) {
        super(fila, columna);
        this.operador = operador;
    }

    public OperadorAbreviado getOperador() {
        return operador;
    }

    public void setOperador(OperadorAbreviado operador) {
        this.operador = operador;
    }

}
