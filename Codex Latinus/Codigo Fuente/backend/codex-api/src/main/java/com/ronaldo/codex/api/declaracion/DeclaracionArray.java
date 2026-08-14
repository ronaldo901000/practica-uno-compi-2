package com.ronaldo.codex.api.declaracion;

import com.ronaldo.codex.api.enums.Tipo;
import com.ronaldo.codex.api.expresion.Expresion;
import com.ronaldo.codex.api.services.verificacion.VerificadorTipos;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ronaldo
 */
public class DeclaracionArray extends Declaracion {

    private int tamaño;
    private List<Expresion> valores;

    public DeclaracionArray(int tamaño, String id, String tipoString, int fila, int columna) {
        super(id, tipoString, fila, columna);
        this.tamaño = tamaño;
        this.valores = new ArrayList<>();
    }

    public DeclaracionArray(int fila, int columna) {
        super(fila, columna);
    }

    public void agregarValor(Expresion valor) {
        this.valores.add(valor);
    }

    public void agregarTipo(String tipoString) {
        VerificadorTipos verificador = new VerificadorTipos();
        this.tipo = verificador.verificar(tipoString);
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    public List<Expresion> getValores() {
        return valores;
    }

    public void setValores(List<Expresion> valores) {
        this.valores = valores;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

}
