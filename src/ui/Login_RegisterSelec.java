// java
package ui;

import servicio.UsuarioService;

import javax.swing.*;
import java.util.Arrays;

/*
 Corregido: se usan métodos reflectivos para instanciar diálogos sin
 referencias directas a clases que pueden no existir (ej: SwingUI).
*/
public class Login_RegisterSelec extends JFrame {
    private JPanel login;
    private JButton OKButton;   // botón para Login
    private JButton button1;    // botón para Register
    private JLabel Imag;
    private JLabel Login;
    private JLabel Register;
    private JLabel Princi_1;

    public Login_RegisterSelec(UsuarioService servicio) {
        super("Autenticación");
        setContentPane(login);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initActions(servicio);
        pack();
        setLocationRelativeTo(null);
    }

    private void initActions(UsuarioService servicio) {
        if (OKButton != null) {
            OKButton.addActionListener(e -> {
                // intentar abrir varios nombres comunes para el diálogo de login
                Object dlg = createInstance(this, servicio,
                        "SwingUI", "Login", "LoginDialog", "LoginForm");
                openIfWindow(dlg, "login", new String[] {"SwingUI","Login","LoginDialog","LoginForm"});
            });
        }
        if (button1 != null) {
            button1.addActionListener(e -> {
                // intentar abrir varios nombres comunes para el diálogo de registro
                Object dlg = createInstance(this, servicio,
                        "SwingRegister", "Register", "RegisterDialog", "RegisterForm");
                openIfWindow(dlg, "registro", new String[] {"SwingRegister","Register","RegisterDialog","RegisterForm"});
            });
        }
    }

    private void openIfWindow(Object dlg, String tipo, String[] triedNames) {
        if (dlg instanceof java.awt.Window) {
            ((java.awt.Window) dlg).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this,
                    "No se pudo abrir el diálogo de " + tipo + ". Se intentaron: " + Arrays.toString(triedNames),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Intenta instanciar una clase dentro del paquete ui probando varios constructores.
     * Devuelve la instancia o null si no se logró.
     */
    private static Object createInstance(Object owner, UsuarioService servicio, String... simpleNames) {
        for (String name : simpleNames) {
            String fqcn = "ui." + name;
            try {
                Class<?> cls = Class.forName(fqcn);

                // intentar varios constructores en orden razonable
                try {
                    java.lang.reflect.Constructor<?> c = cls.getConstructor(java.awt.Window.class, UsuarioService.class);
                    return c.newInstance(owner instanceof java.awt.Window ? owner : null, servicio);
                } catch (NoSuchMethodException ignored) {}

                try {
                    java.lang.reflect.Constructor<?> c = cls.getConstructor(java.awt.Frame.class, UsuarioService.class);
                    return c.newInstance(owner instanceof java.awt.Frame ? owner : null, servicio);
                } catch (NoSuchMethodException ignored) {}

                try {
                    java.lang.reflect.Constructor<?> c = cls.getConstructor(java.awt.Component.class, UsuarioService.class);
                    return c.newInstance(owner instanceof java.awt.Component ? owner : null, servicio);
                } catch (NoSuchMethodException ignored) {}

                try {
                    java.lang.reflect.Constructor<?> c = cls.getConstructor(UsuarioService.class);
                    return c.newInstance(servicio);
                } catch (NoSuchMethodException ignored) {}

                try {
                    java.lang.reflect.Constructor<?> c = cls.getConstructor();
                    return c.newInstance();
                } catch (NoSuchMethodException ignored) {}

            } catch (ClassNotFoundException ignored) {
                // la clase no existe — probar siguiente nombre
            } catch (Exception ex) {
                // si hay otro error al instanciar, intentar siguiente nombre
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UsuarioService servicio = new UsuarioService(); // ajustar según implementación real
            new Login_RegisterSelec(servicio).setVisible(true);
        });
    }
}
