package SistemaLogin.Vista;

import SistemaLogin.Controlador.Login;
import SistemaLogin.Controlador.SesionActiva;
import SistemaLogin.Modelo.DatosLogin;
import SistemaLogin.Modelo.Usuario;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Vista principal del sistema. Muestra el menú por consola
 * y gestiona el proceso de autenticación.
 */
public class ConsolaLogin {
    private static final Scanner scanner = new Scanner(System.in);
    private final DatosLogin datos = new DatosLogin();
    private final Login login = new Login();

    /**
     * Muestra el menú principal del sistema y controla el ciclo de interacción.
     */
    public void menu() {
        int opcion;
        do {
            mostrarOpciones();
            opcion = obtenerOpcion();
            ejecutarOpcion(opcion);
        } while (opcion != 2);
        System.out.println("Cerrando el sistema...");
    }

    /**
     * Solicita usuario y contraseña, autentica, y lanza la sesión si corresponde.
     * Permite hasta 3 intentos de autenticación.
     */
    private void manejarLogin() {
        int intentos = 3;
        scanner.nextLine();

        while (intentos > 0) {
            System.out.print("Usuario: ");
            String nombreUsuario = scanner.nextLine();

            System.out.print("Clave: ");
            String claveIngresada = scanner.nextLine();

            Usuario usuarioAutenticado = login.autenticar(nombreUsuario, claveIngresada, datos);

            if (usuarioAutenticado != null) {
                System.out.println("Inicio de sesión exitoso como " + usuarioAutenticado.getNombre() + ".");
                SesionActiva sesionActiva = new SesionActiva(usuarioAutenticado);
                sesionActiva.menuSesion();
                return;
            } else {
                intentos--;
                System.out.println("Credenciales incorrectas. Intentos restantes: " + intentos);
            }
        }
        System.out.println("Límite de intentos alcanzado. Volviendo al menú principal.\n");
    }

    /**
     * Muestra las opciones del menú principal.
     */
    private void mostrarOpciones() {
        System.out.println("\n==== SISTEMA DE TAREAS ====");
        System.out.println("1. Iniciar sesión.");
        System.out.println("2. Salir.");
        System.out.print("Ingrese una opción: ");
    }

    /**
     * Solicita y valida la opción ingresada por el usuario.
     * @return La opción elegida por el usuario (1 o 2).
     */
    private static int obtenerOpcion() {
        while (true) {
            try {
                int opcion = scanner.nextInt();
                if (opcion == 1 || opcion == 2) {
                    return opcion;
                } else {
                    System.out.print("Opción no válida.");
                }
            } catch (InputMismatchException e) {
                System.out.print("Entrada no válida.");
                scanner.next();
            }
        }
    }

    /**
     * Ejecuta la acción correspondiente a la opción seleccionada.
     *
     * @param opcion La opción ingresada por el usuario.
     */
    private void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> manejarLogin();
            case 2 -> { } //Definido en menú()
            default -> System.out.println("Opción no reconocida. Vuelva a intentar.");
        }
    }
}