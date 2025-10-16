package com.uniajc.mvn;

import javax.swing.SwingUtilities;
import com.uniajc.mvn.vista.VentanaPrincipal;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
        });
    }
}
