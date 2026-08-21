package com.ronaldo.codex.api.expresion;

import com.ronaldo.codex.api.dto.entrada.error.analisis.ErrorSemantico;
import com.ronaldo.codex.api.enums.Categoria;
import com.ronaldo.codex.api.enums.Tipo;
import com.ronaldo.codex.api.semantica.Semantica;
import com.ronaldo.codex.api.semantica.Simbolo;

/**
 *
 * @author ronaldo
 */
public class AccesoArray extends Expresion {

    private int posicion;
    private String id;

    public AccesoArray(int posicion, String id, int fila, int columna) {
        super(fila, columna);
        this.posicion = posicion;
        this.id = id;
    }

    @Override
    public void realizarTraduccion(StringBuffer sb) {
        sb.append(traductor.traducir(id));
        sb.append(" [");
        sb.append(posicion);
        sb.append("] ");
    }

    @Override
    public void verificarSemantica(Semantica semantica) throws Exception {
        String ambitoActual = semantica.getAmbitoActual();

        Simbolo simbolo = semantica.getTablaSimbolos().buscar(this.id, ambitoActual);

        if (simbolo == null) {
            this.tipoResultado = Tipo.ERROR;
            semantica.getErrores().add(new ErrorSemantico(
                    getFila(),
                    getColumna(),
                    this.id,
                    "El arreglo '" + this.id + "' no ha sido declarado"
            ));
            return;
        }

        if (simbolo.getCategoria() != Categoria.ARRAY) {
            this.tipoResultado = Tipo.ERROR;
            semantica.getErrores().add(new ErrorSemantico(
                    getFila(),
                    getColumna(),
                    this.id,
                    "La variable '" + this.id + "' no es un arreglo"
            ));
            return;
        }

        if (this.posicion < 0) {
            this.tipoResultado = Tipo.ERROR;
            semantica.getErrores().add(new ErrorSemantico(
                    getFila(),
                    getColumna(),
                    String.valueOf(this.posicion),
                    "El indice del arreglo no puede ser negativo: " + this.posicion
            ));
            return;
        }

        if (simbolo.getTamañoArray() > 0 && this.posicion >= simbolo.getTamañoArray()) {
            this.tipoResultado = Tipo.ERROR;
            semantica.getErrores().add(new ErrorSemantico(
                    getFila(),
                    getColumna(),
                    String.valueOf(this.posicion),
                    "Índice " + this.posicion + " fuera de rango para el arreglo '"
                    + this.id + "' (tamaño maximo: " + simbolo.getTamañoArray() + ")."
            ));
            return;
        }

        this.tipoResultado = Tipo.values()[simbolo.getIdTipo()];
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
