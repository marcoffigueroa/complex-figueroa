package com.epam.complex.tests;

import com.epam.complex.drivers.DriverSingleton;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Hooks {
    protected static WebDriver driver;
    Logger log = LogManager.getLogger(Hooks.class);

    @Before
    public void setUp()
    {
        log.info("Setting up the browser for the test");
        driver = DriverSingleton.getDriver();
        log.info("Browser setup completed");
    }

    @After
    public void stopBrowser()
    {
        log.info("Tearing down the browser after the test");
        DriverSingleton.closeDriver();
        log.info("Browser teardown completed");
    }


}