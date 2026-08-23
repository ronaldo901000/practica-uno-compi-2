package com.ronaldo.codex.api.structura;

import com.ronaldo.codex.api.declaracion.Declaracion;
import com.ronaldo.codex.api.dto.entrada.error.analisis.ErrorSemantico;
import com.ronaldo.codex.api.semantica.DeclaracionAtributoStructura;
import com.ronaldo.codex.api.semantica.ElementoTablaTipos;
import com.ronaldo.codex.api.semantica.Semantica;
import java.util.List;

/**
 *
 * @author ronaldo
 */
public class DeclaracionStructura extends Declaracion {

    private AtributosStructura atributos;

    public DeclaracionStructura(String id, AtributosStructura atributos, int fila, int columna) {
        super(fila, columna);
        this.id = id;
        this.atributos = atributos;
    }

    @Override
    public void realizarTraduccion(StringBuffer sb) {
        sb.append(traductor.traducir("structura")).append(" ");
        sb.append(traductor.traducir(id));
        sb.append(" { \n");
        atributos.realizarTraduccion(sb);
        sb.append("}").append(traductor.traducir("finis")).append("; \n");
    }

    @Override
    public void verificarSemantica(Semantica semantica) throws Exception {

        //Verificar si ya existe en la tabla de tipos
        if (semantica.getTablaTipos().existeTipo(this.id)) {
            semantica.getErrores().add(new ErrorSemantico(
                    getFila(),
                    getColumna(),
                    this.id,
                    "La estructura '" + this.id + "' ya ha sido definida previamente."
            ));
            return;
        }

        // Crear un nuevo elemento para la tabla de tipos
        int nuevoIdTipo = semantica.getTablaTipos().getTipos().size();
        ElementoTablaTipos nuevoTipoStruct = new ElementoTablaTipos(
                nuevoIdTipo,
                this.id,
                semantica.getAmbitoActual()
        );

        if (this.atributos != null) {
            this.atributos.verificarSemantica(semantica);

            List<DeclaracionAtributoStructura> listaAtributos
                    = this.atributos.obtenerAtributosSemanticos(semantica);

            nuevoTipoStruct.setAtributos(listaAtributos);
        }

        // Guardar la estructura en la Tabla de Tipos
        semantica.getTablaTipos().agregarTipo(nuevoTipoStruct);
    }

    public AtributosStructura getAtributos() {
        return atributos;
    }

    public void setAtributos(AtributosStructura atributos) {
        this.atributos = atributos;
    }

    @Override
    public void generarDot(StringBuffer sb) {
        sb.append("  nodo").append(idNodo)
                .append(" [label=\"Declaracion Structura\\nID: ")
                .append(this.id)
                .append("\", fillcolor=\"white\"];\n");
        if (this.atributos != null) {
            this.atributos.generarDot(sb);
            sb.append("  nodo").append(idNodo)
                    .append(" -> nodo").append(this.atributos.getIdNodo())
                    .append(" [label=\"atributos\"];\n");
        }
    }

}
