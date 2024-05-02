package org.example.tests;


import org.example.pages.DashboardPage;
import org.example.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;

    @BeforeMethod
    public void setUp() {

        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
    }
}
