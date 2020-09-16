package com.clickship.web.automation.pages;

import com.clickship.web.automation.base.BasePage;
import com.clickship.web.automation.constants.TestData;
import com.clickship.web.automation.utils.ElementActions;
import com.clickship.web.automation.utils.LoggerFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

public class ShipmentPage extends BasePage {

    private static final LoggerFactory logger = new LoggerFactory(ShipmentPage.class);
    public RemoteWebDriver driver;
    public ElementActions elementActions;


    By shipmentsInDashboard = By.xpath("//a[text()='Ship']");
    By newShipment = By.xpath("//a[text()='New Shipment']");
    By shippingInfo = By.xpath("//a[text()='Shipping Information']");
    By shipToCompanyName = By.name("order.shipToAddress.company");
    By shipToCompanyAddress = By.name("order.shipToAddress.address1");
    By shipToCompanyPostal = By.name("order.shipToAddress.postalCode");
    By shipToCompanyCity = By.name("order.shipToAddress.city");
    By shipToCompanyPhone = By.name("order.shipToAddress.phone");
    By shipToCompanyAttention = By.name("order.shipToAddress.attention");
    By letterPackage = By.xpath("//input[@name='order.packageTypeId' and @value='1']");
    By getRatesBtn = By.xpath("//div[@id='submit']/span[2]");
    By submitOrderBtn = By.xpath("//span[@id='submitorderBtn']");
    By scheduleLater = By.xpath("//input[@name='order.pickupOptions' and @value='3']");
    By selctRateBtn = By.xpath("//input[@name='order.rateIndex' and @value='0']");
    By transactionNUmber = By.xpath("//strong[contains(text(),'Trans #')]/following::td[1]");


    public ShipmentPage(RemoteWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
        elementActions = new ElementActions(driver);
    }

    public void navigateToShipments() {
        elementActions.waitForElemenWithFluent(shipmentsInDashboard, TestData.timeOut, TestData.pollTime);
        elementActions.click(shipmentsInDashboard);
    }

    public void clickOnNewShipment() {
        elementActions.waitForElemenWithFluent(newShipment, TestData.timeOut, TestData.pollTime);
        elementActions.click(newShipment);
    }

    public void fillShipToDetails() {
        elementActions.waitForElemenWithFluent(shippingInfo, TestData.timeOut, TestData.pollTime);
        elementActions.waitForElemenWithFluent(shipToCompanyName, TestData.timeOut, TestData.pollTime);
        elementActions.enterData(shipToCompanyName, TestData.companyName);
        elementActions.enterData(shipToCompanyAddress, TestData.companyAddress);
        elementActions.enterData(shipToCompanyPostal, TestData.companyPostal);
        elementActions.click(shipToCompanyCity);
        elementActions.waitForElemenWithFluent(shipToCompanyPhone, TestData.timeOut, TestData.pollTime);
        elementActions.enterData(shipToCompanyPhone, TestData.companyPhone);
        elementActions.enterData(shipToCompanyAttention, TestData.companyNameExt);
    }

    public void clickLetterPackage() {
        elementActions.waitForElemenWithFluent(letterPackage, TestData.timeOut, TestData.pollTime);
        elementActions.scrollInToView(driver.findElement(letterPackage));
        elementActions.click(letterPackage);
    }

    public void clickGetRatesBtn() {
        elementActions.waitForElemenWithFluent(getRatesBtn, TestData.timeOut, TestData.pollTime);
        elementActions.click(getRatesBtn);
    }

    public void clickScheduleLater() {
        elementActions.waitForElemenWithFluent(selctRateBtn, TestData.timeOut, TestData.pollTime);
        elementActions.click(selctRateBtn);
        elementActions.waitForElemenWithFluent(scheduleLater, TestData.timeOut, TestData.pollTime);
        elementActions.scrollInToView(driver.findElement(scheduleLater));
        elementActions.click(scheduleLater);
    }

    public void clickSubmitOrderBtn() {
        elementActions.waitForElemenWithFluent(submitOrderBtn, TestData.timeOut, TestData.pollTime);
        elementActions.click(submitOrderBtn);
    }

    public String getTransactionNumber() {
        elementActions.waitForElemenWithFluent(transactionNUmber, TestData.timeOut, TestData.pollTime);
        return elementActions.getElementText(transactionNUmber);
    }


}
