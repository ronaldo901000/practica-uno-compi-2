import { Component, OnDestroy, OnInit } from '@angular/core';
import { CommonModule, NgClass } from '@angular/common';
import { Subscription } from 'rxjs';
import { PasoParseo } from '../../modelos/simulacion/PasoParseo';
import { ElementoPila } from '../../modelos/simulacion/ElementoPila';
import { RespuestaService } from '../../servicios/respuesta/Respuesta.service';

@Component({
  selector: 'app-simulador-pila',
  standalone: true,
  imports: [CommonModule, NgClass],
  templateUrl: './simulador-pila.component.html',
  styleUrl: './simulador-pila.component.css'
})
export class SimuladorPilaComponent implements OnInit, OnDestroy {

  pasos: PasoParseo[] = [];
  pasoActual: number = 0;

  private suscripcion?: Subscription;

  constructor(private respuestaService: RespuestaService) { }

  ngOnInit(): void {
    this.suscripcion = this.respuestaService.respuesta$.subscribe(respuesta => {
      if (respuesta?.simulacionParseo?.pasos) {
        this.pasos = respuesta.simulacionParseo.pasos;
      } else {
        this.pasos = [];
      }
      this.pasoActual = 0;
    });
  }

  ngOnDestroy(): void {
    this.suscripcion?.unsubscribe();
  }

  get pilaActual(): ElementoPila[] {
    const paso = this.pasos[this.pasoActual];
    if (!paso) return [];

    if (paso.accion === 'accept') {
      return [{ simbolo: 'pila vacía', tipo: 'vacia' }];
    }

    return paso.pila ?? [];
  }
  get logAcumulado(): string[] {
    return this.pasos
      .slice(0, this.pasoActual + 1)
      .map(p => p.detalleAccion);
  }


  siguiente(): void {
    if (this.pasoActual < this.pasos.length - 1) {
      this.pasoActual++;
    }
  }

  anterior(): void {
    if (this.pasoActual > 0) {
      this.pasoActual--;
    }
  }

  irAlPrimero(): void {
    this.pasoActual = 0;
  }

  irAlUltimo(): void {
    this.pasoActual = this.pasos.length - 1;
  }
}