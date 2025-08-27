package com.epam.complex.drivers.decorator;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class LoggingWebDriverDecorator implements WebDriver {
    private final WebDriver driver;
    private Logger log = org.apache.logging.log4j.LogManager.getLogger(LoggingWebDriverDecorator.class);

    public LoggingWebDriverDecorator(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void get(String url) {
        log.info("Navigating to URL: {}", url);
        driver.get(url);
    }

    @Override
    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        log.info("Current URL: {}", url);
        return url;
    }

    @Override
    public String getTitle() {
        String title = driver.getTitle();
        log.info("Page title: {}", title);
        return title;
    }

    @Override
    public java.util.List<org.openqa.selenium.WebElement> findElements(org.openqa.selenium.By by) {
        log.info("Finding elements by: {}", by);
        return driver.findElements(by);
    }

    @Override
    public org.openqa.selenium.WebElement findElement(org.openqa.selenium.By by) {
        log.info("Finding element by: {}", by);
        return driver.findElement(by);
    }

    @Override
    public String getPageSource() {
        log.info("Getting page source");
        return driver.getPageSource();
    }

    @Override
    public void close() {
        log.info("Closing browser window");
        driver.close();
    }

    @Override
    public void quit() {
        log.info("Quitting browser");
        driver.quit();
    }

    @Override
    public java.util.Set<String> getWindowHandles() {
        log.info("Getting window handles");
        return driver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        log.info("Getting window handle");
        return driver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        log.info("Switching context");
        return driver.switchTo();
    }

    @Override
    public Navigation navigate() {
        log.info("Navigating");
        return driver.navigate();
    }

    @Override
    public Options manage() {
        log.info("Managing driver options");
        return driver.manage();
    }
}
