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

    public SesionActiva(String usuario) {
        this.usuario = usuario;
        this.datosSesion = new DatosSesion(usuario);
    }

    /**
     * Ciclo de operaciones disponibles en sesión.
     */
    public void menuSesion() {
        // TODO: Mostrar opciones según si el usuario es admin o no.
        // TODO: Escribir tareas.
        // TODO: Registrar usuarios (solo admin).
        // TODO: Salir de sesión.
    }

    private void escribirTarea() {
        // TODO: Pedir tarea al usuario y delegar a datosSesion.
    }

    private void registrarUsuario() {
        // TODO: Usar GestorUsuarios para registrar un nuevo usuario.
    }
}
