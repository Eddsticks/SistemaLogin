package SistemaLogin.Launcher;

import SistemaLogin.Vista.ConsolaLogin;

/**
 * Clase principal del sistema.
 * Contiene el método main para lanzar la aplicación.
 */
public class Inicio {
    public static void main(String[] args) {
        ConsolaLogin consola = new ConsolaLogin();
        consola.menu();
    }
}