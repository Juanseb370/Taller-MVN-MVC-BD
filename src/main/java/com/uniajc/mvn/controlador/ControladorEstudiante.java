package com.uniajc.mvn.controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.uniajc.mvn.modelo.Estudiante;
import com.uniajc.mvn.vista.VistaEstudiante;

public class ControladorEstudiante {
  private List<Estudiante> estudiantes;
  private VistaEstudiante vista;

  public ControladorEstudiante(Estudiante modelo, VistaEstudiante vista) {
    this.vista = vista;
    this.estudiantes = new ArrayList<Estudiante>();

  }
  //ACTUALIZAR VISTA  
  public void actualizarVista() {
    List<Estudiante> estudiantes = listarTodosLosEstudiantes();
    vista.mostrarDetallesEstudiante(estudiantes);
  }
  //AGREGAR ESTUDIANTE
  public void agregarEstudiante(Estudiante estudiante) {
    Estudiante.insertarEstudiante(estudiante);
    System.out.println("Estudiante agregado: " + estudiante.getNombre());
  }

  public List<Estudiante> listarTodosLosEstudiantes() {
    return Estudiante.obtenerTodosLosEstudiantes();
  }





  //ACTUALIZAR ESTUDIANTE

  public void actualizarEstudiante(String nombreOriginal, Estudiante estudianteActualizado) {
    Estudiante.actualizarEstudiante(nombreOriginal, estudianteActualizado);
    System.out.println("Estudiante actualizado: " + estudianteActualizado.getNombre());
}


// ELIMINAR ESTUDIANTE
public void eliminarEstudiante(String nombre) {
    Estudiante.eliminarEstudiante(nombre);
    System.out.println("Estudiante eliminado: " + nombre);
}

}
