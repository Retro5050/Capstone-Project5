package com.example.StationList;

import com.example.Modules.*;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class AdminStationList {

    // The Chrome WebDriver
    WebDriver driver = SetUpDriver.Execute();

    @Test
    public void testAdminStationList() throws InterruptedException {

        // Maximize window
        driver.manage().window().maximize();

        // Navigate to the login screen for an admin
        AdminLogin.Execute(driver);
        AdminClickLogin.Execute(driver);
        assertFalse(driver.getPageSource().contains("admin-panel"));

        // Wait for alert
        Thread.sleep(500);

        // Navigate to Contact List
        driver.findElement(By.className("am-icon-table")).click();
        driver.findElement(By.className("am-icon-institution")).click();
        assertTrue(driver.findElement(By.className("portlet-title")).getText().contains("Station"));

        String sampleStationName = "ejibwafinehass";
        String sampleTime = "1";
        String superUniqueAddon = "alomao";

        // Test Add Route
        int rowNumber;
        while ((rowNumber = SearchTable.Execute(driver, sampleStationName)) != -1) {
            // Delete record
            DeleteRecord.Execute(driver, rowNumber, "/html/body/div[1]/div[2]/div/div[2]/div[2]/div/form/table/tbody/tr[", "]/td[4]/div/div/button[2]", "/html/body/div[2]/div/div[3]/span[2]");

            DismissAlert.Execute(driver);
            driver.navigate().refresh();
        }

        // Add order
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[2]/div[1]/div/div/div/button")).click();
        driver.findElement(By.id("add-station-name")).sendKeys(sampleStationName);
        driver.findElement(By.id("add-station-stay-time")).sendKeys(sampleTime);
        driver.findElement(By.xpath("/html/body/div[4]/div/div[4]/span[2]")).click();

        DismissAlert.Execute(driver);
        Thread.sleep(3000);

        // Check for test id DCNumber
        rowNumber = SearchTable.Execute(driver, sampleStationName);
        assertNotEquals(-1, rowNumber);

        // Update Order to another number
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[2]/div[2]/div/form/table/tbody/tr[" + rowNumber + "]/td[4]/div/div/button[1]")).click();
        driver.findElement(By.id("update-station-name")).sendKeys(sampleStationName + superUniqueAddon);
        driver.findElement(By.xpath("/html/body/div[3]/div/div[4]/span[2]")).click();

        DismissAlert.Execute(driver);
        Thread.sleep(3000);

        // Check for change reflected
        rowNumber = SearchTable.Execute(driver, sampleStationName + superUniqueAddon);
        assertNotEquals(-1, rowNumber);

        // Test Delete Order
        DeleteRecord.Execute(driver, rowNumber, "/html/body/div[1]/div[2]/div/div[2]/div[2]/div/form/table/tbody/tr[", "]/td[4]/div/div/button[2]", "/html/body/div[2]/div/div[3]/span[2]");

        DismissAlert.Execute(driver);
        driver.navigate().refresh();

        // Check for deleted record
        rowNumber = SearchTable.Execute(driver, sampleStationName + superUniqueAddon);
        assertEquals(-1, rowNumber);

        // Logout as an admin
        logout();
        assertEquals(GlobalVariables.ADMIN_LOGIN_URL, driver.getCurrentUrl());
    }

    /**
     * Close out of the WebDriver when finished
     */
    @After
    public void tearDown() {
        TearDownDriver.Execute(driver);
    }

    /**
     * Logs out of TrainTicket
     */
    private void logout() {
        driver.findElement(By.className("am-icon-sign-out")).click();
    }
}

