package com.ronaldo.codex.api.structura;

import com.ronaldo.codex.api.dto.entrada.error.analisis.ErrorSemantico;
import com.ronaldo.codex.api.nodo.Nodo;
import com.ronaldo.codex.api.semantica.Semantica;

/**
 *
 * @author ronaldo
 */
public class DeclaracionStructura extends Nodo {

    private String id;
    private AtributosStructura atributos;

    public DeclaracionStructura(String id, AtributosStructura atributos, int fila, int columna) {
        super(fila, columna);
        this.id = id;
        this.atributos = atributos;
    }

    @Override
    public void verificarSemantica(Semantica semantica) throws Exception {

        if (semantica.getTablaTipos().existeTipo(this.id)) {
            semantica.getErrores().add(new ErrorSemantico(
                    getFila(),
                    getColumna(),
                    this.id,
                    "La estructura '" + this.id
                    + "' ya ha sido definida previamente."
            ));
            return;
        }

        if (this.atributos != null) {
            this.atributos.verificarSemantica(semantica);
        }

        semantica.getTablaTipos().agregarTipo(this.id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AtributosStructura getAtributos() {
        return atributos;
    }

    public void setAtributos(AtributosStructura atributos) {
        this.atributos = atributos;
    }

}
