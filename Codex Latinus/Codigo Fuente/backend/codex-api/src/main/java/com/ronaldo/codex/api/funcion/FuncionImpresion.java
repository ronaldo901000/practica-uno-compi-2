package com.ronaldo.codex.api.funcion;

import com.ronaldo.codex.api.expresion.Expresion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ronaldo
 */
public class FuncionImpresion extends FuncionEspecial {

    private List<Expresion> expresiones;

    public FuncionImpresion(int fila, int columna) {
        super(fila, columna);
        this.expresiones = new ArrayList<>();
    }

    public void insertarCadena(Expresion expresion) {
        this.expresiones.add(expresion);
    }

}
