package com.clickship.web.automation.exceptions;

import java.util.List;

public class TestMethodErrorBuffer {

    private static ThreadLocal<List<Throwable>> testErrorBuffer = new ThreadLocal<>();

    public static List<Throwable> get() {
        return testErrorBuffer.get();
    }

    public static void set(List<Throwable> errorBuffer) {
        testErrorBuffer.set(errorBuffer);
    }

    public static void remove() {
        testErrorBuffer.remove();
    }
}
