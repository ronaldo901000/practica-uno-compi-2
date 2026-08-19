package com.ronaldo.codex.api.resources;

import com.ronaldo.codex.api.dot.error.ErrorDTO;
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

        Analizador analizador = new Analizador();
        try {
            return Response.ok(analizador.analizar(entrada)).build();
        } catch (EntradaException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorDTO(ex.getMessage()))
                    .build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorDTO(ex.getMessage()))
                    .build();
        }
    }
}
