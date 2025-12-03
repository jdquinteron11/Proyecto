// java
package ui;

import servicio.UsuarioService;
import modelo.Usuario;

import javax.swing.*;
import java.awt.*;

public class SwingUI extends JDialog {
    private final JTextField tfEmail = new JTextField(20);
    private final JPasswordField pfPassword = new JPasswordField(20);
    private final JButton btnLogin = new JButton("Iniciar sesión");
    private final JButton btnClose = new JButton("Cerrar");

    public SwingUI(Frame owner, UsuarioService servicio) {
        super(owner, "Login", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initLayout(servicio);
        pack();
        setLocationRelativeTo(owner);
    }

    private void initLayout(UsuarioService servicio) {
        JPanel panel = new JPanel(new BorderLayout(8, 8));

        JPanel fields = new JPanel(new GridLayout(2, 2, 6, 6));
        fields.add(new JLabel("Email:"));
        fields.add(tfEmail);
        fields.add(new JLabel("Password:"));
        fields.add(pfPassword);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttons.add(btnLogin);
        buttons.add(btnClose);

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(fields, BorderLayout.CENTER);
        panel.add(buttons, BorderLayout.SOUTH);

        btnLogin.addActionListener(e -> {
            String email = tfEmail.getText().trim();
            String pass = new String(pfPassword.getPassword());
            if (email.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Rellena email y password", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Usuario u = null;
            try {
                u = servicio.login(email, pass);
            } catch (Exception ignored) {}
            if (u != null) {
                JOptionPane.showMessageDialog(this, "Login correcto: " + u.getNombre(), "OK", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnClose.addActionListener(e -> dispose());

        setContentPane(panel);
    }
}
