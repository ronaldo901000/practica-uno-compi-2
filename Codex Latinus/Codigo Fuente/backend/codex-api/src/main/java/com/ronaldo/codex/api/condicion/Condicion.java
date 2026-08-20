package com.ronaldo.codex.api.condicion;

import com.ronaldo.codex.api.dto.entrada.error.analisis.ErrorSemantico;
import com.ronaldo.codex.api.enums.Tipo;
import com.ronaldo.codex.api.enums.TipoCondicion;
import com.ronaldo.codex.api.expresion.Expresion;
import com.ronaldo.codex.api.nodo.Nodo;
import com.ronaldo.codex.api.semantica.Semantica;

/**
 *
 * @author ronaldo
 */
public class Condicion extends Nodo {

    private Expresion valorUno;
    private Expresion valorDos;
    private TipoCondicion tipo;
    private Tipo tipoResultado;

    public Condicion(Expresion valorUno, Expresion valorDos, TipoCondicion tipo, int fila, int columna) {
        super(fila, columna);
        this.valorUno = valorUno;
        this.valorDos = valorDos;
        this.tipo = tipo;
    }

    public Condicion(Expresion valorUno, TipoCondicion tipo, int fila, int columna) {
        this(valorUno, null, tipo, fila, columna);
    }

    @Override
    public void realizarTraduccion(StringBuffer sb) {
        valorDos.realizarTraduccion(sb);
        sb.append(" ").append(tipo.getOperador()).append(" ");
        if (valorDos != null) {
            valorDos.realizarTraduccion(sb);
        }

    }

    @Override
    public void verificarSemantica(Semantica semantica) throws Exception {
        Tipo tipoUno = Tipo.ERROR;
        Tipo tipoDos = Tipo.ERROR;

        this.valorUno.verificarSemantica(semantica);
        tipoUno = valorUno.getTipoResultado();

        if (tipoUno == Tipo.ERROR) {
            this.tipoResultado = Tipo.ERROR;
            return;
        }

        if (this.tipo == TipoCondicion.NOT || this.valorDos == null) {
            if (tipoUno != Tipo.BOOLEANO) {
                this.tipoResultado = Tipo.ERROR;
                semantica.getErrores().add(new ErrorSemantico(
                        getFila(),
                        getColumna(),
                        "!",
                        "El operador de negacion requiere un operando de tipo "
                        + "BOOLEANO, pero se recibio " + tipoUno
                ));
                return;
            }
            this.tipoResultado = Tipo.BOOLEANO;
            return;
        }

        this.valorDos.verificarSemantica(semantica);
        tipoDos = valorDos.getTipoResultado();

        if (tipoDos == Tipo.ERROR) {
            this.tipoResultado = Tipo.ERROR;
            return;
        }

        if (esOperadorLogico(this.tipo)) {

            if (tipoUno != Tipo.BOOLEANO || tipoDos != Tipo.BOOLEANO) {
                this.tipoResultado = Tipo.ERROR;
                semantica.getErrores().add(new ErrorSemantico(
                        getFila(),
                        getColumna(),
                        String.valueOf(this.tipo),
                        "Los operadores lógicos requieren operandos de tipo BOOLEANO. Se recibió: " + tipoUno + " y " + tipoDos + "."
                ));
                return;
            }
        } else {

            if (!sonTiposCompatiblesParaComparacion(tipoUno, tipoDos, this.tipo)) {
                this.tipoResultado = Tipo.ERROR;
                semantica.getErrores().add(new ErrorSemantico(
                        getFila(),
                        getColumna(),
                        String.valueOf(this.tipo),
                        "Tipos incompatibles para la comparación (" + tipoUno + " " + traducirOperador(this.tipo) + " " + tipoDos + ")."
                ));
                return;
            }
        }

        this.tipoResultado = Tipo.BOOLEANO;
    }

    private boolean esOperadorLogico(TipoCondicion tipoOp) {
        return tipoOp == TipoCondicion.AND || tipoOp == TipoCondicion.OR;
    }

    private boolean sonTiposCompatiblesParaComparacion(Tipo t1, Tipo t2, TipoCondicion op) {
        if (t1 == t2) {
            return true;
        }

        boolean t1EsNumero = (t1 == Tipo.NUMERUS || t1 == Tipo.DECIMALIS);
        boolean t2EsNumero = (t2 == Tipo.NUMERUS || t2 == Tipo.DECIMALIS);

        return t1EsNumero && t2EsNumero;
    }

    private String traducirOperador(TipoCondicion op) {
        return op != null ? op.toString() : "operador";
    }

    public Object getValorUno() {
        return valorUno;
    }

    public void setValorUno(Expresion valorUno) {
        this.valorUno = valorUno;
    }

    public Expresion getValorDos() {
        return valorDos;
    }

    public void setValorDos(Expresion valorDos) {
        this.valorDos = valorDos;
    }

    public TipoCondicion getTipo() {
        return tipo;
    }

    public void setTipo(TipoCondicion tipo) {
        this.tipo = tipo;
    }

    public Tipo getTipoResultado() {
        return tipoResultado;
    }

    public void setTipoResultado(Tipo tipoResultado) {
        this.tipoResultado = tipoResultado;
    }

}
