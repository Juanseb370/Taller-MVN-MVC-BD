package com.uniajc.mvn;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import com.uniajc.mvn.controlador.ControladorCurso;
import com.uniajc.mvn.controlador.ControladorEstudiante;
import com.uniajc.mvn.controlador.ControladorProfesor;

import com.uniajc.mvn.modelo.ConexionDatabase;
import com.uniajc.mvn.modelo.Cursos;
import com.uniajc.mvn.modelo.Estudiante;
import com.uniajc.mvn.modelo.Profesor;

import com.uniajc.mvn.vista.VistaEstudiante;
import com.uniajc.mvn.vista.VistaProfesor;



public class Main {
  public static void main(String[] args) {

    Connection conexion = ConexionDatabase.getConnection();


    //----------------------------------------------------------------------------------------------------
    //descomentar
   //Scanner scanner = new Scanner(System.in); 

   //no descomentar
    // Estudiante estudiante = new Estudiante();
    // estudiante.setNombre("juan almendra");
    // estudiante.setEdad(25);
    
//descomentar
    //VistaEstudiante vista = new VistaEstudiante();

    //no descomentar
    // ControladorEstudiante controlador = new ControladorEstudiante(estudiante, vista);

    // controlador.agregarEstudiante(estudiante);
    // controlador.actualizarVista();
    


    







    // // CODIGO PARA MANEJAR ESTUDIANTES

    // //AGREGAR ESTUDIANTE DESDE CONSOLA

    // System.out.print("Ingrese el nombre del estudiante: ");
    // String nombre = scanner.nextLine();

    // System.out.print("Ingrese la edad del estudiante: ");
    // int edad = scanner.nextInt();

    // scanner.nextLine(); // Consumir el salto de línea pendiente

    // Estudiante estudiante = new Estudiante(nombre, edad,0); // Asignar un ID único, por ejemplo, 0
    // ControladorEstudiante controlador = new ControladorEstudiante(estudiante, vista);
    // controlador.agregarEstudiante(estudiante);
    // controlador.actualizarVista();
    // // scanner.close();
    // scanner.nextLine(); // Consumir el salto de línea pendiente



    // //ACTUALIZAR ESTUDIANTE

    // System.out.print("Ingrese el nombre del estudiante a actualizar: ");
    // Scanner scanner2 = new Scanner(System.in);
    // String nombreOriginal = scanner2.nextLine();
    // System.out.print("Ingrese el nuevo nombre del estudiante: ");
    // String nuevoNombre = scanner2.nextLine();
    // System.out.print("Ingrese la nueva edad del estudiante: ");
    // int nuevaEdad = scanner2.nextInt();

    // scanner.nextLine(); // Consumir el salto de línea pendiente

    // Estudiante estudianteActualizado = new Estudiante(nuevoNombre, nuevaEdad,0);
    // controlador.actualizarEstudiante(nombreOriginal, estudianteActualizado);
    // controlador.actualizarVista();
    // // scanner2.close();

    // scanner2.nextLine(); // Consumir el salto de línea pendiente
    


    // //ELIMINAR ESTUDIANTE
    // System.out.print("Ingrese el nombre del estudiante a eliminar: ");
    // Scanner scanner3 = new Scanner(System.in);
    // String nombreAEliminar = scanner3.nextLine();
    // controlador.eliminarEstudiante(nombreAEliminar);
    // controlador.actualizarVista();
    // scanner3.close();
    // // Cerrar la conexión al final (opcional, ya que el programa termina aquí)
    // try {
    //   if (conexion != null && !conexion.isClosed()) {
    //     conexion.close();
    //     System.out.println("Conexión cerrada.");
    //   }
    // } catch (Exception e) {
    //   System.out.println("Error al cerrar la conexión: " + e.getMessage());
    // }



//---------------------------------------------------------------------------------------------------


     //AGREGAR PROFESOR DESDE CONSOLA

  //   System.out.print("Ingrese el nombre del profesor: ");
  //   Scanner scanner = new Scanner(System.in);
  //   String nombre = scanner.nextLine();
  //   System.out.print("Ingrese la materia del profesor: ");
  //   String materia = scanner.nextLine();
  //   Profesor profesor = new Profesor(nombre, materia,0); // Asignar un ID único, por ejemplo, 0
  //   Profesor.insertarProfesor(profesor);
  //   System.out.println("Profesor agregado: " + profesor.getNombre() + " - Materia: " + profesor.getMateria());

  //   scanner.nextLine(); // Consumir el salto de línea pendiente
  //   // ControladorEstudiante controlador = new ControladorEstudiante(estudiante, vista);
  //   // controlador.agregarEstudiante(estudiante);
  //   // controlador.actualizarVista();
  //   // scanner.close();
    

  //   //ACTUALIZAR PROFESOR DESDE CONSOLA
  //   System.out.print("Ingrese el nombre del profesor a actualizar: ");
  //   Scanner scanner2 = new Scanner(System.in);
  //   String nombreOriginal = scanner2.nextLine();
  //   System.out.print("Ingrese el nuevo nombre del profesor: ");
  //   String nuevoNombre = scanner2.nextLine();
  //   System.out.print("Ingrese la nueva materia del profesor: ");
  //   String nuevaMateria = scanner2.nextLine();
  //   scanner2.nextLine(); // Consumir el salto de línea pendiente
  //   Profesor profesorActualizado = new Profesor(nuevoNombre, nuevaMateria,0);
  //   Profesor.actualizarProfesor(nombreOriginal, profesorActualizado);
  //   System.out.println("Profesor actualizado: " + profesorActualizado.getNombre() + " - Materia: " + profesorActualizado.getMateria());
  //   //controlador.actualizarVistaP();
  //   // scanner2.close();
  //   scanner2.nextLine(); // Consumir el salto de línea pendiente


  

  // //ELIMINAR PROFESOR
  //   System.out.print("Ingrese el nombre del profesor a eliminar: ");
  //   Scanner scanner3 = new Scanner(System.in);
  //   String nombreAEliminar = scanner3.nextLine();
  //   Profesor.eliminarProfesor(nombreAEliminar);
  //   System.out.println("Profesor eliminado: " + nombreAEliminar);
  //   //controlador.actualizarVistaP();
  //   scanner3.close();
  //   //Cerrar la conexión al final (opcional, ya que el programa termina aquí)
  //   try {
  //     if (conexion != null && !conexion.isClosed()) {
  //       conexion.close();
  //       System.out.println("Conexión cerrada.");
  //     }
  //   } catch (Exception e) {
  //     System.out.println("Error al cerrar la conexión: " + e.getMessage());
  //   }



    //---------------------------------------------------------------------------------------------------------------

    


    // AGREGAR CURSO DESDE CONSOLA
    System.out.print("Ingrese el nombre del curso: ");
    Scanner scanner = new Scanner(System.in);
    String nombre = scanner.nextLine();
    System.out.print("Ingrese la descripción del curso: ");
    String descripcion = scanner.nextLine();
    Cursos curso = new Cursos(0,nombre, descripcion); // Asignar un ID único, por ejemplo, 0
    Cursos.insertarCurso(curso);
    System.out.println("Curso agregado: " + curso.getNombre() + " - Descripción: " + curso.getDescripcion());
    scanner.nextLine(); // Consumir el salto de línea pendiente

    
    
    
    //aCTUALIZAR CURSO DESDE CONSOLA
    System.out.print("Ingrese el nombre del curso a actualizar: ");
    Scanner scanner2 = new Scanner(System.in);
    String nombreOriginal = scanner2.nextLine();
    System.out.print("Ingrese el nuevo nombre del curso: ");
    String nuevoNombre = scanner2.nextLine();
    System.out.print("Ingrese la nueva descripción del curso: ");
    String nuevaDescripcion = scanner2.nextLine();
    scanner2.nextLine(); // Consumir el salto de línea pendiente
    Cursos cursoActualizado = new Cursos();
    Cursos.actualizarCurso(nombreOriginal, cursoActualizado);
    System.out.println("Curso actualizado: " + cursoActualizado.getNombre() + " - Descripción: " + cursoActualizado.getDescripcion());
    


  //ELIMINAR CURSO
    System.out.print("Ingrese el nombre del curso a eliminar: ");
    Scanner scanner3 = new Scanner(System.in);
    String nombreCursoAEliminar = scanner3.nextLine();
    Cursos.eliminarCurso(nombreCursoAEliminar);
    System.out.println("Curso eliminado: " + nombreCursoAEliminar);
    //controlador.actualizarVistaP();
    scanner3.close();
    //Cerrar la conexión al final (opcional, ya que el programa termina aquí)
    try {
      if (conexion != null && !conexion.isClosed()) {
        conexion.close();
        System.out.println("Conexión cerrada.");
      }
    } catch (Exception e) {
      System.out.println("Error al cerrar la conexión: " + e.getMessage());
    }


   
    //---------------------------------------------------------------------------------------------------------------



// VOLVERE A CARGAR ESTE CODIGO PARA DESCARTAR COMMMIT RESTRUCTUACION FX

}
}


  

