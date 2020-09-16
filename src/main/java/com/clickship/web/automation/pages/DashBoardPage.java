package com.clickship.web.automation.pages;

import com.clickship.web.automation.base.BasePage;
import com.clickship.web.automation.constants.TestData;
import com.clickship.web.automation.utils.ElementActions;
import com.clickship.web.automation.utils.LoggerFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

public class DashBoardPage extends BasePage {

    private static final LoggerFactory logger = new LoggerFactory(DashBoardPage.class);
    public RemoteWebDriver driver;
    public ElementActions elementActions;


    By userNameDetails = By.xpath("//div[@class='welcomenote']");


    public DashBoardPage(RemoteWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
        elementActions = new ElementActions(driver);
    }

    public String getLoggedInUserName() {
        elementActions.waitForElemenWithFluent(userNameDetails, TestData.timeOut, TestData.pollTime);
        return elementActions.getElementText(userNameDetails);
    }


}
