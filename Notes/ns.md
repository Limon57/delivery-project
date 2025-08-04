package gui;

import javax.swing.*;
import java.awt.*;
import core.GuiLogger;

public class MainGui extends JFrame {

    public MainGui() {
        setTitle("Smart Runner");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        InputPanel inputPanel = new InputPanel();
        OutputPanel outputPanel = new OutputPanel();
        ButtonPanel buttonPanel = new ButtonPanel(inputPanel, outputPanel);
        ToolsMenu toolsMenu = new ToolsMenu(outputPanel);

        setJMenuBar(toolsMenu.getMenuBar());
        add(inputPanel.getScrollPane(), BorderLayout.CENTER);
        add(outputPanel.getScrollPane(), BorderLayout.EAST);
        add(buttonPanel.getPanel(), BorderLayout.SOUTH);

        GuiLogger.hookSystemOut(outputPanel); // Redirect System.out
        setVisible(true);
    }
}




package gui;

import javax.swing.*;

public class InputPanel {
    private final JTextArea inputArea;
    private final JScrollPane scrollPane;

    public InputPanel() {
        inputArea = new JTextArea();
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);

        scrollPane = new JScrollPane(inputArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Paste or type input here"));
    }

    public String getText() {
        return inputArea.getText().trim();
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setText(String text) {
        inputArea.setText(text);
    }
}





package gui;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import core.LogEntry;

public class OutputPanel {
    private final JTextArea outputArea;
    private final JScrollPane scrollPane;
    private final List<LogEntry> logs;

    public OutputPanel() {
        outputArea = new JTextArea(10, 50);
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);

        scrollPane = new JScrollPane(outputArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Output"));

        logs = new ArrayList<>();
    }

    public void appendText(String text) {
        outputArea.append(text);
        outputArea.setCaretPosition(outputArea.getDocument().getLength());
    }

    public void clear() {
        outputArea.setText("");
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public List<LogEntry> getLogs() {
        return logs;
    }

    public JTextArea getTextArea() {
        return outputArea;
    }
}





package gui;

import javax.swing.*;
import core.AlertHandler;

public class ButtonPanel {
    private final JPanel panel;

    public ButtonPanel(InputPanel inputPanel, OutputPanel outputPanel) {
        panel = new JPanel();

        JButton runButton = new JButton("Run");
        runButton.addActionListener(e -> {
            String input = inputPanel.getText();
            if (input.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter input before clicking Run.");
                return;
            }

            AlertHandler handler = new AlertHandler(outputPanel);
            handler.handle(input);
        });

        JButton clearButton = new JButton("Clear Output");
        clearButton.addActionListener(e -> outputPanel.clear());

        panel.add(runButton);
        panel.add(clearButton);
    }

    public JPanel getPanel() {
        return panel;
    }
}





package gui;

import javax.swing.*;

public class ToolsMenu {
    private final JMenuBar menuBar;

    public ToolsMenu(OutputPanel outputPanel) {
        menuBar = new JMenuBar();

        JMenu tools = new JMenu("Tools");

        JMenuItem viewLogs = new JMenuItem("View Logs");
        viewLogs.addActionListener(e -> LogViewerWindow.show(outputPanel.getLogs()));

        JMenuItem clearOutput = new JMenuItem("Clear Output");
        clearOutput.addActionListener(e -> outputPanel.clear());

        tools.add(viewLogs);
        tools.add(clearOutput);
        menuBar.add(tools);
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }
}





package gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import core.LogEntry;

public class LogViewerWindow {
    public static void show(List<LogEntry> logs) {
        JFrame frame = new JFrame("Log Viewer");
        frame.setSize(700, 500);

        JPanel logPanel = new JPanel();
        logPanel.setLayout(new BoxLayout(logPanel, BoxLayout.Y_AXIS));

        for (int i = 0; i < logs.size(); i++) {
            LogEntry entry = logs.get(i);
            JTextArea area = new JTextArea(entry.getContent());
            area.setEditable(false);
            JScrollPane scroll = new JScrollPane(area);
            scroll.setVisible(false);

            JButton toggle = new JButton("View Log " + (i + 1) + " [" + entry.getTimeStamp() + "]");
            toggle.addActionListener(e -> scroll.setVisible(!scroll.isVisible()));

            JPanel entryPanel = new JPanel(new BorderLayout());
            entryPanel.add(toggle, BorderLayout.NORTH);
            entryPanel.add(scroll, BorderLayout.CENTER);

            logPanel.add(entryPanel);
        }

        JScrollPane mainScroll = new JScrollPane(logPanel);
        frame.add(mainScroll);
        frame.setVisible(true);
    }
}

