# almundo

Algunas consideraciones de la solución:
- Manejo colas, limitadas y priorizadas.
- Permitir el acceso concurrente para ambas operaciones.
- Definición de cola comun del tipo "ConcurrentLinkedQueue" sin un límite apara asignar las tareas (llamadas) en ESPERA,
  es decir, esto para las llamadas que no puedan ser atendidas.
- Asignación tiempo aproximado al solicitado entre 5 - 10 seg.

## Compilar

    mvn clean install


## Inicio

Run> Inicio.java. 

## Pruebas 

Se genero una clase para la realización de los test Simulador.java con los siguiente escenario:

* Caso :
Permite la ejecucion concurrente de 10 llamadas (LLamada). Estas son atendidas por los empleados (ASESOR). 
En la medida que los empleados van quedando asignados e ingresan llamadas, las asignaciones van cambiando con base en el 
Responsable, si no hay disponibilidad de (ASESOR) entonces se debe enviar a ESPERA.
