package com.almundo.sitio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Dispatcher que se encarga de gestionar las llamadas
 */
public class Gestion {

    private static final Logger log = LoggerFactory.getLogger(Gestion.class);

    private LLamada llamada;

    private Queue<LLamada> llamadas = new ConcurrentLinkedQueue<LLamada>();

    private LinkedBlockingQueue<LLamada> asesores;

    private Integer cantidadAsesores;

    public Gestion() {

    }

    /**
     * Inicia la gestion de las llamadas de acuerdo a los asesores disponibles
     */
    public Gestion(Integer cantidadAsesores) {
        this.setCantidadAsesores(cantidadAsesores);
        this.setAsesores(new LinkedBlockingQueue<LLamada>(cantidadAsesores));
    }

    /**
     * obtener llamada por responsable
     *
     * @param responsable
     * @param tiempo
     * @return
     */
    public void asignarResponsable(Responsable responsable, Long tiempo) {
        switch (responsable) {
            case ASESOR:
                if (asesores.isEmpty())
                    tomarLLamadaEspera(responsable);
                else
                    log.info("Consumer " + responsable + " tomando la tarea: " + asesores.poll() + " - Tiempo: " + tiempo);
                break;
            default:
                log.info("En este momento nustros asesores se encuentran ocupados");
                break;
        }
        if (log.isDebugEnabled()) {
            log.debug("\nPendientes operadores: " + asesores.size());
        }
    }

    /**
     * toma llamada en espera
     * @param responsable
     *
     */
    private void tomarLLamadaEspera(Responsable responsable) {
        if (!llamadas.isEmpty()) {
            log.info("Consumer " + responsable + " tomando la tarea en espera: " + llamadas.poll() + " - Pendientes: "
                    + llamadas.size());
        }
    }

    /**
     * Este metodo se encarga de asignar a los empleados disponibles las tareas
     * (llamadas).
     *
     * @param llamada
     *            the llamada
     */
    public void asignarLlamada(LLamada llamada) {
        asignarLLamadaAsesor(llamada);
    }

    /**
     * asigna llamada al asesor disponible
     *
     * @param llamada
     */
    private void asignarLLamadaAsesor(LLamada llamada) {
        try {
            this.llamada = llamada;
            this.llamada.setResponsable(Responsable.ASESOR);
            asesores.add(llamada);
            log.info("Asignando la tarea: " + llamada.getLlamada() + " a la cola de operadores.");
        } catch (IllegalStateException ex) {
            log.info(
                    "En este momento todos nuestros operadores se encuentran ocupados, Se asigna la tarea a la cola de SUPERVISORES.");
            asignarLLamadaColaEspera(llamada);
        }
    }

    /**
     * agrega llamada a cola de espera
     *
     * @param llamada
     */
    private void asignarLLamadaColaEspera(LLamada llamada) {
        log.info("Asignando la tarea: " + llamada.getLlamada() + " a la cola de ESPERA.");
        this.llamada = llamada;
        this.llamada.setResponsable(Responsable.ESPERA);
        llamadas.add(llamada);

    }


    public LLamada getLlamada() {
        return llamada;
    }

    public void setLlamada(LLamada llamada) {
        this.llamada = llamada;
    }

    public Queue<LLamada> getLlamadas() {
        return llamadas;
    }

    public void setLlamadas(Queue<LLamada> llamadas) {
        this.llamadas = llamadas;
    }

    public LinkedBlockingQueue<LLamada> getAsesores() {
        return asesores;
    }

    public void setAsesores(LinkedBlockingQueue<LLamada> asesores) {
        this.asesores = asesores;
    }

    public Integer getCantidadAsesores() {
        return cantidadAsesores;
    }

    public void setCantidadAsesores(Integer cantidadAsesores) {
        this.cantidadAsesores = cantidadAsesores;
    }
}
