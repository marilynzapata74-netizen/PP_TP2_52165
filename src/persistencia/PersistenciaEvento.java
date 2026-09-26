package persistencia;
import modelo.EventoUniversitario;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;


public class PersistenciaEvento {

public static void guardarEvento(EventoUniversitario evento, String nombreArchivo) throws IOException {





FileOutputStream archivo = new FileOutputStream(nombreArchivo);
ObjectOutputStream salida = new ObjectOutputStream(archivo);

salida.writeObject(evento);
salida.close();
}

public static EventoUniversitario leerEvento(String nombreArchivo)
    throws IOException, ClassNotFoundException  {

    FileInputStream archivo = new FileInputStream(nombreArchivo);
    ObjectInputStream entrada = new ObjectInputStream(archivo);

    EventoUniversitario evento = (EventoUniversitario) entrada.readObject();
    entrada.close();
    return evento;


}

}








