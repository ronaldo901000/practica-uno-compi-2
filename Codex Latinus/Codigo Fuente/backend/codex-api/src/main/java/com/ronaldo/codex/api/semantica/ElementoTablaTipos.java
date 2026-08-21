package com.ronaldo.codex.api.semantica;

import java.util.List;

/**
 *
 * @author ronaldo
 */
public class ElementoTablaTipos {

    private int id;
    private String nombre;
    private String ambito;
    private List<DeclaracionAtributoStructura> atributos;

    public ElementoTablaTipos(int id, String nombre,
            String ambito) {

        this.id = id;
        this.nombre = nombre;
        this.ambito = ambito;
    }

    public DeclaracionAtributoStructura buscarAtributo(String nombreAtributo) {
        if (this.atributos == null) {
            return null;
        }

        for (DeclaracionAtributoStructura atr : this.atributos) {
            if (atr.getId().equals(nombreAtributo)) {
                return atr;
            }
        }
        return null;
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

    public List<DeclaracionAtributoStructura> getAtributos() {
        return atributos;
    }

    public void setAtributos(List<DeclaracionAtributoStructura> atributos) {
        this.atributos = atributos;
    }

}
