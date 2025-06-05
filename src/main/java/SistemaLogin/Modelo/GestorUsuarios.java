package SistemaLogin.Modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Registra nuevos usuarios en login.txt.
 */
public class GestorUsuarios {
    private final String archivo = "login.txt";

    public GestorUsuarios() {
        crearArchivoSiNoExiste();
        // TODO: Crear archivo si no existe.
    }

    /**
     * Crea el archivo login.txt si no existe.
     * Esta es una copia de la lógica de DatosLogin para asegurar que el GestorUsuarios
     * siempre pueda escribir en el archivo.
     */
    private void crearArchivoSiNoExiste() {
        File file = new File(archivo);

        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("[GestorUsuarios] Archivo 'login.txt' creado exitosamente para registro.");
                } else {
                    System.err.println("[GestorUsuarios] No se pudo crear el archivo 'login.txt' para registro.");
                }
            } catch (IOException e) {
                System.err.println("[GestorUsuarios] Error al crear el archivo 'login.txt': " + e.getMessage());
            }
        }
        // No se imprime nada si ya existe, ya que DatosLogin ya lo gestiona y lo informa.
    }

    public boolean registrar(String usuario, String clave) {
        String nuevaCredencial = usuario + ";" + clave;

        try (FileWriter writer = new FileWriter(archivo, true)) {
            writer.write(nuevaCredencial + System.lineSeparator());
            System.out.println("Usuario '" + usuario + "' registrado exitosamente.");
            return true;
        } catch (IOException e) {
            System.err.println("Error al registrar el usuario '" + usuario + "': " + e.getMessage());
            return false;
        }
    }
}

