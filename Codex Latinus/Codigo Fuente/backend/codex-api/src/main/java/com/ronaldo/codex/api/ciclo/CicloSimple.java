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
public class CicloSimple extends Ciclo {

    public CicloSimple(Condicion condicion, int fila, int columna) {
        super(condicion, fila, columna);
    }

    @Override
    public void realizarTraduccion(StringBuffer sb) {
        sb.append("umday (");

        condicion.realizarTraduccion(sb);
        sb.append(") { \n");

        traducirInstruccionesInternas(sb);
        
        sb.append("} inisfay\n");

    }

    @Override
    public void verificarSemantica(Semantica semantica) throws Exception {

        if (this.getCondicion() != null) {
            this.getCondicion().verificarSemantica(semantica);

            Tipo tipoCondicion = this.getCondicion().getTipoResultado();

            if (tipoCondicion != Tipo.ERROR && tipoCondicion != Tipo.BOOL) {
                semantica.getErrores().add(new ErrorSemantico(
                        getFila(),
                        getColumna(),
                        "while",
                        "La condicion del ciclo dom (while) debe ser de "
                        + "tipo BOOLEANO, pero se obtuvo " + tipoCondicion
                ));
            }
        }

        if (this.instruccionesInternas != null) {
            for (Instruccion instruccion : this.instruccionesInternas) {
                if (instruccion != null) {
                    instruccion.verificarSemantica(semantica);
                }
            }
        }

    }

}
