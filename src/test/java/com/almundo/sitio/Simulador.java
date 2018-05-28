package com.almundo.sitio;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class Simulador.
 */
public class Simulador {

    private List<LLamada> lLamadas = null;
    private Gestion gestion = new Gestion(5);

    /**
     * creamos la simulacion de llamadas
     */
    @Before
    public void inicializador() {
        this.lLamadas = new ArrayList<LLamada>();
        for (int i = 1; i < 11; i++) {
            lLamadas.add(new LLamada(i));
        }
    }

    /**
     *
     */
    @Test
    public void iniciar() {
        // vaciamos las colas
        this.gestion.getAsesores().clear();
        this.gestion.getLlamadas().clear();

        for(LLamada lLamada : this.lLamadas){
            this.gestion.asignarLlamada(lLamada);
        }

        //validamos el estado de la gestion de las llamadas
        Assert.assertEquals(5, gestion.getAsesores().size());
        Assert.assertEquals(5, gestion.getLlamadas().size());
    }


}