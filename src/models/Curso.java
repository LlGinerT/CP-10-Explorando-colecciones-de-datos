package models;

import java.util.HashMap;
import java.util.Map;

/* 
 * Cada curso, contiene un nombre y código únicos, y contiene a las asignaturas que únicas por curso.
 */
public class Curso {
    private String nombre;
    private String codigo;
    private Map<String, Asignatura> asignaturas;

    public Curso(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.asignaturas = new HashMap<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public Map<String, Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void agregarAsignatura(String nombreAsignatura) {
        asignaturas.put(nombreAsignatura, new Asignatura(nombreAsignatura));
    }

    @Override
    public String toString() {
        StringBuilder resultado = new StringBuilder("Curso: " + nombre + " (Código: " + codigo + ")\n");
        for (Asignatura asignatura : asignaturas.values()) {
            resultado.append(asignatura).append("\n");
        }
        return resultado.toString();
    }
}
