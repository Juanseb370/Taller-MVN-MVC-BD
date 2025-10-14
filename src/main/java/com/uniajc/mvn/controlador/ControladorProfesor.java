package com.uniajc.mvn.controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.uniajc.mvn.modelo.Estudiante;
import com.uniajc.mvn.vista.VistaEstudiante;

// ESTA CLASE ES SIMILAR A ControladorEstudiante PERO PARA PROFESORES
public class ControladorProfesor {
    private List<Estudiante> estudiantes;
    private VistaEstudiante vista;
    

    public ControladorProfesor(Estudiante modelo, VistaEstudiante vista) {
        this.vista = vista;
        this.estudiantes = new ArrayList<Estudiante>();
    }
    //ACTUALIZAR VISTA  
    public void actualizarVista() {
        List<Estudiante> estudiantes = listarTodosLosProfesores();
        //IMPRIMIR EN CONSOLA
        vista.mostrarDetallesEstudiante(estudiantes);
    }
    //AGREGAR PROFESOR
    public void agregarProfesor(Estudiante estudiante) {
        Estudiante.insertarEstudiante(estudiante);
        System.out.println("Estudiante agregado: " + estudiante.getNombre());
    }

    //LISTAR TODOS LOS PROFESORES
    public List<Estudiante> listarTodosLosProfesores() {
        return Estudiante.obtenerTodosLosEstudiantes();
    }
    //ACTUALIZAR PROFESOR
    public void actualizarProfesor(String nombreOriginal, Estudiante estudianteActualizado) {
        Estudiante.actualizarEstudiante(nombreOriginal, estudianteActualizado);
        System.out.println("Estudiante actualizado: " + estudianteActualizado.getNombre());
    }
    // ELIMINAR PROFESOR
    public void eliminarProfesor(String nombre) {
        Estudiante.eliminarEstudiante(nombre);
        System.out.println("Estudiante eliminado: " + nombre);
    }

}