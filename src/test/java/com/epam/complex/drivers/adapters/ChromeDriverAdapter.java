package com.epam.complex.drivers.adapters;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.*;
import java.util.Set;
import java.util.List;
import java.time.Duration;

public class ChromeDriverAdapter implements WebDriver {
    private final ChromeDriver chromeDriver;

    public ChromeDriverAdapter() {
        this.chromeDriver = new ChromeDriver();
        this.chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        this.chromeDriver.manage().window().maximize();
    }

    @Override
    public void get(String url) {
        chromeDriver.get(url);
    }

    @Override
    public String getCurrentUrl() {
        return chromeDriver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return chromeDriver.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return chromeDriver.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return chromeDriver.findElement(by);
    }

    @Override
    public String getPageSource() {
        return chromeDriver.getPageSource();
    }

    @Override
    public void close() {
        chromeDriver.close();
    }

    @Override
    public void quit() {
        chromeDriver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return chromeDriver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return chromeDriver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return chromeDriver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return chromeDriver.navigate();
    }

    @Override
    public Options manage() {
        return chromeDriver.manage();
    }
}

