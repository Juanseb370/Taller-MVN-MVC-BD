package com.uniajc.mvn.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static final String URL = "jdbc:mysql://localhost:3306/taller_mvn"; // Cambia por el nombre de tu BD
    private static final String USER = "root";  // Cambia por tu usuario
    private static final String PASSWORD = "";  // Cambia por tu contraseña

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println(" Error al conectar con la base de datos: " + e.getMessage());
            return null;
        }
    }
}
