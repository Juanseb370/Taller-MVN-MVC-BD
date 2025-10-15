package com.uniajc.mvn;

import com.uniajc.mvn.controlador.ControladorEstudiante;
import com.uniajc.mvn.controlador.ControladorProfesor;
import com.uniajc.mvn.controlador.ControladorCurso;
import com.uniajc.mvn.vista.VistaEstudiante;
import com.uniajc.mvn.vista.VistaProfesor;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Instancias de vistas (solo para mostrar datos en consola)
        VistaEstudiante vistaEst = new VistaEstudiante();
        VistaProfesor vistaProf = new VistaProfesor();

        // Instancias de controladores
        ControladorEstudiante ctrlEst = new ControladorEstudiante(null, vistaEst);
        ControladorProfesor ctrlProf = new ControladorProfesor(null, vistaProf);
        ControladorCurso ctrlCurso = new ControladorCurso();

        int opcionPrincipal;

        do {
            System.out.println("\n===== MENÚ PRINCIPAL =====");
            System.out.println("1. Gestión de Estudiantes");
            System.out.println("2. Gestión de Profesores");
            System.out.println("3. Gestión de Cursos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcionPrincipal = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcionPrincipal) {
                case 1 -> menuEstudiantes(ctrlEst, scanner);
                case 2 -> menuProfesores(ctrlProf, scanner);
                case 3 -> menuCursos(ctrlCurso, scanner);
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción inválida, intente de nuevo.");
            }
        } while (opcionPrincipal != 0);

        scanner.close();
    }

    // ======== MÉTODOS AUXILIARES ========

    private static void menuEstudiantes(ControladorEstudiante ctrl, Scanner sc) {
        int opcion;
        do {
            System.out.println("\n--- MENÚ ESTUDIANTES ---");
            System.out.println("1. Insertar estudiante");
            System.out.println("2. Listar estudiantes");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Nombre del estudiante: ");
                    String nombre = sc.nextLine();
                    System.out.print("Edad: ");
                    int edad = sc.nextInt();
                    sc.nextLine();

                    Estudiante est = new Estudiante(nombre, edad);
                    ctrl.agregarEstudiante(est);
                }
                case 2 -> ctrl.actualizarVista();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private static void menuProfesores(ControladorProfesor ctrl, Scanner sc) {
        int opcion;
        do {
            System.out.println("\n--- MENÚ PROFESORES ---");
            System.out.println("1. Insertar profesor");
            System.out.println("2. Actualizar profesor");
            System.out.println("3. Eliminar profesor");
            System.out.println("4. Listar profesores");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Nombre del profesor: ");
                    String nombre = sc.nextLine();
                    System.out.print("Materia: ");
                    String materia = sc.nextLine();

                    Profesor prof = new Profesor(nombre, materia);
                    ctrl.agregarProfesor(prof);
                }
                case 2 -> {
                    System.out.print("Nombre actual del profesor: ");
                    String nombreOriginal = sc.nextLine();
                    System.out.print("Nuevo nombre: ");
                    String nuevoNombre = sc.nextLine();
                    System.out.print("Nueva materia: ");
                    String nuevaMateria = sc.nextLine();

                    Profesor profAct = new Profesor(nuevoNombre, nuevaMateria);
                    ctrl.actualizarProfesor(nombreOriginal, profAct);
                }
                case 3 -> {
                    System.out.print("Nombre del profesor a eliminar: ");
                    String nombreEliminar = sc.nextLine();
                    ctrl.eliminarProfesor(nombreEliminar);
                }
                case 4 -> ctrl.actualizarVistaP();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private static void menuCursos(ControladorCurso ctrl, Scanner sc) {
        int opcion;
        do {
            System.out.println("\n--- MENÚ CURSOS ---");
            System.out.println("1. Insertar curso");
            System.out.println("2. Actualizar curso");
            System.out.println("3. Eliminar curso");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Nombre del curso: ");
                    String nombre = sc.nextLine();
                    System.out.print("Créditos del curso: ");
                    int creditos = sc.nextInt();
                    sc.nextLine();

                    Cursos cursoNuevo = new Cursos(nombre, creditos);
                    Cursos.insertarCurso(cursoNuevo); // método del modelo
                    System.out.println("Curso agregado: " + nombre);
                }
                case 2 -> {
                    System.out.print("Nombre actual del curso: ");
                    String nombreOriginal = sc.nextLine();
                    System.out.print("Nuevo nombre del curso: ");
                    String nuevoNombre = sc.nextLine();
                    System.out.print("Nuevos créditos: ");
                    int nuevosCreditos = sc.nextInt();
                    sc.nextLine();

                    Cursos cursoAct = new Cursos(nuevoNombre, nuevosCreditos);
                    Cursos.actualizarCurso(nombreOriginal, cursoAct);
                    System.out.println("Curso actualizado: " + nuevoNombre);
                }
                case 3 -> {
                    System.out.print("Nombre del curso a eliminar: ");
                    String nombreEliminar = sc.nextLine();
                    Cursos.eliminarCurso(nombreEliminar);
                    System.out.println("Curso eliminado: " + nombreEliminar);
                }
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }
}


