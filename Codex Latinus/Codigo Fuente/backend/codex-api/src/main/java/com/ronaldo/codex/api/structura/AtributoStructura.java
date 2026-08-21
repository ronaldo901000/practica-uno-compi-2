package com.ronaldo.codex.api.structura;

import com.ronaldo.codex.api.dto.entrada.error.analisis.ErrorSemantico;
import com.ronaldo.codex.api.enums.EstructuraDato;
import com.ronaldo.codex.api.enums.Tipo;
import com.ronaldo.codex.api.nodo.Nodo;
import com.ronaldo.codex.api.semantica.Semantica;
import com.ronaldo.codex.api.services.verificacion.VerificadorTipos;

/**
 *
 * @author ronaldo
 */
public class AtributoStructura extends Nodo {

    private EstructuraDato estructura;
    private String id;
    private String tipoDato;
    private Tipo tipoResultado;

    public AtributoStructura(String id, String tipoDato, int fila, int columna, String estructuraCadena) {
        super(fila, columna);
        this.id = id;
        this.tipoDato = tipoDato;

        VerificadorTipos vt = new VerificadorTipos();
        this.estructura = vt.verificarEstructuraDato(estructuraCadena);
    }

    @Override
    public void realizarTraduccion(StringBuffer sb) {
        sb.append(traductor.traducir(estructura.getNombreEnMinusculas()));
        sb.append(" ").append(traductor.traducir(id)).append(" : ").
                append(traductor.traducir(tipoDato)).append(";");
    }

    @Override
    public void verificarSemantica(Semantica semantica) throws Exception {

        if (!semantica.getTablaTipos().existeTipo(this.tipoDato)) {
            this.tipoResultado = Tipo.ERROR;
            semantica.getErrores().add(new ErrorSemantico(
                    getFila(),
                    getColumna(),
                    this.id,
                    "El tipo de dato '" + this.tipoDato
                    + "' especificado para el atributo '" + this.id + "' no existe"
            ));
            return;
        }

        this.tipoResultado = obtenerTipoEnum(this.tipoDato);
    }

    private Tipo obtenerTipoEnum(String nombreTipo) {
        for (Tipo t : Tipo.values()) {
            if (t.getText() != null && t.getText().equalsIgnoreCase(nombreTipo)) {
                return t;
            }
        }
        return Tipo.STRUCTURA;
    }

    public EstructuraDato getEstructura() {
        return estructura;
    }

    public void setEstructura(EstructuraDato estructura) {
        this.estructura = estructura;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(String tipoDato) {
        this.tipoDato = tipoDato;
    }

    public Tipo getTipoResultado() {
        return tipoResultado;
    }

    public void setTipoResultado(Tipo tipoResultado) {
        this.tipoResultado = tipoResultado;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

}
