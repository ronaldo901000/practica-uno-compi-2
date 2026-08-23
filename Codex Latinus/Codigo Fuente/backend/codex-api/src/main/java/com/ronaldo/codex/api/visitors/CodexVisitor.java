package com.ronaldo.codex.api.visitors;

import com.ronaldo.codex.api.CodexBaseVisitor;
import com.ronaldo.codex.api.CodexParser;
import com.ronaldo.codex.api.aritmetica.Division;
import com.ronaldo.codex.api.aritmetica.ElementoTerminal;
import com.ronaldo.codex.api.aritmetica.Multiplicacion;
import com.ronaldo.codex.api.aritmetica.Resta;
import com.ronaldo.codex.api.aritmetica.Suma;
import com.ronaldo.codex.api.asignacion.Asignacion;
import com.ronaldo.codex.api.asignacion.AsignacionAbreviada;
import com.ronaldo.codex.api.asignacion.AsignacionArray;
import com.ronaldo.codex.api.asignacion.AsignacionAtributoStruct;
import com.ronaldo.codex.api.bloque.maior.BloqueMaior;
import com.ronaldo.codex.api.bloque.munera.BloqueMunera;
import com.ronaldo.codex.api.bloque.variables.BloqueVariables;
import com.ronaldo.codex.api.ciclo.Ciclo;
import com.ronaldo.codex.api.ciclo.CicloDoWhile;
import com.ronaldo.codex.api.ciclo.CicloIterador;
import com.ronaldo.codex.api.ciclo.CicloSimple;
import com.ronaldo.codex.api.condicion.Condicion;
import com.ronaldo.codex.api.condicional.Condicional;
import com.ronaldo.codex.api.condicional.ElseCondicion;
import com.ronaldo.codex.api.condicional.ElseIfCondicional;
import com.ronaldo.codex.api.condicional.IfCondicional;
import com.ronaldo.codex.api.declaracion.Declaracion;
import com.ronaldo.codex.api.declaracion.DeclaracionArray;
import com.ronaldo.codex.api.declaracion.DeclaracionVariable;
import com.ronaldo.codex.api.enums.Tipo;
import com.ronaldo.codex.api.enums.TipoCondicion;
import com.ronaldo.codex.api.enums.TipoOperadorAbreviado;
import com.ronaldo.codex.api.expresion.AccesoArray;
import com.ronaldo.codex.api.expresion.AccesoVariable;
import com.ronaldo.codex.api.expresion.Expresion;
import com.ronaldo.codex.api.expresion.LlamadaFuncion;
import com.ronaldo.codex.api.expresion.OperadorAbreviado;
import com.ronaldo.codex.api.funcion.Funcion;
import com.ronaldo.codex.api.funcion.FuncionImpresion;
import com.ronaldo.codex.api.funcion.FuncionLectura;
import com.ronaldo.codex.api.funcion.FuncionLecturaGuardado;
import com.ronaldo.codex.api.funcion.FuncionReturn;
import com.ronaldo.codex.api.funcion.FuncionVoid;
import com.ronaldo.codex.api.ast.Ast;
import com.ronaldo.codex.api.expresion.AccesoAtributoStruct;
import com.ronaldo.codex.api.instruccion.Instruccion;
import com.ronaldo.codex.api.instruccion.struct.LlamadaAtributoStruct;
import com.ronaldo.codex.api.interfaces.Visitable;
import com.ronaldo.codex.api.parametros.creacion.ParametroCreacion;
import com.ronaldo.codex.api.parametros.creacion.ParametrosCreacion;
import com.ronaldo.codex.api.parametros.llamada.ParametrosLlamada;
import com.ronaldo.codex.api.retorno.Retorno;
import com.ronaldo.codex.api.services.verificacion.VerificadorTipos;
import com.ronaldo.codex.api.structura.AtributoStructura;
import com.ronaldo.codex.api.structura.AtributosStructura;
import com.ronaldo.codex.api.structura.DeclaracionStructura;
import com.ronaldo.codex.api.structura.construccion.ConstruccionStruct;
import com.ronaldo.codex.api.structura.construccion.ElementoConstruccionStruct;
import java.util.List;

/**
 *
 * @author ronaldo
 */
public class CodexVisitor extends CodexBaseVisitor<Visitable> {

    @Override
    public Ast visitInicio(CodexParser.InicioContext ctx) {
        BloqueVariables bloqueVariables = (BloqueVariables) visit(ctx.bloque_variabiles());
        BloqueMaior bloqueMaior = (BloqueMaior) visit(ctx.bloque_maior());
        BloqueMunera bloqueMunera = (BloqueMunera) visit(ctx.bloque_munera());
        int fila = ctx.start.getLine();
        int columna = ctx.start.getCharPositionInLine();
        return new Ast(bloqueVariables, bloqueMunera, bloqueMaior, fila, columna);
    }

