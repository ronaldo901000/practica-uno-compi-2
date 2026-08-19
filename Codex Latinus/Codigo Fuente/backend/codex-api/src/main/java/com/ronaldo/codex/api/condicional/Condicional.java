package com.ronaldo.codex.api.condicional;

import com.ronaldo.codex.api.condicion.Condicion;
import com.ronaldo.codex.api.instruccion.Instruccion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ronaldo
 */
public abstract class Condicional extends Instruccion {

    protected Condicion condicion;
    protected List<Instruccion> instruccionesInternas;

    public Condicional(int fila, int columna) {
        super(fila, columna);
        this.instruccionesInternas = new ArrayList<>();
    }

    public void agregarInstruccion(Instruccion instruccion) {
        this.instruccionesInternas.add(instruccion);
    }

    public Condicion getCondicion() {
        return condicion;
    }

    public void setCondicion(Condicion condicion) {
        this.condicion = condicion;
    }

    public List<Instruccion> getInstruccionesInternas() {
        return instruccionesInternas;
    }

    public void setInstruccionesInternas(List<Instruccion> instruccionesInternas) {
        this.instruccionesInternas = instruccionesInternas;
    }

}
