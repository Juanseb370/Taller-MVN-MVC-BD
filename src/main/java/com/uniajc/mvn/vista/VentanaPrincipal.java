package com.uniajc.mvn.vista;


import java.sql.Connection;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import com.uniajc.mvn.controlador.ControladorEstudiante;
import com.uniajc.mvn.controlador.ControladorProfesor;

import com.uniajc.mvn.modelo.ConexionDatabase;
import com.uniajc.mvn.modelo.Cursos;
import com.uniajc.mvn.modelo.Estudiante;
import com.uniajc.mvn.modelo.Profesor;

import com.uniajc.mvn.vista.VistaEstudiante;
import com.uniajc.mvn.vista.VistaProfesor;








public class VentanaPrincipal extends JFrame {

    private JTextArea areaTexto;

    public VentanaPrincipal() {
        setTitle("Gestión Académica - Uniajc");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel con pestañas
        JTabbedPane pestañas = new JTabbedPane();

        pestañas.addTab("Estudiantes", crearPanelEstudiantes());
        pestañas.addTab("Profesores", crearPanelProfesores());
        pestañas.addTab("Cursos", crearPanelCursos());

        add(pestañas);
    }

    // ---------------- PANEL ESTUDIANTES ----------------
    private JPanel crearPanelEstudiantes() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel panelCampos = new JPanel(new GridLayout(3, 2, 5, 5));

        JTextField txtNombre = new JTextField();
        JTextField txtEdad = new JTextField();
        JTextField txtBuscar = new JTextField();

        panelCampos.add(new JLabel("Nombre:"));
        panelCampos.add(txtNombre);
        panelCampos.add(new JLabel("Edad:"));
        panelCampos.add(txtEdad);
        panelCampos.add(new JLabel("Nombre a buscar/actualizar/eliminar:"));
        panelCampos.add(txtBuscar);

        JPanel panelBotones = new JPanel();
        JButton btnAgregar = new JButton("Agregar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");

