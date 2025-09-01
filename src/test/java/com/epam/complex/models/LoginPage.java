package com.epam.complex.models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    Logger log = LogManager.getLogger(LoginPage.class);

    private static final By USERNAME_FIELD = By.xpath("//*[@id=\"user-name\"]");
    private static final By PASSWORD_FIELD = By.xpath("//*[@id=\"password\"]");
    private static final By LOGIN_BUTTON = By.xpath("//*[@id=\"login-button\"]");
    private static final By ERROR_MESSAGE = By.cssSelector("h3[data-test='error']");
    private static final By TITLE_ELEMENT = By.cssSelector("div.app_logo");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToLogin() {
        driver.get("https://www.saucedemo.com/");
    }

    public void performLogin(String username, String password) {
        log.info("Performing login with user: {}", username);
        WebElement usernameElement = waitForUsername();
        usernameElement.sendKeys(username);
        log.info("Performing login with password: {}", password);
        WebElement passwordElement = waitForPassword();
        passwordElement.sendKeys(password);
    }

    public void sendUsername(String username) {
        log.info("Sending username: {}", username);
        WebElement usernameElement = waitForUsername();
        usernameElement.sendKeys(username);
    }

    public void sendPassword(String password) {
        log.info("Sending password: {}", password);
        WebElement passwordElement = waitForPassword();
        passwordElement.sendKeys(password);
    }

    public void clickLoginButton() {
        log.info("Clicking login button");
        WebElement loginButton = waitForLoginButton();
        loginButton.click();
    }

    public String getErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(ERROR_MESSAGE));
        return error.getText().trim();
    }

    public void clearUsername() {
        log.info("Clearing username field");
        WebElement usernameElement = waitForUsername();
        forceClear(usernameElement);
        log.info("Username field cleared");
    }

    public void clearPassword() {
        log.info("Clearing password field");
        WebElement passwordElement = waitForPassword();
        forceClear(passwordElement);
        log.info("Password field cleared");
    }

    private void forceClear(WebElement el) {
        el.click();
        el.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        el.sendKeys(Keys.DELETE);

        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(d -> "".equals(el.getAttribute("value")));
    }

    public String getTitle() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement elementTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE_ELEMENT));
        return elementTitle.getText().trim();
    }

    // Métodos privados para esperar cada elemento
    private WebElement waitForUsername() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        log.info("Waiting for username element to be visible");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(USERNAME_FIELD));
    }

    private WebElement waitForPassword() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        log.info("Waiting for password element to be visible");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_FIELD));
    }

    private WebElement waitForLoginButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        log.info("Waiting for login button to be clickable");
        return wait.until(ExpectedConditions.elementToBeClickable(LOGIN_BUTTON));
    }
}