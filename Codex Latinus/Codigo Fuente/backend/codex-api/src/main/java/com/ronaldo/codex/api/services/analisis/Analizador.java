package com.ronaldo.codex.api.services.analisis;

import com.ronaldo.codex.api.CodexLexer;
import com.ronaldo.codex.api.CodexParser;
import com.ronaldo.codex.api.dto.entrada.EntradaDTO;
import com.ronaldo.codex.api.dto.entrada.error.analisis.ErrorAnalisis;
import com.ronaldo.codex.api.dto.respuesta.RespuestaDTO;
import com.ronaldo.codex.api.exceptions.EntradaException;
import com.ronaldo.codex.api.listeners.ErrorLexicoListener;
import com.ronaldo.codex.api.listeners.ErrorSintacticoListener;
import com.ronaldo.codex.api.services.verificacion.VerificadorEntrada;
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

    public RespuestaDTO analizar(EntradaDTO entrada) throws EntradaException {
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

        if (hayErrores()) {
            respuesta.setErrores(errores);  
            respuesta.setHayErrores(true);
        }

        return respuesta;
    }

    private boolean hayErrores() {
        return !this.errores.isEmpty();
    }
}
