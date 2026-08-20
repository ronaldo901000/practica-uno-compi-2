package com.ronaldo.codex.api.expresion;

import com.ronaldo.codex.api.dto.entrada.error.analisis.ErrorSemantico;
import com.ronaldo.codex.api.enums.Categoria;
import com.ronaldo.codex.api.enums.Tipo;
import com.ronaldo.codex.api.parametros.llamada.ParametrosLlamada;
import com.ronaldo.codex.api.semantica.Semantica;
import com.ronaldo.codex.api.semantica.Simbolo;
import java.util.List;

/**
 *
 * @author ronaldo
 */
public class LlamadaFuncion extends Expresion {

    private String id;
    private ParametrosLlamada parametros;

    public LlamadaFuncion(String id, ParametrosLlamada parametros, int fila, int columna) {
        super(fila, columna);
        this.id = id;
        this.parametros = parametros;
    }

    @Override
    public void realizarTraduccion(StringBuffer sb) {
        sb.append(traductor.traducir(id));
        sb.append("(");
        parametros.realizarTraduccion(sb);
        sb.append(")");
    }

    @Override
    public void verificarSemantica(Semantica semantica) throws Exception {
        String ambitoActual = semantica.ambitoActual();

        Simbolo funcion = semantica.getTablaSimbolos().buscar(id, ambitoActual);

        if (funcion == null) {
            funcion = semantica.getTablaSimbolos().buscar(id, Semantica.getGLOBAL());
        }

        if (funcion == null) {
            this.tipoResultado = Tipo.ERROR;
            semantica.getErrores().add(
                    new ErrorSemantico(fila,
                            columna,
                            id,
                            "La funcion '" + id + "' no ha sido declarada."
                    )
            );
            return;
        }

        if (funcion.getCategoria() != Categoria.FUNCION) {
            this.tipoResultado = Tipo.ERROR;
            semantica.getErrores().add(
                    new ErrorSemantico(fila,
                            columna,
                            id,
                            "El id '" + id + "' no corresponde a una funcion."
                    )
            );
            return;
        }

        //evaluacion de los parametros enviados
        List<Expresion> listaArgumentos = null;
        int cantidadArgumentos = 0;
        if (this.parametros != null && this.parametros.getParametros() != null) {
            listaArgumentos = this.parametros.getParametros();
            cantidadArgumentos = listaArgumentos.size();

            //verificacion de cada argumento
            for (Expresion arg : listaArgumentos) {
                arg.verificarSemantica(semantica);
            }
        }

        //validacion de cantidad de parametros
        if (cantidadArgumentos != funcion.getNumeroParams()) {
            this.tipoResultado = Tipo.ERROR;

            semantica.getErrores().add(
                    new ErrorSemantico(
                            fila,
                            columna,
                            id,
                            "La funcion '" + id + "' espera "
                            + funcion.getNumeroParams()
                            + " parametros, pero solo se enviaron "
                            + cantidadArgumentos
                    ));

            return;
        }

        List<String> tiposEsperadosStr = funcion.getListaParams();

        if (listaArgumentos != null && tiposEsperadosStr != null) {

            for (int i = 0; i < cantidadArgumentos; i++) {
                Expresion arg = listaArgumentos.get(i);
                Tipo tipoRecibido = arg.getTipoResultado();

                String nombreTipoEsperado = tiposEsperadosStr.get(i).toUpperCase().trim();
                Tipo tipoEsperado;

                try {
                    tipoEsperado = Tipo.valueOf(nombreTipoEsperado);
                } catch (IllegalArgumentException e) {
                    tipoEsperado = Tipo.ERROR;
                }

                if (tipoRecibido != Tipo.ERROR && tipoEsperado != tipoRecibido) {
                    this.tipoResultado = Tipo.ERROR;
                    semantica.getErrores().add(new ErrorSemantico(
                            arg.getFila(),
                            arg.getColumna(),
                            this.id,
                            "Tipo incorrecto en el parametro " + (i + 1) + " de la funcion '"
                            + this.id + "'. Se esperaba " + nombreTipoEsperado + " pero se recibio " + tipoRecibido
                    ));
                    return;
                }
            }
        }
        this.tipoResultado = funcion.getTipoRetornoEnum();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ParametrosLlamada getParametros() {
        return parametros;
    }

    public void setParametros(ParametrosLlamada parametros) {
        this.parametros = parametros;
    }

}
