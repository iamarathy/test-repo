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

public class ManageCategories extends Main {

    //private String categories = UUID.randomUUID().toString();

    @Test(priority = 1)
    public void add() {
        System.out.print("inside holiday home");
        clicked(By.xpath("//*[@id='__next']/div/nav/div/div/div[2]/ul[12]/button"));
        System.out.print("clicked on button");
        scrollPage(250);
        clicked(By.xpath("//h5[normalize-space()='Manage Portals']"));
        System.out.print("clicked on button");

        //clicked(By.xpath("//button[normalize-space()='Add Category']"));
        //clicked(By.xpath("//*[@id=\"__next\"]/div/main/div/div[2]/div[2]/div/div[2]/div/div/div/div/div[1]/div[3]/div[1]/button"));
        // enterText(By.xpath("//*[@id=\"title\"]"), promotion);
        //clicked(By.xpath("/html/body/div[2]/div[3]/div/div[2]/div/div[2]/div[2]/div/div[1]/button"));

    }
}


