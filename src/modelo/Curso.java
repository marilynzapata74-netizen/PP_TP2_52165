package modelo;

public class Curso extends Actividad implements Certificable {

    public Curso(int id, String titulo, int cupoMaximo) {
        super(id, titulo, cupoMaximo);
    }

    @Override
    public double calcularCostoMateriales() {
        return 0;
    }

    @Override
    public String getTipo() {
        return "Curso";
    }

    @Override
    public void emitirCertificado(Estudiante estudiante) {
        System.out.println("Certificado emitido para " + estudiante.getNombre() + " por completar el curso: " + getTitulo());

    }
}
