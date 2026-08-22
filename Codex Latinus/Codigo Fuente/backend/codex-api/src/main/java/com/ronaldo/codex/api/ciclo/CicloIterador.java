package com.ronaldo.codex.api.ciclo;

import com.ronaldo.codex.api.condicion.Condicion;
import com.ronaldo.codex.api.declaracion.DeclaracionVariable;
import com.ronaldo.codex.api.dto.entrada.error.analisis.ErrorSemantico;
import com.ronaldo.codex.api.enums.Tipo;
import com.ronaldo.codex.api.expresion.Expresion;
import com.ronaldo.codex.api.instruccion.Instruccion;
import com.ronaldo.codex.api.semantica.Semantica;
import java.util.List;

/**
 *
 * @author ronaldo
 */
public class CicloIterador extends Ciclo {

    private Expresion expresionIterador;
    private DeclaracionVariable valor;

    public CicloIterador(DeclaracionVariable valor, Expresion expresionIterador, Condicion condicion, int fila, int columna) {
        super(condicion, fila, columna);
        this.valor = valor;
        this.expresionIterador = expresionIterador;
    }

    @Override
    public void realizarTraduccion(StringBuffer sb) {
        sb.append("erpay (");

        valor.realizarTraduccion(sb);
        sb.append(" ");
        condicion.realizarTraduccion(sb);
        sb.append(" ");
        expresionIterador.realizarTraduccion(sb);
        sb.append(") { \n");

        traducirInstruccionesInternas(sb);
        sb.append("} \n");

    }

    @Override
    public void verificarSemantica(Semantica semantica) throws Exception {

        semantica.entrarAmbito("ciclo-iterador");

        if (valor != null) {
            this.valor.verificarSemantica(semantica);
        }

        if (this.getCondicion() != null) {
            this.getCondicion().verificarSemantica(semantica);

            Tipo tipoCondicion = this.getCondicion().getTipoResultado();

            if (tipoCondicion != Tipo.ERROR && tipoCondicion != Tipo.BOOL) {
                semantica.getErrores().add(new ErrorSemantico(
                        getFila(),
                        getColumna(),
                        "for",
                        "La condicion del ciclo debe ser de tipo BOOLEANO, pero se recibio " + tipoCondicion
                ));
            }
        }

        if (this.expresionIterador != null) {
            this.expresionIterador.verificarSemantica(semantica);
        }

        if (this.instruccionesInternas != null) {
            for (Instruccion instruccion : this.instruccionesInternas) {
                if (instruccion != null) {
                    instruccion.verificarSemantica(semantica);
                }
            }
        }

        semantica.salirAmbito();
    }

    public Expresion getExpresionInterador() {
        return expresionIterador;
    }

    public void setExpresionInterador(Expresion expresionInterador) {
        this.expresionIterador = expresionInterador;
    }

    public Condicion getCondicion() {
        return condicion;
    }

    public void setCondicion(Condicion condicion) {
        this.condicion = condicion;
    }

    public List<Instruccion> getInstruccionesInternas() {
        return instruccionesInternas;
    }

    public void setInstruccionesInternas(List<Instruccion> instruccionesInternas) {
        this.instruccionesInternas = instruccionesInternas;
    }

    public Expresion getExpresionIterador() {
        return expresionIterador;
    }

    public void setExpresionIterador(Expresion expresionIterador) {
        this.expresionIterador = expresionIterador;
    }

    public DeclaracionVariable getValor() {
        return valor;
    }

    public void setValor(DeclaracionVariable valor) {
        this.valor = valor;
    }

}
