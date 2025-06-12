package SistemaLogin.Modelo;

/**
 * Clase que representa a un usuario del sistema.
 */
public class Usuario {
    private String nombre;
    private String clave;
    private Perfil perfil;

    /**
     * Inicializa los atributos del usuario.
     */
    public Usuario(String nombre, String clave, String correo) {
        this.nombre = nombre;
        this.clave = clave;
        this.perfil = new Perfil(correo);
    }

    public String getNombre() {
        return nombre;
    }

    public String getClave() {
        return clave;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
}