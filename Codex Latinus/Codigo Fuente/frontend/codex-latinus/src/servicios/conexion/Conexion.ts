export class Conexion{

    public readonly CONEXION_URL = 'http://localhost:8080/codex-api/api/v1/';

    public getConexionUrl():string{
        return this.CONEXION_URL;
    }
}