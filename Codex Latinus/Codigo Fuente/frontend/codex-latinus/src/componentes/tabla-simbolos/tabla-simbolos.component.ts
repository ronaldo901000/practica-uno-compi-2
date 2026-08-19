import { Component } from '@angular/core';
import { AsyncPipe } from '@angular/common';
import { RespuestaService } from '../../servicios/respuesta/Respuesta.service';

@Component({
  selector: 'app-tabla-simbolos',
  standalone: true,
  imports: [AsyncPipe],
  templateUrl: './tabla-simbolos.component.html',
  styleUrl: './tabla-simbolos.component.css'
})
export class TablaSimbolosComponent {
 constructor(public respuestaService:RespuestaService){}

}
