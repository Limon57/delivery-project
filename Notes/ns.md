 QueryBank queryBank = new QueryBank();

        WebDriver driver = WebDriverManager.getDriver();
        driver.get("https://console.cloud.google.com/bigquery?authuser=0&project=re-ev-zeus-prod");

        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Wait for query editor to load
        WebElement queryBox = WebDriverManager.getWait().until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//div[contains(@class, 'view-lines')]")
                )
        );

        // Give time for it to be fully interactable
        Thread.sleep(2000);

        // Focus and simulate click
        js.executeScript(
                "arguments[0].focus(); arguments[0].dispatchEvent(new MouseEvent('click', {bubbles: true}));",
                queryBox
        );

        Thread.sleep(500); // Let the focus settle

        // Paste the query content
        js.executeScript(
                "arguments[0].textContent = arguments[1];",
                queryBox,
                queryBank.getMainQuery("USCPIL14172731")
        );
        System.out.println("sent");

        // Simulate a key press to activate the Run button
        js.executeScript(
                "var e = new KeyboardEvent('keydown', {key:' ', code:'Space', keyCode:32, which:32, bubbles:true}); arguments[0].dispatchEvent(e);",
                queryBox
        );

        // Click the Run button
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement runButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[.//span[text()='Run']]")
        ));
        runButton.click();
        System.out.println("run clicked");
