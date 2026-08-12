package com.ronaldo.codex.api.listeners.visitors;

import com.ronaldo.codex.api.CodexBaseVisitor;
import com.ronaldo.codex.api.CodexParser;
import com.ronaldo.codex.api.aritmetica.Division;
import com.ronaldo.codex.api.aritmetica.ElementoTerminal;
import com.ronaldo.codex.api.aritmetica.Multiplicacion;
import com.ronaldo.codex.api.aritmetica.Resta;
import com.ronaldo.codex.api.aritmetica.Suma;
import com.ronaldo.codex.api.condicion.Condicion;
import com.ronaldo.codex.api.enums.Tipo;
import com.ronaldo.codex.api.enums.TipoCondicion;
import com.ronaldo.codex.api.enums.TipoOperadorAbreviado;
import com.ronaldo.codex.api.expresion.AccesoArray;
import com.ronaldo.codex.api.expresion.AccesoVariable;
import com.ronaldo.codex.api.expresion.Expresion;
import com.ronaldo.codex.api.expresion.LlamadaFuncion;
import com.ronaldo.codex.api.expresion.OperadorAbreviado;
import com.ronaldo.codex.api.interfaces.Visitable;
import com.ronaldo.codex.api.parametros.Parametros;

/**
 *
 * @author ronaldo
 */
