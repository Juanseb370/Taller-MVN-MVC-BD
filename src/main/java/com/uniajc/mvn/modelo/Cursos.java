package com.uniajc.mvn.modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Cursos {
    private int id;
    private String nombre;
    private int creditos;

    public Cursos() {}

    public Cursos(String nombre, int creditos) {
        this.nombre = nombre;
        this.creditos = creditos;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getCreditos() { return creditos; }
    public void setCreditos(int creditos) { this.creditos = creditos; }

    // ====== MÉTODOS JDBC ======

    public static void insertarCurso(Cursos c) {
        String sql = "INSERT INTO curso (nombre, creditos) VALUES (?, ?)";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNombre());
            ps.setInt(2, c.getCreditos());
            ps.executeUpdate();
            System.out.println(" Curso insertado correctamente.");

        } catch (SQLException ex) {
            System.out.println(" Error al insertar curso: " + ex.getMessage());
        }
    }

    public static void actualizarCurso(String nombreOriginal, Cursos c) {
        String sql = "UPDATE curso SET nombre = ?, creditos = ? WHERE nombre = ?";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNombre());
            ps.setInt(2, c.getCreditos());
            ps.setString(3, nombreOriginal);
            ps.executeUpdate();
            System.out.println(" Curso actualizado correctamente.");

        } catch (SQLException ex) {
            System.out.println(" Error al actualizar curso: " + ex.getMessage());
        }
    }

    public static void eliminarCurso(String nombre) {
        String sql = "DELETE FROM curso WHERE nombre = ?";
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.executeUpdate();
            System.out.println("🗑 Curso eliminado correctamente.");

        } catch (SQLException ex) {
            System.out.println(" Error al eliminar curso: " + ex.getMessage());
        }
    }

    public static List<Cursos> obtenerTodosLosCursos() {
        List<Cursos> lista = new ArrayList<>();
        String sql = "SELECT * FROM curso";

        try (Connection con = ConexionBD.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Cursos c = new Cursos();
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                c.setCreditos(rs.getInt("creditos"));
                lista.add(c);
            }

        } catch (SQLException ex) {
            System.out.println(" Error al obtener cursos: " + ex.getMessage());
        }

        return lista;
    }
}
