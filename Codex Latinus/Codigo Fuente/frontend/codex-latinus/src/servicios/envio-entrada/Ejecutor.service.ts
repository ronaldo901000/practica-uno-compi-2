import { Injectable } from "@angular/core";
import { Conexion } from "../conexion/Conexion";
import { HttpClient } from "@angular/common/http";
import { Entrada } from "../../modelos/Entrada/Entrada";
import  {Respuesta } from "../../modelos/respuesta/Respuesta";
import { Observable } from "rxjs";

@Injectable({ providedIn: 'root' })

export class EjecutorService {

    private conexion = new Conexion;

    constructor(private http: HttpClient) { }

    public ejecutar(entrada: Entrada):Observable<Respuesta> {
        return this.http.post<Respuesta>(`${this.conexion.getConexionUrl()}analisis`,  entrada)
    }
}