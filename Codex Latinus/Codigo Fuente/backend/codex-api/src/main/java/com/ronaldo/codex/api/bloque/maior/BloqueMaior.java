package com.ronaldo.codex.api.bloque.maior;

import com.ronaldo.codex.api.instruccion.Instruccion;
import com.ronaldo.codex.api.nodo.Nodo;
import com.ronaldo.codex.api.semantica.Semantica;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ronaldo
 */
public class BloqueMaior extends Nodo {

    private List<Instruccion> instrucciones;

    public BloqueMaior(int fila, int columna) {
        super(fila, columna);
        this.instrucciones = new ArrayList<>();
    }

    @Override
    public void realizarTraduccion(StringBuffer sb) {
        if (instrucciones.isEmpty()) {
            return;
        }

        sb.append(traductor.traducir("MAIOR>")).append("\n");

        for (Instruccion inst : instrucciones) {
            if (inst != null) {
                inst.realizarTraduccion(sb);
                sb.append("\n");
            }

        }

    }

    public void insertarInstruccion(Instruccion instruccion) {
        this.instrucciones.add(instruccion);
    }

    public List<Instruccion> getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(List<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
    }

    @Override
    public void verificarSemantica(Semantica semantica) throws Exception {
        for (Instruccion inst : instrucciones) {
            if (inst != null) {
                inst.verificarSemantica(semantica);
            }
        }
    }

    @Override
    public void generarDot(StringBuffer sb) {
        sb.append("  nodo").append(idNodo)
                .append(" [label=\"Bloque Maior (Main)\", fillcolor=\"white\"];\n");

        if (instrucciones != null) {
            for (Instruccion inst : instrucciones) {
                if (inst != null) {
                    inst.generarDot(sb);
                    sb.append("  nodo").append(idNodo)
                            .append(" -> nodo").append(inst.getIdNodo())
                            .append(";\n");
                }
            }
        }
    }

}
