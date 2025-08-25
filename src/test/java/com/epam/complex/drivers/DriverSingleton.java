package com.epam.complex.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import java.time.Duration;

public class DriverSingleton {
    private static WebDriver driver;
    private static final Logger log = LogManager.getLogger(DriverSingleton.class);

    private DriverSingleton() {
    }

    public static WebDriver getDriver(String browser) {
        if (driver == null) {
            log.error("Driver is null");
            browser = browser.trim().toLowerCase();
            log.info("Selected browser: {}", browser);
            if (browser.equalsIgnoreCase("chrome")) {
                WebDriverFactory factory = new ChromeDriverFactory();
                log.debug("ChromeDriverFactory created");
                driver = factory.createDriver();
            } else if (browser.equalsIgnoreCase("edge")) {
                WebDriverFactory factory = new EdgeDriverFactory();
                log.debug("EdgeDriverFactory created");
                driver = factory.createDriver();
            } else {
                throw new IllegalArgumentException("Browser no valido || Invalid browser");
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        return driver;
    }
}
