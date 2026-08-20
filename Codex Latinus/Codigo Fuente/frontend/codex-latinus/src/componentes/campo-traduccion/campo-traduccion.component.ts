import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-campo-traduccion',
  standalone: true,
  templateUrl: './campo-traduccion.component.html',
  styleUrl: './campo-traduccion.component.css'
})
export class CampoTraduccionComponent {
  @Input() contenido: string = '';


  public descargar():void{

  }
}