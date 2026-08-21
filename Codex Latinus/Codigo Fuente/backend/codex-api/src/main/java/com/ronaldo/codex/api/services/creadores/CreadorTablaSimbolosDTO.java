package com.ronaldo.codex.api.services.creadores;

import com.ronaldo.codex.api.dto.simbolo.SimboloDTO;
import com.ronaldo.codex.api.semantica.Semantica;
import com.ronaldo.codex.api.semantica.Simbolo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ronaldo
 */
public class CreadorTablaSimbolosDTO {

    public List<SimboloDTO> crear(Semantica semantica) {

        List<SimboloDTO> lista = new ArrayList<>();

        for (Simbolo s : semantica.getTablaSimbolos().getTablaSimbolos()) {
            
            
            SimboloDTO sim = new SimboloDTO();
            sim.setId(s.getLlave().getId());
            sim.setAlcance(s.getLlave().getAmbito());
            sim.setCategoria(s.getCategoria().toString());
            sim.setNumeroParams(s.getNumeroParams());
            sim.setSizeArray(s.getTamañoArray());
            sim.setTipoRetorno(s.getTipoRetornoEnum().toString());
            sim.setListaParams(listarParams(s.getListaParams()));
            sim.setTipo(semantica.getTablaTipos().obtenerNombreTipoPorId(s.getIdTipo()));

            lista.add(sim);
        }
        return lista;
    }

    public String listarParams(List<String> params) {
        String s = "";
        if (params == null) {
            return "-";
        }

        if (params.isEmpty()) {
            return "0";
        }

        for (int i = 0; i < params.size(); i++) {
            s += params.get(i) + " ";

            if (i != params.size() - 1) {
                s += ",";
            }
        }
        return s;
    }

}
