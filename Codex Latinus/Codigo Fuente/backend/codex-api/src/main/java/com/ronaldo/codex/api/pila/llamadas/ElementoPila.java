package com.ronaldo.codex.api.pila.llamadas;

/**
 *
 * @author ronaldo
 */
public class ElementoPila {

    private String simbolo;
    private String tipo;

    public ElementoPila(String simbolo, String tipo) {
        this.simbolo = simbolo;
        this.tipo = tipo;
    }

 
    
    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
