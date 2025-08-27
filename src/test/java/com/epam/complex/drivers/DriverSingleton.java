package com.epam.complex.drivers;

import com.epam.complex.drivers.decorator.LoggingWebDriverDecorator;
import com.epam.complex.drivers.decorator.ScreenshotWebDriverDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class DriverSingleton {
    private static final Logger log = LogManager.getLogger(DriverSingleton.class);
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();


    private DriverSingleton() {}

    public static WebDriver getDriver() {
        WebDriver driver = null;
        String browserName = System.getProperty("browser", "edge").toLowerCase();
        log.info("Browser selected: {}", browserName);

        WebDriverFactory factory;

        if ("edge".equals(browserName)) {
            log.info("Initializing Edge browser");
            factory = new EdgeDriverFactory();
        } else if ("chrome".equals(browserName)) {
            log.info("Initializing Firefox browser");
            factory = new ChromeDriverFactory();
        } else {
            log.info("Browser not recognized");
            throw new IllegalArgumentException("Browser not supported: " + browserName);
        }

        driver = factory.createDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        // Decorate the driver with logging and screenshot
        driver = new LoggingWebDriverDecorator(driver);
        driver = new ScreenshotWebDriverDecorator(driver);
        return driver;
    }

    public static void closeDriver() {
        WebDriver wd = DRIVER.get();
        if (wd != null) {
            wd.quit();
            DRIVER.remove();
        }
    }
}
