package com.ronaldo.codex.api.condicional;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ronaldo
 */
public class IfCondicional extends Condicional {

    private List<Condicional> bifurcaciones;

    public IfCondicional(int fila, int columna) {
        super(fila, columna);
        this.bifurcaciones = new ArrayList<>();
    }

    public void agregarBifurcacion(Condicional condicional) {
        this.bifurcaciones.add(condicional);
    }

}
