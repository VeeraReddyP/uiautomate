package com.clickship.web.automation.listeners;

import com.clickship.web.automation.utils.LoggerFactory;
import org.testng.ITestContext;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Context {

    static LoggerFactory logger = new LoggerFactory(Context.class);
    private Map<String, Object> contextDataMap = Collections.synchronizedMap(new HashMap<>());
    private ITestContext testNGContext = null;

    public Context(ITestContext context) {

        logger.info("Initializing the Context object");
        this.testNGContext = context;

        this.setContextAttribute(context, "testRetryInterval", System.getProperty("testRetryInterval"),
                "5");
        this.setContextAttribute(context, "testRetryCount", System.getProperty("testRetryCount"),
                "3");
    }

    public Object getAttribute(String name) {
        Object obj = this.contextDataMap.get(name);
        return obj == null ? null : obj;
    }

    public String getSuiteParameter(String key) {
        return System.getProperty(key) != null ? System.getProperty(key)
                : this.getTestNGContext().getSuite().getParameter(key);
    }

    public String getTestMethodSignature() {
        return (String) this.getAttribute("testMethodSignature");
    }

    public ITestContext getTestNGContext() {
        return this.testNGContext;
    }

    public void setAttribute(String name, Object value) {
        this.contextDataMap.put(name, value);
    }

    private void setContextAttribute(ITestContext context) {
        if (context != null) {
            Map testParameters = context.getSuite().getXmlSuite().getParameters();
            Iterator var3 = testParameters.entrySet().iterator();

            while (var3.hasNext()) {
                Map.Entry entry = (Map.Entry) var3.next();
                String attributeName = (String) entry.getKey();
                if (this.contextDataMap.get(entry.getKey()) == null) {
                    String sysPropertyValue = System.getProperty((String) entry.getKey());
                    String suiteValue = (String) entry.getValue();
                    this.setContextAttribute((String) attributeName, sysPropertyValue, suiteValue, (String) null);
                }
            }
        }

    }

    private void setContextAttribute(ITestContext context, String attributeName, String sysPropertyValue,
                                     String defaultValue) {
        String suiteValue = null;
        if (context != null) {
            suiteValue = context.getSuite().getParameter(attributeName);
        }

        this.contextDataMap.put(attributeName,
                sysPropertyValue != null ? sysPropertyValue : (suiteValue != null ? suiteValue : defaultValue));
    }

    public String getContextAttribute(String attributeName, String defaultValue) {
        String suiteValue = null;
        if (testNGContext != null) {
            suiteValue = testNGContext.getSuite().getParameter(attributeName);
        }
        suiteValue = suiteValue != null ? suiteValue : defaultValue;
        this.contextDataMap.put(attributeName, suiteValue);
        return suiteValue;

    }

    private void setContextAttribute(String attributeName, String sysPropertyValue, String suiteValue,
                                     String defaultValue) {
        this.contextDataMap.put(attributeName,
                sysPropertyValue != null ? sysPropertyValue : (suiteValue != null ? suiteValue : defaultValue));
    }

    public String getTestRetryCount() {
        return (String) this.getAttribute("testRetryCount");
    }

    public String getTestRetryInterval() {
        return (String) this.getAttribute("testRetryInterval");
    }

}
