package SistemaLogin.Modelo;

import java.time.LocalDateTime;

/**
 * Almacena información de perfil de usuario.
 */
public class Perfil {
    private String correo;
    private LocalDateTime fechaCreacion;

    public  Perfil(String correo) {
        this.correo = correo;
        this.fechaCreacion = LocalDateTime.now();
    }

    public String getCorreo() {
        return correo;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
