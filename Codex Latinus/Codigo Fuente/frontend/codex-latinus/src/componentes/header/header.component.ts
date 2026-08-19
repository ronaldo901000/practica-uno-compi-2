import { Component } from '@angular/core';
import { TextoEntradaService } from '../../servicios/texto-entrada/TextoEntrada.service';
import { EjecutorService } from '../../servicios/envio-entrada/Ejecutor.service';
import { Entrada } from '../../modelos/Entrada/Entrada';
import { Respuesta } from '../../modelos/respuesta/Respuesta';
import { RespuestaService } from '../../servicios/respuesta/Respuesta.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {

  constructor(
    private textoService: TextoEntradaService,
    private ejecutorService: EjecutorService,
    private respuestaService: RespuestaService
  ) { }

  public ejecutar() {
    const texto = this.textoService.getContenido();

    const entrada: Entrada = {
      texto: texto
    };

    this.ejecutorService.ejecutar(entrada).subscribe({
      next: (respuesta: Respuesta) => {
        this.respuestaService.setRespuesta(respuesta);
        console.log('Contiene Errores :', respuesta.hayErrores);
      },
      error: (err) => {
        console.log('HAY ERRORES');
        console.error('Error:', err.error?.mensaje ?? err.message);
      }
    });
  }
}
