package com.clickship.web.automation.base;

import com.clickship.web.automation.driver.DriverFactory;
import com.clickship.web.automation.listeners.ContextManager;
import com.clickship.web.automation.utils.ElementActions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BasePage extends DriverFactory {

    public RemoteWebDriver driver;
    public ElementActions elementAction;

    public BasePage(RemoteWebDriver driver) {

        if (driver == null) {
            String driverVersionNumber = (String) ContextManager.getGlobalContext()
                    .getAttribute("desiredDriverBrowserVersion");
            if (driverVersionNumber != null) {
                this.driver = new DriverFactory().setCustomWebDriverManager();
            } else {
                this.driver = new DriverFactory().setWebDriverManager();
            }
        } else {
            this.driver = driver;
        }
        elementAction = new ElementActions(driver);
    }
}
