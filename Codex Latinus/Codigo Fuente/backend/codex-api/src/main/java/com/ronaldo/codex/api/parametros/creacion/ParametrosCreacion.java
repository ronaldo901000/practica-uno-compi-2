package com.ronaldo.codex.api.parametros.creacion;

import com.ronaldo.codex.api.nodo.Nodo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ronaldo
 */
public class ParametrosCreacion extends Nodo {

    private List<ParametroCreacion> parametros;

    public ParametrosCreacion(int fila, int columna) {
        super(fila, columna);
        this.parametros = new ArrayList<>();
    }

    public void agregarParametro(ParametroCreacion parametro) {
        this.parametros.add(parametro);
    }

    public List<ParametroCreacion> getParametros() {
        return parametros;
    }

    public void setParametros(List<ParametroCreacion> parametros) {
        this.parametros = parametros;
    }

}
