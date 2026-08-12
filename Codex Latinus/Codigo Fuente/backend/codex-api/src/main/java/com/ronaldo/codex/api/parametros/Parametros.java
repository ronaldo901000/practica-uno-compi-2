package com.ronaldo.codex.api.parametros;

import com.ronaldo.codex.api.expresion.Expresion;
import java.util.List;

/**
 *
 * @author ronaldo
 */
public class Parametros extends Expresion {

    private List<Expresion> parametros;

    public Parametros(int fila, int columna) {
        super(fila, columna);
    }

    public void agregarParametro(Expresion expresion) {
        this.parametros.add(expresion);
    }
}
