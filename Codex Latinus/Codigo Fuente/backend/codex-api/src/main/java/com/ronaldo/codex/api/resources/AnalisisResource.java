package com.ronaldo.codex.api.resources;

import com.ronaldo.codex.api.dto.entrada.EntradaDTO;
import com.ronaldo.codex.api.exceptions.EntradaException;
import com.ronaldo.codex.api.services.analisis.Analizador;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author ronaldo
 */
@Path("/analisis")
public class AnalisisResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response analizar(EntradaDTO entrada) {

        // --- DIAGNÓSTICO TEMPORAL ---
        System.out.println("=== DIAGNOSTICO CARACTERES ===");
        for (int i = 0; i < entrada.getTexto().length(); i++) {
            char c = entrada.getTexto().charAt(i);
            if (!Character.isLetterOrDigit(c) && c != ' ' && c != '\n' && c != '\r'
                    && c != ';' && c != ':' && c != '(' && c != ')' && c != '{' && c != '}') {
                System.out.printf("Posición %d: '%c' -> U+%04X (decimal: %d)%n", i, c, (int) c, (int) c);
            }
        }
        System.out.println("===============================");
        // --- FIN DIAGNÓSTICO ---

        Analizador analizador = new Analizador();
        try {
            return Response.ok(analizador.analizar(entrada)).build();
        } catch (EntradaException ex) {
            return Response.status(
                    Response.Status.BAD_REQUEST
            ).entity(ex.getMessage()).build();
        }
    }
}
