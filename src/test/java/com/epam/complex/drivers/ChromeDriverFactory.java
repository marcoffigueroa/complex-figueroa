package com.epam.complex.drivers;

import com.epam.complex.drivers.adapters.ChromeDriverAdapter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

// Factory for creating Chrome WebDriver instances using the Adapter pattern
public class ChromeDriverFactory implements WebDriverFactory {
    private static final Logger log = LogManager.getLogger(ChromeDriverFactory.class);

    @Override
    public WebDriver createDriver() {
        // Setup ChromeDriver using WebDriverManager for portability
        log.debug("Setting up ChromeDriver");
        WebDriverManager.chromedriver().setup();
        // Return ChromeDriver wrapped in an adapter
        return new ChromeDriverAdapter();
    }
}
