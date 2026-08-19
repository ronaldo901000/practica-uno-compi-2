package com.ronaldo.codex.api.semantica;

import com.ronaldo.codex.api.dto.entrada.error.analisis.ErrorSemantico;
import com.ronaldo.codex.api.exceptions.PilaException;
import com.ronaldo.codex.api.pila.Pila;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ronaldo
 */
public class Semantica {

    private TablaSimbolos tablaSimbolos;
    private List<ErrorSemantico> errores;
    private TablaTipos tablaTipos;
    private Pila<String> pilaAmbitos;
    public static final String GLOBAL = "GLOBAL";

    public Semantica() {
        this.tablaTipos = new TablaTipos();
        this.tablaSimbolos = new TablaSimbolos();
        this.errores = new ArrayList<>();
        this.pilaAmbitos = new Pila<>();
        this.pilaAmbitos.apilar(GLOBAL);
    }

    public TablaSimbolos getTablaSimbolos() {
        return tablaSimbolos;
    }

    public void entrarAmbito(String nuevoAmbito) {
        this.pilaAmbitos.apilar(nuevoAmbito);
    }

    public void salirAmbito() throws PilaException {
        if (!pilaAmbitos.estaVacia()) {
            pilaAmbitos.desapilar();
        }
    }

    public String ambitoActual() {
        return this.pilaAmbitos.getTope();
    }

    public void setTablaSimbolos(TablaSimbolos tablaSimbolos) {
        this.tablaSimbolos = tablaSimbolos;
    }

    public List<ErrorSemantico> getErrores() {
        return errores;
    }

    public void setErrores(List<ErrorSemantico> errores) {
        this.errores = errores;
    }

    public TablaTipos getTablaTipos() {
        return tablaTipos;
    }

    public void setTablaTipos(TablaTipos tablaTipos) {
        this.tablaTipos = tablaTipos;
    }

    public Pila<String> getPilaAmbitos() {
        return pilaAmbitos;
    }

    public static String getGLOBAL() {
        return GLOBAL;
    }

}
