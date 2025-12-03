// java
// Archivo: `src/ui/Login.java`
package ui;

import javax.swing.*;

public class Login {
    private JTextField textField1;
    private JTextField textField2;
    private JButton loginButton;
    private JButton cancelarButton;
    private JLabel Pass;
    private JLabel login_correo;

    public void bind(Object usuarioService) {
        if (usuarioService == null) {
            JOptionPane.showMessageDialog(null, "UsuarioService es nulo", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        final JTextField userField = this.textField1;
        final JTextField passField = this.textField2;
        final JButton btnLogin = this.loginButton;
        final JButton btnCancel = this.cancelarButton;

        if (btnLogin != null) {
            for (java.awt.event.ActionListener al : btnLogin.getActionListeners()) {
                btnLogin.removeActionListener(al);
            }
            btnLogin.addActionListener(e -> {
                String email = userField != null ? userField.getText().trim() : "";
                String pass = passField != null ? passField.getText() : "";

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
                        java.awt.Window w = null;
                        if (userField != null) w = SwingUtilities.getWindowAncestor(userField);
                        if (w == null && passField != null) w = SwingUtilities.getWindowAncestor(passField);
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

        if (btnCancel != null) {
            for (java.awt.event.ActionListener al : btnCancel.getActionListeners()) {
                btnCancel.removeActionListener(al);
            }
            btnCancel.addActionListener(e -> {
                java.awt.Window w = null;
                if (userField != null) w = SwingUtilities.getWindowAncestor(userField);
                if (w == null && passField != null) w = SwingUtilities.getWindowAncestor(passField);
                if (w != null) w.dispose();
            });
        }
    }
}
