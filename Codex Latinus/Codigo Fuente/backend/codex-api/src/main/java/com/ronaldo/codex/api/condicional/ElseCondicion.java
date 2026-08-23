package com.ronaldo.codex.api.condicional;

import com.ronaldo.codex.api.instruccion.Instruccion;
import com.ronaldo.codex.api.semantica.Semantica;

/**
 *
 * @author ronaldo
 */
public class ElseCondicion extends Condicional {

    public ElseCondicion(int fila, int columna) {
        super(fila, columna);
    }

    @Override
    public void realizarTraduccion(StringBuffer sb) {
        sb.append("aliterway");
        sb.append("{\n");
        for (Instruccion inst : instruccionesInternas) {
            inst.realizarTraduccion(sb);
        }
        sb.append("}\n");
    }

    @Override
    public void verificarSemantica(Semantica semantica) throws Exception {

        if (this.instruccionesInternas != null) {
            for (Instruccion instruccion : this.instruccionesInternas) {
                if (instruccion != null) {
                    instruccion.verificarSemantica(semantica);
                }
            }
        }

    }

    @Override
    public void generarDot(StringBuffer sb) {

        sb.append("  nodo").append(idNodo)
                .append(" [label=\"Bloque Else\", fillcolor=\"white\"];\n");

        if (this.instruccionesInternas != null) {
            for (Instruccion inst : this.instruccionesInternas) {
                if (inst != null) {
                    inst.generarDot(sb);
                    sb.append("  nodo").append(idNodo)
                            .append(" -> nodo").append(inst.getIdNodo())
                            .append(" [label=\"instrucción\"];\n");
                }
            }
        }
    }
}