    @Override
    public BloqueVariables visitBloque_variabiles(CodexParser.Bloque_variabilesContext ctx) {

        if (ctx.variable() != null) {
            int fila = ctx.start.getLine();
            int columna = ctx.start.getCharPositionInLine();
            BloqueVariables bloque = new BloqueVariables(fila, columna);

            for (CodexParser.VariableContext varCtx : ctx.variable()) {
                bloque.getDeclaraciones().add((Declaracion) visit(varCtx));
            }
            return bloque;
        }

        return null;
    }

    @Override
    public Declaracion visitVariable(CodexParser.VariableContext ctx) {
        if (ctx.variable_simple() != null) {
            return (DeclaracionVariable) visit(ctx.variable_simple());
        } else if (ctx.variable_compuesta() != null) {
            return (DeclaracionArray) visit(ctx.variable_compuesta());
        } else if (ctx.dec_structura() != null) {
            return (DeclaracionStructura) visit(ctx.dec_structura());
        } else if (ctx.constr_structura() != null) {
            return (ConstruccionStruct) visit(ctx.constr_structura());
        }
        return null;
    }

    @Override
    public DeclaracionVariable visitVariable_simple(CodexParser.Variable_simpleContext ctx) {
        return (DeclaracionVariable) visit(ctx.dec_var());
    }

    @Override
    public DeclaracionVariable visitDec_var(CodexParser.Dec_varContext ctx) {
        int fila = ctx.start.getLine();
        int columna = ctx.start.getCharPositionInLine();
        String id = ctx.ID().getText();
        String tipoDato = ctx.tipo_dato().getText();
        Expresion valor = (Expresion) visit(ctx.expresion());

        return new DeclaracionVariable(valor, id, tipoDato, fila, columna);

    }

    @Override
    public DeclaracionArray visitVariable_compuesta(CodexParser.Variable_compuestaContext ctx) {
        return (DeclaracionArray) visit(ctx.dec_array());
    }

    @Override
    public DeclaracionArray visitDec_array(CodexParser.Dec_arrayContext ctx) {
        int fila = ctx.start.getLine();
        int columna = ctx.start.getCharPositionInLine();
        String id = ctx.ID().getText();
        int tamaño = Integer.parseInt(ctx.ENTERO().getText());
        String tipoDato = ctx.tipo_dato().getText();

        if (ctx.ini_array().LLAVE_A() != null) {
            DeclaracionArray declaracionArray = (DeclaracionArray) visit(ctx.ini_array());
            declaracionArray.setFila(fila);
            declaracionArray.setColumna(columna);
            declaracionArray.setId(id);
            declaracionArray.setTamaño(tamaño);
            declaracionArray.agregarTipo(tipoDato);
            return declaracionArray;
        } else {
            DeclaracionArray declaracionArray = new DeclaracionArray(
                    tamaño, id, tipoDato, fila, columna);

            return declaracionArray;
        }
    }

    @Override
    public DeclaracionArray visitIni_array(CodexParser.Ini_arrayContext ctx) {

        if (ctx.valores_ini_array() != null) {
            return (DeclaracionArray) visit(ctx.valores_ini_array());
        } else {
            return null;
        }
    }

    @Override
    public DeclaracionArray visitValores_ini_array(CodexParser.Valores_ini_arrayContext ctx) {

        int fila = ctx.start.getLine();
        int columna = ctx.start.getCharPositionInLine();

        DeclaracionArray declaracionArray = new DeclaracionArray(fila, columna);

        recolectarValoresArray(ctx, declaracionArray);

        return declaracionArray;
    }

    private void recolectarValoresArray(
            CodexParser.Valores_ini_arrayContext ctx,
            DeclaracionArray declaracionArray) {
        if (ctx == null) {
            return;
        }

        if (ctx.valores_ini_array() != null) {
            recolectarValoresArray(ctx.valores_ini_array(), declaracionArray);
        }

        if (ctx.expresion() != null) {
            Expresion valor = (Expresion) visit(ctx.expresion());
            if (valor != null) {
                declaracionArray.agregarValor(valor);
            }
        }
    }

    @Override
    public DeclaracionStructura visitDec_structura(CodexParser.Dec_structuraContext ctx) {
        int fila = ctx.start.getLine();
        int colulmna = ctx.start.getCharPositionInLine();
        String id = ctx.ID_STRUCT().getText();
        AtributosStructura atributos = (AtributosStructura) visit(ctx.atributos());

        return new DeclaracionStructura(id, atributos, fila, colulmna);
    }

    @Override
    public AtributosStructura visitAtributos(CodexParser.AtributosContext ctx) {
        if (ctx == null) {
            return null;
        }

        int fila = ctx.start.getLine();
        int columna = ctx.start.getCharPositionInLine();

        AtributosStructura atributosContainer = new AtributosStructura(fila, columna);

        recolectarAtributos(ctx, atributosContainer);

        return atributosContainer;
    }

    private void recolectarAtributos(CodexParser.AtributosContext ctx, AtributosStructura contenedor) {
        if (ctx == null) {
            return;
        }

        if (ctx.atributos() != null) {
            recolectarAtributos(ctx.atributos(), contenedor);
        }

        if (ctx.atributo() != null) {
            AtributoStructura atributo = (AtributoStructura) visit(ctx.atributo());
            if (atributo != null) {
                contenedor.getAtributos().add(atributo);
            }
        }

    }

