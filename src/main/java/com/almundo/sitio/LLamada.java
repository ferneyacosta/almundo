package com.almundo.sitio;

/**
 * llamada en ejecucion la cual indica numero de llamada y responsable
 */
public class LLamada {

    private Integer llamada;

    private Responsable responsable;

    public LLamada() {

    }

    public LLamada(Integer llamada) {
        this.llamada = llamada;
    }

    public LLamada(Integer llamada, Responsable responsable) {
        this.setLlamada(llamada);
        this.setResponsable(responsable);
    }

    public Integer getLlamada() {
        return llamada;
    }

    public void setLlamada(Integer llamada) {
        this.llamada = llamada;
    }

    public Responsable getResponsable() {
        return responsable;
    }

    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }
}
