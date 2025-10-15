package com.uniajc.mvn.modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Profesor {
    private int id;
    private String nombre;
    private String materia;

    // Constructores
    public Profesor() {}

    public Profesor(String nombre, String materia) {
        this.nombre = nombre;
        this.materia = materia;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getMateria() { return materia; }
    public void setMateria(String materia) { this.materia = materia; }

    // ====== MÉTODOS JDBC ======

    public static void insertarProfesor(Profesor p) {
        String sql = "INSERT INTO profesor (nombre, materia) VALUES (?, ?)";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getMateria());
            ps.executeUpdate();
            System.out.println(" Profesor insertado correctamente.");

        } catch (SQLException ex) {
            System.out.println(" Error al insertar profesor: " + ex.getMessage());
        }
    }

    public static void actualizarProfesor(String nombreOriginal, Profesor p) {
        String sql = "UPDATE profesor SET nombre = ?, materia = ? WHERE nombre = ?";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getMateria());
            ps.setString(3, nombreOriginal);
            ps.executeUpdate();
            System.out.println("✏ Profesor actualizado correctamente.");

        } catch (SQLException ex) {
            System.out.println(" Error al actualizar profesor: " + ex.getMessage());
        }
    }

    public static void eliminarProfesor(String nombre) {
        String sql = "DELETE FROM profesor WHERE nombre = ?";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.executeUpdate();
            System.out.println("🗑 Profesor eliminado correctamente.");

        } catch (SQLException ex) {
            System.out.println(" Error al eliminar profesor: " + ex.getMessage());
        }
    }

    public static List<Profesor> obtenerTodosLosProfesores() {
        List<Profesor> lista = new ArrayList<>();
        String sql = "SELECT * FROM profesor";

        try (Connection con = ConexionBD.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Profesor p = new Profesor();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setMateria(rs.getString("materia"));
                lista.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(" Error al obtener profesores: " + ex.getMessage());
        }

        return lista;
    }
}
