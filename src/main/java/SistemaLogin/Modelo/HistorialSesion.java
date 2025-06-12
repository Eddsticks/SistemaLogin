package SistemaLogin.Modelo;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Para registrar información relevante de una sesión de usuario
 * (Fecha, Hora, Tareas agregadas)
 */
public class HistorialSesion {
    private LocalDateTime inicio;
    private int tareasAgregadas;
    private Map<Prioridad, Integer> tareasPorPrioridad;

    /**
     * Inicia el historial de la sesión.
     */
    public HistorialSesion() {
        this.inicio = LocalDateTime.now();
        this.tareasAgregadas = 0;
        this.tareasPorPrioridad = new HashMap<>();

        for (Prioridad p : Prioridad.values()) {
            tareasPorPrioridad.put(p, 0);
        }
    }

    /**
     * Registra tareas nuevas durante la sesión.
     * Toma en cuenta el total de tareas y el valor específico de prioridad.
     */
    public void registrarNuevaTarea(Prioridad prioridad) {
        this.tareasAgregadas++;
        tareasPorPrioridad.put(prioridad, tareasPorPrioridad.getOrDefault(prioridad, 0) + 1);
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public int getTareasAgregadas() {
        return tareasAgregadas;
    }

    public Map<Prioridad, Integer> getTareasPorPrioridad() {
        return Collections.unmodifiableMap(tareasPorPrioridad);
    }
}
