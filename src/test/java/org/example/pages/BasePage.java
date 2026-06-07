package org.example.pages;

import org.apache.log4j.Logger;
import org.example.webDriver.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected WebDriver driver;
    protected static final Logger logger = Logger.getLogger(BasePage.class);
    protected Actions actions = new Actions(Browser.getDriver());
    protected WebDriverWait wait = new WebDriverWait(Browser.getDriver(), Duration.ofSeconds(25));


    public BasePage(WebDriver driver) {
        this.driver = Browser.getDriver();
        logger.debug("Initialized BasePage with driver.");
    }

    public abstract boolean isDisplayed();

    public void openPage(String url) {

        //todo: add wait here and implement it in the child classes
        logger.debug("Opening page: " + url);
        driver.get(url);
    }

    protected void refresh(String url) {
        driver.get(url);
        logger.debug("Refreshing the page.");
        driver.navigate().refresh();
    }
}
