package com.ronaldo.codex.api.inicio;

import com.ronaldo.codex.api.bloque.maior.BloqueMaior;
import com.ronaldo.codex.api.bloque.variables.BloqueVariables;
import com.ronaldo.codex.api.nodo.Nodo;

/**
 *
 * @author ronaldo
 */
public class Inicio extends Nodo {

    private BloqueVariables bloqueVariables;
    private BloqueMaior bloqueMaior;

    public Inicio(BloqueVariables bloqueVariables, BloqueMaior bloqueMaior, int fila, int columna) {
        super(fila, columna);
        this.bloqueVariables = bloqueVariables;
        this.bloqueMaior = bloqueMaior;
    }

    public BloqueVariables getBloqueVariables() {
        return bloqueVariables;
    }

    public void setBloqueVariables(BloqueVariables bloqueVariables) {
        this.bloqueVariables = bloqueVariables;
    }

    public BloqueMaior getBloqueMaior() {
        return bloqueMaior;
    }

    public void setBloqueMaior(BloqueMaior bloqueMaior) {
        this.bloqueMaior = bloqueMaior;
    }

}
