// Wait for the JSON container to be visible
WebElement jsonContainer = WebDriverManager.getWait().until(
    ExpectedConditions.visibilityOfElementLocated(
        By.cssSelector("div.vt-text-content")
    )
);

// Get all <span> lines inside the JSON container
List<WebElement> lines = jsonContainer.findElements(By.cssSelector("div > span"));

// Build the full JSON string
StringBuilder jsonBuilder = new StringBuilder();
for (WebElement line : lines) {
    String text = line.getText();
    if (!text.isEmpty()) {
        jsonBuilder.append(text).append("\n");
    }
}

// Final JSON result as a string
String fullJson = jsonBuilder.toString();
System.out.println("✅ Extracted JSON:");
System.out.println(fullJson);
