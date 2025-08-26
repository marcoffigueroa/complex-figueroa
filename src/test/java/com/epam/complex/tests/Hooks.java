package com.epam.complex.tests;

import com.epam.complex.drivers.DriverSingleton;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Hooks {
    private static WebDriver driver;
    private static final Logger log = LogManager.getLogger(Hooks.class);

    @Before
    public void setUp() {
        log.info("Initializing test setup");
        driver = DriverSingleton.getDriver();
        log.info("WebDriver initialized successfully");
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            log.warn("WebDriver is null, initializing new instance");
            driver = DriverSingleton.getDriver();
        }
        return driver;
    }

    @After
    public void tearDown() {
        log.info("Cleaning up test resources");
        if (driver != null) {
            driver.quit();
            driver = null;
            log.info("WebDriver closed successfully");
        }
        log.info("Test cleanup completed");
    }


}