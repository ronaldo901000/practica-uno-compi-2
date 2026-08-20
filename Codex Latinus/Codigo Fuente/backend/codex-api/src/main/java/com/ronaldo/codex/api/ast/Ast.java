package com.ronaldo.codex.api.ast;

import com.ronaldo.codex.api.bloque.maior.BloqueMaior;
import com.ronaldo.codex.api.bloque.munera.BloqueMunera;
import com.ronaldo.codex.api.bloque.variables.BloqueVariables;
import com.ronaldo.codex.api.nodo.Nodo;
import com.ronaldo.codex.api.semantica.Semantica;

/**
 *
 * @author ronaldo
 */
public class Ast extends Nodo {

    private BloqueVariables bloqueVariables;
    private BloqueMaior bloqueMaior;
    private BloqueMunera bloqueMunera;

    public Ast(
            BloqueVariables bloqueVariables, BloqueMunera bloqueMunera,
            BloqueMaior bloqueMaior, int fila, int columna) {
        super(fila, columna);
        this.bloqueVariables = bloqueVariables;
        this.bloqueMaior = bloqueMaior;
        this.bloqueMunera = bloqueMunera;
    }

    @Override
    public void verificarSemantica(Semantica semantica) throws Exception {
        if (this.bloqueVariables != null) {
            this.bloqueVariables.verificarSemantica(semantica);
        }
        if (this.bloqueMunera != null) {
            this.bloqueMunera.verificarSemantica(semantica);
        }
        this.bloqueMaior.verificarSemantica(semantica);

    }

    @Override
    public void realizarTraduccion(StringBuffer sb) {
        if (this.bloqueVariables != null) {
            this.bloqueVariables.realizarTraduccion(sb);
        }
        if (this.bloqueMunera != null) {
            this.bloqueMunera.realizarTraduccion(sb);
        }
        this.bloqueMaior.realizarTraduccion(sb);
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

    public BloqueMunera getBloqueMunera() {
        return bloqueMunera;
    }

    public void setBloqueMunera(BloqueMunera bloqueMunera) {
        this.bloqueMunera = bloqueMunera;
    }

}
