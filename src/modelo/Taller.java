package modelo;

public class Taller extends Actividad implements Certificable {

    private boolean requiereNotebook;

    public Taller(int id, String titulo, int cupoMaximo, boolean requiereNotebook) {
        super(id, titulo, cupoMaximo);
        this.requiereNotebook = requiereNotebook;
    }

    @Override
    public void emitirCertificado(Estudiante estudiante) {
        System.out.println("Certificado emitido para " + estudiante.getNombre() + " por completar el taller: " + getTitulo());
    }

    @Override
    public double calcularCostoMateriales() {
        return requiereNotebook ? 5000 : 2000;
    }

    @Override
    public String getTipo() {
        return "Taller";
    }

    public boolean isRequiereNotebook() {
        return requiereNotebook;
    }
}