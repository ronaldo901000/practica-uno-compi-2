import { ErrorAnalisis } from "../error-analisis/ErrorAnalisis";
import { Simbolo } from "../simbolo/Simbolo";

export interface Respuesta {
    hayErrores: boolean;
    errores: ErrorAnalisis[];
    tablaSimbolos:Simbolo[];
}