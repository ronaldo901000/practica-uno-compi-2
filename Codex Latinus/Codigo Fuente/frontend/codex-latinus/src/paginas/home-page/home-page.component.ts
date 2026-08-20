import { Component } from '@angular/core';
import { AsyncPipe } from '@angular/common'; // Requerido para el pipe async
import { FooterComponent } from "../../componentes/footer/footer.component";
import { HeaderComponent } from "../../componentes/header/header.component";
import { EditorComponent } from "../../componentes/editor/editor.component";
import { TablaErroresComponent } from "../../componentes/tabla-errores/tabla-errores.component";
import { TablaSimbolosComponent } from "../../componentes/tabla-simbolos/tabla-simbolos.component";
import { CampoTraduccionComponent } from "../../componentes/campo-traduccion/campo-traduccion.component";
import { TraduccionService } from '../../servicios/traduccion/Traduccion.service';
import { RespuestaService } from '../../servicios/respuesta/Respuesta.service';

@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [
    AsyncPipe,
    FooterComponent,
    HeaderComponent,
    EditorComponent,
    TablaErroresComponent,
    TablaSimbolosComponent,
    CampoTraduccionComponent
  ],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.css'
})
export class HomePageComponent {

  constructor(
    public traduccionService: TraduccionService,
    public respuestaService: RespuestaService
  ) { }

}