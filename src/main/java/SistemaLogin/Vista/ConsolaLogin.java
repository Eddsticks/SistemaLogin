package SistemaLogin.Vista;

import SistemaLogin.Controlador.Login;
import SistemaLogin.Controlador.SesionActiva;
import SistemaLogin.Modelo.DatosLogin;

import java.util.Scanner;

/**
 * Vista principal del sistema.
 * Interactúa con el usuario mediante consola.
 */
public class ConsolaLogin {
    private static final Scanner scanner = new Scanner(System.in);
    private final DatosLogin datos = new DatosLogin();
    private final Login login = new Login();

    /**
     * Controla el ciclo principal del menú del sistema.
     */
    public void menu() {
        int opccion;
        do {
            mostrarOpciones();
            opccion = obtenerOpcion();
            ejecutarOpcion(opccion);
        }
        while (opccion != 2);
    }

    /**
     * Solicita usuario y contraseña y maneja la autenticación.
     */
    private void manejarLogin() {
        int intentos = 3;
        scanner.nextLine();

        while (intentos > 0) {
            System.out.println("Usuario: ");
            String usuario = scanner.nextLine();

            System.out.println("Clave: ");
            String clave = scanner.nextLine();

            if (login.autenticar(usuario, clave, datos)) {
                System.out.println("Inicio de sesión exitoso.");
                return;
            } else {
                intentos--;
                System.out.println("Credenciales incorrectas. Intentos restantes: " + intentos);
            }
        }
        System.out.println("Demasiados intentos fallidos. Volviendo a inicio...\n");
    }

    /**
     * Muestra el menú principal.
     */
    private void mostrarOpciones() {
        System.out.println("==== INICIO DE SESIÓN ====");
        System.out.println("1. Iniciar sesión.\n2. Salir.\n");
    }

    /**
     * Analiza la entrada proporcionada por el usuario.
     * @return opción elegida.
     */
    private static int obtenerOpcion() {
        while (true) {
            System.out.println("Ingrese una opción: ");
            if (scanner.hasNextInt()) {
                int opcion = scanner.nextInt();
                if (opcion == 1 || opcion == 2) {
                    return opcion;
                } else {
                    System.out.println("Ingrese 1 ó 2.");
                }
            } else {
                System.out.println("Entrada no válida.");
                scanner.next();
            }
        }
    }

    /**
     * Ejecuta la opción seleccionada por el usuario.
     *
     * @param opcion opción ingresada por el usuario
     */
    private void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> manejarLogin();
            case 2 -> System.out.println("Saliendo...");
            default -> System.out.println("Opción no reconocida.");
        }
    }
}