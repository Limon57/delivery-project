WebDriver driver = DriverConfig.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.get("https://walmart.chargepoint.com/stations_overview_table_view");

        try {
            Thread.sleep(3000); // allow initial page to load
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // ✅ Auto-switch to the only iframe (should be the table one)
        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        if (iframes.size() > 0) {
            driver.switchTo().frame(iframes.get(0));
        } else {
            System.out.println("No iframe found.");
            driver.quit();
            return;
        }

        // ✅ Wait until the scroll container is present
        WebElement scrollDiv = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.className("dataTables_scrollBody")));

        // ✅ Scroll all the way down (loop until no new height)
        JavascriptExecutor js = (JavascriptExecutor) driver;
        long lastHeight = -1;
        while (true) {
            long newHeight = (Long) js.executeScript(
                "arguments[0].scrollTop = arguments[0].scrollHeight; return arguments[0].scrollHeight;", scrollDiv);
            if (newHeight == lastHeight) break;
            lastHeight = newHeight;
            try {
                Thread.sleep(1000); // wait for new rows to load
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // ✅ Extract all rows
        List<WebElement> rows = driver.findElements(By.cssSelector("tr[role='row']"));
        System.out.println("rows: " + rows.size());

        // Optional: Print each row’s text
        for (WebElement row : rows) {
            System.out.println(row.getText());
