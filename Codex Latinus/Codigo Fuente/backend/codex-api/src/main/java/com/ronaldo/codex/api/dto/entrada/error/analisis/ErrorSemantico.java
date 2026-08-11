package com.ronaldo.codex.api.dto.entrada.error.analisis;

/**
 *
 * @author ronaldo
 */
public class ErrorSemantico extends ErrorAnalisis {

    public ErrorSemantico(int fila, int columna, String lexema, String descripcion) {
        super(fila, columna, lexema, descripcion);
        this.tipo = "Semantico";
    }

}
