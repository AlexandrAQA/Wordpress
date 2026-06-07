package org.example.tests;

import org.testng.annotations.Test;

public class PostTest extends BaseTest{

    @Test
    public void userShouldCreatePost2() {
        logger.debug("Test: user Should Create Post: Opening DashboardPage.");
        loginPage.openLoginPage(url);
        loginPage.login(username, password);
        postsPage.openPostPageAndClickOnCreatingPost();
        postsPage.createPost2("1st Test title", "Try it again. Fail again. Fail better.");

        // assertTrue(postsPage.isPostCreated(), "Post is not created");
    }
}
