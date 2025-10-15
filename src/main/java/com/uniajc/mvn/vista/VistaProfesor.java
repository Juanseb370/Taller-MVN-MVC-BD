package com.uniajc.mvn.vista;

import java.util.List;

public class VistaProfesor {
    public void mostrarDetallesProfesor(List<Profesor> profesores) {
        profesores.forEach(profesor -> {
            System.out.println(" Imprimiendo desde la clase vista: ");
            System.out.println("Nombre: " + profesor.getNombre() + " - " + "Edad: " + profesor.getMateria() + " - " + "ID: " + profesor.getId());
        });
    }   
}