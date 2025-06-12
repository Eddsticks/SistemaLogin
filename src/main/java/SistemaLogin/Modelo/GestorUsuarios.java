package SistemaLogin.Modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Clase que permite registrar nuevos usuarios en el archivo login.txt.
 * No almacena datos en memoria, trabaja directamente sobre el archivo.
 */
public class GestorUsuarios {

    private static final String NOMBRE_ARCHIVO = "login.txt";

    /**
     * Constructor por defecto. Verifica la existencia del archivo de usuarios.
     * Si no existe, intenta crearlo.
     */
    public GestorUsuarios() {
        crearArchivoSiNoExiste();
    }

    /**
     * Crea el archivo login.txt si no existe.
     * Proporciona una robustez adicional para esta clase si es instanciada
     * antes o sin que DatosLogin lo haya hecho.
     */
    private void crearArchivoSiNoExiste() {
        File file = new File(NOMBRE_ARCHIVO);

        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("GestorUsuarios: Archivo '" + NOMBRE_ARCHIVO + "' creado exitosamente para registro.");
                } else {
                    System.err.println("GestorUsuarios: No se pudo crear el archivo '" + NOMBRE_ARCHIVO + "' para registro.");
                }
            } catch (IOException e) {
                System.err.println("GestorUsuarios: Error al crear el archivo '" + NOMBRE_ARCHIVO + "': " + e.getMessage());
            }
        }
    }

    /**
     * Registra un nuevo usuario en el archivo login.txt.
     * Los datos se añaden al final del archivo.
     *
     * @param usuario Nombre del nuevo usuario.
     * @param clave Contraseña del nuevo usuario.
     * @return true si el registro fue exitoso, false si ocurrió un error.
     */
    public boolean registrar(String usuario, String clave, String correo) {
        try (FileWriter fileWriter = new FileWriter(NOMBRE_ARCHIVO, true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {

            printWriter.println(usuario + ";" + clave + ";" + correo);
            System.out.println("Usuario '" + usuario + "' registrado exitosamente.");
            return true;
        } catch (IOException e) {
            System.err.println("Error al registrar el usuario '" + usuario + "': " + e.getMessage());
            return false;
        }
    }
}

