package com.ronaldo.codex.api.expresion;

import com.ronaldo.codex.api.dto.entrada.error.analisis.ErrorSemantico;
import com.ronaldo.codex.api.enums.Tipo;
import com.ronaldo.codex.api.semantica.Semantica;
import com.ronaldo.codex.api.semantica.Simbolo;

/**
 *
 * @author ronaldo
 */
public class AccesoVariable extends Expresion {
    
    private String id;
    
    public AccesoVariable(int fila, int columna, String id) {
        super(fila, columna);
        this.id = id;
    }
    
    @Override
    public void realizarTraduccion(StringBuffer sb) {
        sb.append(traductor.traducir(id));
    }
    
    @Override
    public void verificarSemantica(Semantica semantica) throws Exception {
        String ambito = semantica.getAmbitoActual();
        Simbolo variable = semantica.getTablaSimbolos().buscar(id, ambito);
        
        if (variable == null) {
            semantica.getErrores().add(new ErrorSemantico(
                    fila,
                    columna,
                    id,
                    "La variable '" + id + "' no existe."
            ));
            this.tipoResultado = Tipo.ERROR;
        } else {
            this.tipoResultado = Tipo.values()[variable.getIdTipo()];
        }
    }
    
}
