package com.almundo.sitio.util;

import java.util.Random;

/**
 * Clase utilitaria
 */
public class Util {

    public static int TIME_MIN = 5000;
    public static int TIME_MAX = 10000;

    /**
     * Este metodo permite asignar aleatoriamente un valor
     * entre 5 y 10 segundos.
     *
     * @return the long
     */
    public static Long asignarTiempoLLamada(){
        Random randomGenerator = new Random();
        int value = randomGenerator.nextInt(TIME_MAX - TIME_MIN + 1) + TIME_MIN;
        return new Long(value);
    }
}
