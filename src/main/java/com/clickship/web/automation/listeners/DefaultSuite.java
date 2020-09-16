package com.clickship.web.automation.listeners;

import com.google.inject.Injector;
import org.testng.*;
import org.testng.internal.annotations.IAnnotationFinder;
import org.testng.xml.XmlSuite;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DefaultSuite implements ISuite {

    private XmlSuite xmlSuite;

    public DefaultSuite(){
        this.xmlSuite = new DefaultXmlSuite();
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
        return "Default suite";
    }


    public Map<String, ISuiteResult> getResults() {
        // TODO Auto-generated method stub
        return null;
    }


    public IObjectFactory getObjectFactory() {
        // TODO Auto-generated method stub
        return null;
    }


    public IObjectFactory2 getObjectFactory2() {
        // TODO Auto-generated method stub
        return null;
    }


    public String getOutputDirectory() {
        // TODO Auto-generated method stub
        return null;
    }


    public String getParallel() {
        // TODO Auto-generated method stub
        return null;
    }


    public String getParameter(String parameterName) {
        // TODO Auto-generated method stub
        return null;
    }


    public Map<String, Collection<ITestNGMethod>> getMethodsByGroups() {
        // TODO Auto-generated method stub
        return null;
    }


    public Collection<ITestNGMethod> getInvokedMethods() {
        // TODO Auto-generated method stub
        return null;
    }


    public List<IInvokedMethod> getAllInvokedMethods() {
        // TODO Auto-generated method stub
        return null;
    }


    public Collection<ITestNGMethod> getExcludedMethods() {
        // TODO Auto-generated method stub
        return null;
    }


    public void run() {
        // TODO Auto-generated method stub

    }


    public String getHost() {
        // TODO Auto-generated method stub
        return null;
    }


    public SuiteRunState getSuiteState() {
        // TODO Auto-generated method stub
        return null;
    }


    public IAnnotationFinder getAnnotationFinder() {
        // TODO Auto-generated method stub
        return null;
    }


    public XmlSuite getXmlSuite() {
        // TODO Auto-generated method stub
        return xmlSuite;
    }


    public void addListener(ITestNGListener listener) {
        // TODO Auto-generated method stub

    }


    public List<ITestNGMethod> getAllMethods() {
        // TODO Auto-generated method stub
        return null;
    }


    public Injector getParentInjector() {
        // TODO Auto-generated method stub
        return null;
    }


    public String getParentModule() {
        // TODO Auto-generated method stub
        return null;
    }


    public void setParentInjector(Injector arg0) {
        // TODO Auto-generated method stub

    }

    public String getGuiceStage() {
        // TODO Auto-generated method stub
        return null;
    }

}
