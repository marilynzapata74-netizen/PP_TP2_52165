package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EventoUniversitario implements Serializable {

    private final String id;
    private String titulo;
    private double costoBase;
    private boolean gratuito;
    private static int cantidadEventos = 0;

    private Sala sala;
    private List<Actividad> actividades;
    private List<String> certificadosEmitidos;

    public EventoUniversitario(String id, String titulo, double costoBase, boolean gratuito) {
        this.id = id;
        this.titulo = titulo;
        this.costoBase = costoBase;
        this.gratuito = gratuito;
        this.actividades = new ArrayList<>();
        this.certificadosEmitidos = new ArrayList<>();

        cantidadEventos++;
    }

    public EventoUniversitario(EventoUniversitario otro) {
        this.id = otro.id;
        this.titulo = otro.titulo;
        this.costoBase = otro.costoBase;
        this.gratuito = otro.gratuito;
        this.sala = otro.sala;
        this.actividades = new ArrayList<>(otro.actividades);
        this.certificadosEmitidos = new ArrayList<>(otro.certificadosEmitidos);
        cantidadEventos++;
    }

    public double calcularCostoEstimado() {
        if (gratuito) {
            return 0;
        }
        double costoActividades = 0;
        for (Actividad a : actividades) {
            costoActividades += a.calcularCostoMateriales();
        }
        return (costoBase + costoActividades) * 1.21;
    }

    public void asignarSala(Sala sala) {
        this.sala = sala;
    }

    public void crearActividad(int id, String titulo, int cupo, String tipo, String disertante) {
        if (!tipo.equalsIgnoreCase("Charla")) {
            throw new IllegalArgumentException("Este método crea Charlas. Tipo recibido: " + tipo);
        }
        actividades.add(new Charla(id, titulo, cupo, disertante));
    }

    public void crearActividad(int id, String titulo, int cupo, String tipo, boolean requiereNotebook) {
        if (!tipo.equalsIgnoreCase("Taller")) {
            throw new IllegalArgumentException("Este método crea Talleres. Tipo recibido: " + tipo);

        }


        actividades.add(new Taller(id, titulo, cupo, requiereNotebook));
    }
    public void crearActividad(int id, String titulo, int cupo, String tipo) {
      if (! tipo.equalsIgnoreCase("Curso")) {
          throw new IllegalArgumentException("Este método crea Curso. Tipo recibido: " + tipo);
      }
      actividades.add(new Curso(id, titulo, cupo));



    }
    public void agregarCertificado(String certificado) {
        certificadosEmitidos.add(certificado);
        }

        public void mostrarCertificados()  {
            System.out.println("\n--- Certificados emitidos ---");

            for (String certificado : certificadosEmitidos) {
                System.out.println(certificado);

            }
        }
        public <T extends Actividad> List<T> filtrarActividadesPorTipo(Class<T> tipo) {
        List<T> resultado = new ArrayList<>();
        for (Actividad actividad : actividades) {
            if (tipo.isInstance(actividad)) {
                resultado.add(tipo.cast(actividad));


            }
        }

        return resultado;






}
public double calcularCostoMateriales(List<? extends Actividad> actividades) {
        double total = 0;

        for (Actividad actividad : actividades) {
            total += actividad.calcularCostoMateriales();

        }
        return total;



}



    public void mostrarDatos() {
        System.out.println("Evento: " + titulo + " (ID: " + id + ")");
        System.out.println("Costo base: $" + costoBase);
        System.out.println("Gratuito: " + (gratuito ? "Sí" : "No"));
        System.out.println("Costo estimado total: $" + calcularCostoEstimado());
        System.out.println("Sala asignada: " + (sala != null ? sala.getNombre() : "(sin asignar)"));
        System.out.println("Actividades (" + actividades.size() + "):");
        for (Actividad a : actividades) {
            a.mostrarIdentificacion();
        }
    }

    public static int getCantidadEventos() {
        return cantidadEventos;
    }

    public String getId() {
        return id;
    }

    public List<Actividad> getActividades() {
        return actividades;
    }

    public Sala getSala() {
        return sala;
    }
}