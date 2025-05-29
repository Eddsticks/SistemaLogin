package SistemaLogin.Modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Gestiona el archivo login.txt.
 */
public class DatosLogin {
    private final String archivo = "login.txt";
    private final ArrayList<String> credenciales = new ArrayList<>();

    public DatosLogin() {
        crearArchivoSiNoExiste();
        cargarUsuarios();
    }

    /**
     * Devuelve la lista de credenciales cargadas.
     */
    public ArrayList<String> getCredenciales() {
        return credenciales;
    }

    /**
     * Crea el archivo login.txt si no existe.
     */
    private void crearArchivoSiNoExiste() {
        // TODO: Verificar existencia del archivo y crearlo si no existe.
    }

    /**
     * Carga los pares usuario;clave desde el archivo a la lista.
     */
    private void cargarUsuarios() {
        // TODO: Leer línea por línea y agregar solo las válidas al ArrayList.
    }
}
