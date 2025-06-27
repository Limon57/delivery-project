if (rowText.contains("4108 STATION-04")) {
    try {
        WebElement link = row.findElement(By.tagName("a"));
        link.click();
        Thread.sleep(1000); // Wait for tabs to load

        WebElement statusTab = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//a[contains(@class, 'moduletab') and contains(text(), 'Status')]")
        ));
        statusTab.click();
        System.out.println("clicked on status tab");

    } catch (Exception e) {
        System.out.println(e);
    }
    break;
}

