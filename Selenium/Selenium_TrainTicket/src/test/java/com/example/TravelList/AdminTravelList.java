package com.example.TravelList;

import com.example.Modules.*;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class AdminTravelList {

    // The Chrome WebDriver
    WebDriver driver = SetUpDriverChrome.Execute();

    @Test
    public void testAdminTravelList() throws InterruptedException {

        // Maximize window
        driver.manage().window().maximize();

        // Navigate to the login screen for an admin
        AdminLogin.Execute(driver);
        AdminClickLogin.Execute(driver);
        assertFalse(driver.getPageSource().contains("admin-panel"));

        // Wait for alert
        Thread.sleep(500);

        // Navigate to Contact List
        driver.findElement(By.className("am-icon-globe")).click();
        assertTrue(driver.findElement(By.className("portlet-title")).getText().contains("Travel"));

        String sampleTravelID = "G18005882300";
        String sampleRouteID = "f3d4d4ef-693b-4456-8eed-59c0d717dd08";
        String sampleStartStation = "shanghai";
        String sampleEndStation = "suzhou";

        // Test Add Route
        int rowNumber;
        while ((rowNumber = SearchTable.Execute(driver, "/html/body/div[1]/div[2]/div/div[2]/div[2]/div/form/table", sampleTravelID)) != -1) {
            // Delete record
            DeleteRecord.Execute(driver, rowNumber, "/html/body/div[1]/div[2]/div/div[2]/div[2]/div/form/table/tbody/tr[", "]/td[5]/div/div/button[2]", "/html/body/div[2]/div/div[3]/span[2]");

            DismissAlert.Execute(driver);
            driver.navigate().refresh();
        }

        // Add order
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[2]/div[1]/div/div/div/button")).click();

        driver.findElement(By.id("add_travel_id")).sendKeys(sampleTravelID);

        Select routes = new Select(driver.findElement(By.name("travel_route_id")));
        Select startStation = new Select(driver.findElement(By.name("travel_start_station")));
        Select stationName = new Select(driver.findElement(By.name("travel_station_name")));
        Select endStation = new Select(driver.findElement(By.name("travel_terminal_station")));

        routes.selectByValue(sampleRouteID);
        startStation.selectByValue(sampleStartStation);
        stationName.selectByValue(sampleEndStation);
        endStation.selectByValue(sampleEndStation);

        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[2]/div[2]/div/form/div[3]/div/div[3]/span[2]")).click();

        DismissAlert.Execute(driver);
        Thread.sleep(3000);

        // Check for test id DCNumber
        rowNumber = SearchTable.Execute(driver, "/html/body/div[1]/div[2]/div/div[2]/div[2]/div/form/table", sampleTravelID);
        assertNotEquals(-1, rowNumber);

        // Test Delete Order
        DeleteRecord.Execute(driver, rowNumber, "/html/body/div[1]/div[2]/div/div[2]/div[2]/div/form/table/tbody/tr[", "]/td[1]/div/div/button[2]", "/html/body/div[1]/div[2]/div/div[2]/div[2]/div/form/div[2]/div/div[3]/span[2]");

        DismissAlert.Execute(driver);
        driver.navigate().refresh();

        // Check for deleted record
        rowNumber = SearchTable.Execute(driver, "/html/body/div[1]/div[2]/div/div[2]/div[2]/div/form/table", sampleTravelID);
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
