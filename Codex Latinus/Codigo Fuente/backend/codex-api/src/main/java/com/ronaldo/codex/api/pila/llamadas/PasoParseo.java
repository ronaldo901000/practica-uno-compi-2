package com.ronaldo.codex.api.pila.llamadas;

import java.util.List;

/**
 *
 * @author ronaldo
 */
public class PasoParseo {
    private int numeroPaso;
    private List<ElementoPila> pila;
    private String accion;
    private String detalleAccion;

    public PasoParseo(int numeroPaso, List<ElementoPila> pila, String accion, String detalleAccion) {
        this.numeroPaso = numeroPaso;
        this.pila = pila;
        this.accion = accion;
        this.detalleAccion = detalleAccion;
    }

    public int getNumeroPaso() {
        return numeroPaso;
    }

    public void setNumeroPaso(int numeroPaso) {
        this.numeroPaso = numeroPaso;
    }

    public List<ElementoPila> getPila() {
        return pila;
    }

    public void setPila(List<ElementoPila> pila) {
        this.pila = pila;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getDetalleAccion() {
        return detalleAccion;
    }

    public void setDetalleAccion(String detalleAccion) {
        this.detalleAccion = detalleAccion;
    }
    
    
    
}
