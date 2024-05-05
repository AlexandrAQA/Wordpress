package org.example.pages;

import org.example.webDriver.Browser;
import org.openqa.selenium.WebDriver;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = Browser.getDriver();
    }

    public boolean isDisplayed() {
        return false;
    }

    public void openPage(String url) {
        driver.get(url);
    }

    protected void refresh() {
        driver.navigate().refresh();
    }
}
