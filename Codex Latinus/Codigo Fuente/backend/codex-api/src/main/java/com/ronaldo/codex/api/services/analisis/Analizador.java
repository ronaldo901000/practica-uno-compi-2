package com.ronaldo.codex.api.services.analisis;

import com.ronaldo.codex.api.CodexLexer;
import com.ronaldo.codex.api.CodexParser;
import com.ronaldo.codex.api.dto.entrada.EntradaDTO;
import com.ronaldo.codex.api.dto.entrada.error.analisis.ErrorAnalisis;
import com.ronaldo.codex.api.dto.respuesta.RespuestaDTO;
import com.ronaldo.codex.api.exceptions.EntradaException;
import com.ronaldo.codex.api.ast.Ast;
import com.ronaldo.codex.api.listeners.ErrorLexicoListener;
import com.ronaldo.codex.api.listeners.ErrorSintacticoListener;
import com.ronaldo.codex.api.semantica.Semantica;
import com.ronaldo.codex.api.services.creadores.CreadorTablaSimbolosDTO;
import com.ronaldo.codex.api.services.verificacion.VerificadorEntrada;
import com.ronaldo.codex.api.visitors.CodexVisitor;
import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 *
 * @author ronaldo
 */
public class Analizador {

    private List<ErrorAnalisis> errores = new ArrayList<>();

    public RespuestaDTO analizar(EntradaDTO entrada) throws EntradaException, Exception {
        RespuestaDTO respuesta = new RespuestaDTO();

        VerificadorEntrada verificador = new VerificadorEntrada();

        //verificacion antes de analizar
        verificador.verificar(entrada);

        //analisis
        CharStream input = CharStreams.fromString(entrada.getTexto());

        CodexLexer lexer = new CodexLexer(input);
        lexer.removeErrorListeners();
        ErrorLexicoListener lexerListener = new ErrorLexicoListener(errores);
        lexer.addErrorListener(lexerListener);

        CommonTokenStream tokens = new CommonTokenStream(lexer);

        CodexParser parser = new CodexParser(tokens);
        parser.removeErrorListeners();
        ErrorSintacticoListener parserErrorListener = new ErrorSintacticoListener(this.errores);
        parser.addErrorListener(parserErrorListener);

        ParseTree tree = parser.inicio();

        if (hayErrores() || parser.getNumberOfSyntaxErrors() > 0) {
            respuesta.setErrores(this.errores);
            respuesta.setHayErrores(true);
            return respuesta;
        }

        //recorrido del arbol
        CodexVisitor visitor = new CodexVisitor();
        Ast ast = (Ast) visitor.visit(tree);

        //realizar el analisis semantico
        Semantica semantica = new Semantica();
        analizarSemantica(ast, semantica);

        if (hayErrores()) {
            respuesta.setErrores(this.errores);
            respuesta.setHayErrores(true);
        } else {
            CreadorTablaSimbolosDTO creador = new CreadorTablaSimbolosDTO();
            respuesta.setTablaSimbolos(creador.crear(semantica));

            //Generacion de la traduccion al lenguaje pig latin
            StringBuffer sb = new StringBuffer();
            ast.realizarTraduccion(sb);

            respuesta.setTraduccionPigLatin(sb.toString());
        }

        return respuesta;
    }

    private void analizarSemantica(Ast ast, Semantica semantica) throws Exception {

        ast.verificarSemantica(semantica);

        for (ErrorAnalisis error : semantica.getErrores()) {
            this.errores.add(error);
        }

    }

    private boolean hayErrores() {
        return !this.errores.isEmpty();
    }
}
