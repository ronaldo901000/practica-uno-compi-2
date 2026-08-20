package com.ronaldo.codex.api.structura;

import com.ronaldo.codex.api.dto.entrada.error.analisis.ErrorSemantico;
import com.ronaldo.codex.api.nodo.Nodo;
import com.ronaldo.codex.api.semantica.Semantica;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author ronaldo
 */
public class AtributosStructura extends Nodo {

    private List<AtributoStructura> atributos;

    public AtributosStructura(int fila, int columna) {
        super(fila, columna);
    }

    @Override
    public void realizarTraduccion(StringBuffer sb) {
        if (this.atributos == null || this.atributos.isEmpty()) {
            return;
        }

        for (AtributoStructura atributo : atributos) {
            sb.append("\t");
            atributo.realizarTraduccion(sb);
            sb.append("\n");
        }
    }

    @Override
    public void verificarSemantica(Semantica semantica) throws Exception {
        if (this.atributos == null || this.atributos.isEmpty()) {
            return;
        }

        Set<String> idsUnicos = new HashSet<>();

        for (AtributoStructura atr : this.atributos) {
            if (atr != null) {
                atr.verificarSemantica(semantica);

                if (idsUnicos.contains(atr.getId())) {
                    semantica.getErrores().add(new ErrorSemantico(
                            atr.getFila(),
                            atr.getColumna(),
                            atr.getId(),
                            "Atributo duplicado '" + atr.getId() + "' dentro de la declaracion del struct."
                    ));
                } else {
                    idsUnicos.add(atr.getId());
                }
            }
        }
    }

    public List<AtributoStructura> getAtributos() {
        return atributos;
    }

    public void setAtributos(List<AtributoStructura> atributos) {
        this.atributos = atributos;
    }

}
