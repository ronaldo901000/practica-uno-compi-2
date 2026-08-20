package com.ronaldo.codex.api.expresion;

import com.ronaldo.codex.api.dto.entrada.error.analisis.ErrorSemantico;
import com.ronaldo.codex.api.enums.Categoria;
import com.ronaldo.codex.api.enums.Tipo;
import com.ronaldo.codex.api.enums.TipoOperadorAbreviado;
import com.ronaldo.codex.api.semantica.Semantica;
import com.ronaldo.codex.api.semantica.Simbolo;

/**
 *
 * @author ronaldo
 */
public class OperadorAbreviado extends Expresion {

    private TipoOperadorAbreviado tipo;
    private String id;

    public OperadorAbreviado(TipoOperadorAbreviado tipo, String id, int fila, int columna) {
        super(fila, columna);
        this.tipo = tipo;
        this.id = id;
    }

    @Override
    public void realizarTraduccion(StringBuffer sb) {
        sb.append(traductor.traducir(id));
        sb.append(" ");
        sb.append(tipo.getOperador());
    }

    @Override
    public void verificarSemantica(Semantica semantica) throws Exception {
        String ambitoActual = semantica.ambitoActual();

        Simbolo variable = semantica.getTablaSimbolos().buscar(this.id, ambitoActual);

        if (variable == null) {
            this.tipoResultado = Tipo.ERROR;
            semantica.getErrores().add(new ErrorSemantico(
                    getFila(),
                    getColumna(),
                    this.id,
                    "La variable '" + this.id
                    + "' no existe en el ámbito actual."
            ));
            return;
        }

        if (variable.getCategoria() != Categoria.VAR) {
            this.tipoResultado = Tipo.ERROR;
            semantica.getErrores().add(new ErrorSemantico(
                    getFila(),
                    getColumna(),
                    this.id,
                    "El identificador '" + this.id
                    + "' no es una variable valida para operadores abreviados"
            ));
            return;
        }

        Tipo tipoVariable = obtenerTipoDesdeId(variable.getIdTipo());

        if (tipoVariable != Tipo.NUMERUS && tipoVariable != Tipo.DECIMALIS) {
            this.tipoResultado = Tipo.ERROR;
            semantica.getErrores().add(new ErrorSemantico(
                    getFila(),
                    getColumna(),
                    this.id,
                    "El operador abreviado no es aplicable al tipo "
                    + tipoVariable + ", Solo se permite en NUMERUS o DECIMALIS"
            ));
            return;
        }

        this.tipoResultado = tipoVariable;
    }

    private Tipo obtenerTipoDesdeId(int idTipo) {
        return switch (idTipo) {
            case 0 ->
                Tipo.NUMERUS;
            case 1 ->
                Tipo.DECIMALIS;
            case 2 ->
                Tipo.TEXTUM;
            case 3 ->
                Tipo.LITTERA;
            case 4 ->
                Tipo.BOOLEANO;
            default ->
                Tipo.ERROR;
        };
    }

    public TipoOperadorAbreviado getTipo() {
        return tipo;
    }

    public void setTipo(TipoOperadorAbreviado tipo) {
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
