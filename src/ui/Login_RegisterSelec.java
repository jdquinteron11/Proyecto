package ui;

import servicio.UsuarioService;

import javax.swing.*;
import java.awt.*;

public class Login_RegisterSelec extends JFrame {
    private JPanel login;
    private JButton OKButton;
    private JButton button1;
    private JLabel Princi_1;

    public Login_RegisterSelec(UsuarioService servicio) {
        super("Autenticación");
        createModernUI();
        setContentPane(login);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initActions(servicio);
        setSize(500, 400);
        setLocationRelativeTo(null);
    }

    private void createModernUI() {
        login = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = getWidth(), h = getHeight();
                Color color1 = new Color(67, 97, 238);
                Color color2 = new Color(128, 90, 213);
                GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        login.setLayout(new GridBagLayout());
        login.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Panel blanco con sombra
        JPanel whitePanel = new JPanel(new GridBagLayout());
        whitePanel.setBackground(Color.WHITE);
        whitePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
                BorderFactory.createEmptyBorder(30, 30, 30, 30)
        ));

        GridBagConstraints wgbc = new GridBagConstraints();
        wgbc.insets = new Insets(10, 10, 10, 10);
        wgbc.fill = GridBagConstraints.HORIZONTAL;
        wgbc.gridx = 0;

        // Ícono/Logo
        JLabel iconLabel = new JLabel("🔐");
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 60));
        iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        wgbc.gridy = 0;
        whitePanel.add(iconLabel, wgbc);

        // Título
        Princi_1 = new JLabel("Bienvenido");
        Princi_1.setFont(new Font("Segoe UI", Font.BOLD, 28));
        Princi_1.setForeground(new Color(67, 97, 238));
        Princi_1.setHorizontalAlignment(SwingConstants.CENTER);
        wgbc.gridy = 1;
        whitePanel.add(Princi_1, wgbc);

        // Subtítulo
        JLabel subtitle = new JLabel("Inicia sesión o crea una cuenta");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(Color.GRAY);
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);
        wgbc.gridy = 2;
        wgbc.insets = new Insets(5, 10, 20, 10);
        whitePanel.add(subtitle, wgbc);

        // Botón Login
        OKButton = createModernButton("Iniciar Sesión", new Color(67, 97, 238), "👤");
        wgbc.gridy = 3;
        wgbc.insets = new Insets(10, 10, 10, 10);
        whitePanel.add(OKButton, wgbc);

        // Botón Register
        button1 = createModernButton("Registrarse", new Color(128, 90, 213), "✨");
        wgbc.gridy = 4;
        whitePanel.add(button1, wgbc);

        // Agregar panel blanco al panel principal
        gbc.gridx = 0;
        gbc.gridy = 0;
        login.add(whitePanel, gbc);
    }

    private JButton createModernButton(String text, Color bgColor, String emoji) {
        JButton button = new JButton(emoji + "  " + text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(bgColor);
        button.setPreferredSize(new Dimension(280, 50));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });

        return button;
    }

    private void initActions(UsuarioService servicio) {
        if (OKButton != null) {
            OKButton.addActionListener(e -> {
                SwingUI loginDialog = new SwingUI(this, servicio);
                loginDialog.setVisible(true);
            });
        }

        if (button1 != null) {
            button1.addActionListener(e -> {
                SwingRegister registerDialog = new SwingRegister(this, servicio);
                registerDialog.setVisible(true);
            });
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            UsuarioService servicio = new UsuarioService();
            new Login_RegisterSelec(servicio).setVisible(true);
        });
    }
}