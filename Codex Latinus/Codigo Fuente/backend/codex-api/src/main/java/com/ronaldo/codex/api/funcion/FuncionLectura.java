package com.ronaldo.codex.api.funcion;

import com.ronaldo.codex.api.semantica.Semantica;

/**
 *
 * @author ronaldo
 */
public class FuncionLectura extends FuncionEspecial {

    public FuncionLectura(int fila, int columna) {
        super(fila, columna);
    }

    @Override
    public void verificarSemantica(Semantica semantica) throws Exception {

    }

    @Override
    public void realizarTraduccion(StringBuffer sb) {

        sb.append("%OINK_OINK");
    }

    @Override
    public void generarDot(StringBuffer sb) {
        sb.append("  nodo").append(idNodo)
                .append(" [label=\"Funcion Lectura\\n(%OINK_OINK)\", fillcolor=\"white\"];\n");
    }

}
