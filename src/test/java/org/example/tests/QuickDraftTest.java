package org.example.tests;

import org.example.utils.TestDataGenerator;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class QuickDraftTest extends BaseTest{

    @Test
    public void userShouldCreateQuickDraftWithRandomData() {
        String randomTitle = TestDataGenerator.generateRandomTitle();
        String randomContent = TestDataGenerator.generateRandomContent();

        logger.debug("Test: user Should Create Quick Draft with Random Data: Opening LoginPage.");
        loginPage.openLoginPage(url);
        logger.debug("login with valid credentials.");
        loginPage.login(username, password);
        mainPage.createQuickDraft(randomTitle, randomContent);

       // wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//a[@class='row-title'][1]")));
        List<String> draftTitles = postsPage.getLatestDraftTitles();
        assertTrue(draftTitles.size() > 0, "No drafts found.");
        assertEquals(draftTitles.get(0), randomTitle, "Title is not correct");
        logger.debug("Quick Draft with Random Data created successfully.");
    }

    @Test
    public void userShouldCreateQuickDraftWithHardCodeData() {

        logger.debug("Test: user Should Create Quick Draft with HardCode: Opening LoginPage.");
        loginPage.openLoginPage(url);
        logger.debug("login with valid credentials.");
        loginPage.login(username, password);
        mainPage.createQuickDraft("not randomTitle", "not randomContent");

        // wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//a[@class='row-title'][1]")));
        List<String> draftTitles = postsPage.getLatestDraftTitles();
        assertTrue(draftTitles.size() > 0, "No drafts found.");
        assertEquals(draftTitles.get(0), "not randomTitle", "Title is not correct");
        logger.debug("Quick Draft with Random Data created successfully.");
    }
}
