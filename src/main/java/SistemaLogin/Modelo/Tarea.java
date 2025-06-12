package SistemaLogin.Modelo;
/**
 * Representa una tarea individual del usuario.
 */
public class Tarea {
    private String descripcion;

    /**
     * Constructor que inicializa la descripción de la tarea.
     *
     * @param descripcion contenido de la tarea
     */
    public Tarea(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}