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
    private final Scanner scanner = new Scanner(System.in);
    private final DatosLogin datos = new DatosLogin();
    private final Login login = new Login();

    /**
     * Controla el ciclo principal del menú del sistema.
     */
    public void menu() {
        // TODO: Mostrar opciones en un bucle con switch.
        // TODO: Permitir iniciar sesión o salir del sistema.
    }

    /**
     * Solicita usuario y contraseña y maneja la autenticación.
     */
    private void manejarLogin() {
        // TODO: Pedir usuario y contraseña.
        // TODO: Llamar a login.autenticar().
        // TODO: Si es correcto, iniciar SesionActiva.
    }

    /**
     * Muestra el menú principal.
     */
    private void mostrarOpciones() {
        // TODO: Imprimir menú "1. Iniciar sesión", "2. Salir".
    }
}