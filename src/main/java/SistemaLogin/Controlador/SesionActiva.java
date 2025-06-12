package SistemaLogin.Controlador;

import SistemaLogin.Modelo.DatosSesion;
import SistemaLogin.Modelo.GestorUsuarios;
import SistemaLogin.Modelo.HistorialSesion;
import SistemaLogin.Modelo.Prioridad;
import SistemaLogin.Modelo.Usuario;

import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

/**
 * Representa el ciclo de operaciones disponibles para un usuario autenticado.
 * Ahora maneja la prioridad de las tareas y muestra un historial de sesión.
 */
public class SesionActiva {
    private final Usuario usuarioAutenticado;
    private final Scanner scanner = new Scanner(System.in);
    private final DatosSesion datosSesion;
    private final GestorUsuarios gestorUsuarios;

    /**
     * Constructor que inicializa el usuario y sus datos de sesión.
     *
     * @param usuarioAutenticado el objeto Usuario autenticado.
     */
    public SesionActiva(Usuario usuarioAutenticado) {
        this.usuarioAutenticado = usuarioAutenticado;
        this.datosSesion = new DatosSesion(usuarioAutenticado.getNombre());
        this.gestorUsuarios = new GestorUsuarios();
    }

    /**
     * Muestra el menú interactivo de la sesión y gestiona las opciones.
     * Al salir, muestra el historial de la sesión actual.
     */
    public void menuSesion() {
        int opcion;
        int opcionSalir = usuarioAutenticado.getNombre().equals("admin") ? 4 : 3;

        do {
            mostrarOpcionesSesion();
            opcion = obtenerOpcionSesion();
            ejecutarOpcionSesion(opcion, opcionSalir);
        } while (opcion != opcionSalir);
        mostrarHistorialSesion();
        System.out.println("Cerrando sesión de " + usuarioAutenticado.getNombre() + ". ¡Hasta pronto!");
    }

    private void mostrarOpcionesSesion() {
        System.out.println("\n==== Bienvenido! " + usuarioAutenticado.getNombre().toUpperCase() + " ====");
        System.out.println("1. Escribir nueva tarea.");
        System.out.println("2. Mostrar Mis tareas.");
        if (usuarioAutenticado.getNombre().equals("admin")) {
            System.out.println("3. Registrar nuevo usuario.");
            System.out.println("4. Salir.");
        } else {
            System.out.println("3. Cerrar sesión");
        }
        System.out.print("Ingrese una opción: ");
    }

    private int obtenerOpcionSesion() {
        while (true) {
            try {
                int opcion = scanner.nextInt();
                scanner.nextLine();

                if (usuarioAutenticado.getNombre().equals("admin")) {
                    if (opcion >= 1 && opcion <= 4) {
                        return opcion;
                    }
                } else {
                    if (opcion >= 1 && opcion <= 3) {
                        return opcion;
                    }
                }
                System.out.print("Opción inválida. Intente de nuevo: ");
            } catch (InputMismatchException e) {
                System.out.print("Entrada no válida. Por favor, ingrese un número: ");
                scanner.next();
            }
        }
    }

    private void ejecutarOpcionSesion(int opcion, int opcionSalir) {
        if (opcion == opcionSalir) {
            return;
        }

        switch (opcion) {
            case 1 -> escribirTarea();
            case 2 -> datosSesion.mostrarTareas();
            case 3 -> {
                if (usuarioAutenticado.getNombre().equals("admin")) {
                    registrarUsuario();
                }
            }
            case 4 -> {
                if (!usuarioAutenticado.getNombre().equals("admin")) {
                    System.out.println("Opción no válida.");
                }
            }
            default -> System.out.println("Opción no reconocida. Vuelva a intentar.");
        }
    }

    private void escribirTarea() {
        System.out.println("\n==== Escribir Nueva Tarea ====");
        System.out.print("Ingrese la descripción de la tarea: ");
        String descripcionTarea = scanner.nextLine();

        Prioridad prioridadSeleccionada = obtenerPrioridad();

        datosSesion.agregarTarea(descripcionTarea, prioridadSeleccionada);
        System.out.println("=======================\n");
    }

    private Prioridad obtenerPrioridad() {
        while (true) {
            System.out.println("Seleccione la prioridad:");
            System.out.println("1. BAJA\n2. MEDIA\n3. ALTA");
            System.out.print("Ingrese el número de la prioridad: ");
            try {
                int opcionPrioridad = scanner.nextInt();
                scanner.nextLine();

                switch (opcionPrioridad) {
                    case 1: return Prioridad.BAJA;
                    case 2: return Prioridad.MEDIA;
                    case 3: return Prioridad.ALTA;
                    default: System.out.println("Opción de prioridad inválida. Intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número (1-3).");
                scanner.next();
            }
        }
    }

    private void registrarUsuario() {
        System.out.println("\n--- Registrar Nuevo Usuario ---");
        System.out.print("Ingrese el nombre de usuario para el nuevo registro: ");
        String nuevoUsuario = scanner.nextLine();
        System.out.print("Ingrese la clave para el nuevo registro: ");
        String nuevaClave = scanner.nextLine();
        System.out.print("Ingrese el correo electrónico para el nuevo usuario: ");
        String nuevoCorreo = scanner.nextLine();

        gestorUsuarios.registrar(nuevoUsuario, nuevaClave, nuevoCorreo);
        System.out.println("-------------------------------\n");
    }

    private void mostrarHistorialSesion() {
        HistorialSesion historial = datosSesion.getHistorial();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        System.out.println("\n==== Resumen de Sesión ====");
        System.out.println("Inicio de Sesión: " + historial.getInicio().format(formatter));
        System.out.println("Tareas Agregadas en esta Sesión: " + historial.getTareasAgregadas());
        System.out.println("--- Tareas por Prioridad ---");
        for (Map.Entry<Prioridad, Integer> entry : historial.getTareasPorPrioridad().entrySet()) {
            System.out.println("- " + entry.getKey().name() + ": " + entry.getValue());
        }
        System.out.println("===========================\n");
    }
}