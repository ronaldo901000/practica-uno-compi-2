package com.ronaldo.codex.api.expresion;

/**
 *
 * @author ronaldo
 */
public class AccesoArray extends Expresion {

    private int posicion;
    private String id;

    public AccesoArray(int posicion, String id, int fila, int columna) {
        super(fila, columna);
        this.posicion = posicion;
        this.id = id;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
