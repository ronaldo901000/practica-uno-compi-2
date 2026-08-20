package com.ronaldo.codex.api.listeners;

import com.ronaldo.codex.api.dto.entrada.error.analisis.ErrorAnalisis;
import com.ronaldo.codex.api.dto.entrada.error.analisis.ErrorSintactico;
import java.util.List;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.IntervalSet;

/**
 *
 * @author ronaldo
 */
public class ErrorSintacticoListener extends BaseErrorListener {
    private static final String ERROR_CONDICION_BOOLEANA = "{'verum', 'falsus', 'non', '(', ID, ENTERO, DECIMAL, CADENA, CHAR}";
    private static final String ERROR_ESPERADO_INSTRUCCIONES = "'si', 'dum', 'facere', 'per', '>', '<', ID";
    
    private List<ErrorAnalisis> errores;

    public ErrorSintacticoListener(List<ErrorAnalisis> errores) {
        this.errores = errores;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
            Object offendingSymbol,
            int line,
            int charPositionInLine,
            String msg,
            RecognitionException e) {

        String lexemaErroneo = "";

        if (offendingSymbol instanceof Token) {
            Token token = (Token) offendingSymbol;
            lexemaErroneo = token.getText();
        }

        String tokensEsperados = "";
        if (recognizer instanceof Parser) {
            Parser parser = (Parser) recognizer;
            IntervalSet esperados = parser.getExpectedTokens();
            tokensEsperados = esperados.toString(parser.getVocabulary());
        }

        if (tokensEsperados.equals(ERROR_ESPERADO_INSTRUCCIONES)){
            tokensEsperados = "Se esperaban instrucciones";
        }
        
        if (tokensEsperados.equals(ERROR_CONDICION_BOOLEANA)){
            tokensEsperados = "Se esperaba una condicion de tipo booleana";
        }
        
        
        errores.add(
                new ErrorSintactico(
                        line,
                        charPositionInLine + 1,
                        lexemaErroneo,
                        "Se esperaban: " + tokensEsperados
                ));
    }

}
