package com.ronaldo.codex.api.structura.construccion;

import com.ronaldo.codex.api.dto.entrada.error.analisis.ErrorSemantico;
import com.ronaldo.codex.api.expresion.Expresion;
import com.ronaldo.codex.api.nodo.Nodo;
import com.ronaldo.codex.api.semantica.DeclaracionAtributoStructura;
import com.ronaldo.codex.api.semantica.Semantica;

/**
 *
 * @author ronaldo
 */
public class ElementoConstruccionStruct extends Nodo {

    private String id;
    private String tipoDato;
    private Expresion valorExpresion;
    private int tamañoArray;
    private boolean esArreglo;

    public ElementoConstruccionStruct(String id, String tipoDato, Expresion valorExpresion,
            int tamañoArray, boolean esArreglo, int fila, int columna) {
        super(fila, columna);
        this.id = id;
        this.tipoDato = tipoDato;
        this.valorExpresion = valorExpresion;
        this.tamañoArray = tamañoArray;
        this.esArreglo = esArreglo;
    }

    @Override
    public void verificarSemantica(Semantica semantica) throws Exception {
        if (this.valorExpresion != null) {
            this.valorExpresion.verificarSemantica(semantica);
        }

        if (this.tipoDato != null && !semantica.getTablaTipos().existeTipo(this.tipoDato)) {
            semantica.getErrores().add(new ErrorSemantico(
                    getFila(), getColumna(), this.id,
                    "El tipo de estructura '" + this.tipoDato + "' no existe en la Tabla de Tipos."
            ));
        }
    }

    public void validarCompatibilidadConDefinicion(Semantica semantica, DeclaracionAtributoStructura atributoDefinicion) {

        String tipoIngresado = null;

        if (this.valorExpresion != null) {

            if (this.valorExpresion.getNombreTipoEstructura() != null
                    && !this.valorExpresion.getNombreTipoEstructura().equals("-")) {
                tipoIngresado = this.valorExpresion.getNombreTipoEstructura();

            } else if (this.valorExpresion.getTipoResultado() != null) {
                tipoIngresado = this.valorExpresion.getTipoResultado().name();
            }

        } else if (this.tipoDato != null) {
            tipoIngresado = this.tipoDato;
        }

        if (tipoIngresado == null) {
            semantica.getErrores().add(new ErrorSemantico(
                    getFila(), getColumna(), this.id,
                    "No se pudo resolver el tipo de dato para el atributo '" + this.id + "'."
            ));
            return;
        }

        if (!tipoIngresado.equalsIgnoreCase(atributoDefinicion.getTipo())) {
            semantica.getErrores().add(new ErrorSemantico(
                    getFila(), getColumna(), this.id,
                    "Tipo de dato incompatible para el atributo '" + this.id + "'. Se esperaba '"
                    + atributoDefinicion.getTipo() + "' pero se proporcionó '" + tipoIngresado + "'."
            ));
        }

        boolean definicionEsSerie = atributoDefinicion.esArreglo();
        if (definicionEsSerie != this.esArreglo) {
            String esperado = definicionEsSerie ? "una serie (arreglo)" : "una variable simple";
            String recibido = this.esArreglo ? "una serie" : "una variable simple";

            semantica.getErrores().add(new ErrorSemantico(
                    getFila(), getColumna(), this.id,
                    "Estructura incompatible para el atributo '" + this.id + "'. Se esperaba "
                    + esperado + " pero se proporcionó " + recibido + "."
            ));
        }
    }

    @Override
    public void realizarTraduccion(StringBuffer sb) {
        sb.append(traductor.traducir(this.id)).append(" : ");

        if (this.valorExpresion != null) {
            this.valorExpresion.realizarTraduccion(sb);
        } else if (this.tipoDato != null) {
            sb.append(traductor.traducir(this.tipoDato));
        }

        if (this.esArreglo) {
            sb.append("[").append(this.tamañoArray).append("]");
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(String tipoDato) {
        this.tipoDato = tipoDato;
    }

    public int getTamañoArray() {
        return tamañoArray;
    }

    public void setTamañoArray(int tamañoArray) {
        this.tamañoArray = tamañoArray;
    }

    public boolean isEsArreglo() {
        return esArreglo;
    }

    public void setEsArreglo(boolean esArreglo) {
        this.esArreglo = esArreglo;
    }

    public Expresion getValorExpresion() {
        return valorExpresion;
    }

    public void setValorExpresion(Expresion valorExpresion) {
        this.valorExpresion = valorExpresion;
    }

    @Override
    public void generarDot(StringBuffer sb) {
        StringBuilder label = new StringBuilder();
        label.append("Elemento Struct\\nCampo: ").append(this.id);
        if (this.esArreglo) {
            label.append(" [").append(this.tamañoArray).append("]");
        } else if (this.tipoDato != null) {
            label.append("\\nTipo: ").append(this.tipoDato);
        }

        sb.append("  nodo").append(idNodo)
                .append(" [label=\"").append(label.toString())
                .append("\", fillcolor=\"white\"];\n");

        if (this.valorExpresion != null) {
            this.valorExpresion.generarDot(sb);
            sb.append("  nodo").append(idNodo)
                    .append(" -> nodo").append(this.valorExpresion.getIdNodo())
                    .append(" [label=\"valor\"];\n");
        }
    }

}
