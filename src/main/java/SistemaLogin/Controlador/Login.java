package SistemaLogin.Controlador;

import SistemaLogin.Modelo.DatosLogin;
import SistemaLogin.Modelo.Usuario;

/**
 * Clase encargada de la lógica de autenticación.
 */
public class Login {

    /**
     * Verifica si las credenciales son válidas buscando una coincidencia
     * en la lista de objetos Usuario proporcionada por DatosLogin.
     *
     * @param nombreUsuario Nombre de usuario ingresado.
     * @param claveIngresada Contraseña ingresada.
     * @param datos Instancia de DatosLogin que contiene la lista de usuarios.
     * @return El objeto Usuario autenticado si las credenciales son válidas,
     * o null si la autenticación falla.
     */
    public Usuario autenticar(String nombreUsuario, String claveIngresada, DatosLogin datos) {
        for (Usuario usuarioAlmacenado : datos.getUsuarios()) {
            if (usuarioAlmacenado.getNombre().equals(nombreUsuario) &&
                    usuarioAlmacenado.getClave().equals(claveIngresada)) {
                return usuarioAlmacenado;
            }
        }
        return null;
    }
}