import { Component } from '@angular/core';
import { FooterComponent } from "../../componentes/footer/footer.component";
import { HeaderComponent } from "../../componentes/header/header.component";
import { EditorComponent } from "../../componentes/editor/editor.component";
import { TablaErroresComponent } from "../../componentes/tabla-errores/tabla-errores.component";
import { RespuestaService } from '../../servicios/respuesta/Respuesta.service';
import { TablaSimbolosComponent } from "../../componentes/tabla-simbolos/tabla-simbolos.component";

@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [FooterComponent, HeaderComponent, EditorComponent, TablaErroresComponent, TablaSimbolosComponent],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.css'
})
export class HomePageComponent {


}
