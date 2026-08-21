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

        // Buscar el tipo de la estructura en la TablaTipos
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

        //Valida cada elemento en la construccion
        for (ElementoConstruccionStruct elem : this.elementosConstruccion) {

            elem.verificarSemantica(semantica);

            // Busca si el atributo existe en la definición del struct
            DeclaracionAtributoStructura atrDef = definicionStruct.buscarAtributo(elem.getId());

            if (atrDef == null) {
                semantica.getErrores().add(new ErrorSemantico(
                        elem.getFila(),
                        elem.getColumna(),
                        elem.getId(),
                        "El atributo '" + elem.getId()
                        + "' no existe en la definicion de '" + this.tipoStruct
                ));
            } else {
                //verificacion de los campos llenados en la construccion con la definicion del struct
                elem.validarCompatibilidadConDefinicion(semantica, atrDef);
            }
        }

        // registrar la nueva variable a la tabla de simbolos
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

}
