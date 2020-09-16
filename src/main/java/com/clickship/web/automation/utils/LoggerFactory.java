package com.clickship.web.automation.utils;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Reporter;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoggerFactory {

    public Logger logger;

    private static Map<String, Map<String, Map<String, List<String>>>> pageListenerLogMap = Collections
            .synchronizedMap(new HashMap<>());

    public LoggerFactory(Class<?> clz) {
        this.logger = Logger.getLogger(clz);
    }


    public void log(String url, String message, boolean failed, boolean logToStandardOutput) {

        if (message == null)
            message = "";

        message = message.replaceAll("\\n", "<br/>");

        if (failed) {
            message = "<span style=\"font-weight:bold;color:#FF0066;\">" + message + "</span>";
        }

        Reporter.log(message);
    }

    public void error(String message) {
        logger.error(message);
        message = "<li><b><font color='#FF0000'>" + message + "</font></b></li>";

        if (Level.ERROR.isGreaterOrEqual(this.logger.getEffectiveLevel()))
            log(null, message, false, false);
    }

    public Logger getLogger(Class<?> clz) {

        String log4jConfigFile = System.getProperty("user.dir") + "/src/main/resources/log4j.properties" ;
        PropertyConfigurator.configure(log4jConfigFile);

        return Logger.getLogger(clz);
    }

    public static Map<String, Map<String, List<String>>> getPageListenerLog(String pageListenerClassName) {
        return pageListenerLogMap.get(pageListenerClassName);
    }

    public void info(String message) {
        logger.info(message);
        message = "<li><font color='#00529B'>" + message + "</font></li>";
        if (Level.INFO.isGreaterOrEqual(this.logger.getEffectiveLevel()))
            log(null, message, false, false);
    }

    public void warning(String message) {
        logger.warn(message);
        message = "<li><font color='#00529B'>" + message + "</font></li>";

        if (Level.WARN.isGreaterOrEqual(this.logger.getEffectiveLevel()))
            log(null, message, false, false);
    }

    public void debug(String message) {
        logger.debug(message);
        message = "<li><font color='#9F6000'>" + message + "</font></li>";

        if (Level.DEBUG.isGreaterOrEqual(this.logger.getEffectiveLevel()))
            log(null, message, false, false);
    }
}
