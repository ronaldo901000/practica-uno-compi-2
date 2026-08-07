grammar Codex;

/**Analisis Sintactico**/
inicio:
    bloque_variabiles bloque_munera maior
    ;

/**BLOQUE VARIABILES**/
bloque_variabiles:
    VARIABILES variables
    | /**lambda**/
    ;

variables
    : variables variable
    | variable
    ;

variable:
    variable_simple
    | variable_compuesta
    ;

/**TIPO DE DATOS SIMPLES Y CADENAS**/
variable_simple
    : dec_var
    ;
    
dec_var:
    ESTO ID DOS_P tipo_dato expresion P_COMA
    ;


/**TIPO DE DATOS COMPUESTOS (ARRAYS)**/
variable_compuesta
    : dec_array
    ;
    
dec_array
    : SERIES ID CORCH_A ENTERO DOS_P tipo_dato ini_array P_COMA
    ;

tipo_dato
    : NUMERUS
    | DECIMALIS
    | TEXTUM
    | LITTERA
    | /**cadena vacia (toma el valor de boolean)**/
    ;

ini_array
    : LLAVE_A valores_ini_array LLAVE_C
    | /**lambda**/
    ;

valores_ini_array
    : valores_ini_array COMA expresion
    | expresion
    ;


/**ESTRUCTURAS**/

/**Declaracion**/
dec_structura
    : STRUCTURA ID LLAVE_A atributos LLAVE_C FINIS P_COMA
    ;

atributos
    : atributos COMA atributo
    | atributo
    ;

atributo
    : tipo ID DOS_P tipo_dato_atributo 
    ;

tipo
    : ESTO
    | SERIES
    ;

tipo_dato_atributo
    : tipo_dato
    | ID
    ;
/**construccion de la estructura**/
constr_structura
    : ESTO ID P_COMA ID LLAVE_A  LLAVE_C
    ;

/**BLOQUE MUNERA**/
bloque_munera:
    
    ;

maior:
    ;

expresion:
    expresion MULTI expresion
    | expresion DIV expresion
    | expresion MAS expresion
    | expresion MENOS expresion
    | PAR_A expresion PAR_C
    | ENTERO
    | DECIMAL
    | CADENA
    | CHAR
    | VERUM
    | FALSUS 
    | ID
    ;

/**Analisis Lexico**/

/**palabras reservadas**/
VARIABILES: 'VARIABILES>';
MUNERA: 'MUNERA>';
MAIOR:  'MAIOR>';
ESTO:   'esto';
SERIES: 'series';
NUMERUS: 'numerus';
DECIMALIS:  'decimalis';
TEXTUM: 'textum';
LITTERA: 'littera';
VERUM:  'verum';
FALSUS: 'falsus';
STRUCTURA:  'structura';
SI: 'si';
FINIS:  'finis';
ALITER: 'aliter';
DUM:    'dum';
FACERE: 'facere';
PER:    'per';
PERGE:  'perge';
INTERRUMPE: 'interrumpe';
ACTIO:  'actio';
RATIO:  'ratio';
REDDERE:    'reddere';
NON:    'non';

/**simbolos**/
MAS:    '+';
MENOS:  '-';
MULTI:  '*';
DIV:    '/';
EQ: '=';
EQ_EQ:  '==';
NO_EQ:  '!=';
MAYOR_Q: '>';
MAYOR_EQ_Q: '>=';
MENOR_Q: '<';
MENOR_EQ_Q: '<=';
AND:    '&&';
OR:     '||';
MAS_MAS:    '++';
MENOS_MENOS: '--';
COMA:   ',';
DOS_P:  ':';
P_COMA: ';';
LLAVE_A:    '{';
LLAVE_C:    '}';
CORCH_A:    '[';
CORCH_C:    ']';
PAR_A:  '(';
PAR_C:  ')';

//expresiones regulares
ID: [a-zA-Z_][a-zA-Z0-9_]* ;
ENTERO: [0-9]+ ;
DECIMAL: [0-9]+ '.' [0-9]+;
CADENA : '"' [^"\r\n]* '"' ;
CHAR : '\'' [^'\r\n] '\'' ;
COMENTARIO_LINEA : '//' ~[\r\n]* -> channel(HIDDEN) ;
COMENTARIO_BLOQUE : '/*' .*? '*/' -> channel(HIDDEN) ;
WS: [ \t\n\r] -> skip;








