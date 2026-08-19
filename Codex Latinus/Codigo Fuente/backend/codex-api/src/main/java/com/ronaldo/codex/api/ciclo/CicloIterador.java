package com.ronaldo.codex.api.ciclo;

import com.ronaldo.codex.api.condicion.Condicion;
import com.ronaldo.codex.api.dto.entrada.error.analisis.ErrorSemantico;
import com.ronaldo.codex.api.enums.Tipo;
import com.ronaldo.codex.api.expresion.Expresion;
import com.ronaldo.codex.api.instruccion.Instruccion;
import com.ronaldo.codex.api.semantica.Semantica;
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

@Override
    public void verificarSemantica(Semantica semantica) throws Exception {

        if (this.getCondicion() != null) {
            this.getCondicion().verificarSemantica(semantica);

            Tipo tipoCondicion = this.getCondicion().getTipoResultado();

            if (tipoCondicion != Tipo.ERROR && tipoCondicion != Tipo.BOOLEANO) {
                semantica.getErrores().add(new ErrorSemantico(
                    getFila(),
                    getColumna(),
                    "for",
                    "La condicion del ciclo debe ser de tipo BOOLEANO, pero se recibio " + tipoCondicion
                ));
            }
        }

        if (this.expresionInterador != null) {
            this.expresionInterador.verificarSemantica(semantica);
        }

        if (this.instruccionesInternas != null) {
            for (Instruccion instruccion : this.instruccionesInternas) {
                if (instruccion != null) {
                    instruccion.verificarSemantica(semantica);
                }
            }
        }

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
