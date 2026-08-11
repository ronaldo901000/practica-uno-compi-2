import { Injectable } from "@angular/core";
import { BehaviorSubject } from "rxjs";

@Injectable({
    providedIn: 'root'
})

export class TextoEntradaService{

    private contenidoSubject = new BehaviorSubject<string>('');
    contenido$ = this.contenidoSubject.asObservable();

    getContenido(): string{
        return this.contenidoSubject.value;
    }

    setContenido(texto:string){
        this.contenidoSubject.next(texto);
    }
}
