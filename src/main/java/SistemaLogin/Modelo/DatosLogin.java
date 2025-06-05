package SistemaLogin.Modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Gestiona el archivo login.txt.
 */
public class DatosLogin {
    private final ArrayList<String> credenciales = new ArrayList<>();
    private final String archivo = "login.txt";

    public DatosLogin() {
        crearArchivoSiNoExiste();
        cargarUsuarios();
    }

    /**
     * Devuelve la lista de credenciales cargadas.
     */
    public ArrayList<String> getCredenciales() {
        return credenciales;
    }

    /**
     * Crea el archivo login.txt si no existe.
     */
    private void crearArchivoSiNoExiste() {
        File file = new File(archivo);

        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("Archivo 'login.txt' creado con éxito.\n");
                } else {
                    System.out.println("No se puede crear el archivo.");
                }
            } catch (IOException e) {
                System.err.println("Error al crear el archivo 'login.txt': " + e.getMessage());
            }
        } else {
            System.out.println("El archivo 'login.txt' ya existe");
        }
    }

    /**
     * Carga los pares usuario;clave desde el archivo a la lista.
     */
    private void cargarUsuarios() {

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (!linea.isEmpty() && linea.contains(";")) {
                    credenciales.add(linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
