package com.ronaldo.codex.api.semantica;

import com.ronaldo.codex.api.enums.EstructuraDato;

/**
 *
 * @author ronaldo
 */
public class DeclaracionAtributoStructura {

    private String id;
    private EstructuraDato estructura;
    private String tipo;
    private int idTipo;

    public DeclaracionAtributoStructura(String id, EstructuraDato estructura,
            String tipo, int idTipo) {

        this.id = id;
        this.estructura = estructura;
        this.tipo = tipo;
        this.idTipo = idTipo;

    }

    public boolean esArreglo() {
        return this.estructura == EstructuraDato.SERIES;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EstructuraDato getEstructura() {
        return estructura;
    }

    public void setEstructura(EstructuraDato estructura) {
        this.estructura = estructura;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

}
