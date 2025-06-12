package SistemaLogin.Controlador;

import SistemaLogin.Modelo.DatosSesion;
import SistemaLogin.Modelo.GestorUsuarios;
import SistemaLogin.Modelo.Tarea;
import SistemaLogin.Modelo.Usuario;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Representa el ciclo de operaciones disponibles para un usuario autenticado.
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
     */
    public void menuSesion() {
        int opcion;
        int opcionSalir = usuarioAutenticado.getNombre().equals("admin") ? 4 : 3;

        do {
            mostrarOpcionesSesion();
            opcion = obtenerOpcionSesion();
            ejecutarOpcionSesion(opcion, opcionSalir);
        } while (opcion != opcionSalir);
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
        datosSesion.agregarTarea(descripcionTarea);
        System.out.println("=======================\n");
    }

    private void registrarUsuario() {
        System.out.println("\n--- Registrar Nuevo Usuario ---");
        System.out.print("Ingrese el nombre de usuario para el nuevo registro: ");
        String nuevoUsuario = scanner.nextLine();
        System.out.print("Ingrese la clave para el nuevo registro: ");
        String nuevaClave = scanner.nextLine();

        gestorUsuarios.registrar(nuevoUsuario, nuevaClave);
        System.out.println("-------------------------------\n");
    }
}