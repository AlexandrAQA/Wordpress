package org.example.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MediaTest extends BaseTest{

    @Test
    public void userShouldUploadMediaContent() {
        logger.debug("Test: user Should Upload Media Content: Opening DashboardPage.");
        loginPage.openLoginPage(url);
        loginPage.login(username, password);

        mediaPage.uploadMediaContent();

        String uploadedFileName = "image";

        boolean isUploaded = mediaPage.isMediaFileUploaded(uploadedFileName);
        Assert.assertTrue(isUploaded, "Media content is not uploaded successfully or name is incorrect");

        String lastUploadedFileName = mediaPage.getLastUploadedMediaFileName();
        Assert.assertNotNull(lastUploadedFileName, "No media files found");
        logger.info("Last uploaded file name: " + lastUploadedFileName);
        System.out.println("Last uploaded file name: " + lastUploadedFileName);
    }
}
