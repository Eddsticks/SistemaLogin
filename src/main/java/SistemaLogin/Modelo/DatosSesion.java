package SistemaLogin.Modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 * Clase encargada de encapsular la lógica para manipular las tareas personales
 * del usuario autenticado, usando una lista interna de objetos Tarea.
 */
public class DatosSesion {
    private final String nombreArchivo;
    private final ArrayList<Tarea> tareas = new ArrayList<>();

    /**
     * Constructor que inicializa el nombre del archivo y carga las tareas existentes.
     * Si el archivo <usuario>_todo.txt no existe, lo crea automáticamente.
     *
     * @param usuario Nombre del usuario para el archivo de tareas.
     */
    public DatosSesion(String usuario) {
        this.nombreArchivo = usuario + "_todo.txt";
        crearArchivoSiNoExiste();
        cargarTareas();
    }

    /**
     * Agrega una nueva tarea a la lista interna y la guarda en el archivo del usuario.
     *
     * @param descripcion Texto de la tarea a agregar.
     */
    public void agregarTarea(String descripcion) {
        Tarea nuevaTarea = new Tarea(descripcion);
        tareas.add(nuevaTarea);

        try (FileWriter writer = new FileWriter(nombreArchivo, true);
             PrintWriter printWriter = new PrintWriter(writer)) {
            printWriter.println(descripcion);
            System.out.println("Tarea '" + descripcion + "' guardada en el archivo.");
        } catch (IOException e) {
            System.err.println("Error al guardar la tarea en el archivo: " + e.getMessage());
            tareas.remove(nuevaTarea);
        }
    }

    /**
     * Devuelve una vista inmutable de la lista de tareas.
     * Cualquier intento de modificar la lista devuelta resultará en una UnsupportedOperationException.
     *
     * @return Una List inmutable de objetos Tarea.
     */
    public List<Tarea> getTareas() {
        return Collections.unmodifiableList(tareas);
    }

    /**
     * Muestra todas las tareas almacenadas en el archivo.
     * Itera sobre la lista 'tareas' en memoria.
     */
    public void mostrarTareas() {
        System.out.println("\n==== Tus Tareas en '" + nombreArchivo + "'");
        if (tareas.isEmpty()) {
            System.out.println("No tienes tareas registradas.");
        } else {
            for (int i = 0; i < tareas.size(); i++) {
                System.out.println((i + 1) + ". " + tareas.get(i).getDescripcion());
            }
        }
        System.out.println("===============================\n");
    }

    /**
     * Crea el archivo de tareas si no existe.
     */
    private void crearArchivoSiNoExiste() {
        File file = new File(nombreArchivo);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("Archivo de tareas '" + nombreArchivo + "' creado exitosamente.");
                } else {
                    System.err.println("No se pudo crear el archivo de tareas '" + nombreArchivo + "'.");
                }
            } catch (IOException e) {
                System.err.println("Error al crear el archivo de tareas '" + nombreArchivo + "': " + e.getMessage());
            }
        }
    }

    /**
     * Carga las tareas desde el archivo al ArrayList<Tarea>.
     * Este método se llama en el constructor.
     */
    private void cargarTareas() {
        tareas.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                linea = linea.trim();
                if (!linea.isEmpty()) {
                    tareas.add(new Tarea(linea));
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar las tareas desde el archivo: " + e.getMessage());
        }
    }
}