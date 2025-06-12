package SistemaLogin.Modelo;

/**
 * Representa una tarea individual del usuario.
 */
public class Tarea {
    private String descripcion;
    private Prioridad prioridad;

    /**
     * Inicializa la descripción de la tarea.
     */
    public Tarea(String descripcion, Prioridad prioridad) {
        this.descripcion = descripcion;
        this.prioridad = prioridad;
    }

    public Tarea(String descripcion) {
        this(descripcion, Prioridad.BAJA);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }
}