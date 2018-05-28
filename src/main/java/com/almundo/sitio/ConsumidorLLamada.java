package com.almundo.sitio;

import com.almundo.sitio.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * clase que se encarga de asignar las llamadas
 */
public class ConsumidorLLamada implements Runnable{
    private static final Logger log = LoggerFactory.getLogger(ConsumidorLLamada.class);

    private Gestion gestion;

    private Responsable responsable;

    public ConsumidorLLamada(){

    }

    public ConsumidorLLamada(Gestion gestion, Responsable responsable) {
        this.setGestion(gestion);
        this.setResponsable(responsable);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Runnable#run()
     */
    public synchronized void run() {
        try {
            Long tiempo = Util.asignarTiempoLLamada();
            getGestion().asignarResponsable(this.getResponsable(), tiempo);
            Thread.sleep(tiempo);
        } catch (InterruptedException ex) {
            log.error(ex.getMessage());
        }
    }

    public Gestion getGestion() {
        return gestion;
    }

    public void setGestion(Gestion gestion) {
        this.gestion = gestion;
    }

    public Responsable getResponsable() {
        return responsable;
    }

    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }
}
