package org.example;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogsAllureTest {

    WebDriver driver;
    Logger logger = Logger.getLogger(LogsAllureTest.class);

    @Test
    public void testLogsAllure() {
        logger.info("Starting new test case example");
        logger.info("1st Test output");
        Assert.assertTrue(true);
    }

    @Test
    public void testLogsAllure2() {
        System.out.println("2nd Test output");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com");
        Assert.assertTrue(true);
    }

    @Test
    public void testLogsAllure3() {
        System.out.println("3rd Test output");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com");
        Assert.assertEquals(driver.getTitle(), "Google");
    }

}
