import { Component, ElementRef, ViewChild, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import * as d3 from 'd3';
import 'd3-graphviz';
import { RespuestaService } from '../../servicios/respuesta/Respuesta.service';

@Component({
  selector: 'app-seccion-ast',
  standalone: true,
  imports: [],
  templateUrl: './seccion-ast.component.html',
  styleUrl: './seccion-ast.component.css'
})
export class SeccionAstComponent implements OnInit, OnDestroy {
  @ViewChild('graphContainer') graphContainer!: ElementRef;

  tieneArbol = false;

  private subscripcion?: Subscription;

  constructor(private respuestaService: RespuestaService) { }

  ngOnInit(): void {
    this.subscripcion = this.respuestaService.respuesta$.subscribe((respuesta) => {
      if (respuesta && !respuesta.hayErrores && respuesta.archivoDot) {
        this.tieneArbol = true;
        this.renderizarDot(respuesta.archivoDot);
      } else {
        this.tieneArbol = false;
      }
    });
  }

  ngOnDestroy(): void {
    this.subscripcion?.unsubscribe();
  }

  private renderizarDot(dot: string): void {
    if (!dot.trim() || !this.graphContainer) return;

    try {
      (d3.select(this.graphContainer.nativeElement) as any).graphviz().renderDot(dot);
    } catch (error) {
      console.error('Error al renderizar el árbol sintáctico:', error);
    }
  }
}