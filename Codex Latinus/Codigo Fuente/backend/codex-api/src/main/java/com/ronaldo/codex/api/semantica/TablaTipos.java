package com.ronaldo.codex.api.semantica;

import com.ronaldo.codex.api.enums.Tipo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ronaldo
 */
public class TablaTipos {

    private static final String GLOBAL = "GLOBAL";
    private List<ElementoTablaTipos> tipos;

    public TablaTipos() {
        this.tipos = new ArrayList<>();
        cargarTiposPrimitivos();
    }

    public List<ElementoTablaTipos> getTipos() {
        return tipos;
    }

    public void setTipos(List<ElementoTablaTipos> tipos) {
        this.tipos = tipos;
    }

    public void cargarTiposPrimitivos() {
        tipos.add(new ElementoTablaTipos(0, Tipo.NUMERUS.getText(), GLOBAL));
        tipos.add(new ElementoTablaTipos(1, Tipo.DECIMALIS.getText(), GLOBAL));
        tipos.add(new ElementoTablaTipos(2, Tipo.TEXTUM.getText(), GLOBAL));
        tipos.add(new ElementoTablaTipos(3, Tipo.LITTERA.getText(), GLOBAL));
        tipos.add(new ElementoTablaTipos(4, Tipo.BOOL.getText(), GLOBAL));
        tipos.add(new ElementoTablaTipos(5, Tipo.VOID.getText(), GLOBAL));
    }

    public void agregarTipo(String nombre) {
        int id = tipos.size();

        ElementoTablaTipos e = new ElementoTablaTipos(id, nombre, GLOBAL);
        tipos.add(e);
    }

    public void agregarTipo(ElementoTablaTipos elemento) {
        this.tipos.add(elemento);
    }

    public boolean existeTipo(String nombreTipo) {
        if (this.tipos != null) {
            for (ElementoTablaTipos elemento : this.tipos) {
                if (elemento.getNombre().equalsIgnoreCase(nombreTipo)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int obtenerIdTipo(String nombreTipo) {
        if (this.tipos != null && nombreTipo != null) {
            for (ElementoTablaTipos elemento : this.tipos) {
                if (elemento.getNombre().equalsIgnoreCase(nombreTipo)) {
                    return elemento.getId();
                }
            }
        }
        return -1;
    }

    public String obtenerNombreTipoPorId(int idTipo) {
        if (this.tipos != null) {
            for (ElementoTablaTipos elemento : this.tipos) {
                if (elemento.getId() == idTipo) {
                    return elemento.getNombre();
                }
            }
        }
        return "-";
    }

    public ElementoTablaTipos getTipoPorNombre(String nombre) {
        if (nombre == null || this.tipos == null) {
            return null;
        }

        for (ElementoTablaTipos tipo : this.tipos) {
            if (tipo.getNombre() != null && tipo.getNombre().equalsIgnoreCase(nombre)) {
                return tipo;
            }
        }

        return null;
    }

}
