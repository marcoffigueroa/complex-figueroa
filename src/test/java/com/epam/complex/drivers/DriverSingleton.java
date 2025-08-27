package com.epam.complex.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class DriverSingleton {
    private static final Logger log = LogManager.getLogger(DriverSingleton.class);
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();


    private DriverSingleton() {}

    public static WebDriver getDriver() {
        WebDriver existing = DRIVER.get();
        if (existing == null) {
            String browser = System.getProperty("browser", "chrome").toLowerCase();
            WebDriver wd;
            switch (browser) {
                case "edge":
                    wd = new EdgeDriverAdapter();
                    break;
                default:
                    wd = new ChromeDriver();
            }
            wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            wd.manage().window().maximize();
            DRIVER.set(wd);
        }
        return DRIVER.get();
    }

    public static void closeDriver() {
        WebDriver wd = DRIVER.get();
        if (wd != null) {
            wd.quit();
            DRIVER.remove();
        }
    }
}
