# 🧪 Selenium Java Automation Guide

## 🚀 Introduction
Welcome to the **Selenium Java Automation Guide** – a comprehensive, hands-on tutorial for using **Selenium WebDriver** with **Java**. Whether you're new to automation or looking to refine your skills, this guide covers essential topics with ready-to-use code snippets to help automate real-world tasks and web interactions.

---

## 📦 1. Setup & Configuration

### 🔧 Maven Dependency

To use Selenium with Maven, add the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>4.19.1</version>
</dependency>
```

### ✅ ChromeDriver Setup

```java
WebDriver driver = new ChromeDriver();
```

### ✅ SafariDriver Setup

```java
WebDriver driver = new SafariDriver();
```

Ensure your system has the appropriate browser driver executable in the system `PATH`.

---

## 🌐 2. Launching Browsers

Customize browser options such as incognito mode and window size:

```java
ChromeOptions options = new ChromeOptions();
options.addArguments("--incognito");
options.addArguments("--window-size=1200,800");
WebDriver driver = new ChromeDriver(options);
```

---

## 📍 3. Navigation & Basic Interaction

Navigate to a webpage, refresh it, and read the page title:

```java
driver.get("https://example.com");
driver.navigate().refresh();
System.out.println(driver.getTitle());
```

---

## 🔍 4. Locating Elements

Identify and interact with HTML elements using various locators:

```java
driver.findElement(By.id("username"));
driver.findElement(By.cssSelector(".btn-primary"));
driver.findElement(By.xpath("//input[@name='email']"));
```

---

## 🖱 5. Actions on Elements

Send text to input fields and submit forms:

```java
WebElement input = driver.findElement(By.id("search"));
input.sendKeys("Selenium");
input.submit();
```

---

## 🧠 6. Advanced Interaction

Interact with dropdown menus using the `Select` class:

```java
Select dropdown = new Select(driver.findElement(By.id("country")));
dropdown.selectByVisibleText("United States");
```

---

## ⬇️ 7. Page Scrolling

Scroll vertically using JavaScript:

```java
JavascriptExecutor js = (JavascriptExecutor) driver;
js.executeScript("window.scrollBy(0,1000)");
```

---

## 🔍 8. Text & Keyword Detection

Detect specific keywords on the webpage:

```java
String pageText = driver.getPageSource();
if (pageText.contains("Welcome")) {
    System.out.println("Keyword found");
}
```

---

## 📸 9. Taking Screenshots

Capture screenshots of the browser window:

```java
File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
Files.copy(src.toPath(), Path.of("screenshot.png"));
```

---

## 🪟 10. Handling Multiple Windows/Tabs

Switch between different browser tabs or windows:

```java
String original = driver.getWindowHandle();
for (String handle : driver.getWindowHandles()) {
    driver.switchTo().window(handle);
}
```

---

## ⚠️ 11. Error Handling

Gracefully handle missing elements and avoid test interruptions:

```java
try {
    driver.findElement(By.id("missing"));
} catch (NoSuchElementException e) {
    System.out.println("Element not found");
}
```

---

## 📋 12. Common Use Cases

### 🔐 Login Example

```java
driver.findElement(By.id("username")).sendKeys("user");
driver.findElement(By.id("password")).sendKeys("pass");
driver.findElement(By.id("login")).click();
```

### 📝 Form Filling

```java
driver.findElement(By.name("email")).sendKeys("test@example.com");
driver.findElement(By.name("submit")).click();
```

---

## 🧪 Bonus: TestNG Integration

Automate testing with **TestNG**:

```java
@Test
public void testLogin() {
    WebDriver driver = new ChromeDriver();
    driver.get("https://example.com");
    Assert.assertTrue(driver.getTitle().contains("Example"));
    driver.quit();
}
```

---

## 📄 License

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT).

---

## 🤝 Contributions

Got improvements or examples? Feel free to **fork** this repository and submit a **pull request**. Sharing is caring – your input helps the community grow!
