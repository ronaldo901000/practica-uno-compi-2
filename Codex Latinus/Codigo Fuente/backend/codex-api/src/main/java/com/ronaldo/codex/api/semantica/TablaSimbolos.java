package com.ronaldo.codex.api.semantica;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ronaldo
 */
public class TablaSimbolos {

    private List<Simbolo> tablaSimbolos;
    private static final String GLOBAL = "GLOBAL";

    public TablaSimbolos() {
        this.tablaSimbolos = new ArrayList<>();

    }

    public void insertar(Simbolo simbolo) {
        tablaSimbolos.add(simbolo);
    }

    public Simbolo buscar(String id, String ambitoActual) {
        for (int i = tablaSimbolos.size() - 1; i >= 0; i--) {

            Simbolo simbolo = tablaSimbolos.get(i);

            if (simbolo.getLlave().getId().equals(id)) {
                if (simbolo.getLlave().getAmbito().equals(ambitoActual)
                        || simbolo.getLlave().getAmbito().equals(GLOBAL)) {

                    return simbolo;
                }

            }

        }
        return null;
    }

    public boolean existeEnAmbitoActual(String id, String ambitoActual) {
        for (int i = tablaSimbolos.size() - 1; i >= 0; i--) {
            Simbolo s = tablaSimbolos.get(i);
            if (s.getLlave().getId().equals(id)
                    && s.getLlave().getAmbito().equals(ambitoActual)) {

                return true;
            }
        }
        return false;
    }

    public List<Simbolo> getTablaSimbolos() {
        return tablaSimbolos;
    }

    public void setTablaSimbolos(List<Simbolo> tablaSimbolos) {
        this.tablaSimbolos = tablaSimbolos;
    }

}
