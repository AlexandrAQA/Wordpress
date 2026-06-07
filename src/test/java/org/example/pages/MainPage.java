package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MainPage extends BasePage {

    private static final By LOGOUT_BUTTON_LOCATOR = By.xpath(".//a[@class='ab-item'][contains(text(),'Log Out')]");
    private static final By ADMIN_BUTTON_LOCATOR = By.xpath("//*[@class='avatar avatar-26 photo']");
    private static final By QUICK_DRAFT_TITLE_LOCATOR = By.id("title");
    private static final By QUICK_DRAFT_CONTENT_LOCATOR = By.id("content");
    private static final By QUICK_SAVE_DRAFT_BUTTON_LOCATOR = By.id("save-post");

    private static final By HOWDY_ACCOUNT_LOCATOR = By.xpath(".//a[@class='ab-item'][contains(text(),'Howdy, ')]");
    private static final By WORDPRESS_ON_AZURE_LOCATOR =
            By.xpath(".//div[@class='quicklinks']//*[contains(text(), 'WordPress on Azure')]");

    private static final By VIEW_ALL_DRAFTS_BUTTON_LOCATOR =
            By.xpath(".//*[contains(text(),'View all drafts')]");

    PostsPage postsPage = new PostsPage(driver);

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
        actions.moveToElement(userMenu).perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGOUT_BUTTON_LOCATOR));
        WebElement logoutButton = driver.findElement(LOGOUT_BUTTON_LOCATOR);
        actions.moveToElement(logoutButton).click().perform();
    }

    public void clickOnViewAllDrafts() {
        wait.withTimeout(Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(VIEW_ALL_DRAFTS_BUTTON_LOCATOR)).click();
        wait.withTimeout(Duration.ofSeconds(10));
    }

    public void createQuickDraft(String title, String content) {

        driver.findElement(QUICK_DRAFT_TITLE_LOCATOR).sendKeys(title);
        driver.findElement(QUICK_DRAFT_CONTENT_LOCATOR).sendKeys(content);
        driver.findElement(QUICK_SAVE_DRAFT_BUTTON_LOCATOR).click();
        clickOnViewAllDrafts();
        driver.navigate().refresh();
        wait.withTimeout(Duration.ofSeconds(5));
    }
}
