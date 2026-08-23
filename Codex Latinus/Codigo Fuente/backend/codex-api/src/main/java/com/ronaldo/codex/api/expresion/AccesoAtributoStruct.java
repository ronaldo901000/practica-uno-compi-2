package com.ronaldo.codex.api.expresion;

import com.ronaldo.codex.api.dto.entrada.error.analisis.ErrorSemantico;
import com.ronaldo.codex.api.enums.Categoria;
import com.ronaldo.codex.api.enums.Tipo;
import com.ronaldo.codex.api.semantica.DeclaracionAtributoStructura;
import com.ronaldo.codex.api.semantica.ElementoTablaTipos;
import com.ronaldo.codex.api.semantica.Semantica;
import com.ronaldo.codex.api.semantica.Simbolo;
import com.ronaldo.codex.api.services.verificacion.VerificadorTipos;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ronaldo
 */
public class AccesoAtributoStruct extends Expresion {

    public String idInstancia;
    public List<String> idsLlamada;

    public AccesoAtributoStruct(String idInstancia, int fila, int columna) {
        super(fila, columna);
        this.idInstancia = idInstancia;
        idsLlamada = new ArrayList<>();
    }

    public String getIdInstancia() {
        return idInstancia;
    }

    public List<String> getIdsLlamada() {
        return idsLlamada;
    }

    @Override
    public void verificarSemantica(Semantica semantica) throws Exception {
        String ambito = semantica.getAmbitoActual();

        Simbolo simboloInstancia = semantica.getTablaSimbolos().buscar(this.idInstancia, ambito);

        if (simboloInstancia == null) {
            semantica.getErrores().add(new ErrorSemantico(
                    fila,
                    columna,
                    idInstancia,
                    "La variable o instancia '" + idInstancia + "' no existe en el ambito actual: " + ambito
            ));
            this.tipoResultado = Tipo.ERROR;
            return;
        }

        if (simboloInstancia.getCategoria() != Categoria.STRUCTURA) {
            semantica.getErrores().add(new ErrorSemantico(
                    fila,
                    columna,
                    idInstancia,
                    "El identificador '" + idInstancia + "' no es un Struct valido para acceso a atributos."
            ));
            this.tipoResultado = Tipo.ERROR;
            return;
        }

        String nombreTipoActual = semantica.getTablaTipos().obtenerNombreTipoPorId(simboloInstancia.getIdTipo());

        for (int i = 0; i < idsLlamada.size(); i++) {
            String idAtributo = idsLlamada.get(i);

            ElementoTablaTipos structActual = semantica.getTablaTipos().getTipoPorNombre(nombreTipoActual);

            if (structActual == null) {
                semantica.getErrores().add(new ErrorSemantico(
                        fila,
                        columna,
                        nombreTipoActual,
                        "El tipo de struct '" + nombreTipoActual + "' no esta definido en la tabla de tipos."
                ));
                this.tipoResultado = Tipo.ERROR;
                return;
            }

            DeclaracionAtributoStructura atr = structActual.buscarAtributo(idAtributo);

            if (atr == null) {
                semantica.getErrores().add(new ErrorSemantico(
                        fila,
                        columna,
                        idAtributo,
                        "El atributo '" + idAtributo + "' no existe dentro de la estructura '" + nombreTipoActual + "'."
                ));
                this.tipoResultado = Tipo.ERROR;
                return;
            }

            if (i == idsLlamada.size() - 1) {
                VerificadorTipos verificador = new VerificadorTipos();
                this.tipoResultado = verificador.verificar(atr.getTipo());
            } else {
                nombreTipoActual = atr.getTipo();
            }
        }
    }

    @Override
    public void realizarTraduccion(StringBuffer sb) {
        if (traductor != null) {
            sb.append(traductor.traducir(idInstancia));
        } else {
            sb.append(idInstancia);
        }

        if (idsLlamada != null) {
            for (String atr : idsLlamada) {
                sb.append(".").append(atr);
            }
        }
    }

    @Override
    public void generarDot(StringBuffer sb) {
        StringBuilder ruta = new StringBuilder(this.idInstancia != null ? this.idInstancia : "");
        if (this.idsLlamada != null) {
            for (String atr : this.idsLlamada) {
                ruta.append(".").append(atr);
            }
        }
        sb.append("  nodo").append(idNodo)
                .append(" [label=\"Acceso Atributo:\\n").append(ruta.toString())
                .append("\", fillcolor=\"white\"];\n");
    }
}
