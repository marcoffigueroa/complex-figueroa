package com.epam.complex.drivers;

import com.epam.complex.drivers.decorator.LoggingWebDriverDecorator;
import com.epam.complex.drivers.decorator.ScreenshotWebDriverDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

// Singleton pattern: ensures only one WebDriver instance per thread
public class DriverSingleton {
    // ThreadLocal to maintain a single WebDriver instance per thread
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
    private static final Logger log = LogManager.getLogger(DriverSingleton.class);

    // Private constructor to prevent instantiation
    private DriverSingleton() {}

    // Returns the singleton WebDriver instance for the current thread
    public static WebDriver getDriver() {
        if (DRIVER.get() == null) {
            // Get browser name from system property, default to Edge
            String browserName = System.getProperty("browser", "edge").toLowerCase();
            log.info("Browser selected: {}", browserName);

            WebDriverFactory factory;
            // Abstract Factory pattern: select factory based on browser
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

            // Create driver using factory
            WebDriver createdDriver = factory.createDriver();
            // Set default timeouts and maximize window
            createdDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            createdDriver.manage().window().maximize();
            // Decorator pattern: add logging and screenshot functionality
            createdDriver = new LoggingWebDriverDecorator(createdDriver);
            createdDriver = new ScreenshotWebDriverDecorator(createdDriver);
            DRIVER.set(createdDriver);
            log.info("WebDriver instance created for thread: {}", Thread.currentThread().getId());
        } else {
            log.info("WebDriver instance already exists for thread: {}, returning existing instance", Thread.currentThread().getId());
        }
        return DRIVER.get();
    }

    // Quits and removes the WebDriver instance for the current thread
    public static void closeDriver() {
        WebDriver wd = DRIVER.get();
        if (wd != null) {
            wd.quit();
            DRIVER.remove();
            log.info("WebDriver instance quit and removed for thread: {}", Thread.currentThread().getId());
        }
    }
}
