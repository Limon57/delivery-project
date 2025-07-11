
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class SmartRunnerGUI extends JFrame {

    private JTextArea inputArea;
    private JButton runButton;
    private JButton fileButton;
    private JComboBox<String> optionsDropdown;

    public SmartRunnerGUI() {
        setTitle("Smart Runner");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 📋 Big text area for pasting
        inputArea = new JTextArea();
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(inputArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Paste or type input here"));
        add(scrollPane, BorderLayout.CENTER);

        // 🔘 Bottom buttons panel
        JPanel bottomPanel = new JPanel(new FlowLayout());

        // ▶ Run button (pasted input only)
        runButton = new JButton("Run");
        runButton.addActionListener(e -> {
            String text = inputArea.getText().trim();
            if (!text.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Running with pasted input:\n" + text);
                // insert logic for handling pasted text here
            } else {
                JOptionPane.showMessageDialog(this, "Please paste something to run.");
            }
        });

        // 📁 Choose file button
        fileButton = new JButton("Choose File");
        fileButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                JOptionPane.showMessageDialog(this, "Running with file:\n" + file.getAbsolutePath());
                // insert logic for handling file input here
            }
        });

        // 🔽 Dropdown for pasted + option run
        optionsDropdown = new JComboBox<>(new String[] {
            "Select Action", "Analyze", "Convert", "Summarize"
        });
        optionsDropdown.addActionListener(e -> {
            String selected = (String) optionsDropdown.getSelectedItem();
            String pasted = inputArea.getText().trim();

            if (!selected.equals("Select Action") && !pasted.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Running '" + selected + "' on:\n" + pasted);
                // insert logic for selected operation
            } else if (!selected.equals("Select Action")) {
                JOptionPane.showMessageDialog(this, "Please paste input before choosing an action.");
            }
        });

        bottomPanel.add(runButton);
        bottomPanel.add(fileButton);
        bottomPanel.add(optionsDropdown);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SmartRunnerGUI::new);
    }
}
