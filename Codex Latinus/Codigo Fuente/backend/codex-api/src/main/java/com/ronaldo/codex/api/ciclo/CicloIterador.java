package com.ronaldo.codex.api.ciclo;

import com.ronaldo.codex.api.condicion.Condicion;
import com.ronaldo.codex.api.expresion.Expresion;
import com.ronaldo.codex.api.instruccion.Instruccion;
import java.util.List;

/**
 *
 * @author ronaldo
 */
public class CicloIterador extends Ciclo {

    private Expresion expresionInterador;

    public CicloIterador(Expresion expresionInterador, Condicion condicion, int fila, int columna) {
        super(condicion, fila, columna);
        this.expresionInterador = expresionInterador;
    }

    public Expresion getExpresionInterador() {
        return expresionInterador;
    }

    public void setExpresionInterador(Expresion expresionInterador) {
        this.expresionInterador = expresionInterador;
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
