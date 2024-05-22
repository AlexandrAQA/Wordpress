package org.example.tests;

import org.apache.log4j.Logger;
import org.example.pages.DashboardPage;
import org.example.pages.LoginPage;
import org.example.utils.PropertyReader;
import org.example.webDriver.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
    String url;

    @BeforeMethod
    public void setUp() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);

        driver = new ChromeDriver(options);
        propertyReader = new PropertyReader();
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        browser = propertyReader.getBrowser();
        username = propertyReader.getUsername();
        password = propertyReader.getPassword();
        url = propertyReader.getBaseUrl();
        logger.debug("Test setup completed with browser: " + browser);
    }

    @AfterMethod
    public void tearDown() {
        logger.debug("tearDown() is started.");
        Browser.quitDriver();
        logger.debug("Browser.quitDriver() is completed.");
    }
}