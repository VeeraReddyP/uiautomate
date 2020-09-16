package com.clickship.web.automation.pages;

import com.clickship.web.automation.base.BasePage;
import com.clickship.web.automation.constants.TestData;
import com.clickship.web.automation.utils.ElementActions;
import com.clickship.web.automation.utils.LoggerFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    private static final LoggerFactory logger = new LoggerFactory(LoginPage.class);
    public RemoteWebDriver driver;
    public ElementActions elementActions;


    By loginUsername = By.id("username");
    By loginPwd = By.id("password");
    By loginBtn = By.xpath("//button[@type='button' and contains(text(),'Login')]");
    By loggedInUserName = By.xpath("//span[contains(@class,'welcome')]/h5");
    By amazonWelcomeText = By.xpath("//div[@id='amazonPopup']/descendant::h3");
    By amazonPopUpClose = By.xpath("//div[@id='amazonPopup']//button[text()='Close']");
    By invalidLoginMsg = By.xpath("//div[@class='modal-body']/span[1]");


    public LoginPage(RemoteWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
        elementActions = new ElementActions(driver);
    }

    public void enterLoginDetails(String userName, String password) {
        elementActions.waitForElemenWithFluent(loginUsername, TestData.timeOut, TestData.pollTime);
        elementActions.enterData(loginUsername, userName);
        elementActions.enterData(loginPwd, password);
        elementActions.click(loginBtn);
    }

    public String getAmazonWelcomeText() {
        elementActions.waitForElemenWithFluent(amazonWelcomeText, TestData.timeOut, TestData.pollTime);
        return elementActions.getElementText(amazonWelcomeText);
    }

    public void dismissAmazonPopup() {
        elementActions.waitForElemenWithFluent(amazonPopUpClose, TestData.timeOut, TestData.pollTime);
        elementActions.click(amazonPopUpClose);
    }

    public String getLoginUserName() {
        elementActions.waitForElemenWithFluent(loggedInUserName, TestData.timeOut, TestData.pollTime);
        return elementActions.getElementText(loggedInUserName);
    }

    public String getInvalidLoginText() {
        elementActions.waitForElemenWithFluent(invalidLoginMsg, TestData.timeOut, TestData.pollTime);
        return elementActions.getElementText(invalidLoginMsg);
    }
}
