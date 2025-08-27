package com.epam.complex.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class EdgeDriverFactory implements WebDriverFactory{
    private static final Logger log = LogManager.getLogger(EdgeDriverFactory.class);

    @Override
    public WebDriver createDriver() {
        log.debug("Setting up EdgeDriver");
        // Construir la ruta al driver de manera relativa al proyecto
        String edgeDriverPath = System.getProperty("user.dir") + "/src/test/resources/drivers/msedgedriver.exe";

        // Configurar la propiedad del sistema para apuntar al driver
        System.setProperty("webdriver.edge.driver", edgeDriverPath);
        EdgeDriver driver = new EdgeDriver();
        log.info("EdgeDriver created");
        return driver;
    }
}
