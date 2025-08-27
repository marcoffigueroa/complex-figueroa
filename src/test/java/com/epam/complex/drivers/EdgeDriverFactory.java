package com.epam.complex.drivers;

import com.epam.complex.drivers.adapters.EdgeDriverAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class EdgeDriverFactory implements WebDriverFactory{
    private static final Logger log = LogManager.getLogger(EdgeDriverFactory.class);

    @Override
    public WebDriver createDriver() {
        log.debug("Setting up EdgeDriver");
        String edgeDriverPath = System.getProperty("user.dir") + "/src/test/resources/drivers/msedgedriver.exe";
        System.setProperty("webdriver.edge.driver", edgeDriverPath);
        return new EdgeDriverAdapter();
    }
}
