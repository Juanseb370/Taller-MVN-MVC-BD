package com.uniajc.mvn.controlador;

import java.util.List;

public class ControladorCurso {

    public ControladorCurso() {
    }

    // INSERTAR
    public void agregarCurso(Cursos curso) {
        Cursos.insertarCurso(curso);
        System.out.println("✅ Curso agregado: " + curso.getNombre());
    }

    // ACTUALIZAR
    public void actualizarCurso(String nombreOriginal, Cursos cursoActualizado) {
        Cursos.actualizarCurso(nombreOriginal, cursoActualizado);
        System.out.println("✏️ Curso actualizado: " + cursoActualizado.getNombre());
    }

    // ELIMINAR
    public void eliminarCurso(String nombre) {
        Cursos.eliminarCurso(nombre);
        System.out.println("🗑️ Curso eliminado: " + nombre);
    }

    // LISTAR
    public List<Cursos> listarTodosLosCursos() {
        return Cursos.obtenerTodosLosCursos();
    }

    // Mostrar en consola (si deseas crear una VistaCurso luego)
    public void actualizarVistaCursos() {
        List<Cursos> cursos = listarTodosLosCursos();
        cursos.forEach(c -> {
            System.out.println("Curso: " + c.getNombre() + " | Créditos: " + c.getCreditos());
        });
    }
}

