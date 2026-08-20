package com.ronaldo.codex.api.funcion;

import com.ronaldo.codex.api.expresion.Expresion;
import com.ronaldo.codex.api.semantica.Semantica;
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

    @Override
    public void realizarTraduccion(StringBuffer sb) {
        if (this.expresiones == null || this.expresiones.isEmpty()) {
            return;
        }

        for (Expresion exp : this.expresiones) {
            if (exp != null) {
                sb.append("%OINK ");
                exp.realizarTraduccion(sb);
                sb.append("\n"); 
            }
        }
    }

    public void insertarCadena(Expresion expresion) {
        this.expresiones.add(expresion);
    }

    @Override
    public void verificarSemantica(Semantica semantica) throws Exception {

        if (expresiones == null) {
            return;
        }

        for (int i = 0; i < expresiones.size(); i++) {
            expresiones.get(i).verificarSemantica(semantica);

        }
    }

}
