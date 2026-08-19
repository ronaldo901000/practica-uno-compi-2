package com.ronaldo.codex.api.parametros.llamada;

import com.ronaldo.codex.api.enums.Tipo;
import com.ronaldo.codex.api.expresion.Expresion;
import com.ronaldo.codex.api.semantica.Semantica;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ronaldo
 */
public class ParametrosLlamada extends Expresion {

    private List<Expresion> parametros;

    public ParametrosLlamada(int fila, int columna) {
        super(fila, columna);
        this.parametros = new ArrayList<>();
    }

    @Override
    public void verificarSemantica(Semantica semantica) throws Exception {

        if (this.parametros != null) {
            for (Expresion parametro : this.parametros) {
                if (parametro != null) {
                    parametro.verificarSemantica(semantica);

                    if (parametro.getTipoResultado() == Tipo.ERROR) {
                        this.tipoResultado = Tipo.ERROR;
                    }
                }
            }
        }
    }

    public void agregarParametro(Expresion expresion) {
        this.parametros.add(expresion);
    }

    public List<Expresion> getParametros() {
        return parametros;
    }

    public void setParametros(List<Expresion> parametros) {
        this.parametros = parametros;
    }

}
