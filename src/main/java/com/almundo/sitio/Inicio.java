package com.almundo.sitio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Inicio {

    private static final Logger log = LoggerFactory.getLogger(Inicio.class);

    private static final Integer LLAMADAS = 16;

    public static void main(String... args){
        try {
            log.info("Inicio simulación.");
            // definimos un alcance para cada cola
            Gestion gestion = new Gestion(5);
            ExecutorService executor = Executors.newFixedThreadPool(18);
            // Creamos un productor que simula 10 llamados concurrentes
            for(int i = 1 ; i < LLAMADAS; i++){
                executor.execute( new ProductorLLamada(gestion, i));
            }
            // Agregamos el asesor y llamada en espera
            executor.execute(new ConsumidorLLamada(gestion, Responsable.ASESOR));
            executor.shutdown();
            executor.awaitTermination(2, TimeUnit.MINUTES);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
