package com.ronaldo.codex.api.asignacion;

import com.ronaldo.codex.api.enums.Tipo;
import com.ronaldo.codex.api.expresion.OperadorAbreviado;
import com.ronaldo.codex.api.instruccion.Instruccion;
import com.ronaldo.codex.api.semantica.Semantica;

/**
 *
 * @author ronaldo
 */
public class AsignacionAbreviada extends Instruccion {

    private OperadorAbreviado operador;

    public AsignacionAbreviada(OperadorAbreviado operador, int fila, int columna) {
        super(fila, columna);
        this.operador = operador;
    }

    @Override
    public void realizarTraduccion(StringBuffer sb) {
        operador.realizarTraduccion(sb);
    }

    @Override
    public void verificarSemantica(Semantica semantica) throws Exception {
        if (this.operador != null) {
            this.operador.verificarSemantica(semantica);

            if (this.operador.getTipoResultado() == Tipo.ERROR) {
                return;
            }
        }
    }

    public OperadorAbreviado getOperador() {
        return operador;
    }

    public void setOperador(OperadorAbreviado operador) {
        this.operador = operador;
    }

    @Override
    public void generarDot(StringBuffer sb) {
        sb.append("  nodo").append(idNodo)
                .append(" [label=\"Asignacion Abreviada\", fillcolor=\"white\"];\n");

        if (this.operador != null) {
            this.operador.generarDot(sb);
            sb.append("  nodo").append(idNodo)
                    .append(" -> nodo").append(this.operador.getIdNodo())
                    .append(";\n");
        }
    }

}
