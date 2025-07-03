package org.example.alertIssues;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class GUI extends JFrame {
    private JTextField manualTextField;
    private JTextField imagePathField;
    private JButton browseImageButton;
    private JButton runWithTextButton;
    private JButton runWithImageButton;

    private InoperativeEVSEsList evseChecker;

    public GUI(InoperativeEVSEsList evseChecker) {
        this.evseChecker = evseChecker;
        setupUI();
    }

    private void setupUI() {
        setTitle("Inoperative EVSE Checker");
        setSize(600, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        // Manual Text
        manualTextField = new JTextField();
        inputPanel.add(new JLabel("Manual Input Text:"));
        inputPanel.add(manualTextField);

        // Image Path
        imagePathField = new JTextField();
        browseImageButton = new JButton("Browse...");
        browseImageButton.addActionListener(this::onBrowseImage);

        inputPanel.add(new JLabel("Image Path:"));
        inputPanel.add(imagePathField);
        inputPanel.add(new JLabel("")); // empty label for layout
        inputPanel.add(browseImageButton);

        add(inputPanel, BorderLayout.CENTER);

        // Run Buttons
        JPanel buttonPanel = new JPanel();
        runWithTextButton = new JButton("Run with Manual Text");
        runWithImageButton = new JButton("Run with Image");

        runWithTextButton.addActionListener(this::onRunWithText);
        runWithImageButton.addActionListener(this::onRunWithImage);

        buttonPanel.add(runWithTextButton);
        buttonPanel.add(runWithImageButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void onBrowseImage(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File selected = fileChooser.getSelectedFile();
            imagePathField.setText(selected.getAbsolutePath());
        }
    }

    private void onRunWithText(ActionEvent e) {
        String text = manualTextField.getText();
        if (!text.isEmpty()) {
            evseChecker.runWithManualInput(text);
        } else {
            JOptionPane.showMessageDialog(this, "Please enter manual text.");
        }
    }

    private void onRunWithImage(ActionEvent e) {
        String imagePath = imagePathField.getText();
        if (!imagePath.isEmpty()) {
            evseChecker.runWithImage(imagePath);
        } else {
            JOptionPane.showMessageDialog(this, "Please select an image.");
        }
    }
}
