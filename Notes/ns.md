EdgeOptions options = new EdgeOptions();
    // optional: hide “automated test software” heuristics
    options.addArguments("--disable-blink-features=AutomationControlled");

    WebDriver driver = new EdgeDriver(options);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300));

    driver.get("https://www.office.com/");

    // Click the "Sign in" button if needed (uncomment and adjust locator)
    // driver.findElement(By.xpath("//a[normalize-space()='Sign in' or normalize-space()='Sign in to your account']")).click();

    // ✅ Office/SSO usually redirects IN THE SAME TAB:
    wait.until(d -> driver.getCurrentUrl().startsWith("https://login.microsoftonline.com")
                     || driver.getCurrentUrl().startsWith("https://login.live.com"));

    System.out.println("Please complete Microsoft login in the tab…");

    // When login finishes, you’ll be redirected back (or you can just navigate)
    wait.until(d -> !driver.getCurrentUrl().contains("login.microsoftonline.com"));
    // Or: wait for a known post-login element on Office
    // wait.until(ExpectedConditions.titleContains("Microsoft"));

    // Continue wherever you want
    driver.get("https://portal.azure.com/");





EdgeOptions options = new EdgeOptions();
    options.addArguments("--disable-popup-blocking"); // make sure popups aren’t blocked
    WebDriver driver = new EdgeDriver(options);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300));

    driver.get("about:blank");

    // Open the Microsoft login in a separate window
    ((JavascriptExecutor) driver).executeScript(
        "window.open('https://login.microsoftonline.com/', '_blank', 'width=520,height=680');");

    wait.until(ExpectedConditions.numberOfWindowsToBe(2));

    String main = driver.getWindowHandle();
    String popup = driver.getWindowHandles().stream().filter(h -> !h.equals(main)).findFirst().get();
    driver.switchTo().window(popup);

    System.out.println("Complete login in the Microsoft popup… I’ll wait.");

    // If your flow closes the popup when done:
    wait.until(ExpectedConditions.numberOfWindowsToBe(1));
    driver.switchTo().window(main);

    driver.get("https://portal.azure.com/");




