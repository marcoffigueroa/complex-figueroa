package com.epam.complex.drivers;

import com.epam.complex.drivers.decorator.LoggingWebDriverDecorator;
import com.epam.complex.drivers.decorator.ScreenshotWebDriverDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class DriverSingleton {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
    private static final Logger log = LogManager.getLogger(DriverSingleton.class);

    private DriverSingleton() {}

    public static WebDriver getDriver() {
        if (DRIVER.get() == null) {
            String browserName = System.getProperty("browser", "edge").toLowerCase();
            log.info("Browser selected: {}", browserName);

            WebDriverFactory factory;
            if ("edge".equals(browserName)) {
                log.info("Initializing Edge browser");
                factory = new EdgeDriverFactory();
            } else if ("chrome".equals(browserName)) {
                log.info("Initializing Chrome browser");
                factory = new ChromeDriverFactory();
            } else {
                log.info("Browser not recognized");
                throw new IllegalArgumentException("Browser not supported: " + browserName);
            }

            WebDriver createdDriver = factory.createDriver();
            createdDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            createdDriver.manage().window().maximize();
            // Decorators
            createdDriver = new LoggingWebDriverDecorator(createdDriver);
            createdDriver = new ScreenshotWebDriverDecorator(createdDriver);
            DRIVER.set(createdDriver);
            log.info("WebDriver instance created for thread: {}", Thread.currentThread().getId());
        } else {
            log.info("WebDriver instance already exists for thread: {}, returning existing instance", Thread.currentThread().getId());
        }
        return DRIVER.get();
    }

    public static void closeDriver() {
        WebDriver wd = DRIVER.get();
        if (wd != null) {
            wd.quit();
            DRIVER.remove();
            log.info("WebDriver instance quit and removed for thread: {}", Thread.currentThread().getId());
        }
    }
}