    @Override
    public AtributoStructura visitAtributo(CodexParser.AtributoContext ctx) {
        int fila = ctx.start.getLine();
        int columna = ctx.start.getCharPositionInLine();
        String estructura = ctx.tipo().getText();
        String id = ctx.ID().getText();
        String tipoDato;

        if (ctx.tipo_dato_atributo().tipo_dato() != null) {
            tipoDato = ctx.tipo_dato_atributo().tipo_dato().getText();
        } else if (ctx.tipo_dato_atributo().ID() != null) {
            tipoDato = ctx.tipo_dato_atributo().ID().getText();
        } else {
            tipoDato = ctx.tipo_dato_atributo().ID_STRUCT().getText();
        }

        return new AtributoStructura(id, tipoDato, fila, columna, estructura);
    }


    /*
   


    @Override
    public T visitAsig_atributo_array(CodexParser.Asig_atributo_arrayContext ctx) {
        return visitChildren(ctx);
    }

    @Override
    public T visitElementos_asig(CodexParser.Elementos_asigContext ctx) {
        return visitChildren(ctx);
    }

     */
    @Override
    public ConstruccionStruct visitConstr_structura(CodexParser.Constr_structuraContext ctx) {

        int fila = ctx.start.getLine();
        int columna = ctx.start.getCharPositionInLine();

        String idVariable = ctx.ID().getText();

        String tipoStruct = ctx.ID_STRUCT().getText();

        ConstruccionStruct struct = new ConstruccionStruct(tipoStruct, fila, columna);
        struct.setId(idVariable);

        if (ctx.elementos_construccion() != null) {
            recolectarElementosConstruccion(ctx.elementos_construccion(), struct);
        }

        return struct;
    }

    private void recolectarElementosConstruccion(CodexParser.Elementos_construccionContext ctx, ConstruccionStruct struct) {
        if (ctx == null) {
            return;
        }

        if (ctx.elementos_construccion() != null) {
            recolectarElementosConstruccion(ctx.elementos_construccion(), struct);
        }

        if (ctx.ele_construccion() != null) {
            ElementoConstruccionStruct elemento = visitEle_construccion(ctx.ele_construccion());
            if (elemento != null) {
                struct.insertarElementoConstruccion(elemento);
            }
        }
    }

    @Override
    public ElementoConstruccionStruct visitEle_construccion(CodexParser.Ele_construccionContext ctx) {
        String id = ctx.ID().getText();
        int fila = ctx.start.getLine();
        int columna = ctx.start.getCharPositionInLine();

        Expresion valorExpresion = null;
        String tipoDato = null;
        int tamañoArray = 0;
        boolean esArray = false;

        CodexParser.Valor_elem_construccionContext valorCtx = ctx.valor_elem_construccion();

        if (valorCtx.expresion() != null) {
            valorExpresion = (Expresion) visit(valorCtx.expresion());
        } else if (valorCtx.ID_STRUCT() != null) {
            tipoDato = valorCtx.ID_STRUCT().getText();

            if (valorCtx.tamaño_array() != null && valorCtx.tamaño_array().ENTERO() != null) {
                esArray = true;
                tamañoArray = Integer.parseInt(valorCtx.tamaño_array().ENTERO().getText());
            }
        }

        if (!esArray && ctx.tamaño_array() != null && ctx.tamaño_array().ENTERO() != null) {
            esArray = true;
            tamañoArray = Integer.parseInt(ctx.tamaño_array().ENTERO().getText());
        }

        return new ElementoConstruccionStruct(id, tipoDato, valorExpresion, tamañoArray, esArray, fila, columna);
    }

    @Override
    public Asignacion visitAsignacion(CodexParser.AsignacionContext ctx) {
        int fila = ctx.ID().getSymbol().getLine();
        int columna = ctx.ID().getSymbol().getCharPositionInLine();

        String id = ctx.ID().getText();
        Expresion expresion = (Expresion) visit(ctx.expresion());

        return new Asignacion(id, expresion, fila, columna);
    }

    @Override
    public AsignacionArray visitAsignacion_array(CodexParser.Asignacion_arrayContext ctx) {
        int fila = ctx.start.getLine();
        int columna = ctx.start.getCharPositionInLine();
        int posicion = Integer.parseInt(ctx.tamaño_array().ENTERO().getText());
        String id = ctx.ID().getText();
        Expresion expresion = (Expresion) visit(ctx.expresion());

        return new AsignacionArray(id, posicion, expresion, fila, columna);
    }

    @Override
    public BloqueMunera visitBloque_munera(CodexParser.Bloque_muneraContext ctx) {
        if (ctx.funcion() != null) {

            BloqueMunera munera = new BloqueMunera(
                    ctx.start.getLine(),
                    ctx.start.getCharPositionInLine()
            );

            for (CodexParser.FuncionContext funCtx : ctx.funcion()) {
                munera.getFunciones().add((Funcion) visit(funCtx));
            }
            return munera;
        }
        return null;
    }

