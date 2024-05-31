package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.NoSuchElementException;

public class MainPage extends BasePage {

    public static final By LOGOUT_BUTTON_LOCATOR = By.xpath(".//a[@class='ab-item'][contains(text(),'Log Out')]");
    public static final By ADMIN_BUTTON_LOCATOR = By.xpath("//*[@class='avatar avatar-26 photo']");
    public static final By QUICK_DRAFT_TITLE_LOCATOR = By.id("title");
    public static final By QUICK_DRAFT_CONTENT_LOCATOR = By.id("content");
    public static final By QUICK_SAVE_DRAFT_BUTTON_LOCATOR = By.id("save-post");

    private static final By HOWDY_ACCOUNT_LOCATOR = By.xpath(".//a[@class='ab-item'][contains(text(),'Howdy, ')]");
    public static final By WORDPRESS_ON_AZURE_LOCATOR =
            By.xpath(".//div[@class='quicklinks']//*[contains(text(), 'WordPress on Azure')]");

    public MainPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public boolean isDisplayed() {
        logger.debug("Checking if admin home page is displayed.");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(HOWDY_ACCOUNT_LOCATOR));
            WebElement howdyTitle = driver.findElement(HOWDY_ACCOUNT_LOCATOR);
            WebElement wordPressOnAzure = driver.findElement(WORDPRESS_ON_AZURE_LOCATOR);


            if (howdyTitle.isDisplayed() && wordPressOnAzure.isDisplayed()) {
                logger.debug("Admin home page is displayed.");
                return true;
            } else {
                logger.error("Admin home page is not displayed.");
                return false;
            }
        } catch (NoSuchElementException noSuchElementException) {
            logger.error("Admin home page is not displayed. Element not found.", noSuchElementException);
            return false;
        }
    }



    public boolean isLoginSuccessful() {
        // check if login is successful
        return false;  //return some locator from main page
    }

    public void logout() {

        WebElement userMenu = driver.findElement(ADMIN_BUTTON_LOCATOR);
        actions.moveToElement(userMenu).perform(); // Наведение мыши на меню пользователя

        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGOUT_BUTTON_LOCATOR));
        WebElement logoutButton = driver.findElement(LOGOUT_BUTTON_LOCATOR);
        actions.moveToElement(logoutButton).click().perform(); // Клик по кнопке выхода
    }

    public void createQuickDraft(String title, String content) {
        driver.findElement(QUICK_DRAFT_TITLE_LOCATOR).sendKeys(title);
        driver.findElement(QUICK_DRAFT_CONTENT_LOCATOR).sendKeys(content);
        driver.findElement(QUICK_SAVE_DRAFT_BUTTON_LOCATOR).click();
    }
}
