package com.ronaldo.codex.api.dto.simbolo;

import com.ronaldo.codex.api.enums.Categoria;

public class SimboloDTO {

    private String id;
    private String categoria;
    private String tipo;
    private String tipoRetorno;
    private int numeroParams;
    private int sizeArray;
    private String listaParams;
    private String alcance;

    public SimboloDTO() {
    }

    // Constructor con todos los campos
    public SimboloDTO(String id, String categoria, String tipo, String tipoRetorno,
            int numeroParams, int tamañoArray, String listaParams, String alcance) {
        this.id = id;
        this.categoria = categoria;
        this.tipo = tipo;
        this.tipoRetorno = tipoRetorno;
        this.numeroParams = numeroParams;
        this.sizeArray = tamañoArray;
        this.listaParams = listaParams;
        this.alcance = alcance;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTipo() {
        if(this.categoria.equals(Categoria.FUNCION.toString())){
            return "-";
        }
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipoRetorno() {
        if (this.categoria.equals(Categoria.FUNCION.toString())) {
            return tipoRetorno;
        }
        return "-";
    }

    public void setTipoRetorno(String tipoRetorno) {
        this.tipoRetorno = tipoRetorno;
    }

    public int getNumeroParams() {
        return numeroParams;
    }

    public void setNumeroParams(int numeroParams) {
        this.numeroParams = numeroParams;
    }

    public String getAlcance() {
        return alcance;
    }

    public void setAlcance(String alcance) {
        this.alcance = alcance;
    }

    public String getListaParams() {
        return listaParams;
    }

    public void setListaParams(String listaParams) {
        this.listaParams = listaParams;
    }

    public int getSizeArray() {
        return sizeArray;
    }

    public void setSizeArray(int sizeArray) {
        this.sizeArray = sizeArray;
    }

}
