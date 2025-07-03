// Wait for the Monaco editor container to appear
        WebElement editorContainer = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.cssSelector("div.monaco-editor")
        ));
        System.out.println("✅ Found editor container");

        // Wait for Monaco editor to be fully initialized
        String waitForMonaco = 
            "return new Promise(resolve => {" +
            "  function check() {" +
            "    if (window.monaco && monaco.editor && monaco.editor.getModels().length > 0) {" +
            "      resolve(true);" +
            "    } else {" +
            "      setTimeout(check, 200);" +
            "    }" +
            "  }" +
            "  check();" +
            "});";
        ((JavascriptExecutor) driver).executeAsyncScript(waitForMonaco);
        System.out.println("✅ Monaco is ready");

        // Get your query from QueryBank
        QueryBank queryBank = new QueryBank();
        String query = queryBank.getMainQuery("USCPIL14172731");

        // Inject the query using Monaco's setValue
        String injectQueryScript =
            "const model = monaco.editor.getModels()[0];" +
            "model.setValue(arguments[0]);";
        ((JavascriptExecutor) driver).executeScript(injectQueryScript, query);
        System.out.println("✅ Query injected");

        // Wait for the "Run" button to be clickable
        WebElement runButton = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button[.//span[text()='Run']]")
        ));
        runButton.click();
        System.out.println("✅ Run button clicked");
