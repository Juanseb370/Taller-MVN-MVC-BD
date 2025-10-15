package com.uniajc.mvn.controlador;

import java.util.ArrayList;
import java.util.List;

import com.uniajc.mvn.vista.VistaProfesor;

public class ControladorProfesor {
    private List<Profesor> profesores;
    private VistaProfesor vista;

    public ControladorProfesor(Profesor modelo, VistaProfesor vista) {
        this.vista = vista;
        this.profesores = new ArrayList<>();
    }

    // LISTAR
    public void actualizarVistaP() {
        List<Profesor> profesores = listarTodosLosProfesores();
        vista.mostrarDetallesProfesor(profesores);
    }

    // INSERTAR
    public void agregarProfesor(Profesor profesor) {
        Profesor.insertarProfesor(profesor);
        System.out.println("✅ Profesor agregado: " + profesor.getNombre());
    }

    // ACTUALIZAR
    public void actualizarProfesor(String nombreOriginal, Profesor profesorActualizado) {
        Profesor.actualizarProfesor(nombreOriginal, profesorActualizado);
        System.out.println("✏️ Profesor actualizado: " + profesorActualizado.getNombre());
    }

    // ELIMINAR
    public void eliminarProfesor(String nombre) {
        Profesor.eliminarProfesor(nombre);
        System.out.println("🗑️ Profesor eliminado: " + nombre);
    }

    // LISTAR
    public List<Profesor> listarTodosLosProfesores() {
        return Profesor.obtenerTodosLosProfesores();
    }
}
