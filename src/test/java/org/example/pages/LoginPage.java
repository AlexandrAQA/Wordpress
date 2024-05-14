package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private static final By USERNAME_INPUT_LOCATOR = By.xpath(".//input[@id = 'user_login']");
    private static final By PASSWORD_INPUT_LOCATOR = By.xpath(".//input[@id = 'user_pass']");
    private static final By LOGIN_BUTTON = By.xpath(".//input[@id = 'wp-submit']");
    private static final By REMEMBER_ME_CHECKBOX_LOCATOR = By.xpath(".//input[@name='rememberme']");
    private static final By ERROR_MESSAGE_LOCATOR = By.xpath(".//div[@id='login_error']");
    private static final By ERROR_MESSAGE_WHEN_EMPTY_USERNAME_LOCATOR = By.xpath(".//div[text()=' The username field is empty.']");
    private static final By ERROR_MESSAGE_WHEN_INVALID_CREDENTIALS_LOCATOR = By.xpath(".//div[text()=' The username ']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        if (username != null) {
            driver.findElement(USERNAME_INPUT_LOCATOR).sendKeys(username);
        }
        if (password != null) {
            driver.findElement(PASSWORD_INPUT_LOCATOR).sendKeys(password);
        }
        //todo: а если все таки null?
        driver.findElement(REMEMBER_ME_CHECKBOX_LOCATOR).click();
        driver.findElement(LOGIN_BUTTON).submit();
    }

    public void openLoginPage() {
        logger.debug("Opening login page.");
        driver.get("https://wordpress-test-app-for-selenium.azurewebsites.net/wp-admin");
    }

    public void invalidLogin(String username, String password) {
        driver.findElement(USERNAME_INPUT_LOCATOR).sendKeys(username);
        driver.findElement(PASSWORD_INPUT_LOCATOR).sendKeys(password);
        driver.findElement(REMEMBER_ME_CHECKBOX_LOCATOR).click();
        driver.findElement(LOGIN_BUTTON).submit();
    }

    public boolean isLoginButtonVisible() {
        // check if login button is visible
        return false;
    }

    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE_LOCATOR).getText();
    }

    public String getErrorMessageWhenEmptyUsername() {
        return driver.findElement(ERROR_MESSAGE_WHEN_EMPTY_USERNAME_LOCATOR).getText();
    }

    public String getErrorMessageWhenInvalidCredentials() {
        return driver.findElement(ERROR_MESSAGE_WHEN_INVALID_CREDENTIALS_LOCATOR).getText();
    }

    public boolean isLoginSuccessful() {
        // check if login is successful
        return false;
    }
}
