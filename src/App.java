import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import modelo.*;
import persistencia.PersistenciaEvento;
import hilos.EnvioTicketsThread;


public class App {

    public static void main(String[] args) {

        System.out.println("=== TP1 - Paradigmas de Programación - UTN FRM ===\n");

        // ----- Ejercicio 1: creación de evento -----
        EventoUniversitario evento1 = new EventoUniversitario("EV01", "Jornada de Tecnología", 10000, false);

        // ----- Ejercicio 2/3: estudiantes, sala, actividades, inscripciones -----
        // a. Se registran estudiantes
        Estudiante e1 = new Estudiante("50001", "Ana Gómez");
        Estudiante e2 = new Estudiante("50002", "Luis Pérez");
        Estudiante e3 = new Estudiante("50003", "Marta Ruiz");

        // c. Se asigna una sala al evento
        Sala salaMagna = new Sala(1, "Sala Magna");
        evento1.asignarSala(salaMagna);

        // d. Se crean actividades del evento (Charla y Taller, polimorfismo)
        evento1.crearActividad(1, "Introducción a Java", 30, "Charla", "Ing. Roberto Díaz");
        evento1.crearActividad(2, "Taller de Spring Boot", 20, "Taller", true);

        evento1.crearActividad(3, "Charla de prueba de cupo", 1, "Charla", "Docente de prueba");
        evento1.crearActividad(4, "Curso de Java Avanzado", 25, "Curso");

        Actividad charla = evento1.getActividades().get(0);
        Actividad taller = evento1.getActividades().get(1);

        Actividad charlaPrueba = evento1.getActividades().get(2);
        Actividad curso = evento1.getActividades().get(3);

        List<Charla> charlas = evento1.filtrarActividadesPorTipo(Charla.class);
        List<Taller> talleres = evento1.filtrarActividadesPorTipo(Taller.class);
        List<Curso> cursos = evento1.filtrarActividadesPorTipo(Curso.class);

        double costoTalleres = evento1.calcularCostoMateriales(talleres);
        System.out.println("Costo de materiales de talleres: $" + costoTalleres);
        double costoCharlas = evento1.calcularCostoMateriales(charlas);
        System.out.println("Costo de materiales de charlas: $" + costoCharlas);
        double costoCursos = evento1.calcularCostoMateriales(cursos);
        System.out.println("Costo de materiales de cursos: $" + costoCursos);
        System.out.println("Cantidad de charlas: " + charlas.size());
        System.out.println("Cantidad de talleres: " + talleres.size());
        System.out.println("Cantidad de cursos: " + cursos.size());




        // e. Se inscriben estudiantes en cada actividad
        try {
        charla.inscribir(e1);
        charla.inscribir(e2);

        }
        catch (CupoExcedidoException e) {
            System.out.println("Error al inscribir:" + e.getMessage());
        }
        try {
            taller.inscribir(e2);
            taller.inscribir(e3);
        }
        catch (CupoExcedidoException e) {
            System.out.println("Error al inscribir: " + e.getMessage());
        }

        try {
            charlaPrueba.inscribir(e1);
            charlaPrueba.inscribir(e2);
        }
        catch (CupoExcedidoException e) {
            System.out.println("Error controlado de cupo: " + e.getMessage());
        }

        try {
            curso.inscribir(e1);
        }
        catch (CupoExcedidoException e) {
            System.out.println("Error al inscribir en el curso: " + e.getMessage());

        }
        List<Inscripcion> todasLasInscripciones = new ArrayList<>();
        todasLasInscripciones.addAll(charla.getInscripciones());
        todasLasInscripciones.addAll(taller.getInscripciones());
        todasLasInscripciones.addAll(charlaPrueba.getInscripciones());
        todasLasInscripciones.addAll(curso.getInscripciones());

        todasLasInscripciones.get(0).confirmar();
        todasLasInscripciones.get(1).confirmar();
        todasLasInscripciones.get(2).confirmar();

        EnvioTicketsThread hiloTickets = new EnvioTicketsThread(todasLasInscripciones);
        hiloTickets.start();

        System.out.println("\n[HILO PRINCIPAL] Continúo mostrando información del evento...");
        evento1.mostrarDatos();

        for (Actividad actividad : evento1.getActividades()) {
            actividad.mostrarInscripciones();
        }
        if (taller instanceof Certificable) {
            Certificable certificable = (Certificable) taller;
            certificable.emitirCertificado(e2);
            certificable.emitirCertificado(e2);
            evento1.agregarCertificado("Luis Pérez - Taller de Spring Boot");

        }

        if (curso instanceof Certificable) {
            Certificable certificableCurso = (Certificable) curso;
            certificableCurso.emitirCertificado(e1);
            evento1.agregarCertificado("Ana Gómez - Curso de Java Avanzado");
        }
        evento1.mostrarCertificados();



        try {
            PersistenciaEvento.guardarEvento(evento1, "evento.dat");

        }
        catch (IOException e) {
            System.out.println("Error al guardar el evento: " + e.getMessage());
}

            try  {
                EventoUniversitario eventoRecuperado = PersistenciaEvento.leerEvento("evento.dat");
                System.out.println("\n--- Evento recuperado desde archivo ---");
                eventoRecuperado.mostrarDatos();

            }

            catch (IOException e) {
                System.out.println("Error al leer el archivo del evento: " + e.getMessage());
            }

            catch (ClassNotFoundException e)  {
                System.out.println("Error al recontruir el evento: " + e.getMessage());

            }
            finally {
                System.out.println("Finalizó el proceso de lectura del evento.");
            }





        // Constructor de copia (Ejercicio 1), creado ahora para demostrar
        // que también copia la sala asignada y la lista de actividades.
        EventoUniversitario copiaEvento1 = new EventoUniversitario(evento1);

        // f. Resumen del evento y de sus actividades (identificación polimórfica)
        evento1.mostrarDatos();
        System.out.println();
        for (Actividad a : evento1.getActividades()) {
            a.mostrarInscripciones();
        }

        System.out.println("\n--- Copia de evento1 (constructor de copia, Ejercicio 1) ---");
        copiaEvento1.mostrarDatos();

        // g. Total de eventos creados
        System.out.println("\nTotal de eventos creados: " + EventoUniversitario.getCantidadEventos());
    }
}