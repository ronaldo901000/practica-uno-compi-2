import { Component, inject } from '@angular/core';
import { TextoEntradaService } from '../../servicios/texto-entrada/TextoEntrada.service';
import { EjecutorService } from '../../servicios/envio-entrada/Ejecutor.service';
import { Entrada } from '../../modelos/Entrada/Entrada';
import { Respuesta } from '../../modelos/respuesta/Respuesta';
import { RespuestaService } from '../../servicios/respuesta/Respuesta.service';
import { TraduccionService } from '../../servicios/traduccion/Traduccion.service';
import { EntradaService } from '../../servicios/entrada/Entrada.service';
import { DescargaService } from '../../servicios/descarga/Descarga.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  sePuedeTraducir: boolean = false;

  contenidoArchivo: string = '';
  nombreArchivo: string = '';

  private descargaService = inject(DescargaService);

  constructor(
    private textoService: TextoEntradaService,
    private ejecutorService: EjecutorService,
    private respuestaService: RespuestaService,
    private traduccionService: TraduccionService,
    private entradaServices: EntradaService
  ) { }

  public ejecutar() {
    const texto = this.textoService.getContenido();
    this.traduccionService.setIsTraducible(false);

    const entrada: Entrada = {
      texto: texto
    };

    if (!entrada.texto || entrada.texto.trim() === '') {
      return;
    }

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

  public descargar(): void {
    const contenido = this.textoService.getContenido();

    if (!contenido || contenido.trim() === '') return;

    this.descargaService.descargarTexto(contenido, 'codigoLatin.latin');
  }

  onArchivoSeleccionado(event: Event): void {
    const input = event.target as HTMLInputElement;

    if (!input.files || input.files.length === 0) {
      return;
    }

    const archivo = input.files[0];

    if (!archivo.name.toLowerCase().endsWith('.lat')) {
      alert('Por favor selecciona un archivo con extensión .lat');
      input.value = '';
      return;
    }

    this.nombreArchivo = archivo.name;

    const lector = new FileReader();

    lector.onload = () => {
      this.contenidoArchivo = lector.result as string;

      this.entradaServices.setRespuesta(this.contenidoArchivo);
      this.textoService.setContenido(this.contenidoArchivo);
    };

    lector.onerror = () => {
      console.error('Error al leer el archivo');
      alert('Ocurrió un error al leer el archivo.');
    };

    lector.readAsText(archivo);

    input.value = '';
  }
}