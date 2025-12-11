package ui;

import javax.swing.*;
import java.awt.*;

public class Login {
    // Panel principal que contiene todos los componentes
    private JPanel mainPanel;
    private JTextField textField1;  // campo de email
    private JPasswordField textField2;  // campo de password
    private JButton loginButton;
    private JButton cancelarButton;
    private JLabel Pass;
    private JLabel login_correo;

    // Constructor que crea la interfaz
    public Login() {
        createUI();
    }

    /**
     * Crea la interfaz gráfica
     */
    private void createUI() {
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Label Email
        login_correo = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(login_correo, gbc);

        // Campo Email
        textField1 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        mainPanel.add(textField1, gbc);

        // Label Password
        Pass = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(Pass, gbc);

        // Campo Password
        textField2 = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(textField2, gbc);

        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        loginButton = new JButton("Login");
        cancelarButton = new JButton("Cancelar");
        buttonPanel.add(loginButton);
        buttonPanel.add(cancelarButton);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        mainPanel.add(buttonPanel, gbc);
    }

    /**
     * MÉTODO NUEVO: Devuelve el panel principal
     * Este método es el que necesita SwingUI para mostrar la interfaz
     */
    public JPanel getPanel() {
        return mainPanel;
    }

    /**
     * Vincula el servicio de usuario a los botones
     */
    public void bind(Object usuarioService) {
        if (usuarioService == null) {
            JOptionPane.showMessageDialog(null, "UsuarioService es nulo", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        final JTextField userField = this.textField1;
        final JPasswordField passField = this.textField2;
        final JButton btnLogin = this.loginButton;
        final JButton btnCancel = this.cancelarButton;

        // Limpiar listeners anteriores
        if (btnLogin != null) {
            for (java.awt.event.ActionListener al : btnLogin.getActionListeners()) {
                btnLogin.removeActionListener(al);
            }

            btnLogin.addActionListener(e -> {
                String email = userField != null ? userField.getText().trim() : "";
                String pass = passField != null ? new String(passField.getPassword()) : "";

                if (email.isEmpty() || pass.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Rellena email y password", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    java.lang.reflect.Method m = usuarioService.getClass().getMethod("login", String.class, String.class);
                    Object result = m.invoke(usuarioService, email, pass);

                    boolean ok;
                    if (result == null) ok = false;
                    else if (result instanceof Boolean) ok = (Boolean) result;
                    else ok = true; // si devuelve Usuario u otro objeto, considerarlo éxito

                    if (ok) {
                        String nombre = null;
                        if (result != null) {
                            try {
                                java.lang.reflect.Method getNombre = result.getClass().getMethod("getNombre");
                                Object n = getNombre.invoke(result);
                                if (n != null) nombre = n.toString();
                            } catch (Exception ignored) {}
                        }
                        JOptionPane.showMessageDialog(null, "Login correcto" + (nombre != null ? ": " + nombre : ""), "OK", JOptionPane.INFORMATION_MESSAGE);

                        // Cerrar la ventana
                        java.awt.Window w = SwingUtilities.getWindowAncestor(mainPanel);
                        if (w != null) w.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NoSuchMethodException nsme) {
                    JOptionPane.showMessageDialog(null, "UsuarioService no tiene login(String,String)", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error al invocar login: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        }

        // Botón Cancelar
        if (btnCancel != null) {
            for (java.awt.event.ActionListener al : btnCancel.getActionListeners()) {
                btnCancel.removeActionListener(al);
            }

            btnCancel.addActionListener(e -> {
                java.awt.Window w = SwingUtilities.getWindowAncestor(mainPanel);
                if (w != null) w.dispose();
            });
        }
    }
}