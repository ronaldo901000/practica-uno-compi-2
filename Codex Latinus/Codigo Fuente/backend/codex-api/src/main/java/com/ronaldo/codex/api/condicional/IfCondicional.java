package com.ronaldo.codex.api.condicional;

import com.ronaldo.codex.api.dto.entrada.error.analisis.ErrorSemantico;
import com.ronaldo.codex.api.enums.Tipo;
import com.ronaldo.codex.api.instruccion.Instruccion;
import com.ronaldo.codex.api.semantica.Semantica;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ronaldo
 */
public class IfCondicional extends Condicional {

    private List<Condicional> bifurcaciones;

    public IfCondicional(int fila, int columna) {
        super(fila, columna);
        this.bifurcaciones = new ArrayList<>();
    }

    @Override
    public void realizarTraduccion(StringBuffer sb) {
        sb.append("isay(");
        condicion.realizarTraduccion(sb);
        sb.append("){\n");
        for (Instruccion inst : instruccionesInternas) {
            if (inst != null) {
                inst.realizarTraduccion(sb);
            }

        }
        sb.append("}\n");
        for (Condicional c : bifurcaciones) {
            c.realizarTraduccion(sb);
        }
        sb.append("\n");
    }

    @Override
    public void verificarSemantica(Semantica semantica) throws Exception {

        if (this.condicion != null) {
            this.condicion.verificarSemantica(semantica);

            Tipo tipoCondicion = this.condicion.getTipoResultado();

            if (tipoCondicion != Tipo.ERROR && tipoCondicion != Tipo.BOOL) {
                semantica.getErrores().add(new ErrorSemantico(
                        getFila(),
                        getColumna(),
                        "if",
                        "La condicion de la sentencia 'si' debe ser de tipo "
                        + "BOOLEANO, pero se obtuvo " + tipoCondicion
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

        if (this.bifurcaciones != null) {
            for (Condicional bifurcacion : this.bifurcaciones) {
                if (bifurcacion != null) {
                    bifurcacion.verificarSemantica(semantica);
                }
            }
        }
    }

    public void agregarBifurcacion(Condicional condicional) {
        this.bifurcaciones.add(condicional);
    }

    public List<Condicional> getBifurcaciones() {
        return bifurcaciones;
    }

}
