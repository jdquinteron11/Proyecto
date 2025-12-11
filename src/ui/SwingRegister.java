package ui;

import servicio.UsuarioService;
import modelo.Rol;

import javax.swing.*;
import java.awt.*;

public class SwingRegister extends JDialog {
    private JPanel Mainpad;
    private JTextField nombreField;
    private JTextField apellidoField;
    private JTextField edadField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JComboBox<Rol> rolComboBox;
    private JButton registerButton;

    public SwingRegister(Frame owner, UsuarioService servicio) {
        super(owner, "Registro", true);
        createModernUI();
        setContentPane(Mainpad);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(owner);

        registerButton.addActionListener(e -> onRegister(servicio));
    }

    private void createModernUI() {
        Mainpad = new JPanel(new GridBagLayout());
        Mainpad.setBackground(Color.WHITE);
        Mainpad.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título
        JLabel titleLabel = new JLabel("✨ Crear Nueva Cuenta");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(128, 90, 213));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 20, 0);
        Mainpad.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.insets = new Insets(8, 10, 8, 10);

        // Nombre
        addField("👤 Nombre:", gbc, 1);
        nombreField = new JTextField(20);
        styleTextField(nombreField);
        gbc.gridx = 1;
        Mainpad.add(nombreField, gbc);

        // Apellido
        addField("👤 Apellido:", gbc, 2);
        apellidoField = new JTextField(20);
        styleTextField(apellidoField);
        gbc.gridx = 1;
        Mainpad.add(apellidoField, gbc);

        // Edad
        addField("🎂 Edad:", gbc, 3);
        edadField = new JTextField(20);
        styleTextField(edadField);
        gbc.gridx = 1;
        Mainpad.add(edadField, gbc);

        // Email
        addField("📧 Email:", gbc, 4);
        emailField = new JTextField(20);
        styleTextField(emailField);
        gbc.gridx = 1;
        Mainpad.add(emailField, gbc);

        // Password
        addField("🔑 Contraseña:", gbc, 5);
        passwordField = new JPasswordField(20);
        styleTextField(passwordField);
        gbc.gridx = 1;
        Mainpad.add(passwordField, gbc);

        // Confirmar Password
        addField("🔒 Confirmar:", gbc, 6);
        confirmPasswordField = new JPasswordField(20);
        styleTextField(confirmPasswordField);
        gbc.gridx = 1;
        Mainpad.add(confirmPasswordField, gbc);

        // Rol
        addField("👔 Rol:", gbc, 7);
        rolComboBox = new JComboBox<>(Rol.values());
        rolComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        rolComboBox.setBackground(Color.WHITE);
        gbc.gridx = 1;
        Mainpad.add(rolComboBox, gbc);

        // Botón Registrar
        registerButton = new JButton("Crear Cuenta");
        registerButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        registerButton.setForeground(Color.WHITE);
        registerButton.setBackground(new Color(128, 90, 213));
        registerButton.setPreferredSize(new Dimension(200, 45));
        registerButton.setFocusPainted(false);
        registerButton.setBorderPainted(false);
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        registerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerButton.setBackground(new Color(148, 110, 233));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registerButton.setBackground(new Color(128, 90, 213));
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        Mainpad.add(registerButton, gbc);
    }

    private void addField(String labelText, GridBagConstraints gbc, int row) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setForeground(new Color(80, 80, 80));
        gbc.gridx = 0;
        gbc.gridy = row;
        Mainpad.add(label, gbc);
    }

    private void styleTextField(JTextField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
    }

    private void onRegister(UsuarioService servicio) {
        // Validar campos vacíos
        String nombre = nombreField.getText().trim();
        String apellido = apellidoField.getText().trim();
        String edadStr = edadField.getText().trim();
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        Rol rol = (Rol) rolComboBox.getSelectedItem();

        if (nombre.isEmpty() || apellido.isEmpty() || edadStr.isEmpty() ||
                email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "⚠️ Por favor completa todos los campos",
                    "Campos incompletos",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validar edad
        int edad;
        try {
            edad = Integer.parseInt(edadStr);
            if (edad < 1 || edad > 120) {
                JOptionPane.showMessageDialog(this,
                        "⚠️ Edad debe estar entre 1 y 120",
                        "Edad inválida",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "⚠️ La edad debe ser un número válido",
                    "Error de formato",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validar contraseñas coincidan
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this,
                    "⚠️ Las contraseñas no coinciden",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validar longitud de contraseña
        if (password.length() < 4) {
            JOptionPane.showMessageDialog(this,
                    "⚠️ La contraseña debe tener al menos 4 caracteres",
                    "Contraseña débil",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Registrar usuario
        boolean success = servicio.register(nombre, apellido, edad, email, password, rol);

        if (success) {
            JOptionPane.showMessageDialog(this,
                    "✅ ¡Registro exitoso!\n\n" +
                            "📧 Email: " + email + "\n" +
                            "🔑 Contraseña: " + password + "\n" +
                            "👔 Rol: " + rol + "\n\n" +
                            "⚠️ IMPORTANTE: Guarda tu contraseña, la necesitarás para iniciar sesión",
                    "Registro completado",
                    JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "❌ Error: El email ya está registrado",
                    "Error de registro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}