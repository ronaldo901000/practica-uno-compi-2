package com.ronaldo.codex.api.dto.entrada.error.analisis;

/**
 *
 * @author ronaldo
 */
public class ErrorLexico extends ErrorAnalisis{
    
    public ErrorLexico(int fila, int columna, String lexema, String descripcion) {
        super(fila, columna, lexema, descripcion);
        this.tipo = "Léxico";
    }
    
    
    
}
