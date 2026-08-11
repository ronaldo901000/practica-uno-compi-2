import { Component, Input } from '@angular/core';
import { AsyncPipe } from '@angular/common';
import { RespuestaService } from '../../servicios/respuesta/Respuesta.service';

@Component({
  selector: 'app-tabla-errores',
  standalone: true,
  imports: [AsyncPipe],
  templateUrl: './tabla-errores.component.html',
  styleUrl: './tabla-errores.component.css'
})
export class TablaErroresComponent {

    constructor(public respuestaService:RespuestaService){}
}
