package com.epam.complex.tests;

import com.epam.complex.drivers.DriverSingleton;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Cucumber hooks for test setup and teardown
public class Hooks {
    Logger log = LogManager.getLogger(Hooks.class);

    // Runs before each scenario: sets up the browser
    @Before
    public void setUp()
    {
        log.info("Setting up the browser for the test");
        WebDriver driver = DriverSingleton.getDriver();
        log.info("Browser setup completed");
    }

    // Runs after each scenario: closes the browser
    @After
    public void stopBrowser()
    {
        log.info("Tearing down the browser after the test");
        DriverSingleton.closeDriver();
        log.info("Browser teardown completed");
    }

}