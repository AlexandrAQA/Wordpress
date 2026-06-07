package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class PostsPage extends BasePage {
    private static final By POSTS_PAGE_HEADER_LOCATOR = By.xpath(".//h1[@class='wp-heading-inline']");

    private static final By LATEST_DRAFT_TITLE_LOCATOR = By.xpath(".//a[@class='row-title'][1]");

    private static final String POSTS_PAGE_URL = "https://wordpress-test-app-for-selenium.azurewebsites.net/wp-admin/edit.php";
    private static final By ADD_NEW_POST_BUTTON_LOCATOR = By.xpath(".//a[@class='page-title-action']");
    private static final By ADD_TEXT_FOR_POST_LOCATOR = By.xpath("//span[@data-rich-text-placeholder='Type / to choose a block']");
    private static final By ADD_TITLE_LOCATOR = By.xpath("//*[contains(text(),'Add title')]");
    private static final By PUBLISH_BUTTON_LOCATOR = By.xpath(".//button[@class='components-button editor-post-publish-button editor-post-publish-button__button is-primary']");
    private static final By POSTS_PAGE_ITEMS_LIST_LOCATOR = By.id("the-list");


    public PostsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isDisplayed() {
        if (POSTS_PAGE_HEADER_LOCATOR.equals("Posts")) {
            return true;
        } else {
            return false;
        }
    }

    public List<String> getLatestDraftTitles() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LATEST_DRAFT_TITLE_LOCATOR));
        List<WebElement> elements = driver.findElements(LATEST_DRAFT_TITLE_LOCATOR);
        return elements.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    // todo: @FindBy(xpath = ".//iframe[@name='editor-canvas']")
    //       public WebElement iframe;
    // is it better to use @FindBy annotation?

    public void openPostPageAndClickOnCreatingPost() {
        driver.get(POSTS_PAGE_URL);
        driver.findElement(ADD_NEW_POST_BUTTON_LOCATOR).click();
    }

    public void clickTitleAndTextToSendKeys(String title, String text) {
        driver.findElement(ADD_TITLE_LOCATOR).sendKeys(title);
        driver.findElement(ADD_TEXT_FOR_POST_LOCATOR).sendKeys(text);
        driver.findElement(PUBLISH_BUTTON_LOCATOR).click();
    }

    public void createNewPost(String title, String text) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(".//iframe[@name='editor-canvas']")));
        driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@name='editor-canvas']")));
        WebElement wrapper = driver.findElement(By.className("edit-post-visual-editor__post-title-wrapper"));
        wrapper.click();
        WebElement contenteditableTrue = wrapper.findElement(By.xpath(".//h1[@contenteditable='true']"));
        contenteditableTrue.clear();
        contenteditableTrue.sendKeys(title);
        driver.switchTo().defaultContent();
        System.out.println("Title is added");
    }
}