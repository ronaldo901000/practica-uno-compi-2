import { Component, inject, Input, OnInit } from '@angular/core';
import { DescargaService } from '../../servicios/descarga/Descarga.service';

@Component({
  selector: 'app-campo-traduccion',
  standalone: true,
  templateUrl: './campo-traduccion.component.html',
  styleUrl: './campo-traduccion.component.css'
})
export class CampoTraduccionComponent {
@Input() contenido: string = '';
  private descargaService = inject(DescargaService);

  public descargar(): void {

    if (!this.contenido || this.contenido.trim() === '') return;
    
    this.descargaService.descargarTexto(this.contenido, 'codigo_traducido.pig');
  }
}