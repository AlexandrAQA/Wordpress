package org.example.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {


    public static final By USERNAME_INPUT_LOCATOR = By.xpath(".//input[@id = 'user_login']");
    public static final By PASSWORD_INPUT_LOCATOR = By.xpath(".//input[@id = 'user_pass']");
    public static final By LOGIN_BUTTON = By.xpath(".//input[@id = 'wp-submit']");
    public static final By REMEMBER_ME_CHECKBOX_LOCATOR = By.xpath(".//input[@name='rememberme']");
    public static final By ERROR_MESSAGE_LOCATOR = By.xpath(".//div[@id='login_error']");
    public static final String ERROR_MESSAGE_WHEN_PASSWORD_IS_EMPTY = "Error: The password field is empty.";
    public static final String ADMIN_USERNAME = "admin";
    public static final String ADMIN_PASSWORD = "kiyF5Oc#*8iE9DKx8bACg2DR";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void loginAsStandardAdmin(String username, String password) {
        driver.findElement(USERNAME_INPUT_LOCATOR).sendKeys(username);
        driver.findElement(PASSWORD_INPUT_LOCATOR).sendKeys(password);
        driver.findElement(REMEMBER_ME_CHECKBOX_LOCATOR).click();
        driver.findElement(LOGIN_BUTTON).submit();
    }

    public void openLoginPage() {
        driver.get("https://wordpress-test-app-for-selenium.azurewebsites.net/wp-admin");
    }


    public boolean isLoginButtonVisible() {
        // check if login button is visible
        return false;
    }

    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE_LOCATOR).getText();
    }

    public boolean isLoginSuccessful() {
        // check if login is successful
        return false;
    }

}
