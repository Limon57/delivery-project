package org.example.gui.guiComponents;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ToolsMenu {
    private final JMenuBar menuBar;
    private final JMenu tools;
    private boolean debugMode = false;

    public ToolsMenu(OutputPanel outputPanel) {
        menuBar = new JMenuBar();
        tools = new JMenu("Tools");

        // View Logs
        JMenuItem viewLogs = new JMenuItem("View Logs");
        viewLogs.addActionListener((ActionEvent e) ->
            LogViewerWindow.show(outputPanel.getLogs()));
        tools.add(viewLogs);

        // Clear Output
        JMenuItem clearOutput = new JMenuItem("Clear Output");
        clearOutput.addActionListener((ActionEvent e) ->
            outputPanel.clear());
        tools.add(clearOutput);

        // Debug Toggle
        JMenuItem debugItem = new JMenuItem("Toggle Debug");
        debugItem.addActionListener((ActionEvent e) -> {
            debugMode = !debugMode;
            String state = debugMode ? "True" : "False";
            debugItem.setText("Debug: " + state);
            GuiLogger.setDebugMode(debugMode);  // Make sure GuiLogger has this method
        });
        tools.add(debugItem);

        // Login Tool
        JMenuItem loginItem = new JMenuItem("Login");
        loginItem.addActionListener((ActionEvent e) -> {
            try {
                LoginHandler loginHandler = new LoginHandler();
                loginHandler.bigQueryLogin();
                JOptionPane.showMessageDialog(null, "Login successful");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,
                        "Login Failed: " + ex.getMessage());
            }
        });
        tools.add(loginItem);

        // Optional: Dark Mode Toggle (if re-enabled)
        /*
        JMenuItem darkModeItem = new JMenuItem("Toggle Dark Mode");
        darkModeItem.addActionListener(e -> {
            // Toggle dark mode flag and call updateTheme()
        });
        tools.add(darkModeItem);
        */

        // Attach menu
        menuBar.add(tools);
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }
}





