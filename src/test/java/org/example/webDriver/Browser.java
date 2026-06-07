package org.example.webDriver;

import org.example.utils.PropertyReader;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class Browser {

    private static WebDriver driver;

    private Browser() {
    }

    private static void initDriver() {
        PropertyReader propertyReader = new PropertyReader();
        String browserType = propertyReader.getBrowser();
        if (browserType == null) {
            throw new RuntimeException("Browser type is not specified in the project.properties file");
        }

        BrowserTypeEnum type = BrowserTypeEnum.valueOf(browserType.trim().toUpperCase());
        driver = BrowserFactory.createInstance(type);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        if (type != BrowserTypeEnum.MOBILE) {
            driver.manage().window().maximize();
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

