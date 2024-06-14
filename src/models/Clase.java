package models;

/* 
 * Las clases contienen el dia, hora y asignatura impartida.
 */
public class Clase implements Comparable<Clase> {
    private String dia;
    private String hora;
    private Asignatura asignatura;

    public Clase(String dia, String hora, Asignatura asignatura) {
        this.dia = dia;
        this.hora = hora;
        this.asignatura = asignatura;
    }

    public String getDia() {
        return dia;
    }

    public String getHora() {
        return hora;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    @Override
    public int compareTo(Clase otraClase) {
        if (!this.dia.equals(otraClase.dia)) {
            return this.dia.compareTo(otraClase.dia);
        }
        return this.hora.compareTo(otraClase.hora);
    }

    @Override
    public String toString() {
        return dia + " " + hora + " (" + asignatura.getNombre() + ")";
    }
}
