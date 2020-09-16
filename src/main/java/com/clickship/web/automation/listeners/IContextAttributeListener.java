package com.clickship.web.automation.listeners;

import org.testng.ITestContext;

public interface IContextAttributeListener {

    public void load(ITestContext testNGCtx, Context context);
}