        areaTexto = new JTextArea(10, 50);
        areaTexto.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaTexto);

        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);

        panel.add(panelCampos, BorderLayout.NORTH);
        panel.add(panelBotones, BorderLayout.CENTER);
        panel.add(scroll, BorderLayout.SOUTH);

        ControladorEstudiante controlador = new ControladorEstudiante(null, null);

        // Acción Agregar
        btnAgregar.addActionListener(e -> {
            try {
                String nombre = txtNombre.getText();
                int edad = Integer.parseInt(txtEdad.getText());
                Estudiante est = new Estudiante(nombre, edad, 0);
                controlador.agregarEstudiante(est);
                areaTexto.append(" Estudiante agregado: " + nombre + " (" + edad + " años)\n");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Error al agregar estudiante.");
            }
        });

        // Acción Actualizar
        btnActualizar.addActionListener(e -> {
            try {
                String original = txtBuscar.getText();
                String nuevoNombre = txtNombre.getText();
                int nuevaEdad = Integer.parseInt(txtEdad.getText());
                Estudiante nuevo = new Estudiante(nuevoNombre, nuevaEdad, 0);
                controlador.actualizarEstudiante(original, nuevo);
                areaTexto.append(" Estudiante actualizado: " + nuevoNombre + "\n");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Error al actualizar estudiante.");
            }
        });

        // Acción Eliminar
        btnEliminar.addActionListener(e -> {
            try {
                String nombreEliminar = txtBuscar.getText();
                controlador.eliminarEstudiante(nombreEliminar);
                areaTexto.append(" Estudiante eliminado: " + nombreEliminar + "\n");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Error al eliminar estudiante.");
            }
        });

        return panel;
    }

    // ---------------- PANEL PROFESORES ----------------
    private JPanel crearPanelProfesores() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel panelCampos = new JPanel(new GridLayout(3, 2, 5, 5));

        JTextField txtNombre = new JTextField();
        JTextField txtMateria = new JTextField();
        JTextField txtBuscar = new JTextField();

        panelCampos.add(new JLabel("Nombre:"));
        panelCampos.add(txtNombre);
        panelCampos.add(new JLabel("Materia:"));
        panelCampos.add(txtMateria);
        panelCampos.add(new JLabel("Nombre a buscar/actualizar/eliminar:"));
        panelCampos.add(txtBuscar);

        JPanel panelBotones = new JPanel();
        JButton btnAgregar = new JButton("Agregar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");

        JTextArea area = new JTextArea(10, 50);
        area.setEditable(false);
        JScrollPane scroll = new JScrollPane(area);

        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);

        panel.add(panelCampos, BorderLayout.NORTH);
        panel.add(panelBotones, BorderLayout.CENTER);
        panel.add(scroll, BorderLayout.SOUTH);

        // Botones funcionales
        btnAgregar.addActionListener(e -> {
            String nombre = txtNombre.getText();
            String materia = txtMateria.getText();
            Profesor profesor = new Profesor(nombre, materia, 0);
            Profesor.insertarProfesor(profesor);
            area.append(" Profesor agregado: " + nombre + " - " + materia + "\n");
        });

        btnActualizar.addActionListener(e -> {
            String original = txtBuscar.getText();
            String nuevoNombre = txtNombre.getText();
            String nuevaMateria = txtMateria.getText();
            Profesor profAct = new Profesor(nuevoNombre, nuevaMateria, 0);
            Profesor.actualizarProfesor(original, profAct);
            area.append(" Profesor actualizado: " + nuevoNombre + "\n");
        });

        btnEliminar.addActionListener(e -> {
            String nombreEliminar = txtBuscar.getText();
            Profesor.eliminarProfesor(nombreEliminar);
            area.append(" Profesor eliminado: " + nombreEliminar + "\n");
        });

        return panel;
    }

    // ---------------- PANEL CURSOS ----------------
    private JPanel crearPanelCursos() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel panelCampos = new JPanel(new GridLayout(3, 2, 5, 5));

        JTextField txtNombre = new JTextField();
        JTextField txtDescripcion = new JTextField();
        JTextField txtBuscar = new JTextField();

        panelCampos.add(new JLabel("Nombre del Curso:"));
        panelCampos.add(txtNombre);
        panelCampos.add(new JLabel("Descripción:"));
        panelCampos.add(txtDescripcion);
        panelCampos.add(new JLabel("Nombre a buscar/actualizar/eliminar:"));
        panelCampos.add(txtBuscar);

        JPanel panelBotones = new JPanel();
        JButton btnAgregar = new JButton("Agregar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");

        JTextArea area = new JTextArea(10, 50);
        area.setEditable(false);
        JScrollPane scroll = new JScrollPane(area);

        panelBotones.add(btnAgregar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);

        panel.add(panelCampos, BorderLayout.NORTH);
        panel.add(panelBotones, BorderLayout.CENTER);
        panel.add(scroll, BorderLayout.SOUTH);

        // Botones funcionales
        btnAgregar.addActionListener(e -> {
            String nombre = txtNombre.getText();
            String descripcion = txtDescripcion.getText();
            Cursos curso = new Cursos(0, nombre, descripcion);
            Cursos.insertarCurso(curso);
            area.append(" Curso agregado: " + nombre + "\n");
        });

        btnActualizar.addActionListener(e -> {
            String original = txtBuscar.getText();
            String nuevoNombre = txtNombre.getText();
            String nuevaDesc = txtDescripcion.getText();
            Cursos nuevo = new Cursos(0, nuevoNombre, nuevaDesc);
            Cursos.actualizarCurso(original, nuevo);
            area.append(" Curso actualizado: " + nuevoNombre + "\n");
        });

        btnEliminar.addActionListener(e -> {
            String nombreEliminar = txtBuscar.getText();
            Cursos.eliminarCurso(nombreEliminar);
            area.append(" Curso eliminado: " + nombreEliminar + "\n");
        });

        return panel;
    }

    // ---------------- MAIN ----------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
        });
    }
}
