/**
 * Logs into the TrainTicket service as an administrator
 */

package com.example.Modules;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.example.Modules.GlobalVariables.*;

public class AdminLogin {
    /**
     * Logs into the TrainTicket service as an admin
     *
     * @param driver WebDriver
     */
    public static void Execute(WebDriver driver) {
        AdminClickLogin.Execute(driver);
        driver.findElement(By.id("doc-ipt-email-1")).click();
        driver.findElement(By.id("doc-ipt-email-1")).sendKeys(ADMIN_USERNAME);
        driver.findElement(By.id("doc-ipt-pwd-1")).click();
        driver.findElement(By.id("doc-ipt-pwd-1")).sendKeys(ADMIN_PASSWORD);

        System.out.println(driver.getPageSource());
        System.out.println(driver.findElement(By.tagName("BUTTON")).getText());
        System.out.println(driver.findElement(By.tagName("BUTTON")).isDisplayed());

        driver.findElement(By.tagName("BUTTON")).click();
    }
}
