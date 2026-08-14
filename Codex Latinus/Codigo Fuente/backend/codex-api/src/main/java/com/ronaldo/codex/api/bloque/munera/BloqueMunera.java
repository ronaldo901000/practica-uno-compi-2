package com.ronaldo.codex.api.bloque.munera;

import com.ronaldo.codex.api.funcion.Funcion;
import com.ronaldo.codex.api.nodo.Nodo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ronaldo
 */
public class BloqueMunera extends Nodo {

    private List<Funcion> funciones;

    public BloqueMunera(int fila, int columna) {
        super(fila, columna);
        this.funciones = new ArrayList<>();
    }

    public List<Funcion> getFunciones() {
        return funciones;
    }

    public void setFunciones(List<Funcion> funciones) {
        this.funciones = funciones;
    }

}
