import { Component } from '@angular/core';
import { TextoEntradaService } from '../../servicios/texto-entrada/TextoEntrada.service';
import { EjecutorService } from '../../servicios/envio-entrada/Ejecutor.service';
import { Entrada } from '../../modelos/Entrada/Entrada';
import { Respuesta } from '../../modelos/respuesta/Respuesta';
import { RespuestaService } from '../../servicios/respuesta/Respuesta.service';
import { TraduccionService } from '../../servicios/traduccion/Traduccion.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  sePuedeTraducir: boolean = false

  constructor(
    private textoService: TextoEntradaService,
    private ejecutorService: EjecutorService,
    private respuestaService: RespuestaService,
    private traduccionService: TraduccionService

  ) { }

  public ejecutar() {

    const texto = this.textoService.getContenido();
    this.traduccionService.setIsTraducible(false);

    const entrada: Entrada = {
      texto: texto
    };

    this.ejecutorService.ejecutar(entrada).subscribe({
      next: (respuesta: Respuesta) => {
        this.respuestaService.setRespuesta(respuesta);

        if (!respuesta.errores) {
          this.sePuedeTraducir = true;
        }
      },
      error: (err) => {
        console.error('Error:', err.error?.mensaje ?? err.message);
      }
    });
  }

  public verTraduccion(): void {
    this.traduccionService.setIsTraducible(true);
  }
}
