package com.ronaldo.codex.api.dto.respuesta;

import com.ronaldo.codex.api.dto.entrada.error.analisis.ErrorAnalisis;
import com.ronaldo.codex.api.dto.entrada.error.analisis.ErrorLexico;
import com.ronaldo.codex.api.dto.entrada.error.analisis.ErrorSemantico;
import com.ronaldo.codex.api.dto.entrada.error.analisis.ErrorSintactico;
import java.util.List;

/**
 *
 * @author ronaldo
 */
public class RespuestaDTO {
    
    private boolean hayErrores;
    private List<ErrorAnalisis> errores;

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
    
}
