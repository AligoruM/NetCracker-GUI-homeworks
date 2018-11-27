package homework1;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Swing extends JFrame {
    private GameModel gameModel;
    private JTable table;

    public Swing() {
        super("Game Library");
        gameModel = new GameModel();
        configFrame();
        createAndConfigTable();
        createAndConfigButtons();
        setVisible(true);
    }

    private void configFrame() {
        setSize(400, 300);
        setLocation(150, 100);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setMinimumSize(new Dimension(400, 300));
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                switch (JOptionPane.showConfirmDialog(Swing.this,
                        "Do you want to save data?", "Close Window?",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE)) {
                    case JOptionPane.YES_OPTION:
                        gameModel.saveToDefaultFile();
                        System.exit(0);
                    case JOptionPane.NO_OPTION:
                        System.exit(0);
                        break;
                    case JOptionPane.CANCEL_OPTION:
                        break;
                }
            }
        });
    }

    private void createAndConfigTable() {
        table = new JTable(gameModel);
        setLayout(new BorderLayout());
        JScrollPane jScrollPane = new JScrollPane(table);
        add(jScrollPane);
        table.getColumn("Genre").setCellEditor(new GenreEditor(new JComboBox<>(Genre.values())));
        table.getColumn("Game name").setCellEditor(new NameEditor(new JTextField()));
        table.getColumn("Developer").setCellEditor(new DevEditor(new JTextField()));
        table.getColumn("Price").setCellEditor(new PriceEditor(new JTextField()));
    }

    private void createAndConfigButtons() {
        JPanel grid = new JPanel(new GridLayout(1, 2, 5, 0));
        JPanel flow = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        flow.add(grid);
        add(flow, BorderLayout.SOUTH);

        JButton addButton = new JButton("Add game");
        JButton delButton = new JButton("Delete game");

        addButton.addActionListener(e -> {
            JDialog dia = new AddGameDialog(Swing.this, "Add game", true, gameModel);
            dia.setVisible(true);
        });

        delButton.addActionListener(e -> {
            if (table.getSelectedRow() != -1){
                switch (JOptionPane.showConfirmDialog(Swing.this,
                        "Are you sure you want to delete this game from library?", "Delete game?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE)) {
                    case JOptionPane.YES_OPTION:
                        gameModel.deleteGameByIndex(table.getSelectedRow());
                        break;
                    default:
                        break;
                }
        }
        });

        addButton.setToolTipText("Add some game");
        delButton.setToolTipText("Delete chosen game");


        grid.add(addButton);
        grid.add(delButton);
    }

    private class PriceEditor extends DefaultCellEditor {
        private PriceEditor(JTextField textField) {
            super(textField);
            addCellEditorListener(new CellEditorListener() {
                @Override
                public void editingStopped(ChangeEvent e) {
                    if (!getCellEditorValue().toString().isEmpty())
                        if(Validation.isValidData(getCellEditorValue().toString()))
                            gameModel.change(getCellEditorValue().toString(), table.getSelectedRow(), 3);
                        else JOptionPane.showMessageDialog(table, "Should be non-negative integer!", "Warning", JOptionPane.WARNING_MESSAGE);
                    else JOptionPane.showMessageDialog(table, "Can not be empty!", "Warning", JOptionPane.WARNING_MESSAGE);

                }

                @Override
                public void editingCanceled(ChangeEvent e) {

                }
            });
        }
    }

    private class NameEditor extends DefaultCellEditor {
        private NameEditor(JTextField textField) {
            super(textField);
            addCellEditorListener(new CellEditorListener() {
                @Override
                public void editingStopped(ChangeEvent e) {
                    if (!getCellEditorValue().toString().isEmpty())
                        gameModel.change(getCellEditorValue().toString(), table.getSelectedRow(), 0);
                    else JOptionPane.showMessageDialog(table, "Can not be empty!", "Warning", JOptionPane.WARNING_MESSAGE);
                }

                @Override
                public void editingCanceled(ChangeEvent e) {

                }
            });
        }
    }

    private class DevEditor extends DefaultCellEditor {
        private DevEditor(JTextField textField) {
            super(textField);
            addCellEditorListener(new CellEditorListener() {
                @Override
                public void editingStopped(ChangeEvent e) {
                    if (!getCellEditorValue().toString().isEmpty())
                        gameModel.change(getCellEditorValue().toString(), table.getSelectedRow(), 2);
                    else JOptionPane.showMessageDialog(table, "Can not be empty!", "Warning", JOptionPane.WARNING_MESSAGE);
                }

                @Override
                public void editingCanceled(ChangeEvent e) {

                }
            });
        }
    }

    private class GenreEditor extends DefaultCellEditor {
        private GenreEditor(JComboBox<? extends Genre> comboBox) {
            super(comboBox);
            setClickCountToStart(2);
            addCellEditorListener(new CellEditorListener() {
                @Override
                public void editingStopped(ChangeEvent e) {
                    gameModel.change(getCellEditorValue().toString(), table.getSelectedRow(), 1);
                }

                @Override
                public void editingCanceled(ChangeEvent e) {

                }
            });

        }

    }
}
