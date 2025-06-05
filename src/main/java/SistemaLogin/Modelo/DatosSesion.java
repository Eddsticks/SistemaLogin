package SistemaLogin.Modelo;

import java.io.*;
import java.util.Scanner;

/**
 * Maneja las tareas personales de un usuario autenticado.
 */
public class DatosSesion {
    private final String nombreArchivo;

    public DatosSesion(String usuario) {
        this.nombreArchivo = usuario + "_todo.txt";
        crearArchivoSiNoExiste();
    }

    /**
     * Crea el archivo de tareas si no existe.
     */
    private void crearArchivoSiNoExiste() {
        File file = new File((nombreArchivo));

        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("Archivo de tareas '" + nombreArchivo + "' creado exitosamente.");
                } else {
                    System.out.println("No se pudo crear el archivo de tareas '" + nombreArchivo + "'.");
                }
            } catch (IOException e) {
                System.err.println("Error al crear el archivo de tareas '" + nombreArchivo + "': " + e.getMessage());
            }
        } else {
            System.out.println("El archivo de tareas '" + nombreArchivo + "' ya existe.");
        }
    }

    /**
     * Escribe una nueva tarea al final del archivo.
     *
     * @param tarea Texto de la tarea.
     * @return true si se guardó correctamente, false si ocurrió un error.
     */
    public boolean escribirTarea(String tarea) {
        try (FileWriter writer = new FileWriter(nombreArchivo, true)) {
            writer.write(tarea + System.lineSeparator());
            System.out.println("Tarea guardada en : '" + nombreArchivo + "'");
            return true;
        } catch (IOException e) {
            System.err.println("Error al escribir la tarea.");
            return false;
        }
    }

    /**
     * Muestra todas las tareas almacenadas en el archivo.
     */
    public void mostrarTareas() {
        System.out.println("\n==== Tus Tareas en '" + nombreArchivo + "'");
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            int contadorTareas = 0;
            while ((linea = reader.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    contadorTareas++;
                    System.out.println(contadorTareas + ". " + linea);
                }
            }
            if (contadorTareas == 0) {
                System.out.println("No tienes tareas registradas.");
            }
        } catch (IOException e) {
            System.out.println("Error al leer tareas");
        }
        System.out.println("===============================\n");
    }
}