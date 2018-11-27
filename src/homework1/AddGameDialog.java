package homework1;

import javax.swing.*;
import java.awt.*;

public class AddGameDialog extends JDialog {
    private GameModel mod;
    private JPanel grid = new JPanel(new GridLayout(4, 2, 5, 5));

    private TextField nameField = new TextField(18);
    private TextField devField = new TextField(18);
    private JFormattedTextField priceField = new JFormattedTextField();
    private JComboBox<Genre> genreBox = new JComboBox<>();

    private JLabel nameLabel = new JLabel("Game name");
    private JLabel devLabel = new JLabel("Developer name");
    private JLabel priceLabel = new JLabel("Price");
    private JLabel genreLabel = new JLabel("Genre");

    private JButton addButton = new JButton("Add");
    private JButton cancelButton = new JButton("Cancel");


    public AddGameDialog(Frame owner, String title, boolean modal, GameModel model) {
        super(owner, title, modal);
        mod = model;
        setLayout(new FlowLayout());
        setLocationRelativeTo(owner);
        setSize(350, 200);
        setResizable(false);
        addContent();
    }

    private void addContent() {
        configGrid();
        add(grid);
        configButtons();
        add(addButton);
        add(cancelButton);
    }

    private void configGrid() {
        grid.add(nameLabel);
        grid.add(nameField);

        grid.add(devLabel);
        grid.add(devField);

        priceField.setToolTipText("Should be positive integer");
        grid.add(priceLabel);
        grid.add(priceField);

        grid.add(genreLabel);
        genreBox.setModel(new DefaultComboBoxModel<>(Genre.values()));
        grid.add(genreBox);
    }

    private void configButtons() {
        addButton.addActionListener(e -> {
            if (Validation.someIsEmpty(nameField.getText(), priceField.getText(), devField.getText())) {
                JOptionPane.showMessageDialog(this, "One(or more) field is empty!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (Validation.isValidData(priceField.getText())) {
                mod.addGame(nameField.getText(), Integer.parseInt(priceField.getText()),
                        devField.getText(), Genre.valueOf(genreBox.getSelectedItem().toString()));
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Data is not valid. Check fields.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });
        cancelButton.addActionListener(e -> dispose());
    }
}
