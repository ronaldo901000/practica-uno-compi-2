import { Component, effect } from '@angular/core';
import { TextoEntradaService } from '../../servicios/texto-entrada/TextoEntrada.service';

@Component({
  selector: 'app-editor',
  standalone: true,
  imports: [],
  templateUrl: './editor.component.html',
  styleUrl: './editor.component.css'
})
export class EditorComponent {

  contenido: string = '';
  lineas: number[] = [1];

  constructor(private textoService:TextoEntradaService) {
    effect(() => {
        this.actualizarLineas();
    });
  }

  actualizarLineas() {
    const total = this.contenido.split('\n').length;
    this.lineas = Array.from({ length: total }, (_, i) => i + 1);
  }

  sincronizarScroll(event: any) {
    const contadorLineas = document.getElementById('line-counter');
    if (contadorLineas) {
      contadorLineas.scrollTop = event.target.scrollTop;
    }
  }

  onContenidoCambia(nuevoContenido: string) {
    this.contenido = nuevoContenido;
    this.actualizarLineas();
  }
  onTextoChange(entrada:string){
    this.textoService.setContenido(entrada);
  }

}
