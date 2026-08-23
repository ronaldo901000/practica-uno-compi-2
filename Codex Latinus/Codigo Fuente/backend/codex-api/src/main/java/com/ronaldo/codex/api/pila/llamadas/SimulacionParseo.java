package com.ronaldo.codex.api.pila.llamadas;

import java.util.List;

/**
 *
 * @author ronaldo
 */
public class SimulacionParseo {

    private List<PasoParseo> pasos;

    public SimulacionParseo(List<PasoParseo> pasos) {
        this.pasos = pasos;
    }

    
    
    public List<PasoParseo> getPasos() {
        return pasos;
    }

    public void setPasos(List<PasoParseo> pasos) {
        this.pasos = pasos;
    }

}
