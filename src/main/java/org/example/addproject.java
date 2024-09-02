package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.UUID;


public class addproject extends Main {


    @Test(priority = 1)
    public void navigateToUserProfile() {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        driver.getCurrentUrl();
        clicked(By.xpath("//*[@id=\"__next\"]/div/nav/div/div/div[2]/ul[2]/button"));
        clicked(By.xpath("//*[@id=\"__next\"]/div/nav/div/div/div[2]/ul[2]/div[1]/div/div/a/div"));
        String prjtname = randomString(4);
        String license = UUID.randomUUID().toString();
        String projectno=randomString2(6);
        String des=randomString2(750);
        setInputValue(driver,"projectTitle", prjtname);
        setInputValue(driver,"licenseNumber",  license);
        setInputValue(driver,"projectNumber", projectno);
        drop(By.name("masterDeveloperSelector"),By.xpath("//*[@id=\"masterDeveloperSelector-option-0\"]"));
        drop(By.name("locationCountrySelect"),By.xpath("//*[@id=\"locationCountrySelect-option-0\"]"));
        drop(By.name("locationState"),By.xpath("//*[@id=\"locationState-option-0\"]"));
        drop(By.name("locationCitySelector"),By.xpath("//*[@id=\"locationCitySelector-option-0\"]"));
        drop(By.name("locationCommunity"),By.xpath("//*[@id=\"locationCommunity-option-0\"]"));
        drop(By.name("locationSubCommunity"),By.xpath("//*[@id=\"locationSubCommunity-option-0\"]"));
        drop(By.name("completionStatus"),By.xpath("//*[@id=\"completionStatus-option-0\"]"));
        String s=setInputValue(driver,"completionPercentage","2000");
        System.out.println(s);
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div[2]/div/div/div[3]/div/div[1]")).click();
        String s1=driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div[2]/div/div/div[3]/div/div[2]/div/div/div[2]/div[3]/p")).getText();
        System.out.println(s1);
        Assert.assertEquals(s1,"The value must be less than 100");
        driver.findElement(By.name("completionPercentage")).sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        setInputValue(driver,"completionPercentage","100");
        selectDate(driver,"completionPercentageDate","/html/body/div[2]/div[3]/div/div/div/div[2]/div/div[2]/div/div/div[2]/div/div[4]/button[3]");
        setInputValue(driver,"plotArea","5000");
        setInputValue(driver,"builtupArea","3000");
        validateURL("https://dashboard.aqaryint.com/dashboard/project/add_project");
        drop(By.name("furnished"),By.xpath("//*[@id=\"furnished-option-1\"]"));
        setInputValue(driver,"noOfProperties","10");
        drop(By.name("lifeStyle"),By.xpath("//*[@id=\"lifeStyle-option-0\"]"));
        drop(By.name("ownership"),By.xpath("//*[@id=\"ownership-option-0\"]"));
        selectDate(driver,"startDate","/html/body/div[2]/div[3]/div/div/div/div[2]/div/div[2]/div/div/div[2]/div/div[2]/button[3]");
        selectDate(driver,"completionDate","/html/body/div[2]/div[3]/div/div/div/div[2]/div/div[2]/div/div/div[2]/div/div[5]/button[5]");
        selectDate(driver,"handoverDate","/html/body/div[2]/div[3]/div/div/div/div[2]/div/div[2]/div/div/div[2]/div/div[5]/button[7]");
        setInputValue(driver,"serviceCharge","5000");
        scrollPage(500);
        String descriptionText = generateRandomChars(800);
        String arabicDescriptionText = generateRandomChars(800);
        Entertext(driver, "//*[@id='description']", descriptionText);
        Entertext(driver, "//*[@id='description_arabic']", arabicDescriptionText);
        Assert.assertEquals(getTextUsingJavaScript(driver, "//*[@id='description']"), descriptionText);
        Assert.assertEquals(getTextUsingJavaScript(driver, "//*[@id='description_arabic']"), arabicDescriptionText);
        selectFacility(driver, "Security Staff");
        selectAmenity(driver, "Balcony or Terrace");
        clicked(By.xpath("//button[@type='submit' and contains(text(), 'Submit')]"));


    }
    public void selectFacility(WebDriver driver, String facilityName) {
        WebElement facility = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='" + facilityName + "']")));
        facility.click();
    }

    public void selectAmenity(WebDriver driver, String amenityName) {
        WebElement amenity = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='" + amenityName + "']")));
        amenity.click();
    }
    public String getTextUsingJavaScript(WebDriver driver, String xpath) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return arguments[0].value;", element);
    }

    public  void Entertext(WebDriver driver,String xpath,String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = arguments[1];", element, text);
        js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", element);
    }

}
 