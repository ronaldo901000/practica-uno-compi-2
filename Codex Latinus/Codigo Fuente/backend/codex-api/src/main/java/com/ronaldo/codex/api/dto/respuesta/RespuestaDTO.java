package com.ronaldo.codex.api.dto.respuesta;

import com.ronaldo.codex.api.dto.entrada.error.analisis.ErrorAnalisis;
import com.ronaldo.codex.api.dto.simbolo.SimboloDTO;
import java.util.List;

/**
 *
 * @author ronaldo
 */
public class RespuestaDTO {

    private boolean hayErrores;
    private List<ErrorAnalisis> errores;
    private List<SimboloDTO> tablaSimbolos;

    public boolean isHayErrores() {
        return hayErrores;
    }

    public void setHayErrores(boolean hayErrores) {
        this.hayErrores = hayErrores;
    }

    public List<ErrorAnalisis> getErrores() {
        return errores;
    }

    public void setErrores(List<ErrorAnalisis> erroresLexicos) {
        this.errores = erroresLexicos;
    }

    public List<SimboloDTO> getTablaSimbolos() {
        return tablaSimbolos;
    }

    public void setTablaSimbolos(List<SimboloDTO> tablaSimbolos) {
        this.tablaSimbolos = tablaSimbolos;
    }

}
