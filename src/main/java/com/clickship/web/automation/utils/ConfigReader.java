package com.clickship.web.automation.utils;

import com.clickship.web.automation.exceptions.AutomationException;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class ConfigReader {

    public static Properties prop;
    static LoggerFactory logger = new LoggerFactory(ConfigReader.class);
    private ConfigReader() {
        throw new IllegalStateException("ConfigReader class");
    }

    public static String getProperty(String key) {
        prop = new Properties();
        try {
            FileReader reader = new FileReader(System.getProperty("user.dir") + "/src/main/resources/config.properties");
            prop.load(reader);
            return prop.getProperty(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prop.getProperty(key);
    }

    public static Properties loadConfigProperties() {
        if (prop != null) {
            return prop; // configProperties is loaded only once.
        }

        prop = new Properties();
        try {
            prop.load(new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/config.properties"));
        } catch (IOException e) {
            throw new AutomationException("Could not read config.properties");
        }
        return prop;
    }

    public static void setSystemProperty() {
        prop = loadConfigProperties();
        Boolean browserAlreadySetAtSystemLevel = false;
        String browserValueAtSystemProperty = System.getProperty("browser");
        if (null != browserValueAtSystemProperty && (!browserValueAtSystemProperty.isEmpty())) {
            browserAlreadySetAtSystemLevel = true;
        }
        Enumeration<?> e = prop.propertyNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            if (key.equals("browser") && browserAlreadySetAtSystemLevel) {
                logger.info("Browser Already set at system properties, ignoring the vale from properties file");
            } else {
                System.out.println(key + " --> " + prop.getProperty(key));
                System.setProperty(key, prop.getProperty(key));
            }
        }

    }

}
