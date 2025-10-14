package com.uniajc.mvn.controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.uniajc.mvn.modelo.Profesor;
import com.uniajc.mvn.vista.VistaProfesor;

// ESTA CLASE ES SIMILAR A ControladorEstudiante PERO PARA PROFESORES
public class ControladorProfesor {
    private List<Profesor> profesores;
    private VistaProfesor vista;
    

    public ControladorProfesor(Profesor modelo, VistaProfesor vista) {
        this.vista = vista;
        this.profesores = new ArrayList<Profesor>();
    }   
    //ACTUALIZAR VISTA  
    public void actualizarVistaP() {
        List<Profesor> profesores = listarTodosLosProfesores();
        //IMPRIMIR EN CONSOLA
        vista.mostrarDetallesProfesor(profesores);
    }
    //AGREGAR PROFESOR
    public void agregarProfesor(Profesor profesor) {
        Profesor.insertarProfesor(profesor);
        System.out.println("Profesor agregado: " + profesor.getNombre());
    }

    //LISTAR TODOS LOS PROFESORES
    public List<Profesor> listarTodosLosProfesores() {
        return Profesor.obtenerTodosLosProfesores();
    }
    //ACTUALIZAR PROFESOR
    public void actualizarProfesor(String nombreOriginal, Profesor profesorActualizado) {
        Profesor.actualizarProfesor(nombreOriginal, profesorActualizado);
        System.out.println("Profesor actualizado: " + profesorActualizado.getNombre());
    }
    // ELIMINAR PROFESOR
    public void eliminarProfesor(String nombre) {
        Profesor.eliminarProfesor(nombre);
        System.out.println("Profesor eliminado: " + nombre);
    }

}