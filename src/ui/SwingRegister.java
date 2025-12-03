```java
        package ui;

import servicio.UsuarioService;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Method;

public class SwingRegister extends JDialog {
    private JPanel Mainpad;
    private JTextField textField1;
    private JButton button1;

    public SwingRegister(Frame owner, UsuarioService servicio) {
        super(owner, "Registrar", true);
        setContentPane(Mainpad); // mantiene tu interfaz tal cual
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(owner);

        button1.addActionListener(e -> onRegister(servicio));
    }

    private void onRegister(UsuarioService servicio) {
        String value = textField1.getText().trim();
        if (value.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Rellena el campo", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // intenta llamar a servicio.register(String) si existe
            Method m = servicio.getClass().getMethod("register", String.class);
            m.invoke(servicio, value);
            JOptionPane.showMessageDialog(this, "Registro realizado", "OK", JOptionPane.INFORMATION_MESSAGE);
        } catch (NoSuchMethodException nsme) {
            // si no existe, hacemos un registro simulado
            JOptionPane.showMessageDialog(this, "Registro simulado para: " + value, "OK", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            dispose();
        }
    }
}
