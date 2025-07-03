QueryBank queryBank = new QueryBank();

        WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Open BigQuery
        driver.get("https://console.cloud.google.com/bigquery?authuser=0&project=re-ev-zeus-prod");

        // Wait for editor area to be present
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement queryBox = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//div[contains(@class, 'view-lines')]")
        ));

        // Click to focus the editor
        js.executeScript("arguments[0].click();", queryBox);
        Thread.sleep(1000);  // Let it stabilize

        // Type the query using sendKeys (simulate user input)
        queryBox.click();
        queryBox.sendKeys(Keys.CONTROL + "a"); // Select all
        queryBox.sendKeys(Keys.DELETE);        // Clear existing content
        queryBox.sendKeys(queryBank.getMainQuery("USCPIL14172731"));
        System.out.println("Query pasted");

        // Wait until Run button is clickable
        WebElement runButton = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button[.//span[text()='Run']]")
        ));

        runButton.click();
        System.out.println("Run button clicked");
