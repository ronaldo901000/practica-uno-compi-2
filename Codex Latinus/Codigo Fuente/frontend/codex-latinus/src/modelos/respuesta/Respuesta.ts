import { ErrorAnalisis } from "../error-analisis/ErrorAnalisis";
import { Simbolo } from "../simbolo/Simbolo";
import { SimulacionParseo } from "../simulacion/SimulacionParseo";

export interface Respuesta {
    hayErrores: boolean;
    errores: ErrorAnalisis[];
    tablaSimbolos:Simbolo[];
    traduccionPigLatin:string;
    archivoDot: string;
    simulacionParseo: SimulacionParseo;
}