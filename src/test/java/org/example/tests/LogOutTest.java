package org.example.tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class LogOutTest extends BaseTest {

    @Test
    public void userShouldLogOut() {
        logger.debug("Test: user Should Login With Valid Credentials: Opening LoginPage.");
        loginPage.openLoginPage(url);
        logger.debug("login with valid credentials.");
        loginPage.login(username, password);
        mainPage.logout();
        assertTrue(loginPage.isDisplayed(), "User can not logOut or Login page is not opened");

    }
}
