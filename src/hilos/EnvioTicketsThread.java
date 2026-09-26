package hilos;
import modelo.Inscripcion;
import java.util.List;


public class EnvioTicketsThread extends Thread {
    private List<Inscripcion> inscripciones;


    public EnvioTicketsThread (List<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }
    @Override
    public void run() {
        for (Inscripcion inscripcion : inscripciones) {
            Inscripcion.TicketDeAcceso ticket = inscripcion.generarTicket();
            if (ticket != null) {
                System.out.println("[HILO TICKETS] Enviando ticket...");
                ticket.mostrarTicket();

            }

        }

    }

}
