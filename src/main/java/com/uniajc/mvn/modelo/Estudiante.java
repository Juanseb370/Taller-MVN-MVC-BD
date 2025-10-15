package com.uniajc.mvn.modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Estudiante {
    private int id;
    private String nombre;
    private int edad;

    // Constructor vacío
    public Estudiante() {}

    // Constructor con parámetros
    public Estudiante(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    // ====== MÉTODOS JDBC ======

    public static void insertarEstudiante(Estudiante e) {
        String sql = "INSERT INTO estudiante (nombre, edad) VALUES (?, ?)";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, e.getNombre());
            ps.setInt(2, e.getEdad());
            ps.executeUpdate();
            System.out.println(" Estudiante insertado correctamente.");

        } catch (SQLException ex) {
            System.out.println(" Error al insertar estudiante: " + ex.getMessage());
        }
    }

    public static void actualizarEstudiante(String nombreOriginal, Estudiante e) {
        String sql = "UPDATE estudiante SET nombre = ?, edad = ? WHERE nombre = ?";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, e.getNombre());
            ps.setInt(2, e.getEdad());
            ps.setString(3, nombreOriginal);
            ps.executeUpdate();
            System.out.println("✏ Estudiante actualizado correctamente.");

        } catch (SQLException ex) {
            System.out.println(" Error al actualizar estudiante: " + ex.getMessage());
        }
    }

    public static void eliminarEstudiante(String nombre) {
        String sql = "DELETE FROM estudiante WHERE nombre = ?";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.executeUpdate();
            System.out.println("️ Estudiante eliminado correctamente.");

        } catch (SQLException ex) {
            System.out.println(" Error al eliminar estudiante: " + ex.getMessage());
        }
    }

    public static List<Estudiante> obtenerTodosLosEstudiantes() {
        List<Estudiante> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudiante";

        try (Connection con = ConexionBD.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Estudiante e = new Estudiante();
                e.setId(rs.getInt("id"));
                e.setNombre(rs.getString("nombre"));
                e.setEdad(rs.getInt("edad"));
                lista.add(e);
            }

        } catch (SQLException ex) {
            System.out.println(" Error al obtener estudiantes: " + ex.getMessage());
        }

        return lista;
    }
}
