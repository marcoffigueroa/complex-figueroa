package com.epam.complex.models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    WebElement usernameElement;
    WebElement passwordElement;
    WebElement loginButton;
    Logger log = LogManager.getLogger(LoginPage.class);

    private static final By USERNAME_FIELD = By.xpath("//*[@id=\"user-name\"]");
    private static final By PASSWORD_FIELD = By.xpath("//*[@id=\"password\"]");
    private static final By LOGIN_BUTTON = By.xpath("//*[@id=\"login-button\"]");
    private static final By ERROR_MESSAGE = By.cssSelector("h3[data-test='error']");
    private static final By TITLE_ELEMENT = By.cssSelector("div.app_logo");
    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public void navigateToLogin(){
        driver.get("https://www.saucedemo.com/");
        waitForElements();
    }

    private void waitForElements() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        log.info("Waiting for login elements to be visible");
        usernameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(USERNAME_FIELD));
        passwordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_FIELD));
        loginButton = wait.until(ExpectedConditions.elementToBeClickable(LOGIN_BUTTON));
    }

    public void performLogin(String username, String password){
        log.info("Performing login with user: {}", username);
        usernameElement.sendKeys(username);
        log.info("Performing login with password: {}", password);
        passwordElement.sendKeys(password);
    }

    public void sendUsername(String username) {
        log.info("Sending username: {}", username);
        usernameElement.sendKeys(username);
    }

    public void sendPassword(String password) {
        log.info("Sending password: {}", password);
        passwordElement.sendKeys(password);
    }

    public void clickLoginButton() {
        log.info("Clicking login button");
        loginButton.click();
    }

    public String getErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(ERROR_MESSAGE));
        return error.getText().trim();
    }

    public void clearUsername() {
        log.info("Clearing username field");
        usernameElement.clear();
    }

    public void clearPassword() {
        log.info("Clearing password field");
        passwordElement.clear();
    }

    public String getTitle() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement elementTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE_ELEMENT));
        return elementTitle.getText().trim();
    }
}