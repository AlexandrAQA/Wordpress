package org.example.tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    private static final String ERROR_MESSAGE_WHEN_PASSWORD_IS_EMPTY = "Error: The password field is empty.";
    private static final String ERROR_MESSAGE_WHEN_USERNAME_IS_EMPTY = "Error: The username field is empty.";
    private static final String ERROR_MESSAGE_WHEN_INVALID_CREDENTIALS = "Error: The username invalidUserName is not registered on this site. " +
            "If you are unsure of your username, try your email address instead.";

    private static final String ERROR_MESSAGE_WHEN_EMPTY_CREDENTIALS =
            "Error: The username field is empty.\n" +
                    "Error: The password field is empty.";

    @Test
    public void userShouldLoginWithValidCredentials() {
        loginPage.openLoginPage();
        loginPage.loginAsStandardAdmin(username, password);
        assertTrue(dashboardPage.isDashboardPageOpened(), "User can not logIn or Dashboard page is not opened");
    }

    @Test
    public void passwordShouldBeRequiredForLogin() {
        loginPage.openLoginPage();
        loginPage.loginAsStandardAdmin(username, "");
        assertEquals(loginPage.getErrorMessage(),
                ERROR_MESSAGE_WHEN_PASSWORD_IS_EMPTY, "Correct error message is not displayed");
    }

    @Test
    public void usernameShouldBeRequiredForLogin() {
        loginPage.openLoginPage();
        loginPage.loginAsStandardAdmin("", password);
        assertEquals(loginPage.getErrorMessage(),
                ERROR_MESSAGE_WHEN_USERNAME_IS_EMPTY, "Correct error message is not displayed");
    }

    @Test
    public void userWithEmptyCredentialsShouldNotLogIn() {
        loginPage.openLoginPage();
        loginPage.loginAsStandardAdmin("", "");
        assertEquals(loginPage.getErrorMessageWhenEmptyUsername(),
                ERROR_MESSAGE_WHEN_EMPTY_CREDENTIALS, "Correct error message about username is not displayed");

    }

    @Test
    public void userWithInvalidCredentialsShouldNotLogIn() {
        loginPage.openLoginPage();
        loginPage.loginAsStandardAdmin("invalidUserName", "invalidPassword");
        assertEquals(loginPage.getErrorMessageWhenInvalidCredentials(),
                ERROR_MESSAGE_WHEN_INVALID_CREDENTIALS, "Correct error message is not displayed");
    }
}
