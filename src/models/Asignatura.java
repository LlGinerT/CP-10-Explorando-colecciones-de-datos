package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* 
 * Las asignaturas, contienen su total de clases a la semana.
 */
public class Asignatura {
    private String nombre;
    private List<Clase> clases;

    public Asignatura(String nombre) {
        this.nombre = nombre;
        this.clases = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Clase> getClases() {
        return clases;
    }

    public void agregarClase(String dia, String hora) {
        Clase nuevaClase = new Clase(dia, hora, this);
        clases.add(nuevaClase);
        Collections.sort(clases);
    }

    public void eliminarClase(String dia, String hora) {
        clases.removeIf(clase -> clase.getDia().equals(dia) && clase.getHora().equals(hora));
    }

    public List<Clase> buscarClase(String dia) {
        List<Clase> clasesEnDia = new ArrayList<>();
        for (Clase clase : clases) {
            if (clase.getDia().equals(dia)) {
                clasesEnDia.add(clase);
            }
        }
        return clasesEnDia;
    }

    @Override
    public String toString() {
        StringBuilder resultado = new StringBuilder(nombre + ":\n");
        for (Clase clase : clases) {
            resultado.append("  ").append(clase).append("\n");
        }
        return resultado.toString();
    }
}
