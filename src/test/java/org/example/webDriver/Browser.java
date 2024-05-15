package org.example.webDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.utils.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Browser {

    private static WebDriver driver;

    private Browser() {
    }

    private static void initDriver() {
        WebDriverManager.chromedriver().setup();
        PropertyReader propertyReader = new PropertyReader();
        String browserType = propertyReader.getBrowser();
        if (browserType != null) {
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        } else {
            throw new RuntimeException("Browser type is not specified in the project.properties file");
        }
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}

