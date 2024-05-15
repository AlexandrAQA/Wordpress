package org.example.pages;

import org.apache.log4j.Logger;
import org.example.webDriver.Browser;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    protected WebDriver driver;
    protected static final Logger logger = Logger.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        this.driver = Browser.getDriver();
        logger.debug("Initialized BasePage with driver.");
    }

    public abstract boolean isDisplayed();

    public void openPage(String url) {
        //todo: add wait here
        logger.debug("Opening page: " + url);
        driver.get(url);
    }

    protected void refresh(String url) {
        driver.get(url);
        logger.debug("Refreshing the page.");
        driver.navigate().refresh();
    }
}
