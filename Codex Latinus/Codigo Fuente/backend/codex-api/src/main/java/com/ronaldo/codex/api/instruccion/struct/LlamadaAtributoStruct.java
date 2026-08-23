package com.ronaldo.codex.api.instruccion.struct;

import com.ronaldo.codex.api.expresion.AccesoAtributoStruct;
import com.ronaldo.codex.api.instruccion.Instruccion;
import com.ronaldo.codex.api.semantica.Semantica;

/**
 *
 * @author ronaldo
 */
public class LlamadaAtributoStruct extends Instruccion {

    private AccesoAtributoStruct acceso;

    public LlamadaAtributoStruct(AccesoAtributoStruct acceso, int fila, int columna) {
        super(fila, columna);
        this.acceso = acceso;
    }

    @Override
    public void verificarSemantica(Semantica semantica) throws Exception {
        if (acceso != null) {
            acceso.verificarSemantica(semantica);
        }
    }

    @Override
    public void realizarTraduccion(StringBuffer sb) {
        if (acceso != null) {
            acceso.realizarTraduccion(sb);
        }
    }

    public AccesoAtributoStruct getAcceso() {
        return acceso;
    }

    public void setAcceso(AccesoAtributoStruct acceso) {
        this.acceso = acceso;
    }

    @Override
    public void generarDot(StringBuffer sb) {
        sb.append("  nodo").append(idNodo)
                .append(" [label=\"Llamada Atributo Struct\", fillcolor=\"white\"];\n");
        if (this.acceso != null) {
            this.acceso.generarDot(sb);
            sb.append("  nodo").append(idNodo)
                    .append(" -> nodo").append(this.acceso.getIdNodo())
                    .append(";\n");
        }
    }

}
