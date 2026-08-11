package com.ronaldo.codex.api.dto.entrada.error.analisis;

/**
 *
 * @author ronaldo
 */
public class ErrorSintactico extends ErrorAnalisis {

    public ErrorSintactico(int fila, int columna, String lexema, String descripcion) {
        super(fila, columna, lexema, descripcion);
        this.tipo = "Sintactico";
    }

}
