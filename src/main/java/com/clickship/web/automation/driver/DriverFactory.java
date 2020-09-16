package com.clickship.web.automation.driver;

import com.clickship.web.automation.exceptions.AutomationException;
import com.clickship.web.automation.listeners.ContextManager;
import com.clickship.web.automation.utils.ConfigReader;
import com.clickship.web.automation.utils.LoggerFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;

import java.util.Map;

public class DriverFactory {
    private static final LoggerFactory logger = new LoggerFactory(DriverFactory.class);
    private static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>();

    public static synchronized RemoteWebDriver getDriver() {
        logger.info("Thread id = " + Thread.currentThread().getId());
        return (RemoteWebDriver) driver.get();
    }

    public static synchronized void killDriver() {

        if (!(driver.get() == null)) {
            driver.get().quit();
        } else {
            try {
                throw new Exception("WebDriver Exception Occurred");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private RemoteWebDriver createDriverInstance() {
        initiateDriver();
        return getDriver();
    }

    public synchronized void initiateDriver() {
        String browser = getBrowserValue();
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.setPageLoadStrategy(PageLoadStrategy.EAGER);
            driver.set(new ChromeDriver(options));
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver.set(new FirefoxDriver());
        } else if (browser.equalsIgnoreCase("ie")) {
            WebDriverManager.iedriver().setup();
            driver.set(new InternetExplorerDriver());
        }
        String runEnv= System.getProperty("runEnv");
        System.out.println(":::::url "+"https://"+runEnv+ConfigReader.getProperty("url"));
        driver.get().get("https://"+runEnv+ConfigReader.getProperty("url"));
        driver.get().manage().window().maximize();
    }

    public String getBrowserValue() {
        String browser = (String) ContextManager.getGlobalContext().getAttribute("browser");
        ITestContext context = ContextManager.getTestLevelContext();
        if (context != null) {
            Map<String, String> testParameters = context.getCurrentXmlTest().getAllParameters();
            if (!testParameters.isEmpty()) {
                if (testParameters.containsKey("browser"))
                    browser = testParameters.get("browser");
            }
        }
        if (browser.equals("") || browser == null) {
            throw new AutomationException("Browser value is set to null or blank, please pass correct browser value");
        }
        return browser;
    }

    public String getDriverVersion() {
        String driverVersionNumber = (String) ContextManager.getGlobalContext()
                .getAttribute("desiredDriverBrowserVersion");
        if (driverVersionNumber.equals("") || driverVersionNumber == null) {
            throw new AutomationException("Browser driver version is missing, please specify the correct driver version number");
        }
        return driverVersionNumber;
    }


    public RemoteWebDriver setWebDriverManager() {
        String browser = getBrowserValue();

        if (browser.equalsIgnoreCase("chrome")) {
            logger.debug(WebDriverManager.chromedriver().getVersions().toString());
            WebDriverManager.chromedriver().setup();
            logger.debug("Current chrome browser binary path--> " + WebDriverManager.chromedriver().getBinaryPath());
            logger.debug("Current chrome browser binary version--> "
                    + WebDriverManager.chromedriver().getDownloadedVersion());
            return createDriverInstance();

        } else if (browser.equalsIgnoreCase("firefox")) {
            logger.debug(WebDriverManager.firefoxdriver().getVersions().toString());
            WebDriverManager.firefoxdriver().setup();
            logger.debug("Current firefox browser binary path--> " + WebDriverManager.firefoxdriver().getBinaryPath());
            logger.debug("Current firefox browser binary version--> "
                    + WebDriverManager.firefoxdriver().getDownloadedVersion());
            return createDriverInstance();

        } else if (browser.equalsIgnoreCase("opera")) {
            logger.debug(WebDriverManager.operadriver().getVersions().toString());
            WebDriverManager.operadriver().setup();
            logger.debug("Current opera browser binary path--> " + WebDriverManager.operadriver().getBinaryPath());
            logger.debug(
                    "Current opera browser binary version--> " + WebDriverManager.operadriver().getDownloadedVersion());
            return createDriverInstance();

        } else if (browser.equalsIgnoreCase("ie")) {
            logger.debug(WebDriverManager.iedriver().getVersions().toString());
            WebDriverManager.iedriver().setup();
            logger.debug("Current IE browser binary path--> " + WebDriverManager.iedriver().getBinaryPath());
            logger.debug("Current IE browser binary version--> " + WebDriverManager.iedriver().getDownloadedVersion());
            return createDriverInstance();

        } else if (browser.equalsIgnoreCase("edge")) {
            logger.debug(WebDriverManager.edgedriver().getVersions().toString());
            WebDriverManager.edgedriver().setup();
            logger.debug("Current edge browser binary path--> " + WebDriverManager.edgedriver().getBinaryPath());
            logger.debug(
                    "Current edge browser binary version--> " + WebDriverManager.edgedriver().getDownloadedVersion());
            return createDriverInstance();

        } else if (browser.equalsIgnoreCase("safari")) {
            return createDriverInstance();

        } else {
            logger.error("No Browser is mentioned -->" + browser + "Please mention browser name");
            return null;
        }

    }

    public RemoteWebDriver setCustomWebDriverManager() {

        String browser = getBrowserValue();
        String driverVersionNumber = getDriverVersion();

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().version(driverVersionNumber).setup();
            return createDriverInstance();
        } else if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.firefoxdriver().version(driverVersionNumber).setup();
            return createDriverInstance();
        } else if (browser.equalsIgnoreCase("opera")) {
            WebDriverManager.operadriver().version(driverVersionNumber).setup();
            return createDriverInstance();
        } else if (browser.equalsIgnoreCase("ie")) {
            WebDriverManager.iedriver().version(driverVersionNumber).setup();
            return createDriverInstance();
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().version(driverVersionNumber).setup();
            return createDriverInstance();
        } else {
            logger.error("No Browser is mentioned -->" + browser + "Please mention browser name");
            return null;
        }

    }

}
