
        package ui;

import javax.swing.*;
import java.awt.*;

public class DialogWrapper extends JDialog {
    public DialogWrapper(Frame owner, JPanel content, String title) {
        super(owner, title, true);
        setContentPane(content);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(owner);
    }

    public void showDialog() {
        setVisible(true);
    }
}
