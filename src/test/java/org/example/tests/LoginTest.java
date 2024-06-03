package org.example.tests;

import io.qameta.allure.*;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Listeners({AllureTestNg.class})
public class LoginTest extends BaseTest {

    private static final String ERROR_MESSAGE_WHEN_PASSWORD_IS_EMPTY = "Error: The password field is empty.";
    private static final String ERROR_MESSAGE_WHEN_USERNAME_IS_EMPTY = "Error: The username field is empty.";
    private static final String ERROR_MESSAGE_WHEN_INVALID_CREDENTIALS = "Error: The username invalidUserName is not registered on this site. " +
            "If you are unsure of your username, try your email address instead.";

    private static final String ERROR_MESSAGE_WHEN_EMPTY_CREDENTIALS =
            "Error: The username field is empty.\n" +
                    "Error: The password field is empty.";

    @Test @Severity(SeverityLevel.CRITICAL)
    public void userShouldLoginWithValidCredentials() {
        logger.debug("Test: user Should Login With Valid Credentials: Opening LoginPage.");
        loginPage.openLoginPage(url);
        logger.debug("login with valid credentials.");
        loginPage.login(username, password);
        assertTrue(mainPage.isDisplayed(), "User can not logIn or Dashboard page is not opened");
        logger.debug("User logged in successfully with valid credentials.");
    }

    @Test
    public void passwordShouldBeRequiredForLogin() {
        logger.debug("Test: password Should Be Required For Login: Opening LoginPage.");
        loginPage.openLoginPage(url);
        logger.debug("Attempting to log in with no password.");
        loginPage.login(username, "");
        assertEquals(loginPage.getErrorMessage(),
                ERROR_MESSAGE_WHEN_PASSWORD_IS_EMPTY, "Correct error message is not displayed");
        logger.warn("Login without a password failed as expected.");
    }

    @Test
    public void usernameShouldBeRequiredForLogin() {
        logger.debug("Test: username Should Be Required For Login: Opening LoginPage.");
        loginPage.openLoginPage(url);
        loginPage.login("", password);
        assertEquals(loginPage.getErrorMessage(),
                ERROR_MESSAGE_WHEN_USERNAME_IS_EMPTY, "Correct error message is not displayed");
    }

    @Test @Severity(SeverityLevel.CRITICAL)
    public void userWithEmptyCredentialsShouldNotLogIn() {
        logger.debug("Test: user With Empty Credentials Should Not LogIn");
        loginPage.openLoginPage(url);
        loginPage.login("", "");
        assertEquals(loginPage.getErrorMessageWhenEmptyUsername(),
                ERROR_MESSAGE_WHEN_EMPTY_CREDENTIALS, "Correct error message about username is not displayed");
        logger.warn("Login with empty credentials failed as expected.");

    }

    @Test @Flaky // This test is marked as flaky for trying Allure annotation
    @Description("This test checks that user with invalid credentials should not log in.")
    @Link("https://fake.jira.org")
    @Issue("TES-123")
    @TmsLink("TEST-RAIL-TC-123")
    @Severity(SeverityLevel.CRITICAL)
    public void userWithInvalidCredentialsShouldNotLogIn() {
        logger.debug("Test: user With Invalid Credentials Should Not LogIn: Opening LoginPage.");
        loginPage.openLoginPage(url);
        loginPage.login("invalidUserName", "invalidPassword");
        assertEquals(loginPage.getErrorMessageWhenInvalidCredentials(),
                ERROR_MESSAGE_WHEN_INVALID_CREDENTIALS, "Correct error message is not displayed");
        logger.warn("Login with invalid credentials failed as expected.");
    }
}
