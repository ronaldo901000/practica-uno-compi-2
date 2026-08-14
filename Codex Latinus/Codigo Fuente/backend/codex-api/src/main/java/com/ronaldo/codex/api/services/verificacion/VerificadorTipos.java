package com.ronaldo.codex.api.services.verificacion;

import com.ronaldo.codex.api.enums.EstructuraDato;
import com.ronaldo.codex.api.enums.Tipo;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author ronaldo
 */
public class VerificadorTipos {

    private static final String NUMERUS = "numerus";
    private static final String DECIMALIS = "decimalis";
    private static final String TEXTUM = "textum";
    private static final String LITTERA = "littera";
    private static final String BOOL = "bool";
    private static final String ESTO = "esto";
    private static final String SERIES = "series";

    public Tipo verificar(String entrada) {
        if (StringUtils.isBlank(entrada)) {
            System.out.println("EN VERIFICADOR DE TIPOS LLEGO ALGO NULO");
        }

        if (entrada.equals(NUMERUS)) {
            return Tipo.NUMERUS;
        } else if (entrada.equals(DECIMALIS)) {
            return Tipo.DECIMAL;
        } else if (entrada.equals(TEXTUM)) {
            return Tipo.TEXTUM;
        } else if (entrada.equals(TEXTUM)) {
            return Tipo.LITTERA;
        }
        return null;
    }

    public EstructuraDato verificarEstructuraDato(String cadena) {
        if (cadena.equals(ESTO)) {
            return EstructuraDato.ESTO;
        } else {
            return EstructuraDato.SERIES;
        }
    }
}
