package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PostsPage extends BasePage {
    public static final By POSTS_PAGE_HEADER_LOCATOR = By.xpath(".//h1[@class='wp-heading-inline']");
    public static final By POSTS_PAGE_ITEMS_LIST_LOCATOR = By.id("the-list");

    public PostsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isDisplayed() {
        return false;
    }
}
