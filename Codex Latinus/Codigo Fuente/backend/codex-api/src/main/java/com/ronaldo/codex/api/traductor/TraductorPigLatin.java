package com.ronaldo.codex.api.traductor;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author ronaldo
 */
public class TraductorPigLatin {

    private static final String VOCALES = "aeiouAEIOU";

    public String traducir(String texto) {
        if (StringUtils.isBlank(texto)) {
            return texto;
        }

        //Empieza con vocal
        if (VOCALES.indexOf(texto.charAt(0)) >= 0) {
            return texto + "way";
        }

                // Empieza con consonante(s)
        int primeraVocal = 0;

        while (primeraVocal < texto.length()
                && VOCALES.indexOf(texto.charAt(primeraVocal)) < 0) {
            primeraVocal++;
        }

        // No contiene vocales
        if (primeraVocal == texto.length()) {
            return texto + "ay";
        }

        return texto.substring(primeraVocal)
                + texto.substring(0, primeraVocal)
                + "ay";
    }
}
