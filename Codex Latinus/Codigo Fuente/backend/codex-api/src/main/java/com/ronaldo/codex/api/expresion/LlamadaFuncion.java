package com.ronaldo.codex.api.expresion;

import com.ronaldo.codex.api.parametros.Parametros;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ronaldo
 */
@Getter
@Setter
public class LlamadaFuncion extends Expresion{
    private String id;
    private Parametros parametros;

    public LlamadaFuncion(String id, Parametros parametros, int fila, int columna) {
        super(fila, columna);
        this.id = id;
        this.parametros = parametros;
    }
    
}
