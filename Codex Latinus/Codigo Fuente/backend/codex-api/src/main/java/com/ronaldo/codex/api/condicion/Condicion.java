package com.ronaldo.codex.api.condicion;

import com.ronaldo.codex.api.enums.TipoCondicion;
import com.ronaldo.codex.api.nodo.Nodo;

/**
 *
 * @author ronaldo
 */
public class Condicion extends Nodo {

    private Object valorUno;
    private Object valorDos;
    private TipoCondicion tipo;

    public Condicion(Object valorUno, Object valorDos, TipoCondicion tipo, int fila, int columna) {
        super(fila, columna);
        this.valorUno = valorUno;
        this.valorDos = valorDos;
        this.tipo = tipo;
    }

    public Condicion(Object valorUno, TipoCondicion tipo, int fila, int columna) {
        this(valorUno, null, tipo, fila, columna);
    }

    public Object getValorUno() {
        return valorUno;
    }

    public void setValorUno(Object valorUno) {
        this.valorUno = valorUno;
    }

    public Object getValorDos() {
        return valorDos;
    }

    public void setValorDos(Object valorDos) {
        this.valorDos = valorDos;
    }

    public TipoCondicion getTipo() {
        return tipo;
    }

    public void setTipo(TipoCondicion tipo) {
        this.tipo = tipo;
    }

}
