package com.clickship.web.automation.listeners;

import com.google.inject.Injector;
import com.google.inject.Module;
import org.testng.*;
import org.testng.xml.XmlTest;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class DefaultTestNGContext implements ITestContext {

    ISuite suite;

    public DefaultTestNGContext() {
        this.suite = new DefaultSuite();
    }

    public Object getAttribute(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    public void setAttribute(String name, Object value) {
        // TODO Auto-generated method stub

    }

    public Set<String> getAttributeNames() {
        // TODO Auto-generated method stub
        return null;
    }

    public Object removeAttribute(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }

    public Date getStartDate() {
        // TODO Auto-generated method stub
        return null;
    }

    public Date getEndDate() {
        // TODO Auto-generated method stub
        return null;
    }

    public IResultMap getPassedTests() {
        // TODO Auto-generated method stub
        return null;
    }

    public IResultMap getSkippedTests() {
        // TODO Auto-generated method stub
        return null;
    }

    public IResultMap getFailedButWithinSuccessPercentageTests() {
        // TODO Auto-generated method stub
        return null;
    }

    public IResultMap getFailedTests() {
        // TODO Auto-generated method stub
        return null;
    }

    public String[] getIncludedGroups() {
        // TODO Auto-generated method stub
        return null;
    }

    public String[] getExcludedGroups() {
        // TODO Auto-generated method stub
        return null;
    }

    public String getOutputDirectory() {
        try {
            return this.getClass().getResource("/").getPath()
                    + "../../test-output/defaultSuite";
        } catch (Exception e) {
            return null;
        }
    }

    public ISuite getSuite() {
        return suite;
    }

    public ITestNGMethod[] getAllTestMethods() {
        // TODO Auto-generated method stub
        return null;
    }

    public String getHost() {
        // TODO Auto-generated method stub
        return null;
    }

    public Collection<ITestNGMethod> getExcludedMethods() {
        // TODO Auto-generated method stub
        return null;
    }

    public IResultMap getPassedConfigurations() {
        // TODO Auto-generated method stub
        return null;
    }

    public IResultMap getSkippedConfigurations() {
        // TODO Auto-generated method stub
        return null;
    }

    public IResultMap getFailedConfigurations() {
        // TODO Auto-generated method stub
        return null;
    }

    public XmlTest getCurrentXmlTest() {
        // TODO Auto-generated method stub
        return null;
    }

    public List<Module> getGuiceModules(Class<? extends Module> cls) {
        // TODO Auto-generated method stub
        return null;
    }

    public void addGuiceModule(Class<? extends Module> cls, Module module) {
        // TODO Auto-generated method stub

    }

    public Injector getInjector(List<Module> moduleInstances) {
        // TODO Auto-generated method stub
        return null;
    }

    public void addInjector(List<Module> moduleInstances, Injector injector) {
        // TODO Auto-generated method stub

    }

    public Injector getInjector(IClass iClass) {
        // TODO Auto-generated method stub
        return null;
    }

}