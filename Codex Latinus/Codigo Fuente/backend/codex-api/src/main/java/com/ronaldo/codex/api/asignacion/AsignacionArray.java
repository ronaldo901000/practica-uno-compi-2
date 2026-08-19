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
public class AsignacionArray extends Instruccion {

    private String id;
    private int posicion;
    private Expresion expresion;

    public AsignacionArray(String id, int posicion, Expresion expresion, int fila, int columna) {
        super(fila, columna);
        this.id = id;
        this.posicion = posicion;
        this.expresion = expresion;
    }

    @Override
    public void verificarSemantica(Semantica semantica) throws Exception {
        String ambitoActual = semantica.ambitoActual();

        
        Simbolo variable = semantica.getTablaSimbolos().buscar(this.id, ambitoActual);
        if (variable == null) {
            variable = semantica.getTablaSimbolos().buscar(this.id, semantica.getGLOBAL());
        }

        if (variable == null) {
            semantica.getErrores().add(new ErrorSemantico(
                    getFila(),
                    getColumna(),
                    this.id,
                    "El arreglo '" + this.id + "' no ha sido declarado."
            ));
            return;
        }
        
        if (variable.getCategoria() != Categoria.ARRAY) {
            semantica.getErrores().add(new ErrorSemantico(
                    getFila(),
                    getColumna(),
                    this.id,
                    "El identificador '" + this.id + "' no es un arreglo asignable."
            ));
            return;
        }

        int tamanoMaximo = variable.getTamañoArray(); 
        if (this.posicion < 0 || this.posicion >= tamanoMaximo) {
            semantica.getErrores().add(new ErrorSemantico(
                    getFila(),
                    getColumna(),
                    this.id,
                    "Indice fuera de rango para el arreglo '" + this.id
                    + "'. Indice enviado: " + this.posicion + ", Tamano maximo: " + tamanoMaximo
            ));
        }

        if (this.expresion != null) {
            this.expresion.verificarSemantica(semantica);

            Tipo tipoExpresion = this.expresion.getTipoResultado();

            if (tipoExpresion == Tipo.ERROR) {
                return;
            }

            Tipo tipoArray = obtenerTipoDesdeId(variable.getIdTipo());

            if (tipoArray != tipoExpresion) {

                boolean esConversionPermitida = (tipoArray == Tipo.DECIMALIS && tipoExpresion == Tipo.NUMERUS)
                        || (tipoArray == Tipo.TEXTUM); 

                if (!esConversionPermitida) {
                    semantica.getErrores().add(new ErrorSemantico(
                            getFila(),
                            getColumna(),
                            this.id,
                            "No se puede asignar un valor de tipo " + tipoExpresion
                            + " a una posicion del arreglo '" + this.id + "' de tipo " + tipoArray + "."
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

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
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
