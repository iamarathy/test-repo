package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

public class promotions extends Main  {

    private String promotion = UUID.randomUUID().toString();

    @Test(priority = 4)
    public void add() {
        clicked(By.xpath("//*[@id=\"__next\"]/div/nav/div/div/div[2]/ul[2]/button"));
        clicked(By.xpath("//*[@id=\"__next\"]/div/nav/div/div/div[2]/ul[2]/div[8]/div/div/a/div/div/p"));
        clicked(By.xpath("//*[@id=\"__next\"]/div/main/div/div[2]/div[2]/div/div[2]/div/div/div/div/div[1]/div[3]/div[1]/button"));
        enterText(By.xpath("//*[@id=\"title\"]"), promotion);
        clicked(By.xpath("/html/body/div[2]/div[3]/div/div[2]/div/div[2]/div[2]/div/div[1]/button"));

        // Verify promotion type is present and delete it
        Assert.assertTrue(isPromotionTypePresent());
        deletePromotionType();

        verifyToast("Promotion Type Deleted successfully.");
    }

    private boolean isPromotionTypePresent() {
        List<WebElement> tableRows = driver.findElements(By.cssSelector("tr"));
        for (WebElement row : tableRows) {
            List<WebElement> cells = row.findElements(By.cssSelector("td.MuiTableCell-root.MuiTableCell-body.MuiTableCell-sizeMedium.css-1gp8781"));
            for (WebElement cell : cells) {
                if (cell.getText().equals(promotion)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void deletePromotionType() {
        List<WebElement> tableRows = driver.findElements(By.cssSelector("tr"));
        for (WebElement row : tableRows) {
            List<WebElement> cells = row.findElements(By.cssSelector("td.MuiTableCell-root.MuiTableCell-body.MuiTableCell-sizeMedium.css-1gp8781"));
            for (WebElement cell : cells) {
                if (cell.getText().equals(promotion)) {
                    WebElement deleteButton = row.findElement(By.cssSelector("button.MuiButton-containedError"));
                    deleteButton.click();
                    clicked(By.xpath("/html/body/div[2]/div[3]/div/div[2]/div/div[2]/button[1]"));
                    return;
                }
            }
        }
    }

    private void verifyToast(String expectedToast) {
        System.out.println("inside verify toast");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement toastElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='1']/div[1]/div[2]")));
        String actualToast = toastElement.getText();
        Assert.assertEquals(actualToast, expectedToast, "Toast message did not match!");
        System.out.println("Toast message is matching");

    }
}
