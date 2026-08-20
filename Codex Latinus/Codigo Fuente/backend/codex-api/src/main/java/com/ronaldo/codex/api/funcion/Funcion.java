package com.ronaldo.codex.api.funcion;

import com.ronaldo.codex.api.declaracion.Declaracion;
import com.ronaldo.codex.api.instruccion.Instruccion;
import com.ronaldo.codex.api.nodo.Nodo;
import com.ronaldo.codex.api.parametros.creacion.ParametroCreacion;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ronaldo
 */
public abstract class Funcion extends Nodo {

    protected String id;
    protected List<ParametroCreacion> parametros;
    protected List<Declaracion> variables;
    protected List<Instruccion> instrucciones;

    public Funcion(int fila, int columna, String id) {
        super(fila, columna);
        this.id = id;
        this.parametros = new ArrayList<>();
        this.variables = new ArrayList<>();
        this.instrucciones = new ArrayList<>();
    }

    public void traducirParametros(StringBuffer sb) {
        if (parametros.isEmpty()) {
            return;
        }

        for (int i = 0; i < parametros.size(); i++) {
            ParametroCreacion param = parametros.get(i);
            param.realizarTraduccion(sb);

            if (i < parametros.size() - 1) {
                sb.append(", ");
            }
        }
    }

    public void traducirSeccionVariables(StringBuffer sb) {
        if (variables.isEmpty()) {
            return;
        }

        sb.append("ARIABILESVay [\n");
        for (Declaracion dec : variables) {
            sb.append("\t");
            dec.realizarTraduccion(sb);
            sb.append("\n");

        }
        sb.append("] \n");
    }

    protected void traducirInstruccionesInternas(StringBuffer sb) {
        if (!this.instrucciones.isEmpty()) {

            for (Instruccion inst : this.instrucciones) {
                sb.append("\t");
                inst.realizarTraduccion(sb);
                sb.append("\n");
            }

        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ParametroCreacion> getParametros() {
        return parametros;
    }

    public void setParametros(List<ParametroCreacion> parametros) {
        this.parametros = parametros;
    }

    public List<Declaracion> getVariables() {
        return variables;
    }

    public void setVariables(List<Declaracion> variables) {
        this.variables = variables;
    }

    public List<Instruccion> getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(List<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
    }

}
