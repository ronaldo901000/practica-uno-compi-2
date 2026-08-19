package com.ronaldo.codex.api.semantica;

/**
 *
 * @author ronaldo
 */
public class ElementoTablaTipos {

    private int id;
    private String nombre;
    private String ambito;

    public ElementoTablaTipos(int id, String nombre, String ambito) {
        this.id = id;
        this.nombre = nombre;
        this.ambito = ambito;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

}
