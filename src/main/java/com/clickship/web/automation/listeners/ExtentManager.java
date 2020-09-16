package com.clickship.web.automation.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.util.Date;

public class ExtentManager {

    private static final String OUTPUT_FOLDER = "./reports/";
    private static final String FILE_NAME = "AutomationReport.html";
    public static ExtentTest extentTest;
    private static ExtentReports extent;

    public static ExtentReports createInstance() {
        String directory = System.getProperty("user.dir") + "/reports/";
        new File(directory).mkdir();
        String path = directory + FILE_NAME;
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(path);
        htmlReporter.config().setDocumentTitle("Automation Test Results");
        htmlReporter.config().setReportName("Automation Test Results");
        htmlReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setReportUsesManualConfiguration(true);

        return extent;

    }

    public static String getReportName() {
        Date d = new Date();
        String fileName = "AutomationReport_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
        return fileName;
    }

}
