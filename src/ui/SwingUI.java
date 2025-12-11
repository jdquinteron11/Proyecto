package ui;

import servicio.UsuarioService;

import javax.swing.*;
import java.awt.*;

public class SwingUI extends JDialog {
    private Login loginPanel;

    public SwingUI(Frame owner, UsuarioService servicio) {
        super(owner, "Iniciar Sesión", true);

        // Crear el panel de login
        loginPanel = new Login();
        loginPanel.bind(servicio);

        // Configurar el diálogo con estilo moderno
        setContentPane(loginPanel.getPanel());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setUndecorated(false);

        // Agregar sombra y bordes redondeados (efecto visual)
        getRootPane().setBorder(BorderFactory.createLineBorder(new Color(67, 97, 238), 2));

        pack();
        setLocationRelativeTo(owner);
    }
}