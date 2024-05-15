package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {

    public static final By LOGOUT_LINK = By.xpath(".//a[@id = 'wp-admin-bar-logout']/span");

    public MainPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public boolean isDisplayed() {
        return false;
    }

    //todo: implement this method in the future
    // успешный ли логин можно проверить по тому, открылась ли logOut(main) страница.
    // Мы не можем получить такую информацию с LogIn страницы
    public boolean isLoginSuccessful() {
        // check if login is successful
        return false;  //return some locator from main page
    }

    public void logout() {
        // implement logout logic
    }
}
