package com.ronaldo.codex.api.semantica;

import java.util.Objects;

/**
 *
 * @author ronaldo
 */
public class Llave {

    private String id;
    private String ambito;

    public Llave(String id, String alcance) {
        this.id = id;
        this.ambito = alcance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAmbito() {
        return ambito;
    }

    public void setAlcance(String alcance) {
        this.ambito = alcance;
    }

}
