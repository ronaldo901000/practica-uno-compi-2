package com.ronaldo.codex.api.parametros.creacion;

import com.ronaldo.codex.api.dto.entrada.error.analisis.ErrorSemantico;
import com.ronaldo.codex.api.enums.Tipo;
import com.ronaldo.codex.api.nodo.Nodo;
import com.ronaldo.codex.api.semantica.Semantica;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author ronaldo
 */
public class ParametrosCreacion extends Nodo {

    private List<ParametroCreacion> parametros;
    private Tipo tipoResultado;

    public ParametrosCreacion(int fila, int columna) {
        super(fila, columna);
        this.parametros = new ArrayList<>();
    }

    @Override
    public void realizarTraduccion(StringBuffer sb) {
        if (parametros.isEmpty()) {
            return;
        }
        for (int i = 0; i < parametros.size(); i++) {
            ParametroCreacion p = parametros.get(i);
            p.realizarTraduccion(sb);
            if (i < parametros.size() - 1) {
                sb.append(", ");
            }

        }

    }

    @Override
    public void verificarSemantica(Semantica semantica) throws Exception {
        if (this.parametros == null || this.parametros.isEmpty()) {
            return;
        }

        Set<String> nombresUnicos = new HashSet<>();

        for (ParametroCreacion param : this.parametros) {
            if (param != null) {
                if (nombresUnicos.contains(param.getId())) {
                    this.tipoResultado = Tipo.ERROR;
                    semantica.getErrores().add(new ErrorSemantico(
                            param.getFila(),
                            param.getColumna(),
                            param.getId(),
                            "Parametro duplicado '" + param.getId() + "' en la declaración de la funcion"
                    ));
                } else {
                    nombresUnicos.add(param.getId());
                }

                param.verificarSemantica(semantica);

                if (param.getTipoResultado() == Tipo.ERROR) {
                    this.tipoResultado = Tipo.ERROR;
                }
            }
        }
    }

    public void agregarParametro(ParametroCreacion parametro) {
        this.parametros.add(parametro);
    }

    public List<ParametroCreacion> getParametros() {
        return parametros;
    }

    public void setParametros(List<ParametroCreacion> parametros) {
        this.parametros = parametros;
    }

    public Tipo getTipoResultado() {
        return tipoResultado;
    }

    public void setTipoResultado(Tipo tipoResultado) {
        this.tipoResultado = tipoResultado;
    }

}
