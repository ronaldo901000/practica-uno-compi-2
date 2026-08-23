package com.ronaldo.codex.api.retorno;

import com.ronaldo.codex.api.dto.entrada.error.analisis.ErrorSemantico;
import com.ronaldo.codex.api.enums.Tipo;
import com.ronaldo.codex.api.expresion.AccesoVariable;
import com.ronaldo.codex.api.expresion.Expresion;
import com.ronaldo.codex.api.instruccion.Instruccion;
import com.ronaldo.codex.api.semantica.Semantica;
import com.ronaldo.codex.api.semantica.Simbolo;

public class Retorno extends Instruccion {

    private Expresion valor;

    public Retorno(Expresion valor, int fila, int columna) {
        super(fila, columna);
        this.valor = valor;
    }

    @Override
    public void verificarSemantica(Semantica semantica) throws Exception {
        if (valor != null) {
            valor.verificarSemantica(semantica);
        }

        String tipoEsperado = semantica.getTipoRetornoEsperado();

        if (tipoEsperado == null) {
            semantica.getErrores().add(new ErrorSemantico(
                    getFila(), getColumna(), "reddere",
                    "El uso de 'reddere' solo es valido dentro de una funcion con retorno"
            ));
            return;
        }

        if (valor instanceof AccesoVariable) {
            AccesoVariable var = (AccesoVariable) valor;
            Simbolo simbolo = semantica.getTablaSimbolos().buscar(var.getId(), semantica.getAmbitoActual());
            if (simbolo == null) {
                return;
            }
            String tipoRetorno = semantica.getTablaTipos().obtenerNombreTipoPorId(simbolo.getIdTipo());

            if (!tipoEsperado.equals(tipoRetorno)) {
                semantica.getErrores().add(new ErrorSemantico(
                        getFila(), getColumna(), "reddere",
                        "Tipo de retorno incompatible. Se esperaba '" + tipoEsperado
                        + "' pero se recibio '" + tipoRetorno + "'."
                ));
            }
            return;
        }

        Tipo tipoValorEnum = (valor != null) ? valor.getTipoResultado() : Tipo.VOID;

        if (tipoValorEnum == Tipo.ERROR) {
            return;
        }

        String tipoValorStr = tipoValorEnum.name();

        if (!tipoValorStr.equalsIgnoreCase(tipoEsperado)) {
            semantica.getErrores().add(new ErrorSemantico(
                    getFila(), getColumna(), "reddere",
                    "Tipo de retorno incompatible. Se esperaba '" + tipoEsperado
                    + "' pero se recibio '" + tipoValorStr + "'."
            ));
        }
    }

    @Override
    public void realizarTraduccion(StringBuffer sb) {
        sb.append("eddereray ");
        if (valor != null) {
            valor.realizarTraduccion(sb);
        }
        sb.append(" ;");
    }

    public Expresion getValor() {
        return valor;
    }

    @Override
    public void generarDot(StringBuffer sb) {
        sb.append("  nodo").append(idNodo)
                .append(" [label=\"Retorno (reddere)\", fillcolor=\"white\"];\n");

        if (this.valor != null) {
            this.valor.generarDot(sb);
            sb.append("  nodo").append(idNodo)
                    .append(" -> nodo").append(this.valor.getIdNodo())
                    .append(" [label=\"valor\"];\n");
        }
    }

}
