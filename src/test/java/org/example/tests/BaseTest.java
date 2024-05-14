package org.example.tests;

import org.apache.log4j.Logger;
import org.example.pages.DashboardPage;
import org.example.pages.LoginPage;
import org.example.utils.PropertyReader;
import org.example.webDriver.Browser;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected static final Logger logger = Logger.getLogger(BaseTest.class);

    protected WebDriver driver;
    LoginPage loginPage;
    DashboardPage dashboardPage;

    PropertyReader propertyReader;
    String username;
    String password;
    String browser;

    @BeforeMethod
    public void setUp() {
        propertyReader = new PropertyReader();
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        browser = propertyReader.getBrowser();
        username = propertyReader.getUsername();
        password = propertyReader.getPassword();
    }

    @AfterMethod
    public void tearDown() {
        Browser.quitDriver();
    }
}