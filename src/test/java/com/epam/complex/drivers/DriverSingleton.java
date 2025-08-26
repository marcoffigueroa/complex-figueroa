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

    public static WebDriver getDriver() {
        if (driver == null) {
            log.info("Driver is null");
            String browser = System.getProperty("browser", "edge").trim().toLowerCase();
            log.info("Selected browser: {}", browser);
            switch (browser) {
                case "chrome" -> {
                    WebDriverFactory factory = new ChromeDriverFactory();
                    log.debug("ChromeDriverFactory creado");
                    driver = factory.createDriver();
                }
                case "edge" -> {
                    WebDriverFactory factory = new EdgeDriverFactory();
                    log.debug("EdgeDriverFactory creado");
                    driver = factory.createDriver();
                }
                default -> throw new IllegalArgumentException("Navegador no válido: " + browser);
            }

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            log.info("Driver inicializado correctamente");
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
