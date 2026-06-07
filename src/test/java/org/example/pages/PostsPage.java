package org.example.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
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
    private static final By VIEW_ALL_POSTS_LOCATOR = By.xpath(".//a[@class='row-title']");


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

    public void createNewPost(String title) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(".//iframe[@name='editor-canvas']")));
        driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@name='editor-canvas']")));
        WebElement wrapper = driver.findElement(By.className("edit-post-visual-editor__post-title-wrapper"));
        wrapper.click();
        WebElement contenteditableTrue = wrapper.findElement(By.xpath(".//h1[@contenteditable='true']"));
        contenteditableTrue.clear();
        contenteditableTrue.sendKeys(title);
        logger.info("Title is added");
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.visibilityOfElementLocated(PUBLISH_BUTTON_LOCATOR));
        driver.findElement(PUBLISH_BUTTON_LOCATOR).click();
        logger.info("Post is published");

    }
    public boolean isTheLastPostNameCorrect(String titleName) {
        wait.withTimeout(Duration.ofSeconds(10));
        driver.get(POSTS_PAGE_URL);
        wait.withTimeout(Duration.ofSeconds(10));
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException e) {
            logger.info("No alert found");
        }
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(VIEW_ALL_POSTS_LOCATOR));
        List<WebElement> allPosts = driver.findElements(VIEW_ALL_POSTS_LOCATOR);
        wait.withTimeout(Duration.ofSeconds(10));

        for (WebElement post : allPosts) {
            if(post.getText().contains(titleName)) {
                System.out.println("Post with title " + titleName + " found");
                return true;
            } else {
                logger.error("Post with title " + titleName + " not found");
            }
        }
        return false;
    }
}