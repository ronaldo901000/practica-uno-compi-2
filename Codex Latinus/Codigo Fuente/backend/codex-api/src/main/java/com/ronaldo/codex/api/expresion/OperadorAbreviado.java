package com.ronaldo.codex.api.expresion;

import com.ronaldo.codex.api.enums.TipoOperadorAbreviado;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ronaldo
 */
@Getter
@Setter
public class OperadorAbreviado extends Expresion {

    private TipoOperadorAbreviado tipo;
    private String id;

    public OperadorAbreviado(TipoOperadorAbreviado tipo, String id, int fila, int columna) {
        super(fila, columna);
        this.tipo = tipo;
        this.id = id;
    }

}
