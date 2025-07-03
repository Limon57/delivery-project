// Wait for Monaco's container (ensures it's loaded visually)
        WebElement editorContainer = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("div.monaco-editor")
        ));
        System.out.println("✅ Editor container found");

        // Wait for the hidden textarea used by Monaco
        WebElement inputArea = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("textarea.inputarea")
        ));
        System.out.println("✅ Input area found");

        // Set your query
        String query = "SELECT * FROM `your_table` LIMIT 100;";

        // Inject text directly into the input area
        js.executeScript(
            "let textarea = arguments[0];" +
            "textarea.value = arguments[1];" +
            "textarea.dispatchEvent(new Event('input', { bubbles: true }));",
            inputArea, query
        );
        System.out.println("✅ Query injected");

        // Wait for the Run button and click it
        WebElement runButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[.//span[text()='Run']]")
        ));
        runButton.click();
        System.out.println("✅ Run clicked");