    @Override
    public Funcion visitFuncion(CodexParser.FuncionContext ctx) {
        if (ctx.funcion_sin_retorno() != null) {
            return (FuncionVoid) visit(ctx.funcion_sin_retorno());
        } else if (ctx.funcion_con_retorno() != null) {
            return (FuncionReturn) visit(ctx.funcion_con_retorno());
        }
        return null;
    }

    @Override
    public FuncionVoid visitFuncion_sin_retorno(CodexParser.Funcion_sin_retornoContext ctx) {
        int fila = ctx.start.getLine();
        int columna = ctx.start.getCharPositionInLine();
        String id = ctx.ID().getText();
        FuncionVoid funcionVoid = new FuncionVoid(fila, columna, id);

        if (ctx.parametros() != null) {
            ParametrosCreacion parametros = (ParametrosCreacion) visit(ctx.parametros());
            if (parametros != null && parametros.getParametros() != null) {
                for (ParametroCreacion param : parametros.getParametros()) {
                    funcionVoid.getParametros().add(param);
                }
            }
        }

        if (ctx.seccion_var_funcion() != null && ctx.seccion_var_funcion().variable() != null) {
            for (CodexParser.VariableContext variableContext : ctx.seccion_var_funcion().variable()) {
                Declaracion declaracion = (Declaracion) visit(variableContext);
                if (declaracion != null) {
                    funcionVoid.getVariables().add(declaracion);
                }
            }
        }

        if (ctx.instruccion() != null) {
            for (CodexParser.InstruccionContext instCtx : ctx.instruccion()) {
                Instruccion instruccion = (Instruccion) visit(instCtx);
                if (instruccion != null) {
                    funcionVoid.getInstrucciones().add(instruccion);
                }
            }
        }

        return funcionVoid;
    }

    @Override
    public FuncionReturn visitFuncion_con_retorno(CodexParser.Funcion_con_retornoContext ctx) {

        int fila = ctx.start.getLine();
        int columna = ctx.start.getCharPositionInLine();

        String id = ctx.ID().getText();

        String tipoRetorno = (ctx.tipo_dato() != null)
                ? ctx.tipo_dato().getText()
                : ctx.ID_STRUCT().getText();

        FuncionReturn funcionReturn = new FuncionReturn(tipoRetorno, fila, columna, id);

        if (ctx.parametros() != null) {
            ParametrosCreacion parametros = (ParametrosCreacion) visit(ctx.parametros());
            if (parametros != null && parametros.getParametros() != null) {
                for (ParametroCreacion param : parametros.getParametros()) {
                    funcionReturn.getParametros().add(param);
                }
            }
        }

        if (ctx.seccion_var_funcion() != null && ctx.seccion_var_funcion().variable() != null) {
            for (CodexParser.VariableContext variableContext : ctx.seccion_var_funcion().variable()) {
                Declaracion declaracion = (Declaracion) visit(variableContext);
                if (declaracion != null) {
                    funcionReturn.getVariables().add(declaracion);
                }
            }
        }

        if (ctx.instruccion() != null) {
            for (CodexParser.InstruccionContext instCtx : ctx.instruccion()) {
                Instruccion instruccion = (Instruccion) visit(instCtx);
                if (instruccion != null) {
                    funcionReturn.getInstrucciones().add(instruccion);
                }
            }
        }

        return funcionReturn;
    }

    @Override
    public FuncionLectura visitFun_lectura(CodexParser.Fun_lecturaContext ctx) {
        int fila = ctx.start.getLine();
        int columna = ctx.start.getCharPositionInLine();

        return new FuncionLectura(fila, columna);
    }

    @Override
    public FuncionLecturaGuardado visitFun_lectura_guardado(CodexParser.Fun_lectura_guardadoContext ctx) {

        int fila = ctx.MENOR_Q(0).getSymbol().getLine();
        int columna = ctx.MENOR_Q(0).getSymbol().getCharPositionInLine();
        String id = ctx.ID().getText();

        return new FuncionLecturaGuardado(id, fila, columna);
    }

    @Override
    public FuncionImpresion visitFun_impresion(CodexParser.Fun_impresionContext ctx) {

        if (ctx.impresion() != null) {

            int fila = ctx.impresion().start.getLine();
            int columna = ctx.impresion().start.getCharPositionInLine();

            FuncionImpresion funcionImpresion = new FuncionImpresion(fila, columna);

            //recolectar las impresiones 
            recolectarImpresion(ctx.impresion(), funcionImpresion);

            return funcionImpresion;
        }

        return null;
    }

    private void recolectarImpresion(CodexParser.ImpresionContext ctx, FuncionImpresion funcionImpresion) {
        if (ctx == null) {
            return;
        }

        // Mas de una expresion para imprimir
        if (ctx.impresion() != null) {
            recolectarImpresion(ctx.impresion(), funcionImpresion);
        }

        // Una expresion
        if (ctx.expresion() != null) {
            Expresion expr = (Expresion) visit(ctx.expresion());
            if (expr != null) {
                funcionImpresion.insertarCadena(expr);
            }
        }
    }

