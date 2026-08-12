package com.ronaldo.codex.api.condicion;

import com.ronaldo.codex.api.enums.TipoCondicion;
import com.ronaldo.codex.api.nodo.Nodo;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ronaldo
 */
@Getter
@Setter
public class Condicion extends Nodo{
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
}
