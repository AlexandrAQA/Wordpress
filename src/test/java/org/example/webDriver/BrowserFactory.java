package org.example.webDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {
    public static WebDriver createInstance(BrowserTypeEnum browserTypeEnum) {
        WebDriver driver;

        switch (browserTypeEnum) {
            case CHROME:
                ChromeOptions options = new ChromeOptions();
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(options);
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            case MOBILE:
                ChromeOptions mobileOptions = new ChromeOptions();
                mobileOptions.addArguments("--window-size=375,812");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(mobileOptions);
                break;
            default:
                throw new IllegalArgumentException("Browser not supported. Please check your configuration.");
        }
        return driver;
    }
}
