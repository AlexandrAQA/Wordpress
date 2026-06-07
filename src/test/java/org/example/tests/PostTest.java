package org.example.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PostTest extends BaseTest{

    private String title = "1st Test title";

    @Test
    public void userShouldCreatePost() {
        logger.debug("Test: user Should Create Post: Opening DashboardPage.");
        loginPage.openLoginPage(url);
        loginPage.login(username, password);
        postsPage.openPostPageAndClickOnCreatingPost();
        postsPage.createNewPost(title);
        Assert.assertTrue(postsPage.isTheLastPostNameCorrect(title));
    }
}
