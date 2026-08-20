package com.ronaldo.codex.api.parametros.creacion;

import com.ronaldo.codex.api.dto.entrada.error.analisis.ErrorSemantico;
import com.ronaldo.codex.api.enums.Categoria;
import com.ronaldo.codex.api.enums.Tipo;
import com.ronaldo.codex.api.nodo.Nodo;
import com.ronaldo.codex.api.semantica.Llave;
import com.ronaldo.codex.api.semantica.Semantica;
import com.ronaldo.codex.api.semantica.Simbolo;

/**
 *
 * @author ronaldo
 */
public class ParametroCreacion extends Nodo {

    private String id;
    private Tipo tipo;
    private Tipo tipoResultado;

    public ParametroCreacion(String id, Tipo tipo, int fila, int columna) {
        super(fila, columna);
        this.id = id;
        this.tipo = tipo;
    }

    @Override
    public void realizarTraduccion(StringBuffer sb) {
        sb.append("estoway ");
        sb.append(traductor.traducir(id));
        sb.append(" : ");
        sb.append(traductor.traducir(tipo.getText()));

    }

    @Override
    public void verificarSemantica(Semantica semantica) throws Exception {
        if (this.tipo == null || this.tipo == Tipo.ERROR) {
            this.tipoResultado = Tipo.ERROR;
            semantica.getErrores().add(new ErrorSemantico(
                    getFila(),
                    getColumna(),
                    this.id,
                    "El parámetro '" + this.id + "' no puede ser de tipo " + this.tipo
            ));
            return;
        }

        if (!semantica.getTablaTipos().existeTipo(this.tipo.getText())) {
            this.tipoResultado = Tipo.ERROR;
            semantica.getErrores().add(new ErrorSemantico(
                    getFila(),
                    getColumna(),
                    this.id,
                    "El tipo de dato '" + this.tipo.getText() + "' especificado para el parámetro no existe."
            ));
            return;
        }

        String ambitoActual = semantica.ambitoActual();

        int idTipoNumerico = semantica.getTablaTipos().obtenerIdTipo(this.tipo.getText());

        if (idTipoNumerico == -1) {
            this.tipoResultado = Tipo.ERROR;
            semantica.getErrores().add(new ErrorSemantico(
                    getFila(),
                    getColumna(),
                    this.id,
                    "El tipo '" + this.tipo.getText() + "' no se encuentra registrado."
            ));
            return;
        }

        Simbolo parametroSimbolo = new Simbolo();
        parametroSimbolo.setLlave(new Llave(this.id, ambitoActual));
        parametroSimbolo.setCategoria(Categoria.VAR);
        parametroSimbolo.setIdTipo(idTipoNumerico);

        semantica.getTablaSimbolos().insertar(parametroSimbolo);
        this.tipoResultado = this.tipo;
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
