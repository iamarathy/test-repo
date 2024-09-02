package org.example;//import io.qameta.allure.Allure;
//import io.github.bonigarcia.wdm.WebDriverManager;
import io.cucumber.java.bs.A;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.testng.Assert.assertEquals;

public class Main {
    public String promotiontype = randomString(5);
    public static WebDriver driver;
    public static WebDriverWait wait;
  /*  public static WebDriver getDriver() {
        if (driver == null) {
            driver = new ChromeDriver();
        }
        return driver;
    }*/


    @BeforeSuite
    public void setUpSuite() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    @AfterSuite
    public void tearDown() throws InterruptedException {
        if (driver != null) {
            Thread.sleep(3000);
            driver.quit();
        }
    }

    public void clicked(By locator) {
        WebElement findElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        findElement.click();
    }

    public void enterText(By locator, String text) {

        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.sendKeys(text);
    }


    public void validateURL(String expectedURL) {
        String currentURL = driver.getCurrentUrl();
        assertEquals(currentURL, expectedURL, "The URL is incorrect!");
    }

    public void scrollPage(int pixels) {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("window.scrollBy(0," + pixels + ")", "");

    }

    public void selectDate(WebDriver driver, String name, String xpath) {
        WebElement Dropdown = driver.findElement(By.name(name));
        Dropdown.click();
        WebElement Dropdown2 = driver.findElement(By.xpath(xpath));
        Dropdown2.click();
        WebElement ok = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div/div/div[3]/button[2]"));
        ok.click();
    }

    public void SelectDropdowns(WebDriver driver, By locator) {
        WebElement Dropdown = wait.until(ExpectedConditions.elementToBeClickable(locator));
        Dropdown.click();
    }

    public String randomString(int length) {
        // String symbols = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String symbols = "abcdefghijklmnopqrstuvwxyz123456789";
        Random rnd = new Random();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            str.append(symbols.charAt(rnd.nextInt(symbols.length())));
        }
        return str.toString();
    }

    public static String generateRandomChars(int totalChars) {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        // Generate the specified number of random characters
        for (int i = 0; i < totalChars; i++) {
            int x = random.nextInt(26) + 65; // Generates a number between 65 and 90 (A-Z)
            stringBuilder.append((char) x);
        }

        return stringBuilder.toString(); // Return the generated string
    }

    public String randomString2(int length) {
        String symbols = "abcdefghijklmnopqrstuvwxyz123456789";
        Random rnd = new Random();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < length; i++) {
            str.append(symbols.charAt(rnd.nextInt(symbols.length())));
        }
        return str.toString();

    }

    public void drop(By locator1, By locator2) {
        WebElement drop = wait.until(ExpectedConditions.elementToBeClickable(locator1));
        drop.click();
        WebElement drop2 = wait.until(ExpectedConditions.elementToBeClickable(locator2));
        drop2.click();
    }

    public String setInputValue(WebDriver driver, String name, String value) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.name(name)));
        element.sendKeys(value);
        return value;
    }

    public void InlineErrorValidator(By locator, String expectedValue) {
        WebElement inlineError = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        String actual = inlineError.getText();
        assertEquals(actual, expectedValue);
    }

    public void SuccessValidator(By locator, String expectedValue) {
        WebElement success = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        String actual = success.getText();
        assertEquals(actual, expectedValue);
    }

    public void promoChecker(By locator, String value) {
        WebElement promoName = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        String actual = promoName.getText();
        assertEquals(actual, value);
    }

    public void validateErrorMessages(List<WebElement> actualMessages, String[] expectedMessages) {
        Assert.assertEquals(actualMessages.size(), expectedMessages.length, "Number of validation messages does not match the expected count.");
        //Allure.step("The number of validation messages are matching with the expected count");
        // for (int i = 0; i < actualMessages.size(); i++) {
        //   Assert.assertEquals(actualMessages.get(i).getText(), expectedMessages[i], "Validation message mismatch at index " + i);
        //   Allure.step("The actual validation message is matching with the expected one");
        //     }
    }

    public void checkIfAdded(By locator, String value) {
        WebElement promoName = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        String actual = promoName.getText();
        Assert.assertEquals(actual, value);
        // Allure.step(value+" is showing after adding");
    }
}

//}
 