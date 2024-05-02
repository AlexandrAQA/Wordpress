package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PagesPage extends BasePage {

    public static final By PAGES_PAGE_HEADER_LOCATOR = By.xpath(".//h1[@class='wp-heading-inline'][contains(text(),'Pages')]");
    public static final By PAGES_TABLE_ELEMENTS_LIST_LOCATOR = By.id("the-list");

    public PagesPage(WebDriver driver) {
        super(driver);
    }

}
