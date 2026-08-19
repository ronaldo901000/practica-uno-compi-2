package com.ronaldo.codex.api.dot.error;

/**
 *
 * @author ronaldo
 */
public class ErrorDTO {

    private String mensaje;

    public ErrorDTO(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}
