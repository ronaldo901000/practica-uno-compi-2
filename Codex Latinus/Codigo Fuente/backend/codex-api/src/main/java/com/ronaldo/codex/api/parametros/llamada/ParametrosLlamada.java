package com.ronaldo.codex.api.parametros.llamada;

import com.ronaldo.codex.api.expresion.Expresion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ronaldo
 */
public class ParametrosLlamada extends Expresion {

    private List<Expresion> parametros;

    public ParametrosLlamada(int fila, int columna) {
        super(fila, columna);
        this.parametros = new ArrayList<>();
    }

    public void agregarParametro(Expresion expresion) {
        this.parametros.add(expresion);
    }

    public List<Expresion> getParametros() {
        return parametros;
    }

    public void setParametros(List<Expresion> parametros) {
        this.parametros = parametros;
    }

}
