package com.ronaldo.codex.api.structura.construccion;

import com.ronaldo.codex.api.declaracion.Declaracion;
import com.ronaldo.codex.api.dto.entrada.error.analisis.ErrorSemantico;
import com.ronaldo.codex.api.enums.Categoria;
import com.ronaldo.codex.api.semantica.DeclaracionAtributoStructura;
import com.ronaldo.codex.api.semantica.ElementoTablaTipos;
import com.ronaldo.codex.api.semantica.Llave;
import com.ronaldo.codex.api.semantica.Semantica;
import com.ronaldo.codex.api.semantica.Simbolo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ronaldo
 */
public class ConstruccionStruct extends Declaracion {

    private String tipoStruct;
    private List<ElementoConstruccionStruct> elementosConstruccion;

    public ConstruccionStruct(String tipoStruct, int fila, int columna) {
        super(fila, columna);
        this.tipoStruct = tipoStruct;
        elementosConstruccion = new ArrayList<>();
    }

    @Override
    public void realizarTraduccion(StringBuffer sb) {
        sb.append(traductor.traducir("esto")).append(" ");
        sb.append(traductor.traducir(this.getId())).append(" : ");
        sb.append(traductor.traducir(this.tipoStruct)).append(" {\n");

        if (this.elementosConstruccion != null && !this.elementosConstruccion.isEmpty()) {
            for (int i = 0; i < this.elementosConstruccion.size(); i++) {
                sb.append("\t");
                this.elementosConstruccion.get(i).realizarTraduccion(sb);

                if (i < this.elementosConstruccion.size() - 1) {
                    sb.append(",");
                }
                sb.append("\n");
            }
        }

        sb.append("}");
    }

    @Override
    public void verificarSemantica(Semantica semantica) throws Exception {

        ElementoTablaTipos definicionStruct = semantica.getTablaTipos().getTipoPorNombre(this.tipoStruct);

        if (definicionStruct == null) {
            semantica.getErrores().add(new ErrorSemantico(
                    getFila(),
                    getColumna(),
                    this.tipoStruct,
                    "La estructura '" + this.tipoStruct + "' no ha sido definida."
            ));
            return;
        }

        List<DeclaracionAtributoStructura> atributosEsperados = definicionStruct.getAtributos();

        if (this.elementosConstruccion.size() != atributosEsperados.size()) {
            semantica.getErrores().add(new ErrorSemantico(
                    getFila(),
                    getColumna(),
                    this.getId(),
                    "La instanciación de '" + this.tipoStruct + "' requiere "
                    + atributosEsperados.size() + " atributos, pero se proporcionaron "
                    + this.elementosConstruccion.size()
            ));
        }

        for (ElementoConstruccionStruct elem : this.elementosConstruccion) {

            elem.verificarSemantica(semantica);

            DeclaracionAtributoStructura atrDef = definicionStruct.buscarAtributo(elem.getId());

            if (atrDef == null) {
                semantica.getErrores().add(new ErrorSemantico(
                        elem.getFila(),
                        elem.getColumna(),
                        elem.getId(),
                        "El atributo '" + elem.getId()
                        + "' no existe en la definición de '" + this.tipoStruct + "'"
                ));
            } else {

                elem.validarCompatibilidadConDefinicion(semantica, atrDef);
            }
        }

        Simbolo simbolo = new Simbolo();
        Llave llave = new Llave(this.getId(), semantica.getAmbitoActual());
        simbolo.setLlave(llave);
        simbolo.setCategoria(Categoria.STRUCTURA);
        simbolo.setIdTipo(definicionStruct.getId());

        semantica.getTablaSimbolos().insertar(simbolo);
    }

    public void insertarElementoConstruccion(ElementoConstruccionStruct e) {
        if (elementosConstruccion != null) {
            this.elementosConstruccion.add(e);
        }
    }

    public String getTipoStruct() {
        return tipoStruct;
    }

    public void setTipoStruct(String tipoStruct) {
        this.tipoStruct = tipoStruct;
    }

    public List<ElementoConstruccionStruct> getElementosConstruccion() {
        return elementosConstruccion;
    }

    public void setElementosConstruccion(List<ElementoConstruccionStruct> elementosConstruccion) {
        this.elementosConstruccion = elementosConstruccion;
    }

    @Override
    public void generarDot(StringBuffer sb) {
        sb.append("  nodo").append(idNodo)
                .append(" [label=\"Construcción Struct\\nID: ")
                .append(this.getId() != null ? this.getId() : "Anónimo")
                .append("\\nTipo: ").append(this.tipoStruct)
                .append("\", fillcolor=\"white\"];\n");

        if (this.elementosConstruccion != null) {
            for (ElementoConstruccionStruct elem : this.elementosConstruccion) {
                if (elem != null) {
                    elem.generarDot(sb);
                    sb.append("  nodo").append(idNodo)
                            .append(" -> nodo").append(elem.getIdNodo())
                            .append(" [label=\"elemento\"];\n");
                }
            }
        }
    }

}
