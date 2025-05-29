package SistemaLogin.Controlador;

import SistemaLogin.Modelo.DatosLogin;

/**
 * Clase encargada de la lógica de autenticación.
 */
public class Login {

    /**
     * Verifica si las credenciales son válidas.
     *
     * @param usuario nombre ingresado
     * @param clave contraseña ingresada
     * @param datos instancia de DatosLogin
     * @return true si son válidas, false si no
     */
    public boolean autenticar(String usuario, String clave, DatosLogin datos) {
        String intento = usuario + ";" + clave;
        for (String credencial : datos.getCredenciales()) {
            if (credencial.equals(intento)) {
                return true;
            }
        }
        return false;
    }
}