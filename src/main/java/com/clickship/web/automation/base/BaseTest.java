package com.clickship.web.automation.base;

import com.clickship.web.automation.driver.DriverFactory;
import com.clickship.web.automation.listeners.ContextManager;
import com.clickship.web.automation.listeners.TestMethodListener;
import com.clickship.web.automation.utils.ConfigReader;
import com.clickship.web.automation.utils.LoggerFactory;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.xml.XmlTest;

import java.lang.reflect.Method;

@Listeners({TestMethodListener.class})
public class BaseTest {

    private static final LoggerFactory logger = new LoggerFactory(BaseTest.class);
    public RemoteWebDriver driver;
    DriverFactory driverUtils = new DriverFactory();
    private long testExecutionStartTime;

    public void tearDown() {
        DriverFactory.killDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void afterTestMethod(Method method) {
        tearDown();
        logger.info((Thread.currentThread() + " Finish method " + method.getName()));
    }

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        if (driver != null) {
            driver.quit();
        }
        logger.info((Thread.currentThread() + " Finish Test "));
    }

    @AfterSuite(alwaysRun = true)
    public void afterTestSuite() {
        tearDown();
        long executionTime = System.currentTimeMillis() - testExecutionStartTime;
        if (executionTime > 0) {
            logger.info("Test Suite Execution Time: " + (executionTime) / 1000 / 60 + " minutes.");
        }

    }

    @BeforeTest(alwaysRun = true)
    public void beforeTest(ITestContext testContex, XmlTest xmlTest) {
        ContextManager.initTestLevelContext(testContex, xmlTest);
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeTestMethod(Object[] parameters, Method method, ITestContext testContex, XmlTest xmlTest,
                                 ITestResult testResult) {
        ContextManager.setTestContextAtMethod(testContex);
        //ContextManager.initTestLevelContext(testContex, xmlTest);
        logger.info(Thread.currentThread() + " Start method " + method.getName());
        ContextManager.initThreadContext(testContex, xmlTest);
        if (method != null) {
            ContextManager.getThreadContext().setAttribute("testMethodSignature",
                    constructMethodSignature(method, parameters));
        }
    }

    @BeforeSuite(alwaysRun = true)
    public void beforeTestSuite(ITestContext testContex) {
        ConfigReader.setSystemProperty();
        testExecutionStartTime = System.currentTimeMillis();
        System.setProperty("testRetryCount", ContextManager.getGlobalContext().getTestRetryCount());
        System.setProperty("testRetryInterval", ContextManager.getGlobalContext().getTestRetryInterval());
        ContextManager.initGlobalContext(testContex);
        // to support to call function in @beforeSuite
        ContextManager.initThreadContext(testContex, null);
    }

    private String constructMethodSignature(Method method, Object[] parameters) {
        return method.getDeclaringClass().getCanonicalName() + "." + method.getName() + "("
                + constructParameterString(parameters) + ")";
    }

    private String constructParameterString(Object[] parameters) {
        StringBuffer sbParam = new StringBuffer();

        if (parameters != null) {
            for (int i = 0; i < parameters.length; i++) {
                if (parameters[i] == null) {
                    sbParam.append("null, ");
                } else if (parameters[i] instanceof String) {
                    sbParam.append("\"").append(parameters[i]).append("\", ");
                } else {
                    sbParam.append(parameters[i]).append(", ");
                }
            }
        }

        if (sbParam.length() > 0)
            sbParam.delete(sbParam.length() - 2, sbParam.length() - 1);

        return sbParam.toString();

    }
}
