package com.ronaldo.codex.api.funcion;

/**
 *
 * @author ronaldo
 */
public class FuncionLecturaGuardado extends FuncionEspecial {

    private String id;

    public FuncionLecturaGuardado(String id, int fila, int columna) {
        super(fila, columna);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
