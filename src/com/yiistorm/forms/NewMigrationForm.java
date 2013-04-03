package com.yiistorm.forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NewMigrationForm extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField migrationName;
    public boolean displayed = false;
    MigrationsForm panel;

    public NewMigrationForm(MigrationsForm toolpanel) {
        panel = toolpanel;
        setContentPane(contentPane);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setModal(true);
        setBounds(500, 500, 300, 300);
        getRootPane().setDefaultButton(buttonOK);
        final JDialog dialog = this;
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                migrationName.setBackground(Color.WHITE);
                if (migrationName.getText().trim().length() > 0) {
                    buttonOK.setEnabled(false);
                    buttonOK.setText("...working...");
                    String text = panel.runCommand("migrate create " + migrationName.getText());
                    panel.setMigrateLogText(text);
                    panel.recreateMenus();
                    panel.openMigrationFile(panel.getMigrationsList().get(panel.getMigrationsList().size() - 1));
                    panel.NewFormDisplayed = false;
                    onCancel();
                } else {
                    migrationName.setBackground(Color.PINK);
                }
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.NewFormDisplayed = false;
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        //setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
        // panel.NewFormDisplayed=false;

    }


}