public class CodexVisitor extends CodexBaseVisitor<Visitable> {

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitInicio(CodexParser.InicioContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitBloque_variabiles(CodexParser.Bloque_variabilesContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitVariables(CodexParser.VariablesContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitVariable(CodexParser.VariableContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitVariable_simple(CodexParser.Variable_simpleContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitDec_var(CodexParser.Dec_varContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitVariable_compuesta(CodexParser.Variable_compuestaContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitDec_array(CodexParser.Dec_arrayContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitTipo_dato(CodexParser.Tipo_datoContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitIni_array(CodexParser.Ini_arrayContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitValores_ini_array(CodexParser.Valores_ini_arrayContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitDec_structura(CodexParser.Dec_structuraContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitAtributos(CodexParser.AtributosContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitAtributo(CodexParser.AtributoContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitTipo(CodexParser.TipoContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitTipo_dato_atributo(CodexParser.Tipo_dato_atributoContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitConstr_structura(CodexParser.Constr_structuraContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitElementos_construccion(CodexParser.Elementos_construccionContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitEle_construccion(CodexParser.Ele_construccionContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitTamaño_array(CodexParser.Tamaño_arrayContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitAsig_atributo_array(CodexParser.Asig_atributo_arrayContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitElementos_asig(CodexParser.Elementos_asigContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitAsignacion(CodexParser.AsignacionContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitBloque_munera(CodexParser.Bloque_muneraContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitFunciones(CodexParser.FuncionesContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitFuncion(CodexParser.FuncionContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitFuncion_sin_retorno(CodexParser.Funcion_sin_retornoContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitFuncion_con_retorno(CodexParser.Funcion_con_retornoContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitFun_lectura(CodexParser.Fun_lecturaContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitFun_lectura_guardado(CodexParser.Fun_lectura_guardadoContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitFun_impresion(CodexParser.Fun_impresionContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitImpresion(CodexParser.ImpresionContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitSeccion_var_funcion(CodexParser.Seccion_var_funcionContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitParametros(CodexParser.ParametrosContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitParametro(CodexParser.ParametroContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitBloque_maior(CodexParser.Bloque_maiorContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitInstrucciones(CodexParser.InstruccionesContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitInstruccion(CodexParser.InstruccionContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitCondicional(CodexParser.CondicionalContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override
    public T visitMas_condicionales(CodexParser.Mas_condicionalesContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public T visitCiclo_simple(CodexParser.Ciclo_simpleContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public T visitCiclo_do_while(CodexParser.Ciclo_do_whileContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public T visitCiclo_iterador(CodexParser.Ciclo_iteradorContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public Expresion visitExpresion_iterador(CodexParser.Expresion_iteradorContext ctx) {

        if (ctx.expresion() != null) {
            return (Expresion) visit(ctx.expresion());
        } else {
            return (OperadorAbreviado) visit(ctx.operacion_abrev());
        }
    }

    @Override
    public OperadorAbreviado visitOperacion_abrev(CodexParser.Operacion_abrevContext ctx) {

        int fila = ctx.ID().getSymbol().getLine();
        int columna = ctx.ID().getSymbol().getCharPositionInLine();
        String id = ctx.ID().getText();

        if (ctx.MAS_MAS() != null) {
            return new OperadorAbreviado(TipoOperadorAbreviado.SUMAR, id, fila, columna);
        } else {
            return new OperadorAbreviado(TipoOperadorAbreviado.RESTAR, id, fila, columna);
        }
    }

    @Override
    public Expresion visitExpresion(CodexParser.ExpresionContext ctx) {

        if (ctx.MULTI() != null) {
            int fila = ctx.MULTI().getSymbol().getLine();
            int columna = ctx.MULTI().getSymbol().getCharPositionInLine();

            Object izquierda = visit(ctx.expresion(0));
            Object derecha = visit(ctx.expresion(1));

            return new Multiplicacion(izquierda, derecha, fila, columna);

        } else if (ctx.DIV() != null) {
            int fila = ctx.DIV().getSymbol().getLine();
            int columna = ctx.DIV().getSymbol().getCharPositionInLine();

            Object izquierda = visit(ctx.expresion(0));
            Object derecha = visit(ctx.expresion(1));

            return new Division(izquierda, derecha, fila, columna);

        } else if (ctx.MAS() != null) {

            int fila = ctx.MAS().getSymbol().getLine();
            int columna = ctx.MAS().getSymbol().getCharPositionInLine();

            Object izquierda = visit(ctx.expresion(0));
            Object derecha = visit(ctx.expresion(1));

            return new Suma(izquierda, derecha, fila, columna);

        } else if (ctx.MENOS() != null) {

            int fila = ctx.MENOS().getSymbol().getLine();
            int columna = ctx.MENOS().getSymbol().getCharPositionInLine();

            Object izquierda = visit(ctx.expresion(0));
            Object derecha = visit(ctx.expresion(1));

            return new Resta(izquierda, derecha, fila, columna);

        } else if (ctx.ENTERO() != null) {

            int fila = ctx.ENTERO().getSymbol().getLine();
            int columna = ctx.ENTERO().getSymbol().getCharPositionInLine();
            return new ElementoTerminal(fila, columna, ctx.ENTERO().getText(), Tipo.NUMERUS);

        } else if (ctx.DECIMAL() != null) {

            int fila = ctx.DECIMAL().getSymbol().getLine();
            int columna = ctx.DECIMAL().getSymbol().getCharPositionInLine();
            return new ElementoTerminal(fila, columna, ctx.DECIMAL().getText(), Tipo.DECIMAL);

        } else if (ctx.CADENA() != null) {

            int fila = ctx.CADENA().getSymbol().getLine();
            int columna = ctx.CADENA().getSymbol().getCharPositionInLine();
            return new ElementoTerminal(fila, columna, ctx.CADENA().getText(), Tipo.TEXTUM);

        } else if (ctx.CHAR() != null) {

            int fila = ctx.CHAR().getSymbol().getLine();
            int columna = ctx.CHAR().getSymbol().getCharPositionInLine();
            return new ElementoTerminal(fila, columna, ctx.CHAR().getText(), Tipo.LITTERA);

        } else if (ctx.VERUM() != null) {

            int fila = ctx.VERUM().getSymbol().getLine();
            int columna = ctx.VERUM().getSymbol().getCharPositionInLine();
            return new ElementoTerminal(fila, columna, ctx.VERUM().getText(), Tipo.BOOLEANO);

        } else if (ctx.FALSUS() != null) {

            int fila = ctx.FALSUS().getSymbol().getLine();
            int columna = ctx.FALSUS().getSymbol().getCharPositionInLine();
            return new ElementoTerminal(fila, columna, ctx.FALSUS().getText(), Tipo.BOOLEANO);

        } else if (ctx.ID() != null) {

            int fila = ctx.ID().getSymbol().getLine();
            int columna = ctx.ID().getSymbol().getCharPositionInLine();
            return new AccesoVariable(fila, columna, ctx.ID().getText());

        } else if (ctx.PAR_A() != null) {

            return (Expresion) visit(ctx.expresion(0));

        } else if (ctx.valor_posicion_array() != null) {

            return (AccesoArray) visit(ctx.valor_posicion_array());

        } else if (ctx.llamada_funcion() != null) {

            return (LlamadaFuncion) visit(ctx.llamada_funcion());

        }

        return null;
    }

    @Override
    public AccesoArray visitValor_posicion_array(CodexParser.Valor_posicion_arrayContext ctx) {
        String id = ctx.ID().getText();
        int posicion = Integer.parseInt(ctx.ENTERO().getText());

        return new AccesoArray(posicion, id, ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
    }

    @Override
    public LlamadaFuncion visitLlamada_funcion(CodexParser.Llamada_funcionContext ctx) {
        String id = ctx.ID().getText();
        Parametros parametros = (Parametros) visit(ctx.parametros_llamada());

        return new LlamadaFuncion(id, parametros, ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
    }

    @Override
    public Parametros visitParametros_llamada(CodexParser.Parametros_llamadaContext ctx) {
        Parametros parametros;

        if (ctx.parametros_llamada() != null) {
            // Caso: 2 o mas parametros
            parametros = visitParametros_llamada(ctx.parametros_llamada());
            Expresion nuevaExpresion = (Expresion) visit(ctx.expresion());
            parametros.agregarParametro(nuevaExpresion);
        } else if (ctx.expresion() != null) {
            // Caso: 1 parametros
            parametros = new Parametros(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
            Expresion expresion = (Expresion) visit(ctx.expresion());
            parametros.agregarParametro(expresion);
        } else {
            // Caso: sin parametros
            parametros = new Parametros(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
        }

        return parametros;
    }

    @Override
    public Condicion visitCondicion(CodexParser.CondicionContext ctx) {

        if (ctx.EQ_EQ() != null) {
            int fila = ctx.EQ_EQ().getSymbol().getLine();
            int columna = ctx.EQ_EQ().getSymbol().getCharPositionInLine();

            Object izquierda = visit(ctx.expresion(0));
            Object derecha = visit(ctx.expresion(1));

            return new Condicion(izquierda, derecha, TipoCondicion.IGUAL, fila, columna);

        } else if (ctx.NO_EQ() != null) {
            int fila = ctx.NO_EQ().getSymbol().getLine();
            int columna = ctx.NO_EQ().getSymbol().getCharPositionInLine();

            Object izquierda = visit(ctx.expresion(0));
            Object derecha = visit(ctx.expresion(1));

            return new Condicion(izquierda, derecha, TipoCondicion.DIFERENTE, fila, columna);

        } else if (ctx.MAYOR_EQ_Q() != null) {
            int fila = ctx.MAYOR_EQ_Q().getSymbol().getLine();
            int columna = ctx.MAYOR_EQ_Q().getSymbol().getCharPositionInLine();

            Object izquierda = visit(ctx.expresion(0));
            Object derecha = visit(ctx.expresion(1));

            return new Condicion(izquierda, derecha, TipoCondicion.MAYOR_IGUAL, fila, columna);

        } else if (ctx.MAYOR_Q() != null) {
            int fila = ctx.MAYOR_Q().getSymbol().getLine();
            int columna = ctx.MAYOR_Q().getSymbol().getCharPositionInLine();

            Object izquierda = visit(ctx.expresion(0));
            Object derecha = visit(ctx.expresion(1));

            return new Condicion(izquierda, derecha, TipoCondicion.MAYOR, fila, columna);

        } else if (ctx.MENOR_EQ_Q() != null) {
            int fila = ctx.MENOR_EQ_Q().getSymbol().getLine();
            int columna = ctx.MENOR_EQ_Q().getSymbol().getCharPositionInLine();

            Object izquierda = visit(ctx.expresion(0));
            Object derecha = visit(ctx.expresion(1));

            return new Condicion(izquierda, derecha, TipoCondicion.MENOR_IGUAL, fila, columna);

        } else if (ctx.MENOR_Q() != null) {
            int fila = ctx.MENOR_Q().getSymbol().getLine();
            int columna = ctx.MENOR_Q().getSymbol().getCharPositionInLine();

            Object izquierda = visit(ctx.expresion(0));
            Object derecha = visit(ctx.expresion(1));

            return new Condicion(izquierda, derecha, TipoCondicion.MENOR, fila, columna);

        } else if (ctx.AND() != null) {
            int fila = ctx.AND().getSymbol().getLine();
            int columna = ctx.AND().getSymbol().getCharPositionInLine();

            Object izquierda = visit(ctx.condicion(0));
            Object derecha = visit(ctx.condicion(1));

            return new Condicion(izquierda, derecha, TipoCondicion.Y, fila, columna);

        } else if (ctx.OR() != null) {
            int fila = ctx.OR().getSymbol().getLine();
            int columna = ctx.OR().getSymbol().getCharPositionInLine();

            Object izquierda = visit(ctx.condicion(0));
            Object derecha = visit(ctx.condicion(1));

            return new Condicion(izquierda, derecha, TipoCondicion.O, fila, columna);

        } else if (ctx.NON() != null) {
            int fila = ctx.NON().getSymbol().getLine();
            int columna = ctx.NON().getSymbol().getCharPositionInLine();

            Object valor = visit(ctx.condicion(0));

            return new Condicion(valor, TipoCondicion.NEGACION, fila, columna);

        } else if (ctx.PAR_A() != null) {

            return (Condicion) visit(ctx.condicion(0));

        }

        return null;
    }
}
