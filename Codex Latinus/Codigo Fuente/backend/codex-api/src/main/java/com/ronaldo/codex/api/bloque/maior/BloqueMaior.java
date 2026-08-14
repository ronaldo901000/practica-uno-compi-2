package com.ronaldo.codex.api.bloque.maior;

import com.ronaldo.codex.api.instruccion.Instruccion;
import com.ronaldo.codex.api.nodo.Nodo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ronaldo
 */
public class BloqueMaior extends Nodo {

    private List<Instruccion> instrucciones;

    public BloqueMaior(int fila, int columna) {
        super(fila, columna);
        this.instrucciones = new ArrayList<>();
    }

    public void insertarInstruccion(Instruccion instruccion) {
        this.instrucciones.add(instruccion);
    }

    public List<Instruccion> getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(List<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
    }

}
