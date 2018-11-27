package homework1;

import javax.swing.*;
import java.awt.*;

//написал до того, как нашёл JOptionPane(((
@Deprecated
public class WarningDialog extends JDialog {

    public WarningDialog(JDialog owner, String title, boolean modal, String text) {
        super(owner, title, modal);
        setSize(200,75);
        setLayout(new FlowLayout());
        setResizable(false);
        add(new JLabel(text), BorderLayout.CENTER);
        setLocationRelativeTo(owner);
        setVisible(true);
    }
}
