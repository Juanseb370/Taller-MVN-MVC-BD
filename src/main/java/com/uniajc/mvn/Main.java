package com.uniajc.mvn;

import java.sql.Connection;
import java.util.Scanner;

import com.uniajc.mvn.controlador.ControladorEstudiante;
import com.uniajc.mvn.modelo.ConexionDatabase;
import com.uniajc.mvn.modelo.Estudiante;
import com.uniajc.mvn.vista.VistaEstudiante;

public class Main {
  public static void main(String[] args) {

    Connection conexion = ConexionDatabase.getConnection();

   Scanner scanner = new Scanner(System.in);

    // Estudiante estudiante = new Estudiante();
    // estudiante.setNombre("juan almendra");
    // estudiante.setEdad(25);
    

    VistaEstudiante vista = new VistaEstudiante();
    // ControladorEstudiante controlador = new ControladorEstudiante(estudiante, vista);

    // controlador.agregarEstudiante(estudiante);
    // controlador.actualizarVista();
    





    //AGREGAR ESTUDIANTE DESDE CONSOLA

    System.out.print("Ingrese el nombre del estudiante: ");
    String nombre = scanner.nextLine();

    System.out.print("Ingrese la edad del estudiante: ");
    int edad = scanner.nextInt();

    scanner.nextLine(); // Consumir el salto de línea pendiente

    Estudiante estudiante = new Estudiante(nombre, edad,0); // Asignar un ID único, por ejemplo, 0
    ControladorEstudiante controlador = new ControladorEstudiante(estudiante, vista);
    controlador.agregarEstudiante(estudiante);
    controlador.actualizarVista();
    // scanner.close();
    scanner.nextLine(); // Consumir el salto de línea pendiente



    //ACTUALIZAR ESTUDIANTE

    System.out.print("Ingrese el nombre del estudiante a actualizar: ");
    Scanner scanner2 = new Scanner(System.in);
    String nombreOriginal = scanner2.nextLine();
    System.out.print("Ingrese el nuevo nombre del estudiante: ");
    String nuevoNombre = scanner2.nextLine();
    System.out.print("Ingrese la nueva edad del estudiante: ");
    int nuevaEdad = scanner2.nextInt();

    scanner.nextLine(); // Consumir el salto de línea pendiente

    Estudiante estudianteActualizado = new Estudiante(nuevoNombre, nuevaEdad,0);
    controlador.actualizarEstudiante(nombreOriginal, estudianteActualizado);
    controlador.actualizarVista();
    // scanner2.close();

    scanner2.nextLine(); // Consumir el salto de línea pendiente
    


    //ELIMINAR ESTUDIANTE
    System.out.print("Ingrese el nombre del estudiante a eliminar: ");
    Scanner scanner3 = new Scanner(System.in);
    String nombreAEliminar = scanner3.nextLine();
    controlador.eliminarEstudiante(nombreAEliminar);
    controlador.actualizarVista();
    scanner3.close();
    // Cerrar la conexión al final (opcional, ya que el programa termina aquí)
    try {
      if (conexion != null && !conexion.isClosed()) {
        conexion.close();
        System.out.println("Conexión cerrada.");
      }
    } catch (Exception e) {
      System.out.println("Error al cerrar la conexión: " + e.getMessage());
    }







  }
}
  

