package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogOutPage extends BasePage {
    //здесь наверное буден MainPage

    public static final By LOGOUT_LINK = By.xpath(".//a[@id = 'wp-admin-bar-logout']/span");

    public LogOutPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void logout() {
        // logout logic
    }

    public boolean isLogoutButtonVisible() {
        // check if logout button is visible
        return true;
    }

    public boolean isLogoutSuccessful() {
        // check if logout is successful
        return true;
    }
}
