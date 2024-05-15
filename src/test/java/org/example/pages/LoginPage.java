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
        logger.debug("Initialized LoginPage with driver.");
    }

    @Override
    public boolean isDisplayed() {

        logger.debug("Checking if login page is displayed.");
        // todo: Implement the logic to check if the page is displayed
        return false;
    }

    public void login(String username, String password) {

        logger.debug("trying to login with username: " + username + " and password: " + password);
        if (username != null && password != null) {
            driver.findElement(USERNAME_INPUT_LOCATOR).sendKeys(username);
            driver.findElement(PASSWORD_INPUT_LOCATOR).sendKeys(password);
            logger.debug("Entered username and password.");
        } else {
            logger.error("Something went wrong: Password or Username can not be null");
            throw new IllegalArgumentException("Password or Username can not be null");
        }
        driver.findElement(REMEMBER_ME_CHECKBOX_LOCATOR).click();
        logger.debug("Clicked RememberMe checkbox.");
        driver.findElement(LOGIN_BUTTON).submit();
        logger.debug("Click on Log In button.");
    }

    public void openLoginPage(String url) {

        logger.debug("Opening login page.");
        driver.get(url);
    }

    public String getErrorMessage() {

        logger.debug("Get error message.");
        return driver.findElement(ERROR_MESSAGE_LOCATOR).getText();
    }

    public String getErrorMessageWhenEmptyUsername() {

        logger.debug("Get error message for empty username.");
        return driver.findElement(ERROR_MESSAGE_WHEN_EMPTY_USERNAME_LOCATOR).getText();
    }

    public String getErrorMessageWhenInvalidCredentials() {

        logger.debug("Get error message for invalid credentials.");
        return driver.findElement(ERROR_MESSAGE_WHEN_INVALID_CREDENTIALS_LOCATOR).getText();
    }
}
