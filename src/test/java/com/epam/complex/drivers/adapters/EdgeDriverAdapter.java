package com.epam.complex.drivers.adapters;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.*;
import java.util.Set;
import java.util.List;
import java.time.Duration;

// Adapter pattern: adapts EdgeDriver to the WebDriver interface and centralizes configuration
public class EdgeDriverAdapter implements WebDriver {
    // Wrapped EdgeDriver instance
    private final EdgeDriver edgeDriver;

    // Constructor: initializes and configures the EdgeDriver
    public EdgeDriverAdapter() {
        this.edgeDriver = new EdgeDriver();
        this.edgeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        this.edgeDriver.manage().window().maximize();
    }

    // Delegates all WebDriver methods to the wrapped EdgeDriver
    @Override
    public void get(String url) {
        edgeDriver.get(url);
    }

    @Override
    public String getCurrentUrl() {
        return edgeDriver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return edgeDriver.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return edgeDriver.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return edgeDriver.findElement(by);
    }

    @Override
    public String getPageSource() {
        return edgeDriver.getPageSource();
    }

    @Override
    public void close() {
        edgeDriver.close();
    }

    @Override
    public void quit() {
        edgeDriver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return edgeDriver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return edgeDriver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return edgeDriver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return edgeDriver.navigate();
    }

    @Override
    public Options manage() {
        return edgeDriver.manage();
    }
}
