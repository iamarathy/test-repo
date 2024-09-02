package org.example;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class login extends Main {

    @Test(priority = 1)
    public void Welcome() {
        driver.get("https://dashboard.aqaryint.com/");
        validateURL("https://dashboard.aqaryint.com/");
        clicked(By.xpath("//*[@id=\"home\"]/div/div/div[1]/div/div[3]/div/div/div/div/a"));
    }

    @Test(priority = 2)
    public void SwitchTab() throws InterruptedException {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        Thread.sleep(1000);
        validateURL("https://dashboard.aqaryint.com/dashboard/pages/authentication/portal_registration/login");
    }

    @Test(priority = 3)
    public void Login() throws InterruptedException {
        enterText(By.name("email"), "mark@admin.com");
        enterText(By.name("password"), "mark");
        clicked(By.xpath("//*[@id=\":r0:\"]"));
        Thread.sleep(1000);
        validateURL("https://dashboard.aqaryint.com/dashboard/default");
    }
}
 