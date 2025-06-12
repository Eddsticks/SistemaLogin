package SistemaLogin.Modelo;

import java.io.*;
import java.util.ArrayList;

/**
 * Clase encargada de cargar y administrar los datos de los usuarios desde el archivo login.txt.
 * Encapsula la lista de objetos Usuario.
 */
public class DatosLogin {
    private final ArrayList<Usuario> usuarios = new ArrayList<>();
    private static final String NOMBRE_ARCHIVO = "login.txt";

    /**
     * Constructor que carga los usuarios desde login.txt.
     * Si el archivo no existe, lo crea automáticamente.
     */
    public DatosLogin() {
        crearArchivoSiNoExiste();
        cargarUsuarios();
    }

    /**
     * Devuelve la lista de usuarios cargados.
     *
     * @return lista de usuarios
     */
    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    /**
     * Crea el archivo login.txt si no existe.
     */
    private void crearArchivoSiNoExiste() {
        File file = new File(NOMBRE_ARCHIVO);

        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("Archivo '" + NOMBRE_ARCHIVO + "' creado exitosamente.");
                } else {
                    System.err.println("Error: No se pudo crear el archivo '" + NOMBRE_ARCHIVO + "'.");
                }
            } catch (IOException e) {
                System.err.println("Error de I/O al crear '" + NOMBRE_ARCHIVO + "': " + e.getMessage());
            }
        }
    }

    /**
     * Carga los usuarios desde el archivo a la lista de objetos Usuario.
     * Ahora espera el formato: usuario;clave;correo.
     */
    private void cargarUsuarios() {
        usuarios.clear();

        try (BufferedReader br = new BufferedReader(new FileReader(NOMBRE_ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (!linea.isEmpty() && linea.contains(";")) {
                    String[] partes = linea.split(";");
                    if (partes.length >= 2) {
                        String nombre = partes[0];
                        String clave = partes[1];
                        String correo = "";

                        if (partes.length >= 3) {
                            correo = partes[2];
                        }
                        usuarios.add(new Usuario(nombre, clave, correo));
                    } else {
                        System.err.println("Advertencia: Línea con formato incorrecto en " + NOMBRE_ARCHIVO + " (faltan usuario, clave o correo): " + linea);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Archivo '" + NOMBRE_ARCHIVO + "' no encontrado. Esto no debería ocurrir: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error al leer el archivo '" + NOMBRE_ARCHIVO + "': " + e.getMessage());
        }
    }
}