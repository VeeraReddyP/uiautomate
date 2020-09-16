package com.clickship.web.automation.tests.login;

import com.clickship.web.automation.base.BaseTest;
import com.clickship.web.automation.driver.DriverFactory;
import com.clickship.web.automation.pages.DashBoardPage;
import com.clickship.web.automation.pages.LoginPage;
import com.clickship.web.automation.pages.ShipmentPage;
import com.clickship.web.automation.utils.LoggerFactory;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class LoginTest extends BaseTest {

    private static final LoggerFactory logger = new LoggerFactory(LoginTest.class);
    public DriverFactory driverFactory;
    RemoteWebDriver driver;
    LoginPage loginPage;
    DashBoardPage dashBoardPage;
    ShipmentPage shipmentPage;

    private String actualUserName = "testcs1900";
    private String actualPassword = "admin123";


    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driverFactory = new DriverFactory();
        driver = driverFactory.setWebDriverManager();
        loginPage = new LoginPage(driver);
        dashBoardPage = new DashBoardPage(loginPage.driver);
        shipmentPage = new ShipmentPage(loginPage.driver);
    }

    @Test
    public void createShipment() {
        loginPage.enterLoginDetails(actualUserName, actualPassword);
        String welComeMsg = dashBoardPage.getLoggedInUserName();
        String[] userDetails = welComeMsg.split(",");
        Assert.assertEquals(actualUserName, userDetails[1].trim(), "Oops,Logged in user name not match with expected");
        shipmentPage.navigateToShipments();
        shipmentPage.clickOnNewShipment();
        shipmentPage.fillShipToDetails();
        shipmentPage.clickLetterPackage();
        shipmentPage.clickGetRatesBtn();
        shipmentPage.clickScheduleLater();
        shipmentPage.clickSubmitOrderBtn();
        String transactionNumber = shipmentPage.getTransactionNumber();
        logger.info("Transaction number :::::: " + transactionNumber);
        Assert.assertTrue((Integer.parseInt(transactionNumber)) != 0, "Transaction should not bee Zero");

    }
}
