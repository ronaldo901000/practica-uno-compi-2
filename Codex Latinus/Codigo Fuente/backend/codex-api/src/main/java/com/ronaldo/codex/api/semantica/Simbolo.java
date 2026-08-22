package com.ronaldo.codex.api.semantica;

import com.ronaldo.codex.api.enums.Categoria;
import com.ronaldo.codex.api.enums.Tipo;
import java.util.List;

/**
 *
 * @author ronaldo
 */
public class Simbolo {

    private Llave llave;
    private Categoria categoria;
    private int idTipo;
    private int tipoRetorno;
    private int numeroParams;
    private int tamañoArray;
    private List<String> listaParams;
    private List<String> atributos;

    public Simbolo(String nombre, String ambito, Categoria categoria, int idTipo, int fila, int columna) {
        this.llave = new Llave(nombre, ambito);
        this.categoria = categoria;
        this.idTipo = idTipo;
    }

    public Simbolo() {
    }

    public Llave getLlave() {
        return llave;
    }

    public void setLlave(Llave llave) {
        this.llave = llave;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public Tipo getTipoRetornoEnum() {
        switch (this.tipoRetorno) {
            case 0:
                return Tipo.NUMERUS;
            case 1:
                return Tipo.DECIMALIS;
            case 2:
                return Tipo.TEXTUM;
            case 3:
                return Tipo.LITTERA;
            case 4:
                return Tipo.BOOL;
            case 5:
                return Tipo.VOID;
            default:
                return Tipo.ERROR;
        }
    }

    public void setTipoRetorno(int tipoRetorno) {
        this.tipoRetorno = tipoRetorno;
    }

    public int getNumeroParams() {
        return numeroParams;
    }

    public void setNumeroParams(int numeroParams) {
        this.numeroParams = numeroParams;
    }

    public int getTamañoArray() {
        return tamañoArray;
    }

    public void setTamañoArray(int tamañoArray) {
        this.tamañoArray = tamañoArray;
    }

    public List<String> getListaParams() {
        return listaParams;
    }

    public void setListaParams(List<String> listaParams) {
        this.listaParams = listaParams;
    }

    public List<String> getAtributos() {
        return atributos;
    }

    public void setAtributos(List<String> atributos) {
        this.atributos = atributos;
    }
    
    

}
