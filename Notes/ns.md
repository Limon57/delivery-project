package core;

public class LogEntry {
    private final String timeStamp;
    private final String content;

    public LogEntry(String timeStamp, String content) {
        this.timeStamp = timeStamp;
        this.content = content;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getContent() {
        return content;
    }
}



package core;

import gui.OutputPanel;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AlertHandler {
    private final OutputPanel outputPanel;
    private final StringBuilder currentRunOutput;

    public AlertHandler(OutputPanel outputPanel) {
        this.outputPanel = outputPanel;
        this.currentRunOutput = new StringBuilder();
    }

    public void handle(String text) {
        new Thread(() -> {
            try {
                outputPanel.appendText("Running alert...\n");
                currentRunOutput.append("Running alert...\n");

                // 🧪 Simulate alert execution — replace with your real logic
                Thread.sleep(1000);

                String result = "Alert completed.\n";
                outputPanel.appendText(result);
                currentRunOutput.append(result);

                // Add one LogEntry for this run
                String timestamp = new SimpleDateFormat("HH:mm:ss").format(new Date());
                outputPanel.getLogs().add(new LogEntry(timestamp, currentRunOutput.toString()));

                currentRunOutput.setLength(0); // clear after use

            } catch (Exception e) {
                String error = "Error: " + e.getMessage() + "\n";
                outputPanel.appendText(error);
                currentRunOutput.append(error);
            }
        }).start();
    }
}





package core;

import gui.OutputPanel;

import javax.swing.*;
import java.io.OutputStream;
import java.io.PrintStream;

public class GuiLogger {
    private static final StringBuilder buffer = new StringBuilder();
    private static OutputPanel outputPanel;
    private static final StringBuilder activeRunOutput = new StringBuilder(); // Shared collector
    private static boolean debugMode = false;

    public static void hookSystemOut(OutputPanel panel) {
        outputPanel = panel;
        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                write(new byte[]{(byte) b}, 0, 1);
            }

            @Override
            public void write(byte[] b, int off, int len) {
                String text = new String(b, off, len);
                buffer.append(text);
                if (text.contains("\n")) {
                    flushBuffer();
                }
            }
        }, true));
    }

    private static void flushBuffer() {
        String text = buffer.toString();
        buffer.setLength(0); // clear buffer

        if (outputPanel == null) return;

        SwingUtilities.invokeLater(() -> {
            if (debugMode || text.contains("[LOGGER]")) {
                outputPanel.appendText(text);
                activeRunOutput.append(text);
            }
        });
    }

    public static void setDebugMode(boolean enabled) {
        debugMode = enabled;
    }

    public static StringBuilder getCurrentRunOutput() {
        return activeRunOutput;
    }

    public static void resetRunOutput() {
        activeRunOutput.setLength(0);
    }
}
