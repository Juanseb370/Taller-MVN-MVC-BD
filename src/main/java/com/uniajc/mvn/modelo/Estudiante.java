package com.uniajc.mvn.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

public class Estudiante {
  private String nombre;
  private int edad;
  private int id;

  public Estudiante(int id) {
    this.nombre = "";
    this.edad = 0;
    this.id = id;
  }

  public Estudiante(String nombre, int edad, int id) {
    this.nombre = nombre;
    this.edad = edad;
    this.id = id;
  }

  public int getId() {
    return this.id;
  } 


  public void setId(int id) {
    this.id = id;
  }


  public String getNombre() {
    return this.nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public int getEdad() {
    return this.edad;
  }

  public void setEdad(int edad) {
    this.edad = edad;
  }



  // METODO PARA CREAR Y GUARDAR ESTUDIANTE EN LA BASE DE DATOS
  public static void insertarEstudiante(Estudiante estudiante) {

    String sql = "INSERT INTO estudiante (nombre, edad, id) VALUES (?, ?, ?)";

    try {
      Connection conexion = ConexionDatabase.getConnection();

      PreparedStatement preparedStatement = conexion.prepareStatement(sql);
      PreparedStatement stmt = conexion.prepareStatement(sql);



      //ESTOS SON LOS STMT
      stmt.setString(1, estudiante.getNombre());
      stmt.setInt(2, estudiante.getEdad());
      stmt.setInt(3, estudiante.getId());
      stmt.executeUpdate();



      preparedStatement.setString(1, estudiante.getNombre());
      preparedStatement.setInt(2, estudiante.getEdad());
      preparedStatement.setInt(3, estudiante.getId());

      // Ejecutar la sentencias SQL INSERT, UPDATE o DELETE
      preparedStatement.executeUpdate();
    } catch (Exception e) {
      System.out.println("Error al insertar el estudiante: " + e.getMessage());
      e.printStackTrace();
    }

  }

  public static List<Estudiante> obtenerTodosLosEstudiantes() {

    List<Estudiante> estudiantes = new ArrayList<>();

    String sql = "SELECT nombre, edad FROM estudiante";

    try {
      Connection conexion = ConexionDatabase.getConnection();

      Statement statement = conexion.createStatement();

      // Ejecutar la sentencias SQL SELECT
      ResultSet resultSet = statement.executeQuery(sql);

      while (resultSet.next()) {
        String nombre = resultSet.getString("nombre");
        int edad = resultSet.getInt("edad");
        Estudiante estudiante = new Estudiante(nombre, edad, 0);
        estudiantes.add(estudiante);
      }

    } catch (Exception e) {
      System.out.println("Error al insertar el estudiante: " + e.getMessage());
      e.printStackTrace();
    }

    return estudiantes;
  }


  // ACTUALIZAR ESTUDIANTE
public static void actualizarEstudiante(String nombreOriginal, Estudiante estudianteActualizado) {
    String sql = "UPDATE estudiante SET nombre = ?, edad = ? WHERE nombre = ?";
    try {
        Connection conexion = ConexionDatabase.getConnection();
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, estudianteActualizado.getNombre());
        ps.setInt(2, estudianteActualizado.getEdad());
        ps.setString(3, nombreOriginal);
        ps.executeUpdate();
    } catch (Exception e) {
        System.out.println("Error al actualizar el estudiante: " + e.getMessage());
    }
}

//ELIMINAR ESTUDIANTE

public static void eliminarEstudiante(String nombre) {
    String sql = "DELETE FROM estudiante WHERE nombre = ?";
    try {
        Connection conexion = ConexionDatabase.getConnection();
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, nombre);
        ps.executeUpdate();
    } catch (Exception e) {
        System.out.println("Error al eliminar el estudiante: " + e.getMessage());
    }
}









}
