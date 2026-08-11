package com.ronaldo.codex.api.services.verificacion;

import com.ronaldo.codex.api.dto.entrada.EntradaDTO;
import com.ronaldo.codex.api.exceptions.EntradaException;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author ronaldo
 */
public class VerificadorEntrada {
    
    public void verificar(EntradaDTO entrada) throws EntradaException{
        if(StringUtils.isEmpty(entrada.getTexto())){
            throw new EntradaException("Por favor ingresa una cadena valida.");
        }
    }
    
}
