package com.epam.complex.drivers;

import org.openqa.selenium.WebDriver;

// Abstract Factory pattern: defines contract for browser-specific driver factories
public interface WebDriverFactory {
    // Creates and returns a configured WebDriver instance
    WebDriver createDriver();
}
