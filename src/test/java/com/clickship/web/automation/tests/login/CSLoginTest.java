package com.clickship.web.automation.tests.login;

import com.clickship.web.automation.base.BaseTest;
import com.clickship.web.automation.constants.TestData;
import com.clickship.web.automation.driver.DriverFactory;
import com.clickship.web.automation.pages.LoginPage;
import com.clickship.web.automation.utils.LoggerFactory;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CSLoginTest extends BaseTest {

    private static final LoggerFactory logger = new LoggerFactory(CSLoginTest.class);
    public DriverFactory driverFactory;
    RemoteWebDriver driver;
    LoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driverFactory = new DriverFactory();
        driver = driverFactory.setWebDriverManager();
        loginPage = new LoginPage(driver);
    }

    @Test
    public void valid_login() {
        loginPage.enterLoginDetails(TestData.username, TestData.password);
        String expectedAmazonText = loginPage.getAmazonWelcomeText();
        Assert.assertEquals(TestData.amazonWelcomeText, expectedAmazonText);
        loginPage.dismissAmazonPopup();
        String expectedUserName[] = loginPage.getLoginUserName().split("\n");
        System.out.println(":::::" + expectedUserName[1]);
        Assert.assertEquals(TestData.username, expectedUserName[1]);
    }

    @Test
    public void inValid_login() {
        loginPage.enterLoginDetails(TestData.invalid_username, TestData.invalid_password);
        String expectedErrorText = loginPage.getInvalidLoginText();
        Assert.assertEquals(TestData.invalidLoginMessage, expectedErrorText);
    }

}
