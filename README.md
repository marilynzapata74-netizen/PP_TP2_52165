# PP_TP2_52165

## Trabajo Práctico N°2 - Programación Orientada a Objetos en Java

**Cátedra:** Paradigmas de Programación - UTN Facultad Regional Mendoza  
**Unidad 2:** Organización, reutilización y recursos avanzados en POO

## Descripción del proyecto

Sistema para administrar eventos universitarios, sus actividades, estudiantes e inscripciones, aplicando conceptos avanzados de Programación Orientada a Objetos en Java.

## Ejercicio 1 - Excepciones y persistencia

Se implementó una excepción personalizada para controlar el cupo máximo de las actividades.

También se incorporó persistencia de objetos mediante serialización, permitiendo guardar un evento universitario completo en un archivo y recuperarlo posteriormente.

## Ejercicio 2 - Interfaces y polimorfismo

Se implementó la interfaz `Certificable`.

Las clases `Taller` y `Curso` implementan esta interfaz y permiten emitir certificados a los estudiantes que completan dichas actividades.

## Ejercicio 3 - Genéricos y comodines

Se implementaron métodos genéricos para filtrar las actividades de un evento según su tipo concreto (`Charla`, `Taller` y `Curso`).

También se utilizó un comodín acotado para calcular el costo de materiales de listas de actividades.

## Ejercicio 4 - Clases anidadas e hilos

Se implementó `TicketDeAcceso` como clase anidada miembro de `Inscripcion`.

Las inscripciones inicialmente poseen estado `Pendiente` y algunas son confirmadas. Los tickets se generan únicamente para las inscripciones confirmadas.

La clase `EnvioTicketsThread`, ubicada en el paquete `hilos`, ejecuta concurrentemente el envío de tickets.

Mientras este hilo realiza el envío, el hilo principal continúa mostrando los datos del evento, sus actividades y sus inscripciones, evidenciando en consola dos flujos de ejecución concurrentes.

## Estructura principal

- `modelo`: clases del modelo de dominio.
- `persistencia`: persistencia y recuperación de eventos.
- `hilos`: procesamiento concurrente del envío de tickets.
- `App`: clase principal para ejecutar y demostrar los ejercicios.

## Evidencia de ejecución

El repositorio incluye una captura de consola donde se observa la ejecución concurrente del hilo principal y del hilo encargado del envío de tickets.
