package com.uniajc.mvn.modelo;

import java.util.List;
import java.util.ArrayList;


public class Cursos {
    private int id;
    private String nombre;
    private String descripcion;
    
    public Cursos() {
    }
    
    public Cursos(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    


    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    @Override
    public String toString() {
        return "Cursos [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
    }


  
   

  


    // METODO PARA CREAR Y GUARDAR CURSO EN LA BASE DE DATOS
    public boolean validarDatos() {
        return nombre != null && !nombre.isEmpty() && descripcion != null && !descripcion.isEmpty();
    }

    //METODO PARA INSERTAR CURSO EN LA BASE DE DATOS
    public static void insertarCurso(Cursos curso) {
        if (curso == null || !curso.validarDatos()) {
            System.out.println("Datos del curso no son validos.");
            return;
        }

        String sql = "INSERT INTO cursos (nombre, descripcion) VALUES (?, ?)";

        try {
            java.sql.Connection conn = ConexionDatabase.getConnection();
            java.sql.PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, curso.getNombre());
            pstmt.setString(2, curso.getDescripcion());
            pstmt.executeUpdate();
            System.out.println("Curso insertado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al insertar el curso: " + e.getMessage());
        }
    }






    // // Metodo para inscribir un estudiante al curso
    // public void inscribirEstudiante(Estudiante estudiante) {
    //     if (estudiante != null) {
    //         System.out.println("Estudiante " + estudiante.getNombre() + " inscrito en el curso " + nombre);
    //     } else {
    //         System.out.println("No se puede inscribir un estudiante nulo.");
    //     }
    // }

    // // Metodo para mostrar informacion del curso
    // public void mostrarInformacion() {
    //     System.out.println("ID del curso: " + id);
    //     System.out.println("Nombre del curso: " + nombre);
    //     System.out.println("Descripción del curso: " + descripcion);
    // }


    // METODO PARA ACTUALIZAR NOMBRE Y DESCRIPCION DEL CURSO EN LA BASE DE DATOS
    public static void actualizarCurso(String nombreOriginal, Cursos cursoActualizado) {
        if (cursoActualizado == null || !cursoActualizado.validarDatos()) {
            System.out.println("Datos del curso no son validos.");
            return;
        }

        String sql = "UPDATE cursos SET nombre = ?, descripcion = ? WHERE nombre = ?";

        try {
            java.sql.Connection conn = ConexionDatabase.getConnection();
            java.sql.PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cursoActualizado.getNombre());
            pstmt.setString(2, cursoActualizado.getDescripcion());
            pstmt.setString(3, nombreOriginal);
            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Curso actualizado exitosamente.");
            } else {
                System.out.println("No se encontró un curso con el nombre proporcionado.");
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar el curso: " + e.getMessage());
        }
    }
    
    // METODO PARA ELIMINAR CURSO DE LA BASE DE DATOS
    public static void eliminarCurso(String nombreC) {
        String sql = "DELETE FROM cursos WHERE nombre = ?";

        try {
            java.sql.Connection conn = ConexionDatabase.getConnection();
            java.sql.PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nombreC);
            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Curso eliminado exitosamente.");
            } else {
                System.out.println("No se encontró un curso con el nombre proporcionado.");
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar el curso: " + e.getMessage());
        }
    }


    
}
