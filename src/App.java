import java.util.Scanner;

import models.Asignatura;
import models.Clase;
import models.Curso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    private static Map<String, Curso> cursos = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Crea cursos de ejemplo
        crearCursosDeEjemplo();

        while (true) {
            menu();
            String opcion = scanner.nextLine();
            switch (opcion) {
                case "1":
                    crearCurso();
                    break;
                case "2":
                    agregarAsignatura();
                    break;
                case "3":
                    agregarClase();
                    break;
                case "4":
                    eliminarClase();
                    break;
                case "5":
                    buscarClases();
                    break;
                case "6":
                    visualizarHorario();
                    break;
                case "7":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void menu() {
        System.out.println("----------------------------");
        System.out.println("Gestión de Horarios de Cursos");
        System.out.println("1. Crear Curso");
        System.out.println("2. Agregar Asignatura");
        System.out.println("3. Agregar Clase");
        System.out.println("4. Eliminar Clase");
        System.out.println("5. Buscar Clases en un Día");
        System.out.println("6. Visualizar Horario de Curso");
        System.out.println("7. Salir");
        System.out.println("----------------------------");
        System.out.print("Seleccione una opción: ");
    }

    /**
     * Crea un curso nuevo y lo añade a un hashmap, con su codigo como clave y el
     * objeto en si como valor
     */
    private static void crearCurso() {
        System.out.print("Nombre del curso: ");
        String nombre = scanner.nextLine();
        System.out.println("----------------------------");
        System.out.print("Código del curso: ");
        String codigo = scanner.nextLine();
        System.out.println("----------------------------");
        cursos.put(codigo, new Curso(nombre, codigo));
        System.out.println("Curso " + nombre + " creado con éxito.");
        System.out.println("----------------------------");
    }

    /**
     * Agrega una asignatura a un curso existente
     */
    private static void agregarAsignatura() {
        System.out.print("Código del curso: ");
        String codigo = scanner.nextLine();
        if (cursos.containsKey(codigo)) {
            System.out.print("Nombre de la asignatura: ");
            String nombreAsignatura = scanner.nextLine();
            cursos.get(codigo).agregarAsignatura(nombreAsignatura);
            System.out.println(
                    "Asignatura " + nombreAsignatura + " agregada al curso " + cursos.get(codigo).getNombre() + ".");
        } else {
            System.out.println("Curso no encontrado.");
        }
    }

    /**
     * Agregar una hora de clase de una asignatura del curso.
     */
    private static void agregarClase() {
        System.out.print("Código del curso: ");
        String codigo = scanner.nextLine();
        if (cursos.containsKey(codigo)) {
            System.out.print("Nombre de la asignatura: ");
            String nombreAsignatura = scanner.nextLine();
            Curso curso = cursos.get(codigo);
            if (curso.getAsignaturas().containsKey(nombreAsignatura)) {
                System.out.print("Día de la clase: ");
                String dia = scanner.nextLine();
                System.out.print("Hora de la clase (HH:MM): ");
                String hora = scanner.nextLine();
                curso.getAsignaturas().get(nombreAsignatura).agregarClase(dia, hora);
                System.out.println(
                        "Clase agregada a la asignatura " + nombreAsignatura + " del curso " + curso.getNombre() + ".");
            } else {
                System.out.println("Asignatura no encontrada.");
            }
        } else {
            System.out.println("Curso no encontrado.");
        }
    }

    private static void eliminarClase() {
        System.out.print("Código del curso: ");
        String codigo = scanner.nextLine();
        if (cursos.containsKey(codigo)) {
            System.out.print("Nombre de la asignatura: ");
            String nombreAsignatura = scanner.nextLine();
            Curso curso = cursos.get(codigo);
            if (curso.getAsignaturas().containsKey(nombreAsignatura)) {
                System.out.print("Día de la clase: ");
                String dia = scanner.nextLine();
                System.out.print("Hora de la clase (HH:MM): ");
                String hora = scanner.nextLine();
                curso.getAsignaturas().get(nombreAsignatura).eliminarClase(dia, hora);
                System.out.println("Clase eliminada de la asignatura " + nombreAsignatura + " del curso "
                        + curso.getNombre() + ".");
            } else {
                System.out.println("Asignatura no encontrada.");
            }
        } else {
            System.out.println("Curso no encontrado.");
        }
    }

    private static void buscarClases() {
        System.out.print("Código del curso: ");
        String codigo = scanner.nextLine();
        if (cursos.containsKey(codigo)) {
            System.out.print("Día de la semana: ");
            String dia = scanner.nextLine();
            Curso curso = cursos.get(codigo);
            boolean clasesEncontradas = false;
            for (Asignatura asignatura : curso.getAsignaturas().values()) {
                List<Clase> clases = asignatura.buscarClase(dia);
                if (!clases.isEmpty()) {
                    clasesEncontradas = true;
                    System.out.println("Clases en " + dia + " para la asignatura " + asignatura.getNombre() + ":");
                    for (Clase clase : clases) {
                        System.out.println(clase.getHora());
                    }
                }
            }
            if (!clasesEncontradas) {
                System.out.println("No hay clases en " + dia + " para el curso " + curso.getNombre() + ".");
            }
        } else {
            System.out.println("Curso no encontrado.");
        }
    }

    private static void visualizarHorario() {
        System.out.print("Código del curso: ");
        String codigo = scanner.nextLine();
        if (cursos.containsKey(codigo)) {
            System.out.println(cursos.get(codigo));
        } else {
            System.out.println("Curso no encontrado.");
        }
    }

    private static void crearCursosDeEjemplo() {

        Curso dam = new Curso("Desarrollo de Aplicaciones Multiplataforma", "DAM");
        dam.agregarAsignatura("Programación");
        dam.agregarAsignatura("Bases de Datos");
        dam.agregarAsignatura("Entornos de Desarrollo");

        dam.getAsignaturas().get("Programación").agregarClase("Lunes", "08:00");
        dam.getAsignaturas().get("Programación").agregarClase("Lunes", "10:00");
        dam.getAsignaturas().get("Bases de Datos").agregarClase("Miércoles", "08:00");
        dam.getAsignaturas().get("Entornos de Desarrollo").agregarClase("Viernes", "08:00");

        cursos.put(dam.getCodigo(), dam);

        Curso daw = new Curso("Desarrollo de Aplicaciones Web", "DAW");
        daw.agregarAsignatura("Desarrollo Web en Entorno Cliente");
        daw.agregarAsignatura("Desarrollo Web en Entorno Servidor");
        daw.agregarAsignatura("Despliegue de Aplicaciones Web");

        daw.getAsignaturas().get("Desarrollo Web en Entorno Cliente").agregarClase("Martes", "09:00");
        daw.getAsignaturas().get("Desarrollo Web en Entorno Servidor").agregarClase("Jueves", "09:00");
        daw.getAsignaturas().get("Desarrollo Web en Entorno Servidor").agregarClase("Jueves", "11:00");
        daw.getAsignaturas().get("Despliegue de Aplicaciones Web").agregarClase("Viernes", "10:00");

        cursos.put(daw.getCodigo(), daw);

        System.out.println("Cursos de ejemplo creados: DAM y DAW.");
    }
}