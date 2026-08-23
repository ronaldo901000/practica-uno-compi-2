package com.ronaldo.codex.api.funcion;

import com.ronaldo.codex.api.dto.entrada.error.analisis.ErrorSemantico;
import com.ronaldo.codex.api.semantica.Semantica;
import com.ronaldo.codex.api.semantica.Simbolo;

/**
 *
 * @author ronaldo
 */
public class FuncionLecturaGuardado extends FuncionEspecial {

    private String id;

    public FuncionLecturaGuardado(String id, int fila, int columna) {
        super(fila, columna);
        this.id = id;
    }

    @Override
    public void realizarTraduccion(StringBuffer sb) {
        if (this.id == null || this.id.isEmpty()) {
            return;
        }

        sb.append(traductor.traducir(id)).append(" %OINK OINK ;\n");
    }

    @Override
    public void verificarSemantica(Semantica semantica) throws Exception {
        if (this.id == null || this.id.isEmpty()) {
            return;
        }

        Simbolo simbolo = semantica.getTablaSimbolos().buscar(this.id, semantica.getAmbitoActual());
        if (simbolo == null) {
            simbolo = semantica.getTablaSimbolos().buscar(this.id, semantica.getGLOBAL());
        }

        if (simbolo == null) {
            semantica.getErrores().add(new ErrorSemantico(
                    getFila(),
                    getColumna(),
                    this.id,
                    "La variable '" + this.id + "' no ha sido declarada para lectura."
            ));
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void generarDot(StringBuffer sb) {
        sb.append("  nodo").append(idNodo)
                .append(" [label=\"Lectura Guardado (%OINK OINK):\\nVariable: ").append(this.id != null ? this.id : "desconocida")
                .append("\", fillcolor=\"white\"];\n");
    }

}