    @Override
    public ParametrosCreacion visitParametros(CodexParser.ParametrosContext ctx) {
        if (ctx == null || (ctx.parametros() == null && ctx.parametro() == null)) {

            return new ParametrosCreacion(0, 0);
        }

        int fila = ctx.start.getLine();
        int columna = ctx.start.getCharPositionInLine();

        ParametrosCreacion parametrosCreacion = new ParametrosCreacion(fila, columna);

        recolectarParametros(ctx, parametrosCreacion);

        return parametrosCreacion;
    }

    private void recolectarParametros(CodexParser.ParametrosContext ctx, ParametrosCreacion contenedor) {
        if (ctx == null) {
            return;
        }

        if (ctx.parametros() != null) {
            recolectarParametros(ctx.parametros(), contenedor);
        }

        if (ctx.parametro() != null) {
            ParametroCreacion param = visitParametro(ctx.parametro());
            if (param != null) {
                contenedor.agregarParametro(param);
            }
        }
    }

    @Override
    public ParametroCreacion visitParametro(CodexParser.ParametroContext ctx) {
        if (ctx == null) {
            return null;
        }

        VerificadorTipos verificador = new VerificadorTipos();

        int fila = ctx.start.getLine();
        int columna = ctx.start.getCharPositionInLine();

        String id = ctx.ID().getText();
        Tipo tipo = verificador.verificar(ctx.tipo_dato().getText());

        return new ParametroCreacion(id, tipo, fila, columna);
    }

    @Override
    public BloqueMaior visitBloque_maior(CodexParser.Bloque_maiorContext ctx) {
        int fila = ctx.MAIOR().getSymbol().getLine();
        int columna = ctx.MAIOR().getSymbol().getCharPositionInLine();

        BloqueMaior bloqueMaior = new BloqueMaior(fila, columna);

        for (CodexParser.InstruccionContext instCxt : ctx.instruccion()) {
            Instruccion instruccion = (Instruccion) visit(instCxt);
            bloqueMaior.insertarInstruccion(instruccion);
        }
        return bloqueMaior;
    }

    @Override
    public Instruccion visitInstruccion(CodexParser.InstruccionContext ctx) {

        if (ctx.asignacion() != null) {

            return (Asignacion) visit(ctx.asignacion());

        } else if (ctx.operacion_abrev() != null) {

            OperadorAbreviado operador = (OperadorAbreviado) visit(ctx.operacion_abrev());
            int linea = ctx.operacion_abrev().start.getLine();
            int columna = ctx.operacion_abrev().start.getCharPositionInLine();
            AsignacionAbreviada asignacion = new AsignacionAbreviada(operador, linea, columna);

            return asignacion;

        } else if (ctx.condicional() != null) {
            return (Condicional) visit(ctx.condicional());
        } else if (ctx.ciclo_simple() != null) {
            return (CicloSimple) visit(ctx.ciclo_simple());
        } else if (ctx.ciclo_do_while() != null) {
            return (CicloDoWhile) visit(ctx.ciclo_do_while());
        } else if (ctx.ciclo_iterador() != null) {
            return (CicloIterador) visit(ctx.ciclo_iterador());
        } else if (ctx.fun_lectura() != null) {
            return (FuncionLectura) visit(ctx.fun_lectura());
        } else if (ctx.fun_lectura_guardado() != null) {
            return (FuncionLecturaGuardado) visit(ctx.fun_lectura_guardado());
        } else if (ctx.fun_impresion() != null) {
            return (FuncionImpresion) visit(ctx.fun_impresion());
        } else if (ctx.asignacion_array() != null) {
            return (AsignacionArray) visit(ctx.asignacion_array());
        } else if (ctx.retorno() != null) {
            return (Retorno) visit(ctx.retorno());
        } else if (ctx.asig_atributo_struct() != null) {
            return (AsignacionAtributoStruct) visit(ctx.asig_atributo_struct());
        } else if (ctx.llamada_atributo_struct() != null) {
            int linea = ctx.llamada_atributo_struct().start.getLine();
            int columna = ctx.llamada_atributo_struct().start.getCharPositionInLine();

            AccesoAtributoStruct acceso = visitLlamada_atributo_struct(ctx.llamada_atributo_struct());
            if (acceso != null) {
                return new LlamadaAtributoStruct(acceso, linea, columna);
            }
            return null;
        }
        return null;
    }

    @Override
    public AsignacionAtributoStruct visitAsig_atributo_struct(CodexParser.Asig_atributo_structContext ctx) {
        if (ctx == null || ctx.llamada_atributo_struct() == null || ctx.expresion() == null) {
            return null;
        }

        int fila = ctx.start.getLine();
        int columna = ctx.start.getCharPositionInLine();

        AccesoAtributoStruct acceso = visitLlamada_atributo_struct(ctx.llamada_atributo_struct());
        if (acceso == null) {
            return null;
        }

        LlamadaAtributoStruct llamada = new LlamadaAtributoStruct(acceso, fila, columna);

        Expresion expr = (Expresion) visit(ctx.expresion());

        return new AsignacionAtributoStruct(llamada, expr, fila, columna);
    }

