package modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Inscripcion implements Serializable  {

    private LocalDate fecha;
    private String estado;
    private Estudiante estudiante;
    private Actividad actividad;

    public Inscripcion(Estudiante estudiante, Actividad actividad, String estado) {
        this.estudiante = estudiante;
        this.actividad = actividad;
        this.estado = estado;
        this.fecha = LocalDate.now();

    }

    public LocalDate getFecha() {return fecha;
    }

    public String getEstado() {return estado;

    }

    public Estudiante getEstudiante() {return estudiante;
    }

    public Actividad getActividad() {return actividad;
    }
    public void confirmar() {
        this.estado = "Pendiente";
    }
    public class TicketDeAcceso {

        private String codigo;
        public TicketDeAcceso(String codigo) {
            this.codigo = codigo;
            }

            public String getCodigo() {
            return codigo;
            }

            public void mostrarTicket() {

                System.out.println("Ticket: " + codigo);

                }

            }


        public TicketDeAcceso generarTicket() {
        if (estado.equalsIgnoreCase("Confirmada")) {
            return new TicketDeAcceso("TICKET-" + estudiante.getLegajo());


        }

        return null;


        }


    }
