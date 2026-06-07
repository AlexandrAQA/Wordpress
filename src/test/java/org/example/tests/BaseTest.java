package org.example.tests;

import org.apache.log4j.Logger;
import org.example.pages.LoginPage;
import org.example.pages.MainPage;
import org.example.pages.MediaPage;
import org.example.pages.PostsPage;
import org.example.utils.PropertyReader;
import org.example.webDriver.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

    protected static final Logger logger = Logger.getLogger(BaseTest.class);

    public WebDriver driver;
    public WebDriverWait wait;
    Actions actions;
    LoginPage loginPage;
    MainPage mainPage;
    PostsPage postsPage;
    MediaPage mediaPage;

    PropertyReader propertyReader;
    String username;
    String password;
    String browser;
    String url;

    @BeforeMethod
    public void setUp() {

        driver = Browser.getDriver();

        propertyReader = new PropertyReader();
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        postsPage = new PostsPage(driver);
        mediaPage = new MediaPage(driver);

        browser = propertyReader.getBrowser();
        username = propertyReader.getUsername();
        password = propertyReader.getPassword();
        url = propertyReader.getBaseUrl();
        logger.debug("Test setup completed with browser: " + browser);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        actions = new Actions(driver);
    }

    @AfterMethod
    public void tearDown() {
        logger.debug("tearDown() is started.");
        Browser.quitDriver();
        logger.debug("Browser.quitDriver() is completed.");
    }
}