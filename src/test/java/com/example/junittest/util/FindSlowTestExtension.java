package com.example.junittest.util;

import com.example.junittest.annotation.SlowTest;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Method;

public class FindSlowTestExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {
//    private static final long THRESHOLD = 1000L;
    private long THRESHOLD;



    public FindSlowTestExtension(long THRESHOLD) {
        this.THRESHOLD = THRESHOLD;
    }

    public FindSlowTestExtension() {
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        System.out.println("beforeTestExecution 실행");
        ExtensionContext.Store store = getStore(context);
        store.put("START_TIME", System.currentTimeMillis());
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        System.out.println("afterTestExecution 실행");
        Method requiredTestMethod = context.getRequiredTestMethod();
        SlowTest annotation = requiredTestMethod.getAnnotation(SlowTest.class);
        String testMethodName = context.getRequiredTestMethod().getName();
        ExtensionContext.Store store = getStore(context);
//        Long startTime1 = store.get("START_TIME", Long.class);
//        System.out.println(startTime1);
        Long startTime = store.remove("START_TIME", long.class);
        System.out.println(startTime);
        System.out.println(startTime);
        System.out.println(startTime);
        System.out.println(startTime);
        System.out.println(startTime);
        long duration = System.currentTimeMillis() - startTime;
        if (duration > THRESHOLD && annotation == null) {
            System.out.printf("Please consier mark method [%s] with @SlowTest.\n", testMethodName);
        }
    }

    private static ExtensionContext.Store getStore(ExtensionContext context) {
        String testClassName = context.getRequiredTestClass().getName();
        String testMethodName = context.getRequiredTestMethod().getName();
        ExtensionContext.Store store = context.getStore(ExtensionContext.Namespace.create(testClassName, testMethodName));
        return store;
    }
}
