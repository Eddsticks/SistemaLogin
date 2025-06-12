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
    private final HistorialSesion historial;

    /**
     * Constructor que inicializa el nombre del archivo y carga las tareas existentes.
     * Si el archivo <usuario>_todo.txt no existe, lo crea automáticamente.
     *
     * @param usuario Nombre del usuario para el archivo de tareas.
     */
    public DatosSesion(String usuario) {
        this.nombreArchivo = usuario + "_todo.txt";
        this.historial = new HistorialSesion();
        crearArchivoSiNoExiste();
        cargarTareas();
    }

    /**
     * Agrega una nueva tarea a la lista interna y la guarda en el archivo del usuario.
     *
     * @param descripcion Texto de la tarea a agregar.
     */
    public void agregarTarea(String descripcion, Prioridad prioridad) {
        Tarea nuevaTarea = new Tarea(descripcion, prioridad);
        tareas.add(nuevaTarea);

        historial.registrarNuevaTarea(prioridad);

        try (FileWriter writer = new FileWriter(nombreArchivo, true);
             PrintWriter printWriter = new PrintWriter(writer)) {
            printWriter.println(descripcion);
            System.out.println("Tarea '" + descripcion + "' guardada en el archivo.");
        } catch (IOException e) {
            System.err.println("Error al guardar la tarea en el archivo: " + e.getMessage());
            tareas.remove(nuevaTarea);
        }
    }

    public List<Tarea> getTareas() {
        return Collections.unmodifiableList(tareas);
    }


    public HistorialSesion getHistorial() {
        return historial;
    }

    public void mostrarTareas() {
        System.out.println("\n==== Tus tareas en '" + nombreArchivo + "'");
        if (tareas.isEmpty()) {
            System.out.println("No tienes tareas registradas");
        } else {
            for (int i = 0; i < tareas.size(); i++) {
                Tarea tarea = tareas.get(i);
                System.out.println((i + 1) + ". " + tarea.getDescripcion() + " (Prioridad: " + tarea.getPrioridad().name() + ")");
            }
        }
        System.out.println("===============================");
    }

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

    private void cargarTareas() {
        tareas.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                linea = linea.trim();
                if (!linea.isEmpty()) {
                    String[] partes = linea.split(";");
                    String descripcion = partes[0];
                    Prioridad prioridad = Prioridad.BAJA;

                    if (partes.length > 1) {
                        try {
                            prioridad = Prioridad.valueOf(partes[1].toUpperCase());
                        } catch (IllegalArgumentException e) {
                            System.err.println("Advertencia: Prioridad inválida para la tarea '" + descripcion + "'. Asignando BAJA. " + e.getMessage());
                        }
                    }
                    tareas.add(new Tarea(descripcion, prioridad));
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar las tareas desde el archivo: " + e.getMessage());
        }
    }
}