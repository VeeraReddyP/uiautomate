package com.clickship.web.automation.listeners;

import com.clickship.web.automation.utils.LoggerFactory;
import org.testng.ITestContext;
import org.testng.xml.XmlTest;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.*;

public class ContextManager {

    static LoggerFactory logger = new LoggerFactory(ContextManager.class);
    private static List<IContextAttributeListener> contexAttributeListenerList = Collections.synchronizedList(new ArrayList<>());
    // define the global level context
    private static Context globalContext;
    // define the test level context
    private static Map<String, Context> testLevelContext = Collections.synchronizedMap(new HashMap<>());
    private static ThreadLocal<ITestContext> testContext = new ThreadLocal<ITestContext>();
    // define the thread level Context
    private static ThreadLocal<Context> threadLocalContext = new ThreadLocal<Context>();

    public static void addContexAttributeListener(IContextAttributeListener listener) {
        contexAttributeListenerList.add(listener);
    }

    public static Context getGlobalContext() {
        if (globalContext == null) {
            System.out.println("Initialize default GlobalContext");
            initGlobalContext(new DefaultTestNGContext());
        }
        return globalContext;
    }

    public static void setGlobalContext(Context ctx) {
        globalContext = (ctx);
    }

    public static Context getTestLevelContext(ITestContext testContext) {
        if (testContext != null && testContext.getCurrentXmlTest() != null) {
            if (testLevelContext.get(testContext.getCurrentXmlTest().getName()) == null) {
                // sometimes getTestLevelContext is called before @BeforeTest in
                // TestPlan
                initTestLevelContext(testContext, testContext.getCurrentXmlTest());
            }
            return testLevelContext.get(testContext.getCurrentXmlTest().getName());
        } else {
            return null;
        }
    }

    public static Context getTestLevelContext(String testName) {
        return testLevelContext.get(testName);
    }

    public static Context getThreadContext() {
        if (threadLocalContext.get() == null) {
            System.out.println("Initialize default ThreadContext");
            initThreadContext(getGlobalContext().getTestNGContext(), null);
        }
        return threadLocalContext.get();
    }

    public static void setThreadContext(Context ctx) {
        threadLocalContext.set(ctx);
    }

    public static synchronized ITestContext getTestLevelContext() {
        return testContext.get();
    }

    public static void setTestContextAtMethod(ITestContext testNgContext) {
        testContext.set(testNgContext);
    }

    public static void initGlobalContext(ITestContext testNGCtx) {
        testNGCtx = getContextFromConfigFile(testNGCtx);
        globalContext = new Context(testNGCtx);
        loadCustomizedContextAttribute(testNGCtx, globalContext);
    }

    private static ITestContext getContextFromConfigFile(ITestContext testContex) {
        if (testContex != null) {
            if (testContex.getSuite().getParameter("testConfig") != null) {
                File suiteFile = new File(testContex.getSuite().getXmlSuite().getFileName());
                String configFile = suiteFile.getPath().replace(suiteFile.getName(), "") + testContex.getSuite().getParameter("testConfig");
                NodeList nList = getXMLNodes(configFile, "parameter");
                Map<String, String> parameters = testContex.getSuite().getXmlSuite().getParameters();
                for (int i = 0; i < nList.getLength(); i++) {
                    Node nNode = nList.item(i);
                    parameters.put(nNode.getAttributes().getNamedItem("name").getNodeValue(), nNode.getAttributes().getNamedItem("value").getNodeValue());
                }
                testContex.getSuite().getXmlSuite().setParameters(parameters);
            }
        }
        return testContex;
    }

    public static void initTestLevelContext(ITestContext testNGCtx, XmlTest xmlTest) {
        Context context = new Context(testNGCtx);
        if (xmlTest != null) {
            Map<String, String> testParameters = xmlTest.getAllParameters();
            // parse the test level parameters
            for (Map.Entry<String, String> entry : testParameters.entrySet()) {
                context.setAttribute(entry.getKey(), entry.getValue());
            }

        }
        testLevelContext.put(xmlTest.getName(), context);
    }

    public static void initTestLevelContext(XmlTest xmlTest) {
        initTestLevelContext(globalContext.getTestNGContext(), xmlTest);
    }

    public static void initThreadContext() {
        initThreadContext(globalContext.getTestNGContext(), null);
    }

    public static void initThreadContext(ITestContext testNGCtx) {
        initThreadContext(testNGCtx, null);
    }

    public static void initThreadContext(ITestContext testNGCtx, XmlTest xmlTest) {
        Context context = new Context(testNGCtx);

        loadCustomizedContextAttribute(testNGCtx, context);

        if (xmlTest != null) {
            Map<String, String> testParameters = xmlTest.getAllParameters();
            // parse the test level parameters
            for (Map.Entry<String, String> entry : testParameters.entrySet()) {

                if (System.getProperty(entry.getKey()) == null)
                    context.setAttribute(entry.getKey(), entry.getValue());

            }

        }

        threadLocalContext.set(context);
    }

    public static void initThreadContext(XmlTest xmlTest) {
        initThreadContext(globalContext.getTestNGContext(), xmlTest);
    }

    private static void loadCustomizedContextAttribute(ITestContext testNGCtx, Context context) {
        for (int i = 0; i < contexAttributeListenerList.size(); i++) {
            contexAttributeListenerList.get(i).load(testNGCtx, context);
        }
    }

    public static NodeList getXMLNodes(String xmlFileName, String tagName) {
        NodeList nList = null;
        try {
            File xmlFile = new File(xmlFileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            nList = doc.getElementsByTagName(tagName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nList;
    }
}
