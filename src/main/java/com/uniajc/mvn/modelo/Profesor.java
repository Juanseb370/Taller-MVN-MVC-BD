package com.uniajc.mvn.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Profesor {
    private String nombre;
    private String materia;
    private int id_profesor;
    
    public Profesor() {
        
    }
    
    public Profesor(String nombre, String materia, int id) {
        this.nombre = nombre;
        this.materia = materia;
        this.id_profesor = id;
    }
    
    public int getId() {
        return this.id_profesor;
    } 
    
    public void setId(int id) {
        this.id_profesor = id;
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getMateria() {
        return this.materia;
    }
    
    public void setMateria(String materia) {
        this.materia = materia;
    }


    // METODO PARA CREAR Y GUARDAR PROFESOR EN LA BASE DE DATOS
    public static void insertarProfesor(Profesor profesor) {
        Connection connection = ConexionDatabase.getConnection();
        String sql = "INSERT INTO profesor (nombre, materia) VALUES (?, ?)";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, profesor.getNombre());
            preparedStatement.setString(2, profesor.getMateria());
            preparedStatement.executeUpdate();
            System.out.println("Profesor insertado: " + profesor.getNombre());
        } catch (SQLException e) {
            System.out.println("Error al insertar el profesor: " + e.getMessage());
        }
    }

    // METODO LISTAR PROFESORES
    public static List<Profesor> obtenerTodosLosProfesores() {
        List<Profesor> profesores = new ArrayList<>();
        String sql = "SELECT * FROM profesor";
        try {
            Connection connection = ConexionDatabase.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Profesor profesor = new Profesor();
                profesor.setId(resultSet.getInt("id_profesor"));
                profesor.setNombre(resultSet.getString("nombre"));
                profesor.setMateria(resultSet.getString("materia"));
                profesores.add(profesor);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los profesores: " + e.getMessage());
        }
        return profesores;
    }




    
}
