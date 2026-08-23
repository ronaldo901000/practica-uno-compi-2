import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class DescargaService {


    descargarTexto(contenido: string, nombreArchivo: string, tipoMime: string = 'text/plain;charset=utf-8'): void {
        const blob = new Blob([contenido], { type: tipoMime });
        const url = window.URL.createObjectURL(blob);

        const enlace = document.createElement('a');
        enlace.href = url;
        enlace.download = nombreArchivo;

        document.body.appendChild(enlace);
        enlace.click();

        document.body.removeChild(enlace);
        window.URL.revokeObjectURL(url);
    }
}