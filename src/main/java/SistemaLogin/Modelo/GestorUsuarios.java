package SistemaLogin.Modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Registra nuevos usuarios en login.txt.
 */
public class GestorUsuarios {
    private final String archivo = "login.txt";

    public GestorUsuarios() {
        // TODO: Crear archivo si no existe.
    }

    public boolean registrar(String usuario, String clave) {
        // TODO: Agregar usuario al archivo login.txt.
        return false;
    }
}
