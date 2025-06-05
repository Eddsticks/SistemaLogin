package SistemaLogin.Controlador;

import SistemaLogin.Modelo.DatosSesion;
import SistemaLogin.Modelo.GestorUsuarios;

import java.util.Scanner;

/**
 * Representa la sesión de un usuario logueado.
 */
public class SesionActiva {
    private final String usuario;
    private final Scanner scanner = new Scanner(System.in);
    private final DatosSesion datosSesion;
    private final GestorUsuarios gestorUsuarios;

    public SesionActiva(String usuario) {
        this.usuario = usuario;
        this.datosSesion = new DatosSesion(usuario);
        this.gestorUsuarios = new GestorUsuarios();
    }

    /**
     * Ciclo de operaciones disponibles en sesión.
     */
    public void menuSesion() {
        int opcion;
        int opcionSalir = usuario.equals("admin") ? 4 : 3;
        do {
            mostrarOpcionesSesion();
            opcion = obtenerOpcionSesion();
            ejecutarOpcionSesion(opcion);
        } while (opcion != opcionSalir);
    }

    private void mostrarOpcionesSesion() {
        System.out.println("==== Bienvenido! " + usuario.toUpperCase() + " ====");
        System.out.println("1. Escribir nueva tarea.\n2. Mostrar Mis tareas.");
        if (usuario.equals("admin")) {
            System.out.println("3. Registrar nuevo usuario.\n4. Salir.");
        } else {
            System.out.println("3. Cerrar sesión");
        }
        System.out.println("Ingrese una opción: ");
    }

    private int obtenerOpcionSesion() {
        while (true) {
            if (scanner.hasNextInt()) {
                int opcion = scanner.nextInt();
                scanner.nextLine();

                if (usuario.equals("admin")) {
                    if (opcion >= 1 && opcion <= 4) {
                        return opcion;
                    }
                } else {
                    if (opcion >= 1 && opcion <= 3) {
                        return opcion;
                    }
                }
                System.out.print("Opción inválida. Intente de nuevo: ");
            } else {
                System.out.print("Entrada no válida. Por favor, ingrese un número: ");
                scanner.next();
            }
        }
    }

    private void ejecutarOpcionSesion(int opcion) {
        switch (opcion) {
            case 1 -> escribirTarea();
            case 2 -> datosSesion.mostrarTareas();
            case 3 -> {
                if (usuario.equals("admin")) {
                    registrarUsuario();
                } else {
                    System.out.println("Cerrando sesión de " + usuario + ". ¡Hasta pronto!");
                }
            }
            case 4 -> {
                if (usuario.equals("admin")) {
                    System.out.println("Cerrando sesión de " + usuario + ". ¡Hasta pronto!");
                } else {
                    System.out.println("Opción no válida.");
                }
            }
            default -> System.out.println("Opción no reconocida. Vuelva a intentar.");
        }
    }

    private void escribirTarea() {
        System.out.println("\n==== Escribir Nueva Tarea ====");
        System.out.print("Ingrese la tarea: ");
        String tarea = scanner.nextLine();
        if (datosSesion.escribirTarea(tarea)) {
            System.out.println("Tarea guardada exitosamente.");
        } else {
            System.out.println("No se pudo guardar la tarea.");
        }
        System.out.println("=======================\n");
    }

    private void registrarUsuario() {
        System.out.println("\n--- Registrar Nuevo Usuario ---");
        System.out.print("Ingrese el nombre de usuario para el nuevo registro: ");
        String nuevoUsuario = scanner.nextLine();
        System.out.print("Ingrese la clave para el nuevo registro: ");
        String nuevaClave = scanner.nextLine();

        if (gestorUsuarios.registrar(nuevoUsuario, nuevaClave)) {
            System.out.println("Usuario '" + nuevoUsuario + "' registrado con éxito.");
        } else {
            System.out.println("No se pudo registrar el usuario '" + nuevoUsuario + "'.");
        }
        System.out.println("-------------------------------\n");
    }
}
