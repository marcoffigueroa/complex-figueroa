package com.epam.complex.tests;

import com.epam.complex.drivers.DriverSingleton;
import com.epam.complex.models.LoginPage;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class LoginAutomation {
    static WebDriver driver;
    LoginPage loginPage;
    Logger log = LogManager.getLogger(LoginAutomation.class);

    @BeforeEach
    public void setTest() {
        log.debug("STARTING LOGIN TESTS");
        log.info("Getting the driver");
        driver = DriverSingleton.getDriver("chrome");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void happyPathLogin() {
        log.info("Login test");
        String username = "standard_user";
        String password = "secret_sauce";

        loginPage.navigateToLogin();
        loginPage.performLogin(username, password);
    }

    @AfterEach
    public void closeDriver() {
        if (loginPage != null) {
            log.info("Clearing login page reference");
            loginPage = null;
        }
        if (driver != null) {
            log.info("Closing WebDriver");
            driver.quit();
            driver = null;
        }
    }
}