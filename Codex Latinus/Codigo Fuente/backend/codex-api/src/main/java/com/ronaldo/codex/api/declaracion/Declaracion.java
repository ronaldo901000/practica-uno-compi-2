package com.ronaldo.codex.api.declaracion;

import com.ronaldo.codex.api.enums.Tipo;
import com.ronaldo.codex.api.nodo.Nodo;
import com.ronaldo.codex.api.services.verificacion.VerificadorTipos;

/**
 *
 * @author ronaldo
 */
public abstract class Declaracion extends Nodo {
    
    protected String id;
    protected Tipo tipo;

    public Declaracion(String id, String tipoString, int fila, int columna) {
        super(fila, columna);
        this.id = id;

        VerificadorTipos verificador = new VerificadorTipos();
        this.tipo = verificador.verificar(tipoString);
    }

    public Declaracion(int fila, int columna) {
        super(fila, columna);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

}
