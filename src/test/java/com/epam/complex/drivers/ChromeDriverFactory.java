package com.epam.complex.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverFactory implements WebDriverFactory {
    private static final Logger log = LogManager.getLogger(ChromeDriverFactory.class);

    @Override
    public WebDriver createDriver() {
        log.debug("Setting up ChromeDriver");
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        log.info("ChromeDriver created");
        return driver;
    }
}
