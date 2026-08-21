package com.ronaldo.codex.api.asignacion;

import com.ronaldo.codex.api.dto.entrada.error.analisis.ErrorSemantico;
import com.ronaldo.codex.api.enums.Categoria;
import com.ronaldo.codex.api.enums.Tipo;
import com.ronaldo.codex.api.expresion.Expresion;
import com.ronaldo.codex.api.instruccion.Instruccion;
import com.ronaldo.codex.api.semantica.Semantica;
import com.ronaldo.codex.api.semantica.Simbolo;

/**
 *
 * @author ronaldo
 */
public class Asignacion extends Instruccion {

    private String id;
    private Expresion expresion;

    public Asignacion(String id, Expresion expresion, int fila, int columna) {
        super(fila, columna);
        this.id = id;
        this.expresion = expresion;
    }

    @Override
    public void realizarTraduccion(StringBuffer sb){
        
    }
    
    @Override
    public void verificarSemantica(Semantica semantica) throws Exception {
        String ambitoActual = semantica.getAmbitoActual();

        Simbolo variable = semantica.getTablaSimbolos().buscar(this.id, ambitoActual);
        if (variable == null) {
            variable = semantica.getTablaSimbolos().buscar(this.id, "global");
        }

        if (variable == null) {
            semantica.getErrores().add(new ErrorSemantico(
                    getFila(),
                    getColumna(),
                    this.id,
                    "La variable '" + this.id + "' no ha sido declarada."
            ));
            return;
        }

        if (variable.getCategoria() != Categoria.VAR) {
            semantica.getErrores().add(new ErrorSemantico(
                    getFila(),
                    getColumna(),
                    this.id,
                    "El identificador '" + this.id + "' no es una variable asignable."
            ));
            return;
        }

        if (this.expresion != null) {
            this.expresion.verificarSemantica(semantica);

            Tipo tipoExpresion = this.expresion.getTipoResultado();

            if (tipoExpresion == Tipo.ERROR) {
                return;
            }

            Tipo tipoVariable = obtenerTipoDesdeId(variable.getIdTipo());

            if (tipoVariable != tipoExpresion) {

                boolean esConversionPermitida = (tipoVariable == Tipo.DECIMALIS && tipoExpresion == Tipo.NUMERUS);

                if (!esConversionPermitida) {
                    semantica.getErrores().add(new ErrorSemantico(
                            getFila(),
                            getColumna(),
                            this.id,
                            "No se puede asignar un valor de tipo ->" + tipoExpresion
                            + " a la variable '" + this.id + "' de tipo " + tipoVariable + "."
                    ));
                }
            }
        }
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
            case 5 ->
                Tipo.VOID;
            default ->
                Tipo.ERROR;
        };
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Expresion getExpresion() {
        return expresion;
    }

    public void setExpresion(Expresion expresion) {
        this.expresion = expresion;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

}
