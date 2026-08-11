package com.ronaldo.codex.api.listeners;

import com.ronaldo.codex.api.dto.entrada.error.analisis.ErrorAnalisis;
import com.ronaldo.codex.api.dto.entrada.error.analisis.ErrorLexico;
import java.util.List;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

/**
 *
 * @author ronaldo
 */
public class ErrorLexicoListener extends BaseErrorListener {

    private List<ErrorAnalisis> errores;
    
    public ErrorLexicoListener(List<ErrorAnalisis> errores){
        this.errores = errores;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
            int line, int charPositionInLine, String msg, RecognitionException e) {

        String lexema = "";

        if (recognizer instanceof Lexer) {
            Lexer lexer = (Lexer) recognizer;
            CharStream input = lexer.getInputStream();
            int index = lexer.getCharIndex();

            try {
                lexema = input.getText(org.antlr.v4.runtime.misc.Interval.of(index - 1, index - 1));
            } catch (Exception ex) {
                lexema = "?";
            }
        }

        errores.add(new ErrorLexico(line, charPositionInLine + 1, lexema, "Simbolo desconocido"));
    }

}
