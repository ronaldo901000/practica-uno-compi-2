package com.ronaldo.codex.api.listeners;

import com.ronaldo.codex.api.CodexBaseListener;
import com.ronaldo.codex.api.CodexParser;
import com.ronaldo.codex.api.semantica.TablaSimbolos;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

/**
 *
 * @author ronaldo
 */
public class ExpresionListener extends CodexBaseListener {

    private final ParseTreeProperty<Object> valueTree = new ParseTreeProperty<>();
    private TablaSimbolos TablaSimbolos = new TablaSimbolos();

    @Override
    public void exitExpresion(CodexParser.ExpresionContext ctx) {

        
        if (ctx.ENTERO() != null) {
            int value = Integer.parseInt(ctx.ENTERO().getText());
            valueTree.put(ctx, value);
        } else if (ctx.DECIMAL() != null) {
            double value = Double.parseDouble(ctx.DECIMAL().getText());
            valueTree.put(ctx, value);
        } else if (ctx.CADENA() != null) {
            String value = ctx.CADENA().getText();
            valueTree.put(ctx, value);
        } else if (ctx.CHAR() != null) {
            char value = ctx.CHAR().getText().charAt(0);
            valueTree.put(ctx, value);
        } else if (ctx.VERUM() != null) {
            valueTree.put(ctx, true);
        } else if (ctx.FALSUS() != null) {
            valueTree.put(ctx, false);
        } else if (ctx.MULTI() != null) {
            Object izq = valueTree.get(ctx.expresion(0));
            Object der = valueTree.get(ctx.expresion(1));
            valueTree.put(ctx, operar(izq, der, "*"));
        } else if (ctx.DIV() != null) {
            Object izq = valueTree.get(ctx.expresion(0));
            Object der = valueTree.get(ctx.expresion(1));
            valueTree.put(ctx, operar(izq, der, "/"));
        } else if (ctx.MAS() != null) {
            Object izq = valueTree.get(ctx.expresion(0));
            Object der = valueTree.get(ctx.expresion(1));
            valueTree.put(ctx, operar(izq, der, "+"));
        } else if (ctx.MENOS() != null) {
            Object izq = valueTree.get(ctx.expresion(0));
            Object der = valueTree.get(ctx.expresion(1));
            valueTree.put(ctx, operar(izq, der, "-"));
        }

    }

    private Object operar(Object izq, Object der, String op) {

        if (izq instanceof Integer && der instanceof Integer) {
            int a = (Integer) izq, b = (Integer) der;
            return switch (op) {
                case "+" ->
                    a + b;
                case "-" ->
                    a - b;
                case "*" ->
                    a * b;
                case "/" ->
                    a / b;
                default ->
                    throw new RuntimeException("Operador inválido");
            };
        }

        double a = ((Number) izq).doubleValue();
        double b = ((Number) der).doubleValue();

        return switch (op) {
            case "+" ->
                a + b;
            case "-" ->
                a - b;
            case "*" ->
                a * b;
            case "/" ->
                a / b;
            default ->
                throw new RuntimeException("Operador inválido");
        };
    }
}
