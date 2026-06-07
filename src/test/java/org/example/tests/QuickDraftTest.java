package org.example.tests;

import org.example.pages.PostsPage;
import org.example.utils.TestDataGenerator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class QuickDraftTest extends BaseTest{


    @Test
    public void userShouldCreateQuickDraft() {
        logger.debug("Test: user Should Create Quick Draft: Opening LoginPage.");
        loginPage.openLoginPage(url);
        logger.debug("login with valid credentials.");
        loginPage.login(username, password);
        mainPage.createQuickDraft("Title 123", "Content");
        postsPage.clickOnViewAllDrafts();
        List<String> draftTitles = postsPage.getLatestDraftTitles();
        assertTrue(draftTitles.size() > 0, "No drafts found.");
        assertEquals(draftTitles.get(0), "Title 123", "Title is not correct");
        logger.debug("Quick Draft created successfully.");
    }

    @Test //todo: add wait or smth to make sure that draft is created
    public void userShouldCreateQuickDraftWithRandomData() {
        String randomTitle = TestDataGenerator.generateRandomTitle();
        String randomContent = TestDataGenerator.generateRandomContent();

        logger.debug("Test: user Should Create Quick Draft with Random Data: Opening LoginPage.");
        loginPage.openLoginPage(url);
        logger.debug("login with valid credentials.");
        loginPage.login(username, password);
        mainPage.createQuickDraft(randomTitle, randomContent);
        postsPage.clickOnViewAllDrafts();
       // wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//a[@class='row-title'][1]")));
        List<String> draftTitles = postsPage.getLatestDraftTitles();
        assertTrue(draftTitles.size() > 0, "No drafts found.");
        assertEquals(draftTitles.get(0), randomTitle, "Title is not correct");
        logger.debug("Quick Draft with Random Data created successfully.");
    }
}
