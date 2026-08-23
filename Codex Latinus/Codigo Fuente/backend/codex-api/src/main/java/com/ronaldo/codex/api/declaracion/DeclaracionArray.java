package com.ronaldo.codex.api.declaracion;

import com.ronaldo.codex.api.dto.entrada.error.analisis.ErrorSemantico;
import com.ronaldo.codex.api.enums.Categoria;
import com.ronaldo.codex.api.enums.Tipo;
import com.ronaldo.codex.api.expresion.Expresion;
import com.ronaldo.codex.api.semantica.Llave;
import com.ronaldo.codex.api.semantica.Semantica;
import com.ronaldo.codex.api.semantica.Simbolo;
import com.ronaldo.codex.api.services.verificacion.VerificadorTipos;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ronaldo
 */
public class DeclaracionArray extends Declaracion {
    
    private int tamaño;
    private List<Expresion> valores;
    
    public DeclaracionArray(int tamaño, String id, String tipoString, int fila, int columna) {
        super(id, tipoString, fila, columna);
        this.tamaño = tamaño;
        this.valores = new ArrayList<>();
    }
    
    public DeclaracionArray(int fila, int columna) {
        super(fila, columna);
        this.valores = new ArrayList<>();
        
    }
    
    @Override
    public void realizarTraduccion(StringBuffer sb) {
        sb.append("eriessay ");
        sb.append(traductor.traducir(id));
        sb.append(" ");
        sb.append("[").append(tamaño).append("]");
        sb.append(" : ");
        sb.append(traductor.traducir(tipo.getText())).append(" ");
        
        if (!valores.isEmpty()) {
            sb.append("{");
            for (int i = 0; i < valores.size(); i++) {
                Expresion e = valores.get(i);
                
                e.realizarTraduccion(sb);
                
                if (i < valores.size() - 1) {
                    sb.append(", ");
                }
                
            }
            sb.append("}");
        }
        sb.append("; ");
        
    }
    
    @Override
    public void verificarSemantica(Semantica semantica) throws Exception {
        String ambitoActual = semantica.getAmbitoActual();

        //Validar si ya existe en el ambito actual
        if (semantica.getTablaSimbolos().existeEnAmbitoActual(this.id, ambitoActual)) {
            semantica.getErrores().add(new ErrorSemantico(
                    fila,
                    columna,
                    id,
                    "El arreglo '" + this.id + "' ya existe en el ambito: " + ambitoActual
            ));
        }
        
        Simbolo nuevoSimbolo = new Simbolo();
        nuevoSimbolo.setLlave(new Llave(this.id, ambitoActual));
        nuevoSimbolo.setCategoria(Categoria.ARRAY);
        nuevoSimbolo.setIdTipo(this.tipo.ordinal());
        nuevoSimbolo.setTamañoArray(this.tamaño);
        semantica.getTablaSimbolos().insertar(nuevoSimbolo);

        //Validar el tamaño del arreglo
        if (this.tamaño <= 0) {
            semantica.getErrores().add(new ErrorSemantico(
                    fila,
                    columna,
                    id,
                    "El tamaño del arreglo '" + this.id + "' debe ser mayor a 0."
            ));
        }

        //Validar los valores iniciales si fueron proporcionados
        if (this.valores != null && !this.valores.isEmpty()) {
            
            if (this.valores.size() > this.tamaño) {
                semantica.getErrores().add(new ErrorSemantico(
                        fila,
                        columna,
                        id,
                        "Se proporcionaron " + this.valores.size() + " elementos, pero el tamaño maximo es " + this.tamaño
                ));
            }
            
            for (Expresion exp : this.valores) {
                if (exp != null) {
                    exp.verificarSemantica(semantica);
                    Tipo tipoElemento = exp.getTipoResultado();
                    
                    if (tipoElemento != Tipo.ERROR && tipoElemento != this.tipo) {
                        semantica.getErrores().add(new ErrorSemantico(
                                fila,
                                columna,
                                id,
                                "Tipo incompatible en el arreglo '" + this.id + "'. Se esperaba " + this.tipo + " pero se encontro " + tipoElemento
                        ));
                    }
                }
            }
        }
    }
    
    public void agregarValor(Expresion valor) {
        this.valores.add(valor);
    }
    
    public void agregarTipo(String tipoString) {
        VerificadorTipos verificador = new VerificadorTipos();
        this.tipo = verificador.verificar(tipoString);
    }
    
    public int getTamaño() {
        return tamaño;
    }
    
    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }
    
    public List<Expresion> getValores() {
        return valores;
    }
    
    public void setValores(List<Expresion> valores) {
        this.valores = valores;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public Tipo getTipo() {
        return tipo;
    }
    
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
    
}
