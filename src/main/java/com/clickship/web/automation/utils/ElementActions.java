package com.clickship.web.automation.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestException;

import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class ElementActions {

    static LoggerFactory logger = new LoggerFactory(ElementActions.class);
    public RemoteWebDriver driver;
    public WebDriverWait wait;

    public ElementActions(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public WebElement getElement(@NotNull By selector) {
        try {
            return driver.findElement(selector);
        } catch (Exception e) {
            logger.error(String.format("Element %s does not exist - proceeding", selector));
        }
        return null;
    }

    public void clearField(@NotNull By selector) {
        try {
            getElement(selector).clear();
        } catch (Exception e) {
            logger.error(
                    String.format("The following element could not be cleared: [%s]", getElement(selector).getText()));
        }
    }

    public void enterData(@NotNull final By locator, @NotNull String text) {
        try {
            logger.info("enter text into the locator " + locator.toString());
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(text);
        } catch (Exception e) {
            throw new RuntimeException("Exception:" + e + " on ");
        }

    }

    public void sendKeys(@NotNull final By locator, @NotNull String text) {
        try {
            logger.info("enter text into the locator " + locator.toString());
            driver.findElement(locator).sendKeys(text);
        } catch (Exception e) {
            throw new RuntimeException("Exception:" + e + " on ");
        }

    }

    public void waitFor() {
        try {
            Thread.sleep(10000);
        } catch (Exception e) {

        }
    }

    public void click(@NotNull By selector) {
        WebElement element = getElement(selector);
        waitForElementToBeClickable(selector);
        try {
            if (element != null)
                element.click();
            logger.info("click on the locator " + selector.toString());
        } catch (Exception e) {
            throw new TestException(String.format("The following element is not clickable: [%s]", selector));
        }
    }

    public void waitForElementToBeClickable(@NotNull By selector) {
        try {
            wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.elementToBeClickable(selector));
        } catch (Exception e) {
            throw new TestException("The following element is not clickable: " + selector);
        }
    }

    public void waitForElementToBeVisible(@NotNull By selector) {
        try {
            wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.presenceOfElementLocated(selector));
        } catch (Exception e) {
            throw new NoSuchElementException(String.format(
                    "The following element was not visible within [%s] seconds: %s ", "10".toString(), selector));
        }
    }

    public void selectDropdownByText(@NotNull final By locator, @NotNull String text) {

        logger.info("Select drop down by visible txt: " + text);
        waitForElementToBeVisible(locator);

        new Select(driver.findElement(locator)).selectByVisibleText(text);

    }

    public String getElementText(@NotNull By selector) {
        waitForElementToBeVisible(selector);
        try {
            return getElement(selector).getText().trim();
        } catch (Exception e) {
            logger.error(String.format("Element %s does not exist - proceeding", selector));
        }
        return null;
    }

    public boolean waitForElemenWithFluent(@NotNull By selector, int timeOut, int pollTime) {
        try {
            FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
            wait.withTimeout(Duration.ofSeconds(timeOut));
            wait.pollingEvery(Duration.ofMillis(pollTime));
            wait.ignoring(NoSuchElementException.class);

            Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {
                public Boolean apply(WebDriver webDriver) {
                    WebElement element;
                    try {
                        element = webDriver.findElement(selector);
                        if (element.isDisplayed())
                            return true;
                    } catch (WebDriverException e) {
                        logger.error(e.getMessage());
                    }
                    return false;
                }
            };
            wait.until(function);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;

    }

    public boolean waitForElemenToBeClickWithFluent(@NotNull By selector, int timeOut, int pollTime) {
        try {
            FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
            wait.withTimeout(Duration.ofSeconds(timeOut));
            wait.pollingEvery(Duration.ofSeconds(pollTime));
            wait.ignoring(NoSuchElementException.class);
            wait.until(ExpectedConditions.elementToBeClickable(selector));

            Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>() {
                public Boolean apply(WebDriver webDriver) {
                    WebElement element;
                    try {
                        element = webDriver.findElement(selector);
                        if (element.isDisplayed())
                            return true;
                    } catch (WebDriverException e) {
                        logger.error(e.getMessage());
                    }
                    return false;
                }
            };
            wait.until(function);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public List<WebElement> getElements(@NotNull By locator) {
        return driver.findElements(locator);
    }

    public boolean isElementPresent(@NotNull WebElement ele) {
        try {
            ele.isDisplayed();
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public WebElement scrollInToView(@NotNull WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        return element;
    }
}
