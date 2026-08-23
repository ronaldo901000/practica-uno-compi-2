import { Injectable, signal } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EntradaService {

  private respuesta = signal<string>('');

  respuesta$ = this.respuesta.asReadonly();

  setRespuesta(valor: string): void {
    this.respuesta.set(valor);
  }
}