package com.almundo.sitio;

public class ProductorLLamada implements Runnable{

    private Gestion gestion;
    Integer llamada = 0;
    Integer total = 0;
    boolean cargarColas = false;

    public ProductorLLamada(){

    }

    public ProductorLLamada(Gestion gestion, boolean cargarColas, int total) {
        this.gestion = gestion;
        this.total = total;
        this.cargarColas = cargarColas;
    }

    public ProductorLLamada(Gestion gestion, Integer llamada) {
        this.gestion = gestion;
        this.llamada = llamada;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Runnable#run()
     */
    public synchronized void run() {
        if (!this.cargarColas) {
            this.gestion.asignarLlamada(new LLamada(this.llamada));
        } else {
            for (int i = 0; i < total; i++) {
                this.gestion.asignarLlamada(new LLamada(i));
            }
        }

    }
}
