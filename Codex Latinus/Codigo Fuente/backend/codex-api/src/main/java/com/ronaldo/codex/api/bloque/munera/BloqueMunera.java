package com.ronaldo.codex.api.bloque.munera;

import com.ronaldo.codex.api.funcion.Funcion;
import com.ronaldo.codex.api.nodo.Nodo;
import com.ronaldo.codex.api.semantica.Semantica;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ronaldo
 */
public class BloqueMunera extends Nodo {

    private List<Funcion> funciones;

    public BloqueMunera(int fila, int columna) {
        super(fila, columna);
        this.funciones = new ArrayList<>();
    }

    public List<Funcion> getFunciones() {
        return funciones;
    }

    public void setFunciones(List<Funcion> funciones) {
        this.funciones = funciones;
    }

    @Override
    public void realizarTraduccion(StringBuffer sb) {
        if (funciones.isEmpty()) {
            return;
        }

        sb.append(traductor.traducir("MUNERA>")).append("\n");

        for (Funcion fun : funciones) {
            fun.realizarTraduccion(sb);
            sb.append("\n");
        }

        sb.append("\n");
    }

    @Override
    public void verificarSemantica(Semantica semantica) throws Exception {
        for (Funcion fun : funciones) {
            if (fun != null) {
                fun.verificarSemantica(semantica);
            }
        }
    }

}
