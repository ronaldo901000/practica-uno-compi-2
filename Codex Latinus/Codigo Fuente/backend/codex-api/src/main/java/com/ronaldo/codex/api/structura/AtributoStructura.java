package com.ronaldo.codex.api.structura;

import com.ronaldo.codex.api.enums.EstructuraDato;
import com.ronaldo.codex.api.nodo.Nodo;
import com.ronaldo.codex.api.services.verificacion.VerificadorTipos;

/**
 *
 * @author ronaldo
 */
public class AtributoStructura extends Nodo {

    private EstructuraDato estructura;
    private String id;
    private String tipoDato;

    public AtributoStructura(String id, String tipoDato, int fila, int columna, String estructuraCadena) {
        super(fila, columna);
        this.id = id;
        this.tipoDato = tipoDato;

        VerificadorTipos vt = new VerificadorTipos();
        this.estructura = vt.verificarEstructuraDato(id);
    }

    public EstructuraDato getEstructura() {
        return estructura;
    }

    public void setEstructura(EstructuraDato estructura) {
        this.estructura = estructura;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(String tipoDato) {
        this.tipoDato = tipoDato;
    }

}
