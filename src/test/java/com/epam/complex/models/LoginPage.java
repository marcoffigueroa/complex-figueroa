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

// Page Object Model for the login page of SauceDemo
public class LoginPage {
    // WebDriver instance used by this page object
    WebDriver driver;
    Logger log = LogManager.getLogger(LoginPage.class);

    // Locators for login page elements
    private static final By USERNAME_FIELD = By.xpath("//*[@id=\"user-name\"]");
    private static final By PASSWORD_FIELD = By.xpath("//*[@id=\"password\"]");
    private static final By LOGIN_BUTTON = By.xpath("//*[@id=\"login-button\"]");
    private static final By ERROR_MESSAGE = By.cssSelector("h3[data-test='error']");
    private static final By TITLE_ELEMENT = By.cssSelector("div.app_logo");

    // Constructor: receives WebDriver instance
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Navigates to the login page URL
    public void navigateToLogin() {
        driver.get("https://www.saucedemo.com/");
    }

    // Fills in username and password fields (does not click login)
    public void performLogin(String username, String password) {
        log.info("Performing login with user: {}", username);
        WebElement usernameElement = waitForUsername();
        usernameElement.sendKeys(username);
        log.info("Performing login with password: {}", password);
        WebElement passwordElement = waitForPassword();
        passwordElement.sendKeys(password);
    }

    // Sends username to the username field
    public void sendUsername(String username) {
        log.info("Sending username: {}", username);
        WebElement usernameElement = waitForUsername();
        usernameElement.sendKeys(username);
    }

    // Sends password to the password field
    public void sendPassword(String password) {
        log.info("Sending password: {}", password);
        WebElement passwordElement = waitForPassword();
        passwordElement.sendKeys(password);
    }

    // Clicks the login button
    public void clickLoginButton() {
        log.info("Clicking login button");
        WebElement loginButton = waitForLoginButton();
        loginButton.click();
    }

    // Returns the error message text if present
    public String getErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(ERROR_MESSAGE));
        return error.getText().trim();
    }

    // Clears the username field using keyboard shortcuts
    public void clearUsername() {
        log.info("Clearing username field");
        WebElement usernameElement = waitForUsername();
        forceClear(usernameElement);
        log.info("Username field cleared");
    }

    // Clears the password field using keyboard shortcuts
    public void clearPassword() {
        log.info("Clearing password field");
        WebElement passwordElement = waitForPassword();
        forceClear(passwordElement);
        log.info("Password field cleared");
    }

    // Utility method to clear a field by selecting all and deleting
    private void forceClear(WebElement el) {
        el.click();
        el.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        el.sendKeys(Keys.DELETE);

        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(d -> "".equals(el.getAttribute("value")));
    }

    // Returns the title text after successful login
    public String getTitle() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement elementTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE_ELEMENT));
        return elementTitle.getText().trim();
    }

    // Waits for the username field to be visible
    private WebElement waitForUsername() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        log.info("Waiting for username element to be visible");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(USERNAME_FIELD));
    }

    // Waits for the password field to be visible
    private WebElement waitForPassword() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        log.info("Waiting for password element to be visible");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_FIELD));
    }

    // Waits for the login button to be clickable
    private WebElement waitForLoginButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        log.info("Waiting for login button to be clickable");
        return wait.until(ExpectedConditions.elementToBeClickable(LOGIN_BUTTON));
    }
}