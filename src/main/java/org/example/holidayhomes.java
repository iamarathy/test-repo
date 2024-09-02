package org.example;

//import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class holidayhomes extends Main {

    @Test(priority = 0)
    public void addHolidayHome() {
        System.out.println("Inside holiday home");
        clicked(By.xpath("//*[@id='__next']/div/nav/div/div/div[2]/ul[12]/button"));
        System.out.println("Clicked on button");
        clicked(By.xpath("//*[@id='__next']/div/nav/div/div/div[2]/ul[12]/div[1]/div/div/a/div"));
    }

    @Test(priority = 1)
    public void experience() {
        drop(By.name("holidayHomeType"), By.xpath("//*[@id='holidayHomeType-option-1']"));
        enterText(By.name("en_title"), "Green Home");
        enterText(By.xpath("//input[@id='ar_title']"), "لقثثى اخةث");
        clicked(By.xpath("//input[@id='experience_category']"));
        clicked(By.xpath("//li[@id='experience_category-option-0']"));
        clicked(By.xpath("//li[@id='experience_category-option-5']"));
        enterText(By.name("no_of_hours"), "5");
        enterText(By.name("price_person"), "5000");

        // Selecting the experience package
        clicked(By.xpath("//input[@id='experience_package']"));
        clicked(By.xpath("//li[@id='experience_package-option-0']"));
        clicked(By.xpath("//li[@id='experience_package-option-1']"));
    }

    @Test(priority = 2)
    public void stay() {
        drop(By.name("holidayHomeType"), By.xpath("//*[@id='holidayHomeType-option-0']"));
        enterText(By.name("en_title"), "Green Home");
        enterText(By.xpath("//input[@id='ar_title']"), "لقثثى اخةثس");

        // Selecting the stay category
        clicked(By.xpath("//input[@id='stay_category']"));
        clicked(By.xpath("//li[@id='stay_category-option-2']"));
        clicked(By.xpath("//li[@id='stay_category-option-3']"));
        enterText(By.name("no_of_room"), "5");
        enterText(By.name("price_night"), "5000");
        enterText(By.name("no_bathroom"), "5");

        // Selecting views
        clicked(By.xpath("//input[@id='no_views']"));
        clicked(By.xpath("//li[@id='no_views-option-0']"));
        clicked(By.xpath("//li[@id='no_views-option-1']"));

        // Selecting stay package
        clicked(By.xpath("//input[@id='stay_package']"));
        clicked(By.xpath("//li[@id='stay_package-option-0']"));
        clicked(By.xpath("//li[@id='stay_package-option-1']"));
    }

    @Test(priority = 3)
    public void location() {
        drop(By.xpath("//input[@id='locationCountrySelect']"), By.xpath("//li[@id='locationCountrySelect-option-0']"));
        drop(By.xpath("//input[@id='locationState']"), By.xpath("//li[@id='locationState-option-0']"));
        drop(By.xpath("//input[@id='locationCitySelector']"), By.xpath("//li[@id='locationCitySelector-option-0']"));
        drop(By.xpath("//input[@id='locationCommunity']"), By.xpath("//li[@id='locationCommunity-option-0']"));
        drop(By.xpath("//input[@id='locationSubCommunity']"), By.xpath("//li[@id='locationSubCommunity-option-0']"));
        clicked(By.xpath("//span[normalize-space()='Visitor parking']"));
        clicked(By.xpath("//span[normalize-space()='Security Staff']"));
        clicked(By.xpath("//button[normalize-space()='Submit']"));
    }

    @Test(priority = 4)
    public void checkMandatoryFields() {
        clicked(By.xpath("//*[@id='__next']/div/nav/div/div/div[2]/ul[12]/div[1]/div/div/a/div"));
        clicked(By.xpath("//button[normalize-space()='Submit']"));

        List<WebElement> validationMessages = driver.findElements(By.cssSelector(".MuiFormHelperText-root.css-1hn95st, .MuiFormHelperText-root.Mui-error"));
        String[] expectedMessages = {
                "please select Holiday Home type",
                "Enter title",
                "Please select a country",
                "Please select a state",
                "Please select a city",
                "Please select a community",
                "Please select a sub community"
        };

        validateErrorMessages(validationMessages, expectedMessages);
    }

    @Test(priority = 5)
    public void stayMandatoryFields() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Selecting holiday home type from dropdown
        WebElement holidayHomeType = wait.until(ExpectedConditions.elementToBeClickable(By.name("holidayHomeType")));
        holidayHomeType.click();  // Replace `drop()` with standard click if needed

        WebElement holidayHomeOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='holidayHomeType-option-0']")));
        holidayHomeOption.click();

        // Clicking the submit button
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Submit']")));
        submitButton.click();  // Replace `clicked()` with standard click if needed
        System.out.println("Clicked on submit button in the stay category");

        // Fetching validation messages
        List<WebElement> validationMessages = driver.findElements(By.xpath(
                "//*[contains(@class, 'MuiFormHelperText-root') and " +
                        "(contains(@class, 'css-1hn95st') or contains(@class, 'Mui-error'))]"
        ));

        // Expected validation messages
        String[] expectedMessages = {
                "Enter title",
                "Please select stay category",  // Capitalized 'Please' for consistency
                "Enter Number of rooms",
                "Insert price",
                "Insert no of bathrooms",
                "Enter multiple views",
                "Please select experience package",
                "Please select a country",
                "Please select a state",
                "Please select a city",
                "Please select a community",
                "Please select a sub community"
        };

        // Validating error messages
        validateErrorMessages(validationMessages, expectedMessages);
    }


    @Test(priority = 6)
    public void experienceMandatoryFields() {
        // Selecting experience type from dropdown
        clicked(By.name("holidayHomeType"));
        clicked(By.xpath("//*[@id='holidayHomeType-option-1']"));

        // Clicking the submit button
        clicked(By.xpath("//button[normalize-space()='Submit']"));
        System.out.println("Clicked on submit button in the experience category");

        // Fetching validation messages
        List<WebElement> validationMessages = driver.findElements(By.xpath(
                "//*[contains(@class, 'MuiFormHelperText-root') and " +
                        "(contains(@class, 'css-1hn95st') or contains(@class, 'Mui-error'))]"
        ));

        // Expected validation messages
        String[] expectedMessages = {
                "Enter title",
                "Please select Experience Category",  // Capitalized 'Please' for consistency
                "Insert no of hours",
                "Insert price",
                "Please select experience package",
                "Please select a country",
                "Please select a state",
                "Please select a city",
                "Please select a community",
                "Please select a sub community"
        };

        // Validating error messages
        validateErrorMessages(validationMessages, expectedMessages);
    }

        @Test(priority=7)
        public void managecategory(){
            clicked(By.xpath("//p[normalize-space()='Manage Categories']"));
            clicked(By.xpath("//button[normalize-space()='Add Category']"));

        }

    }



