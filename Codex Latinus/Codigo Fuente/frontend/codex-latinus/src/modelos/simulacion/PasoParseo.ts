import { ElementoPila } from "./ElementoPila";

export interface PasoParseo {
    numeroPaso: number;
    pila: ElementoPila[];
    accion: string;
    detalleAccion: string;
}