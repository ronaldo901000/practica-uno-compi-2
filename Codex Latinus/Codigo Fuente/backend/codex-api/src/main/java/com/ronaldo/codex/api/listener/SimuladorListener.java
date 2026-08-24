package com.ronaldo.codex.api.listener;

import com.ronaldo.codex.api.CodexBaseListener;
import com.ronaldo.codex.api.CodexParser;
import com.ronaldo.codex.api.pila.llamadas.ElementoPila;
import com.ronaldo.codex.api.pila.llamadas.PasoParseo;
import com.ronaldo.codex.api.pila.llamadas.SimulacionParseo;
import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;

/**
 *
 * @author ronaldo
 */
public class SimuladorListener extends CodexBaseListener {

    private List<ElementoPila> pilaActual = new ArrayList<>();
    private List<PasoParseo> pasos = new ArrayList<>();
    private int contador = 0;

    @Override
    public void visitTerminal(TerminalNode node) {
        pilaActual.add(new ElementoPila(node.getText(), "terminal"));
        pasos.add(new PasoParseo(
                ++contador,
                new ArrayList<>(pilaActual),
                "shift",
                "shift " + node.getText()
        ));
    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
        int cantidadHijos = ctx.getChildCount();
        for (int i = 0; i < cantidadHijos; i++) {
            if (!pilaActual.isEmpty()) {
                pilaActual.remove(pilaActual.size() - 1);
            }
        }
        String nombreRegla = CodexParser.ruleNames[ctx.getRuleIndex()];
        pilaActual.add(new ElementoPila(nombreRegla, "noTerminal"));
        pasos.add(new PasoParseo(
                ++contador,
                new ArrayList<>(pilaActual),
                "reduce",
                "reduce " + nombreRegla
        ));
        if (ctx.getParent() == null) {
            pasos.add(new PasoParseo(
                    ++contador,
                    new ArrayList<>(pilaActual),
                    "accept",
                    "accept"
            ));
        }
    }

    public SimulacionParseo obtenerResultado() {
        return new SimulacionParseo(pasos);
    }

}
