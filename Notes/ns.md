package alerts;

import core.GuiLogger;

public class SessionPollingAlert {
    private final GuiLogger logger;

    public SessionPollingAlert(GuiLogger logger) {
        this.logger = logger;
    }

    public void run(String inputText) {
        // Your actual logic goes here
        System.out.println("[LOGGER] Running Session Polling alert...");
        System.out.println("[LOGGER] Input received: " + inputText);
        // Simulate processing...
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {}

        System.out.println("[LOGGER] Session Polling alert complete.");
    }
}


package alerts;

import core.GuiLogger;

public class InoperativeEVSEsListAlert {
    private final GuiLogger logger;

    public InoperativeEVSEsListAlert(GuiLogger logger) {
        this.logger = logger;
    }

    public void run() {
        // Your actual logic goes here
        System.out.println("[LOGGER] Checking for inoperative EVSEs...");
        // Simulate check
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {}

        System.out.println("[LOGGER] EVSEs checked. Report complete.");
    }
}






