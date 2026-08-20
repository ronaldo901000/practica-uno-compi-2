package com.ronaldo.codex.api.ciclo;

import com.ronaldo.codex.api.condicion.Condicion;
import com.ronaldo.codex.api.dto.entrada.error.analisis.ErrorSemantico;
import com.ronaldo.codex.api.enums.Tipo;
import com.ronaldo.codex.api.instruccion.Instruccion;
import com.ronaldo.codex.api.semantica.Semantica;

/**
 *
 * @author ronaldo
 */
public class CicloDoWhile extends Ciclo {

    public CicloDoWhile(Condicion condicion, int fila, int columna) {
        super(condicion, fila, columna);
    }
    
    @Override
    public void realizarTraduccion(StringBuffer sb){
        sb.append("acerefay { \n");
        
        traducirInstruccionesInternas(sb);
        
        sb.append("} umday (");
        condicion.realizarTraduccion(sb);
        sb.append(");");
        
    }
    

    @Override
    public void verificarSemantica(Semantica semantica) throws Exception {

        if (this.getInstruccionesInternas() != null) {
            for (Instruccion inst : instruccionesInternas) {
                if (inst != null) {
                    inst.verificarSemantica(semantica);
                }
            }
        }

        if (condicion != null) {
            this.condicion.verificarSemantica(semantica);
            Tipo t = condicion.getTipoResultado();

            if (t != Tipo.ERROR && t != Tipo.BOOLEANO) {
                semantica.getErrores().add(new ErrorSemantico(
                        getFila(),
                        getColumna(),
                        "dum",
                        "La condicion del ciclo do-while debe ser de tipo"
                        + " BOOLEANO, pero se recibio " + t + "."
                ));
            }

        }

    }

}
