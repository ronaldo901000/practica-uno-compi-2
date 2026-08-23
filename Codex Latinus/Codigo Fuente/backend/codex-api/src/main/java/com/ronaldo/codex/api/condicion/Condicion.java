package com.ronaldo.codex.api.condicion;

import com.ronaldo.codex.api.dto.entrada.error.analisis.ErrorSemantico;
import com.ronaldo.codex.api.enums.Tipo;
import com.ronaldo.codex.api.enums.TipoCondicion;
import com.ronaldo.codex.api.expresion.Expresion;
import com.ronaldo.codex.api.semantica.Semantica;

public class Condicion extends Expresion {

    private Expresion valorUno;
    private Expresion valorDos;
    private TipoCondicion tipo;

    public Condicion(Expresion valorUno, Expresion valorDos,
            TipoCondicion tipo, int fila, int columna) {

        super(fila, columna);
        this.valorUno = valorUno;
        this.valorDos = valorDos;
        this.tipo = tipo;
    }

    public Condicion(Expresion valorUno, TipoCondicion tipo,
            int fila, int columna) {

        this(valorUno, null, tipo, fila, columna);
    }

    @Override
    public void realizarTraduccion(StringBuffer sb) {
        if (tipo == TipoCondicion.NOT) {
            sb.append(tipo.getOperador()).append(" ");
            valorUno.realizarTraduccion(sb);
            return;
        }
        if (valorDos == null) {
            valorUno.realizarTraduccion(sb);
            return;
        }
        valorUno.realizarTraduccion(sb);
        sb.append(" ").append(tipo.getOperador()).append(" ");
        valorDos.realizarTraduccion(sb);
    }

    @Override
    public void verificarSemantica(Semantica semantica) throws Exception {
        Tipo tipoUno = Tipo.ERROR;
        Tipo tipoDos = Tipo.ERROR;

        this.valorUno.verificarSemantica(semantica);
        tipoUno = valorUno.getTipoResultado();

        if (tipoUno == Tipo.ERROR) {
            this.setTipoResultado(Tipo.ERROR);
            return;
        }

        if (this.tipo == TipoCondicion.NOT || this.valorDos == null) {
            if (tipoUno != Tipo.BOOL) {
                this.setTipoResultado(Tipo.ERROR);
                String mensaje = (this.tipo == TipoCondicion.NOT)
                        ? "El operador de negación (non) requiere un operando de tipo boolean, pero se recibió " + tipoUno + "."
                        : "La condición debe ser de tipo boolean, pero se recibió " + tipoUno + ".";
                semantica.getErrores().add(new ErrorSemantico(
                        getFila(),
                        getColumna(),
                        this.tipo == TipoCondicion.NOT ? "non" : "condicion",
                        mensaje
                ));
                return;
            }
            this.setTipoResultado(Tipo.BOOL);
            return;
        }

        this.valorDos.verificarSemantica(semantica);
        tipoDos = valorDos.getTipoResultado();

        if (tipoDos == Tipo.ERROR) {
            this.setTipoResultado(Tipo.ERROR);
            return;
        }

        if (esOperadorLogico(this.tipo)) {
            if (tipoUno != Tipo.BOOL || tipoDos != Tipo.BOOL) {
                this.setTipoResultado(Tipo.ERROR);
                semantica.getErrores().add(new ErrorSemantico(
                        getFila(),
                        getColumna(),
                        String.valueOf(this.tipo),
                        "Los operadores lógicos requieren operandos de tipo boolean. Se recibió: " + tipoUno + " y " + tipoDos + "."
                ));
                return;
            }
        } else {
            if (!sonTiposCompatiblesParaComparacion(tipoUno, tipoDos, this.tipo)) {
                this.setTipoResultado(Tipo.ERROR);
                semantica.getErrores().add(new ErrorSemantico(
                        getFila(),
                        getColumna(),
                        String.valueOf(this.tipo),
                        "Tipos incompatibles para la comparacion (" + tipoUno + " " + traducirOperador(this.tipo) + " " + tipoDos + ")."
                ));
                return;
            }
        }

        this.setTipoResultado(Tipo.BOOL);
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

    public Expresion getValorUno() {
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

    @Override
    public void generarDot(StringBuffer sb) {
        String simboloOp = (this.tipo != null) ? this.tipo.getOperador() : "?";
        String idSimbolo = "nodo" + idNodo + "_op";

        sb.append("  nodo").append(idNodo)
                .append(" [label=\"Condicion\", fillcolor=\"white\"];\n");

        if (this.valorUno != null) {
            this.valorUno.generarDot(sb);
            sb.append("  nodo").append(idNodo)
                    .append(" -> nodo").append(this.valorUno.getIdNodo())
                    .append(" [label=\"izq\"];\n");
        }

        sb.append("  ").append(idSimbolo)
                .append(" [label=\"").append(simboloOp)
                .append("\", fillcolor=\"white\"];\n");
        sb.append("  nodo").append(idNodo)
                .append(" -> ").append(idSimbolo)
                .append(" [label=\"operador\"];\n");

        if (this.valorDos != null) {
            this.valorDos.generarDot(sb);
            sb.append("  nodo").append(idNodo)
                    .append(" -> nodo").append(this.valorDos.getIdNodo())
                    .append(" [label=\"der\"];\n");
        }
    }

}
