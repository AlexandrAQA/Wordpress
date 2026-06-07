package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CommentsPage extends BasePage {

    private static final By COMMENTS_PAGE_HEADER_LOCATOR = By.xpath(".//h1[@class='wp-heading-inline'][contains(text(),'Pages')]");
    private static final By COMMENTS_PAGE_ELEMENTS_LIST_LOCATOR = By.id("the-comment-list");

    public CommentsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isDisplayed() {
        return false;
    }
}
