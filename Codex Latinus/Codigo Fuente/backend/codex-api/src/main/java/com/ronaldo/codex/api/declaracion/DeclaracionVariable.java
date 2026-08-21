package com.ronaldo.codex.api.declaracion;

import com.ronaldo.codex.api.dto.entrada.error.analisis.ErrorSemantico;
import com.ronaldo.codex.api.enums.Categoria;
import com.ronaldo.codex.api.enums.Tipo;
import com.ronaldo.codex.api.expresion.Expresion;
import com.ronaldo.codex.api.semantica.Llave;
import com.ronaldo.codex.api.semantica.Semantica;
import com.ronaldo.codex.api.semantica.Simbolo;

/**
 *
 * @author ronaldo
 */
public class DeclaracionVariable extends Declaracion {

    private Expresion valor;

    public DeclaracionVariable(Expresion valor, String id, String tipoString, int fila, int columna) {
        super(id, tipoString, fila, columna);
        this.valor = valor;
    }

    @Override
    public void realizarTraduccion(StringBuffer sb) {
        sb.append("estoway ");
        sb.append(traductor.traducir(id));
        sb.append(" : ");
        sb.append(tipo.getText());
        sb.append(" ");
        valor.realizarTraduccion(sb);
        sb.append(";");
    }

    @Override
    public void verificarSemantica(Semantica semantica) throws Exception {

        String ambitoActual = semantica.getAmbitoActual();

        //Validar si la variable ya fue declarada en el ambito actual
        if (semantica.getTablaSimbolos().existeEnAmbitoActual(
                this.id, ambitoActual)) {

            semantica.getErrores().add(new ErrorSemantico(
                    fila,
                    columna,
                    id,
                    "La variable '" + this.id
                    + "' ya existe en el ambito: " + ambitoActual
            ));
        }

        //Insercion del simbolo a la tabla de simbolos
        Simbolo nuevoSimbolo = new Simbolo();
        nuevoSimbolo.setLlave(new Llave(this.id, ambitoActual));
        nuevoSimbolo.setCategoria(Categoria.VAR);
        nuevoSimbolo.setIdTipo(this.tipo.ordinal());
        semantica.getTablaSimbolos().insertar(nuevoSimbolo);

        if (this.valor != null) {

            this.valor.verificarSemantica(semantica);
            Tipo tipoValor = this.valor.getTipoResultado();

            if (tipoValor != Tipo.ERROR) {

                if (!esAsignable(this.tipo, tipoValor)) {
                    semantica.getErrores().add(new ErrorSemantico(
                            fila,
                            columna,
                            id,
                            "No se puede asignar " + tipoValor
                            + " a una variable de tipo " + this.tipo
                    ));
                }
            }
        }
    }

    private boolean esAsignable(Tipo destino, Tipo origen) {
        if (destino == origen) {
            return true;
        }

        if (destino == Tipo.TEXTUM) {
            return true;
        }

        if (destino == Tipo.DECIMALIS && origen == Tipo.NUMERUS) {
            return true;
        }

        return false;
    }

    public Expresion getValor() {
        return valor;
    }

    public void setValor(Expresion valor) {
        this.valor = valor;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Tipo getTipo() {
        return tipo;
    }

    @Override
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

}
