package com.ronaldo.codex.api.pila;

import com.ronaldo.codex.api.exceptions.PilaException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ronaldo
 */
public class Pila<P> {

    private List<P> contenedor;

    public Pila() {
        this.contenedor = new ArrayList<>();
    }

    public void apilar(P elemento) {
        this.contenedor.add(elemento);
    }

    public P desapilar() throws PilaException {
        if (estaVacia()) {
            throw new PilaException("La pila esta vacia.");
        }
        return this.contenedor.remove(this.contenedor.size() - 1);
    }

    public P getTope() {
        if (estaVacia()) {
            return null;
        }
        return this.contenedor.get(this.contenedor.size() - 1);
    }

    public boolean estaVacia() {
        return this.contenedor.isEmpty();
    }

    public int getElementos() {
        return this.contenedor.size();
    }
}
