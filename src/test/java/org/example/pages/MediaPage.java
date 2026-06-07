package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MediaPage extends BasePage {

    private static final By MEDIA_PAGE_HEADER_LOCATOR = By.xpath(".//h1[@class='wp-heading-inline']");
    private static final String MEDIA_PAGE_URL = "https://wordpress-test-app-for-selenium.azurewebsites.net/wp-admin/upload.php";
    private static final By MEDIA_PAGE_ITEM_LIST_LOCATOR = By.id("the-list");
    private static final By ADD_NEW_MEDIA_ITEM_LOCATOR = By.id(".//a[@class='page-title-action']");
    private static final By CHOOSE_FILE_LOCATOR = By.xpath("//input[@id='async-upload']");
    private static final By UPLOAD_NEW_MEDIA_BUTTON_LOCATOR = By.xpath("//input[@id='html-upload']");
    private static final String IMAGE_PNG_PATH = "C:\\My_Files\\ItAcademyCourse\\code\\Wordpress\\src\\test\\resources\\image.png";


    public MediaPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isDisplayed() {
        return false;
    }

    public void uploadMediaContent() {
        driver.get("https://wordpress-test-app-for-selenium.azurewebsites.net/wp-admin/media-new.php");

        WebElement uploadElement = driver.findElement(CHOOSE_FILE_LOCATOR);
        uploadElement.sendKeys("C:\\My_Files\\ItAcademyCourse\\code\\Wordpress\\src\\test\\resources\\image.png");
        driver.findElement(UPLOAD_NEW_MEDIA_BUTTON_LOCATOR).click();
    }

    public boolean isMediaFileUploaded(String fileName) {

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        List<WebElement> mediaItems = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(MEDIA_PAGE_ITEM_LIST_LOCATOR));

        for (WebElement mediaItem : mediaItems) {
            if (mediaItem.getText().contains(fileName)) {
                return true;
            }
        }
        return false;
    }

    public String getLastUploadedMediaFileName() {
        List<WebElement> mediaItems = driver.findElements(MEDIA_PAGE_ITEM_LIST_LOCATOR);

        if (!mediaItems.isEmpty()) {
            return mediaItems.get(0).getText();
        }
        return null;
    }
}

