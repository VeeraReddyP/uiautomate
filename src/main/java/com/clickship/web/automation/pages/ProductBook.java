package com.clickship.web.automation.pages;

import com.clickship.web.automation.base.BasePage;
import com.clickship.web.automation.constants.TestData;
import com.clickship.web.automation.utils.ElementActions;
import com.clickship.web.automation.utils.LoggerFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.File;

public class ProductBook extends BasePage {

    private static final LoggerFactory logger = new LoggerFactory(ProductBook.class);
    public RemoteWebDriver driver;
    public ElementActions elementActions;
    By myAccountInDashboard = By.xpath("//a[text()='My Account']");
    By productBookBtn = By.xpath("//a[text()='Product Book']");
    By addEntryBtn = By.xpath("//span[text()='Add Entry']");
    By prodId = By.name("product.productId");
    By prodUnitVal = By.name("product.unitPrice");
    By submitBtn = By.xpath("//button[@type='submit']");
    By uploadFileBtn = By.id("fileUpload");
    By uploadProductFileBtn = By.id("idUpload");
    By fakePathVisible = By.id("valFileName");
    By saveFileBtn = By.xpath("//button[text()='Save File']");
    By successMsg = By.xpath("//div[@class='message']");
    private String productId = TestData.productId;
    public ProductBook(RemoteWebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.driver = driver;
        elementActions = new ElementActions(driver);
    }

    public By createdProdId(String productIdValue) {
        return By.xpath("//td[text()='" + productIdValue + "']");
    }

    public void navigateToMyAccount() {
        elementActions.waitForElemenWithFluent(myAccountInDashboard, TestData.timeOut, TestData.pollTime);
        elementActions.click(myAccountInDashboard);
    }

    public void clickOnProductBook() {
        elementActions.waitForElemenWithFluent(productBookBtn, TestData.timeOut, TestData.pollTime);
        elementActions.click(productBookBtn);
    }

    public void clickOnAddEntry() {
        elementActions.waitForElemenWithFluent(addEntryBtn, TestData.timeOut, TestData.pollTime);
        elementActions.click(addEntryBtn);
    }

    public void fillProductDetails() {
        elementActions.waitForElemenWithFluent(prodId, TestData.timeOut, TestData.pollTime);
        elementActions.enterData(prodId, productId);
        elementActions.waitForElemenWithFluent(prodUnitVal, TestData.timeOut, TestData.pollTime);
        elementActions.enterData(prodUnitVal, TestData.productUnitValue);
        elementActions.click(submitBtn);
    }

    public boolean isProductAdded() {
        elementActions.waitForElemenWithFluent(createdProdId(productId), TestData.timeOut, TestData.pollTime);
        return elementActions.isElementPresent(driver.findElement(createdProdId(productId)));
    }

    public void uploadProductFile(File filePath) {
        elementActions.waitForElemenWithFluent(uploadFileBtn, TestData.timeOut, TestData.pollTime);
        elementActions.scrollInToView(driver.findElement(uploadFileBtn));
        elementActions.sendKeys(uploadProductFileBtn, filePath.getAbsolutePath());
        elementActions.waitForElemenWithFluent(fakePathVisible, TestData.timeOut, TestData.pollTime);
        elementActions.click(saveFileBtn);
    }

    public String getSuccessMsg() {
        elementActions.waitForElemenWithFluent(successMsg, TestData.timeOut, TestData.pollTime);
        return elementActions.getElementText(successMsg);
    }
}
