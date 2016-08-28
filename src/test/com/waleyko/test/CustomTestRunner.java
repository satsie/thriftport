package com.waleyko.test;

import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

public class CustomTestRunner extends SpringJUnit4ClassRunner{

    private CustomTestListener theListener;

    public CustomTestRunner(Class<?> clazz) throws InitializationError 
    {
        super(clazz);
    }

    @Override
    protected Object createTest() throws Exception
    {
        Object test = super.createTest();

        // JUnit4 will call this multiple times for each test method.
        // Need to make sure beforeClassSetup() is only called once.
        if (test instanceof CustomTestListener && theListener == null) {
            theListener = (CustomTestListener) test;
            theListener.beforeClassSetup();
        }
        return test;
    }

}