    @Override
    public Retorno visitRetorno(CodexParser.RetornoContext ctx) {
        int fila = ctx.start.getLine();
        int columna = ctx.start.getCharPositionInLine();
        Expresion ex = (Expresion) visit(ctx.expresion());

        return new Retorno(ex, fila, columna);
    }

    @Override
    public Condicional visitCondicional(CodexParser.CondicionalContext ctx) {
        int fila = ctx.SI().getSymbol().getLine();
        int columna = ctx.SI().getSymbol().getCharPositionInLine();
        Condicion condicion = (Condicion) visit(ctx.condicion());
        IfCondicional ifCondicional = new IfCondicional(fila, columna);
        ifCondicional.setCondicion(condicion);

        // Instrucciones de la rama principal (SI)
        for (CodexParser.InstruccionContext instCtx : ctx.instruccion()) {
            Instruccion instruccion = (Instruccion) visit(instCtx);
            ifCondicional.agregarInstruccion(instruccion);
        }

        for (CodexParser.Mas_condicionalesContext bifCtx : ctx.mas_condicionales()) {
            recolectarTodasLasBifurcaciones(bifCtx, ifCondicional);
        }

        return ifCondicional;
    }

    private void recolectarTodasLasBifurcaciones(CodexParser.Mas_condicionalesContext ctx, IfCondicional ifCondicional) {
        if (ctx == null) {
            return;
        }

        Condicional condicional = (Condicional) visit(ctx);
        if (condicional != null) {
            ifCondicional.agregarBifurcacion(condicional);
        }

        if (ctx.mas_condicionales() != null && !ctx.mas_condicionales().isEmpty()) {
            for (CodexParser.Mas_condicionalesContext subCtx : ctx.mas_condicionales()) {
                recolectarTodasLasBifurcaciones(subCtx, ifCondicional);
            }
        }
    }

    @Override
    public Condicional visitMas_condicionales(CodexParser.Mas_condicionalesContext ctx) {
        int fila = ctx.ALITER().getSymbol().getLine();
        int columna = ctx.ALITER().getSymbol().getCharPositionInLine();

        if (ctx.condicion() != null) {
            ElseIfCondicional elseIf = new ElseIfCondicional(fila, columna);
            Condicion condicion = (Condicion) visit(ctx.condicion());
            elseIf.setCondicion(condicion);

            for (CodexParser.InstruccionContext instCtx : ctx.instruccion()) {
                Instruccion instruccion = (Instruccion) visit(instCtx);
                elseIf.agregarInstruccion(instruccion);
            }
            return elseIf;
        } else {
            ElseCondicion elseCondicion = new ElseCondicion(fila, columna);
            for (CodexParser.InstruccionContext instCtx : ctx.instruccion()) {
                Instruccion instruccion = (Instruccion) visit(instCtx);
                elseCondicion.agregarInstruccion(instruccion);
            }
            return elseCondicion;
        }
    }

    @Override
    public CicloSimple visitCiclo_simple(CodexParser.Ciclo_simpleContext ctx) {
        int fila = ctx.DUM().getSymbol().getLine();
        int columna = ctx.DUM().getSymbol().getCharPositionInLine();
        Condicion condicion = (Condicion) visit(ctx.condicion());

        CicloSimple cicloSimple = new CicloSimple(condicion, fila, columna);

        insertarInstruccionesInternas(cicloSimple, ctx.instruccion());

        return cicloSimple;
    }

    @Override
    public CicloDoWhile visitCiclo_do_while(CodexParser.Ciclo_do_whileContext ctx) {

        int fila = ctx.FACERE().getSymbol().getLine();
        int columna = ctx.FACERE().getSymbol().getCharPositionInLine();
        Condicion condicion = (Condicion) visit(ctx.condicion());

        CicloDoWhile cicloDoWhile = new CicloDoWhile(condicion, fila, columna);

        insertarInstruccionesInternas(cicloDoWhile, ctx.instruccion());

        return cicloDoWhile;

    }

    @Override
    public CicloIterador visitCiclo_iterador(CodexParser.Ciclo_iteradorContext ctx) {
        int fila = ctx.PER().getSymbol().getLine();
        int columna = ctx.PER().getSymbol().getCharPositionInLine();

        DeclaracionVariable valor = (DeclaracionVariable) visit(ctx.dec_var());
        Condicion condicion = (Condicion) visit(ctx.condicion());
        Expresion expresionIterador = (Expresion) visit(ctx.expresion_iterador());

        CicloIterador cicloIterador = new CicloIterador(
                valor,
                expresionIterador,
                condicion,
                fila,
                columna
        );

        insertarInstruccionesInternas(cicloIterador, ctx.instruccion());
        return cicloIterador;
    }

