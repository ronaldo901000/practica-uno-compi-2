package com.ronaldo.codex.api.funcion;

import com.ronaldo.codex.api.declaracion.Declaracion;
import com.ronaldo.codex.api.enums.Tipo;
import com.ronaldo.codex.api.instruccion.Instruccion;
import com.ronaldo.codex.api.parametros.creacion.ParametroCreacion;
import com.ronaldo.codex.api.services.verificacion.VerificadorTipos;
import java.util.List;

/**
 *
 * @author ronaldo
 */
public class FuncionReturn extends Funcion {

    private Tipo tipoRetorno;
    private String idRetorno;

    public FuncionReturn(String tipoRetornoString, String idRetorno,
            int fila, int columna, String id) {

        super(fila, columna, id);
        this.idRetorno = idRetorno;
        VerificadorTipos verificadorTipos = new VerificadorTipos();
        this.tipoRetorno = verificadorTipos.verificar(tipoRetornoString);

    }

    public Tipo getTipoRetorno() {
        return tipoRetorno;
    }

    public void setTipoRetorno(Tipo tipoRetorno) {
        this.tipoRetorno = tipoRetorno;
    }

    public String getIdRetorno() {
        return idRetorno;
    }

    public void setIdRetorno(String idRetorno) {
        this.idRetorno = idRetorno;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ParametroCreacion> getParametros() {
        return parametros;
    }

    public void setParametros(List<ParametroCreacion> parametros) {
        this.parametros = parametros;
    }

    public List<Declaracion> getVariables() {
        return variables;
    }

    public void setVariables(List<Declaracion> variables) {
        this.variables = variables;
    }

    public List<Instruccion> getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(List<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
    }

}
