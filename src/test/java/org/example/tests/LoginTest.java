package org.example.tests;

import org.testng.annotations.Test;

import static org.example.pages.LoginPage.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {


    @Test
    public void userShouldLoginWithValidCredentials() {
    loginPage.openLoginPage();
    loginPage.loginAsStandardAdmin(ADMIN_USERNAME, ADMIN_PASSWORD);
    assertTrue(dashboardPage.isDashboardPageOpened(), "User can not logIn or Dashboard page is not opened");
    }

    @Test
    public void passwordShouldBeRequiredForLogin() {
    loginPage.openLoginPage();
    loginPage.loginAsStandardAdmin(ADMIN_USERNAME, "");
       // System.out.println(loginPage.isErrorMessageDisplayed());
    assertEquals(loginPage.getErrorMessage(),
            ERROR_MESSAGE_WHEN_PASSWORD_IS_EMPTY, "Correct error message is not displayed");
    }
}
