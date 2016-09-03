package com.waleyko.test;

/**
 * Listener that works around JUnit's @BeforeClass happening before Spring dependency injection.
 * See https://dzone.com/articles/enhancing-spring-test
 * @author stacie
 *
 */
public interface CustomTestListener {
    void beforeClassSetup() throws Exception;
}
