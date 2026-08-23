package com.ronaldo.codex.api.asignacion;

import com.ronaldo.codex.api.dto.entrada.error.analisis.ErrorSemantico;
import com.ronaldo.codex.api.enums.Tipo;
import com.ronaldo.codex.api.expresion.Expresion;
import com.ronaldo.codex.api.instruccion.Instruccion;
import com.ronaldo.codex.api.instruccion.struct.LlamadaAtributoStruct;
import com.ronaldo.codex.api.semantica.Semantica;

/**
 *
 * @author ronaldo
 */
public class AsignacionAtributoStruct extends Instruccion {

    private LlamadaAtributoStruct llamada;
    private Expresion expresion;

    public AsignacionAtributoStruct(LlamadaAtributoStruct llamada, Expresion expresion, int fila, int columna) {
        super(fila, columna);
        this.llamada = llamada;
        this.expresion = expresion;
    }

    @Override
    public void verificarSemantica(Semantica semantica) throws Exception {
        if (this.llamada != null) {
            this.llamada.verificarSemantica(semantica);
        } else {
            return;
        }

        if (this.expresion != null) {
            this.expresion.verificarSemantica(semantica);
        } else {
            return;
        }

        Tipo tipoAtributo = this.llamada.getAcceso() != null
                ? this.llamada.getAcceso().getTipoResultado()
                : Tipo.ERROR;

        Tipo tipoExpresion = this.expresion.getTipoResultado();

        if (tipoAtributo == Tipo.ERROR || tipoExpresion == Tipo.ERROR) {
            return;
        }

        if (tipoAtributo != tipoExpresion) {
            String idInstancia = this.llamada.getAcceso() != null
                    ? this.llamada.getAcceso().getIdInstancia()
                    : "struct";

            semantica.getErrores().add(new ErrorSemantico(
                    fila,
                    columna,
                    idInstancia,
                    "Incompatibilidad de tipos en la asignación del atributo:"
                    + " No se puede asignar un valor de tipo '"
                    + tipoExpresion + "' a un atributo de tipo '" + tipoAtributo
            ));
        }
    }

    @Override
    public void realizarTraduccion(StringBuffer sb) {
        if (llamada != null) {
            llamada.realizarTraduccion(sb);
        }
        sb.append(" = ");
        if (expresion != null) {
            expresion.realizarTraduccion(sb);
        }
        sb.append(";");
    }

    public LlamadaAtributoStruct getLlamada() {
        return llamada;
    }

    public void setLlamada(LlamadaAtributoStruct llamada) {
        this.llamada = llamada;
    }

    public Expresion getExpresion() {
        return expresion;
    }

    public void setExpresion(Expresion expresion) {
        this.expresion = expresion;
    }
}
