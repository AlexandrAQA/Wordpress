package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MediaPage extends BasePage {

    public static final By MEDIA_PAGE_HEADER_LOCATOR = By.xpath(".//h1[@class='wp-heading-inline']");
    public static final By MEDIA_PAGE_ITEM_LIST_LOCATOR = By.id("the-list");

    public MediaPage(WebDriver driver) {
        super(driver);
    }
}
