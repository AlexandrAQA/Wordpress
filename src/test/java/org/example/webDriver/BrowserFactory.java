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
                System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            case EDGE:
                driver = new EdgeDriver();
                break;
            case MOBILE:
                ChromeOptions mobileOptions = new ChromeOptions();
                mobileOptions.addArguments("--window-size=375,812"); // Пример мобильного разрешения iPhone X
                System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
                driver = new ChromeDriver(mobileOptions);
                break;
            default:
                throw new IllegalArgumentException(STR."Unsupported browser type: \{browserTypeEnum}");
        }
        return driver;
    }
}
