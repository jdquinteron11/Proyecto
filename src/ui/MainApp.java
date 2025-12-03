// java
package ui;

import servicio.UsuarioService;

import javax.swing.*;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Opcional: usar L&F del sistema
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}

            // Crear el servicio (ajusta si tu constructor difiere)
            UsuarioService servicio = new UsuarioService();

            // Mostrar la ventana seleccionadora
            Login_RegisterSelec selector = new Login_RegisterSelec(servicio);
            selector.setVisible(true);
        });
    }
}
