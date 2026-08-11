import { Injectable } from "@angular/core";
import { BehaviorSubject } from "rxjs";
import { Respuesta } from "../../modelos/respuesta/Respuesta";

@Injectable({
    providedIn: 'root'
})
export class RespuestaService {

    private respuestaSubject = new BehaviorSubject<Respuesta | null>(null);
    respuesta$ = this.respuestaSubject.asObservable();

    getRespuesta(): Respuesta | null {
        return this.respuestaSubject.value;
    }

    setRespuesta(respuesta: Respuesta) {
        this.respuestaSubject.next(respuesta);
    }
}