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
            return Tipo.BOOL;
        }

        if (entrada.equals(NUMERUS)) {
            return Tipo.NUMERUS;
        } else if (entrada.equals(DECIMALIS)) {
            return Tipo.DECIMALIS;
        } else if (entrada.equals(TEXTUM)) {
            return Tipo.TEXTUM;
        } else if (entrada.equals(LITTERA)) {
            return Tipo.LITTERA;
        } else if (entrada.equals(BOOL)) {
            return Tipo.BOOL;
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
