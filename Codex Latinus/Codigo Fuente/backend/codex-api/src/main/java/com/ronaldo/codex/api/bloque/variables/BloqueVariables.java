package com.ronaldo.codex.api.bloque.variables;

import com.ronaldo.codex.api.declaracion.Declaracion;
import com.ronaldo.codex.api.nodo.Nodo;
import com.ronaldo.codex.api.semantica.Semantica;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ronaldo
 */
public class BloqueVariables extends Nodo {
    
    private List<Declaracion> declaraciones;
    
    public BloqueVariables(int fila, int columna) {
        super(fila, columna);
        this.declaraciones = new ArrayList<>();
    }
    
    public List<Declaracion> getDeclaraciones() {
        return declaraciones;
    }
    
    public void setDeclaraciones(List<Declaracion> declaraciones) {
        this.declaraciones = declaraciones;
    }
    
    @Override
    public void verificarSemantica(Semantica semantica) throws Exception {
        for (int i = 0; i < declaraciones.size(); i++) {
            Declaracion dec = declaraciones.get(i);
            if (dec != null) {
                dec.verificarSemantica(semantica);
            }
        }
    }
    
}
