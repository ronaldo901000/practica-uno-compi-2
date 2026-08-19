package com.ronaldo.codex.api.tabla.simbolos;

/**
 *
 * @author ronaldo
 */
public class Llave {

    private String id;
    private String alcance;

    public Llave(String id, String alcance) {
        this.id = id;
        this.alcance = alcance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlcance() {
        return alcance;
    }

    public void setAlcance(String alcance) {
        this.alcance = alcance;
    }

}
