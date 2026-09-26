package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;


public abstract class Actividad implements Serializable {

    private int id;
    private String titulo;
    private int cupoMaximo;
    public static final int CUPO_MINIMO = 5;

    private List<Inscripcion> inscripciones;

    public Actividad(int id, String titulo, int cupoMaximo) {
        this.id = id;
        this.titulo = titulo;
        this.cupoMaximo = cupoMaximo;
        this.inscripciones = new ArrayList<>();
    }

    public Inscripcion inscribir(Estudiante estudiante) throws CupoExcedidoException  {
        if (inscripciones.size() >= cupoMaximo) {
            throw new CupoExcedidoException("No se pudo inscribir a " + estudiante.getNombre()
                    + ": cupo completo en \"" + titulo + "\"");

        }
        Inscripcion inscripcion = new Inscripcion(estudiante, this, "Confirmada");
        inscripciones.add(inscripcion);
        return inscripcion;
    }

    public void mostrarInscripciones() {
        System.out.println("Inscripciones en \"" + titulo + "\" (" + inscripciones.size() + "):");
        for (Inscripcion i : inscripciones) {
            System.out.println("   - " + i.getEstudiante().getNombre()
                    + " | legajo: " + i.getEstudiante().getLegajo()
                    + " | fecha: " + i.getFecha()
                    + " | estado: " + i.getEstado());
        }
    }

    public final void mostrarIdentificacion() {
        System.out.println("[" + getTipo() + "] #" + id + " - " + titulo
                + " | cupo máximo: " + cupoMaximo
                + " | costo materiales: $" + calcularCostoMateriales());
    }

    public abstract double calcularCostoMateriales();

    public abstract String getTipo();

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }
}