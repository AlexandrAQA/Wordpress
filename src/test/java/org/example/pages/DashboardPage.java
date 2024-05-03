package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {

    public static final By DASHBOARD_PAGE_NAME_LOCATOR = By.xpath(".//div[@class='wrap']//h1[contains(text(), 'Dashboard')]");

    public static final By WORDPRESS_ON_AZURE_LOCATOR = By.xpath(".//div[@class='quicklinks']//*[contains(text(), 'WordPress on Azure')]");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDashboardPageOpened() {
        String pagesPageName = driver.findElement(DASHBOARD_PAGE_NAME_LOCATOR).getText();
        if (pagesPageName.contains("Dashboard") && driver.findElement(WORDPRESS_ON_AZURE_LOCATOR).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }
}