    private void insertarInstruccionesInternas(
            Ciclo ciclo,
            List<CodexParser.InstruccionContext> instruccionesCtx) {

        for (CodexParser.InstruccionContext instCtx : instruccionesCtx) {
            Instruccion instruccion = (Instruccion) visit(instCtx);
            ciclo.agregarInstruccionInterna(instruccion);
        }

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
    public AccesoAtributoStruct visitLlamada_atributo_struct(CodexParser.Llamada_atributo_structContext ctx) {
        if (ctx != null && ctx.expresion() != null) {
            Object resultado = visit(ctx.expresion());
            if (resultado instanceof AccesoAtributoStruct) {
                return (AccesoAtributoStruct) resultado;
            }
        }
        return null;
    }

    @Override
    public Expresion visitExpresion(CodexParser.ExpresionContext ctx) {

        if (ctx.MULTI() != null) {
            int fila = ctx.MULTI().getSymbol().getLine();
            int columna = ctx.MULTI().getSymbol().getCharPositionInLine();

            Expresion izquierda = (Expresion) visit(ctx.expresion(0));
            Expresion derecha = (Expresion) visit(ctx.expresion(1));

            return new Multiplicacion(izquierda, derecha, fila, columna);

        } else if (ctx.DIV() != null) {
            int fila = ctx.DIV().getSymbol().getLine();
            int columna = ctx.DIV().getSymbol().getCharPositionInLine();

            Expresion izquierda = (Expresion) visit(ctx.expresion(0));
            Expresion derecha = (Expresion) visit(ctx.expresion(1));

            return new Division(izquierda, derecha, fila, columna);

        } else if (ctx.MAS() != null) {

            int fila = ctx.MAS().getSymbol().getLine();
            int columna = ctx.MAS().getSymbol().getCharPositionInLine();

            Expresion izquierda = (Expresion) visit(ctx.expresion(0));
            Expresion derecha = (Expresion) visit(ctx.expresion(1));

            return new Suma(izquierda, derecha, fila, columna);

        } else if (ctx.MENOS() != null) {

            int fila = ctx.MENOS().getSymbol().getLine();
            int columna = ctx.MENOS().getSymbol().getCharPositionInLine();

            Expresion izquierda = (Expresion) visit(ctx.expresion(0));
            Expresion derecha = (Expresion) visit(ctx.expresion(1));

            return new Resta(izquierda, derecha, fila, columna);

        } else if (ctx.ENTERO() != null) {

            int fila = ctx.ENTERO().getSymbol().getLine();
            int columna = ctx.ENTERO().getSymbol().getCharPositionInLine();
            return new ElementoTerminal(fila, columna, ctx.ENTERO().getText(), Tipo.NUMERUS);

        } else if (ctx.DECIMAL() != null) {

            int fila = ctx.DECIMAL().getSymbol().getLine();
            int columna = ctx.DECIMAL().getSymbol().getCharPositionInLine();
            return new ElementoTerminal(fila, columna, ctx.DECIMAL().getText(), Tipo.DECIMALIS);

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
            return new ElementoTerminal(fila, columna, ctx.VERUM().getText(), Tipo.BOOL);

        } else if (ctx.FALSUS() != null) {

            int fila = ctx.FALSUS().getSymbol().getLine();
            int columna = ctx.FALSUS().getSymbol().getCharPositionInLine();
            return new ElementoTerminal(fila, columna, ctx.FALSUS().getText(), Tipo.BOOL);

        } else if (ctx.PAR_A() != null) {

            return (Expresion) visit(ctx.expresion(0));

        } else if (ctx.valor_posicion_array() != null) {

            return (AccesoArray) visit(ctx.valor_posicion_array());

        } else if (ctx.llamada_funcion() != null) {

            return (LlamadaFuncion) visit(ctx.llamada_funcion());

        }
        if (ctx.PUNTO() != null && ctx.ID() != null) {
            int fila = ctx.start.getLine();
            int columna = ctx.start.getCharPositionInLine();
            String atributo = ctx.ID().getText();

            Expresion izq = (Expresion) visit(ctx.expresion(0));

            if (izq instanceof AccesoAtributoStruct) {
                AccesoAtributoStruct structAcceso = (AccesoAtributoStruct) izq;
                structAcceso.getIdsLlamada().add(atributo);
                return structAcceso;
            }

            String idInstancia = (izq instanceof AccesoVariable)
                    ? ((AccesoVariable) izq).getId()
                    : ctx.expresion(0).getText();

            AccesoAtributoStruct structAcceso = new AccesoAtributoStruct(idInstancia, fila, columna);
            structAcceso.getIdsLlamada().add(atributo);
            return structAcceso;
        } else if (ctx.ID() != null) {

            int fila = ctx.ID().getSymbol().getLine();
            int columna = ctx.ID().getSymbol().getCharPositionInLine();
            return new AccesoVariable(fila, columna, ctx.ID().getText());

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
        ParametrosLlamada parametros = (ParametrosLlamada) visit(ctx.parametros_llamada());

        return new LlamadaFuncion(id, parametros, ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
    }

    @Override
    public ParametrosLlamada visitParametros_llamada(CodexParser.Parametros_llamadaContext ctx) {
        ParametrosLlamada parametros;

        if (ctx.parametros_llamada() != null) {
            // Caso: 2 o mas parametros
            parametros = visitParametros_llamada(ctx.parametros_llamada());
            Expresion nuevaExpresion = (Expresion) visit(ctx.expresion());
            parametros.agregarParametro(nuevaExpresion);
        } else if (ctx.expresion() != null) {
            // Caso: 1 parametros
            parametros = new ParametrosLlamada(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
            Expresion expresion = (Expresion) visit(ctx.expresion());
            parametros.agregarParametro(expresion);
        } else {
            // Caso: sin parametros
            parametros = new ParametrosLlamada(ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
        }

        return parametros;
    }

    @Override
    public Condicion visitCondicion(CodexParser.CondicionContext ctx) {
        if (ctx.EQ_EQ() != null) {
            int fila = ctx.EQ_EQ().getSymbol().getLine();
            int columna = ctx.EQ_EQ().getSymbol().getCharPositionInLine();
            Expresion izquierda = (Expresion) visit(ctx.expresion(0));
            Expresion derecha = (Expresion) visit(ctx.expresion(1));
            return new Condicion(izquierda, derecha, TipoCondicion.IGUAL, fila, columna);
        } else if (ctx.NO_EQ() != null) {
            int fila = ctx.NO_EQ().getSymbol().getLine();
            int columna = ctx.NO_EQ().getSymbol().getCharPositionInLine();
            Expresion izquierda = (Expresion) visit(ctx.expresion(0));
            Expresion derecha = (Expresion) visit(ctx.expresion(1));
            return new Condicion(izquierda, derecha, TipoCondicion.DIFERENTE, fila, columna);
        } else if (ctx.MAYOR_EQ_Q() != null) {
            int fila = ctx.MAYOR_EQ_Q().getSymbol().getLine();
            int columna = ctx.MAYOR_EQ_Q().getSymbol().getCharPositionInLine();
            Expresion izquierda = (Expresion) visit(ctx.expresion(0));
            Expresion derecha = (Expresion) visit(ctx.expresion(1));
            return new Condicion(izquierda, derecha, TipoCondicion.MAYOR_IGUAL, fila, columna);
        } else if (ctx.MAYOR_Q() != null) {
            int fila = ctx.MAYOR_Q().getSymbol().getLine();
            int columna = ctx.MAYOR_Q().getSymbol().getCharPositionInLine();
            Expresion izquierda = (Expresion) visit(ctx.expresion(0));
            Expresion derecha = (Expresion) visit(ctx.expresion(1));
            return new Condicion(izquierda, derecha, TipoCondicion.MAYOR, fila, columna);
        } else if (ctx.MENOR_EQ_Q() != null) {
            int fila = ctx.MENOR_EQ_Q().getSymbol().getLine();
            int columna = ctx.MENOR_EQ_Q().getSymbol().getCharPositionInLine();
            Expresion izquierda = (Expresion) visit(ctx.expresion(0));
            Expresion derecha = (Expresion) visit(ctx.expresion(1));
            return new Condicion(izquierda, derecha, TipoCondicion.MENOR_IGUAL, fila, columna);
        } else if (ctx.MENOR_Q() != null) {
            int fila = ctx.MENOR_Q().getSymbol().getLine();
            int columna = ctx.MENOR_Q().getSymbol().getCharPositionInLine();
            Expresion izquierda = (Expresion) visit(ctx.expresion(0));
            Expresion derecha = (Expresion) visit(ctx.expresion(1));
            return new Condicion(izquierda, derecha, TipoCondicion.MENOR, fila, columna);
        } else if (ctx.AND() != null) {
            int fila = ctx.AND().getSymbol().getLine();
            int columna = ctx.AND().getSymbol().getCharPositionInLine();
            Condicion izquierda = (Condicion) visit(ctx.condicion(0));
            Condicion derecha = (Condicion) visit(ctx.condicion(1));
            return new Condicion(izquierda, derecha, TipoCondicion.AND, fila, columna);
        } else if (ctx.OR() != null) {
            int fila = ctx.OR().getSymbol().getLine();
            int columna = ctx.OR().getSymbol().getCharPositionInLine();
            Condicion izquierda = (Condicion) visit(ctx.condicion(0));
            Condicion derecha = (Condicion) visit(ctx.condicion(1));
            return new Condicion(izquierda, derecha, TipoCondicion.OR, fila, columna);
        } else if (ctx.NON() != null) {
            int fila = ctx.NON().getSymbol().getLine();
            int columna = ctx.NON().getSymbol().getCharPositionInLine();
            Condicion valor = (Condicion) visit(ctx.condicion(0));
            return new Condicion(valor, TipoCondicion.NOT, fila, columna);
        } else if (ctx.PAR_A() != null) {
            return (Condicion) visit(ctx.condicion(0));
        } else {
            int fila = ctx.expresion(0).getStart().getLine();
            int columna = ctx.expresion(0).getStart().getCharPositionInLine();
            Expresion valor = (Expresion) visit(ctx.expresion(0));
            return new Condicion(valor, TipoCondicion.EXPRESION, fila, columna);
        }
    }
}
