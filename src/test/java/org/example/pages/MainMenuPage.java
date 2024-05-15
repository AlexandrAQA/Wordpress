package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainMenuPage extends BasePage {

    public static final By MAIN_MENU_12_PAGES_LIST_LOCATOR =
            By.xpath("//*[@class='wp-menu-name']");
    public static final String MAIN_MENU_12_PAGES_LIST_LOCATOR_PATTERN =
            "//*[@class='wp-menu-name' and contains(text(),'%s')])";

    public MainMenuPage(WebDriver driver) {
        super(driver);
    }
}
