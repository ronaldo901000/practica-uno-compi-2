package com.ronaldo.codex.api.simbolo;

import com.ronaldo.codex.api.enums.CategoriaSimbolo;
import com.ronaldo.codex.api.enums.Tipo;
import java.util.List;

/**
 *
 * @author ronaldo
 */
public class Simbolo {
    private String id;
    private Object valor;
    private CategoriaSimbolo categoria;
    private Tipo tipo;
    private int tamañoArray;
    private List<Tipo> parametros;
    private int tipoRetorno;
    
}
