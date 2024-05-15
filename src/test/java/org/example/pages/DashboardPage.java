package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage extends BasePage {

    public static final By DASHBOARD_PAGE_NAME_LOCATOR =
            By.xpath(".//div[@class='wrap']//h1[contains(text(), 'Dashboard')]");

    public static final By WORDPRESS_ON_AZURE_LOCATOR =
            By.xpath(".//div[@class='quicklinks']//*[contains(text(), 'WordPress on Azure')]");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isDisplayed() {
        return false;
    }

    public boolean isDashboardPageOpened() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(DASHBOARD_PAGE_NAME_LOCATOR));
        return !driver.findElements(DASHBOARD_PAGE_NAME_LOCATOR).isEmpty();
    }
}
