package com.ronaldo.codex.api.dto.entrada.error.analisis;

/**
 *
 * @author ronaldo
 */
public class ErrorAnalisis {

    protected int fila;
    protected int columna;
    protected String lexema;
    protected String descripcion;
    protected String tipo;

    public ErrorAnalisis(int fila, int columna, String lexema, String descripcion) {
        this.fila = fila;
        this.columna = columna;
        this.lexema = lexema;
        this.descripcion = descripcion;
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

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
}
