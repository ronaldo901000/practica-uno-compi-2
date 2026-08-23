package com.ronaldo.codex.api.aritmetica;

import com.ronaldo.codex.api.enums.Tipo;
import com.ronaldo.codex.api.expresion.Expresion;
import com.ronaldo.codex.api.semantica.Semantica;

/**
 *
 * @author ronaldo
 */
public class ElementoTerminal extends Expresion {

    private Tipo tipo;
    private Object valor;

    public ElementoTerminal(int fila, int columna, Object valor, Tipo tipo) {
        super(fila, columna);
        this.valor = valor;
        this.tipo = tipo;
        this.tipoResultado = tipo;
    }

    @Override
    public void realizarTraduccion(StringBuffer sb) {
        sb.append(valor.toString());
    }

    @Override
    public void verificarSemantica(Semantica semantica) throws Exception {
        this.tipoResultado = this.tipo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    @Override
    public void generarDot(StringBuffer sb) {
        String textoValor = this.valor != null ? this.valor.toString() : "null";

        textoValor = textoValor.replace("\"", "\\\"");

        sb.append("  nodo").append(idNodo)
                .append(" [label=\"").append(textoValor)
                .append("\", fillcolor=\"white\"];\n");
    }

}
