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
        usernameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        passwordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("login-button")));
    }

    public void performLogin(String username, String password){
        log.info("Performing login with user: {}", username);
        usernameElement.sendKeys(username);
        log.info("Performing login with password: {}", password);
        passwordElement.sendKeys(password);
        log.info("Performing login button");
        loginButton.click();
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
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(5));
        WebElement error = wait.until(
                org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(
                        org.openqa.selenium.By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3"))
        );
        return error.getText().trim();
    }

    public void clearusername() {
        log.info("Clearing username field");
        usernameElement.clear();
    }

    public void clearPassword() {
        log.info("Clearing password field");
        passwordElement.clear();
    }

    public String getTitle() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement elementTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"header_container\"]/div[1]/div[2]/div")));
        return  elementTitle.getText().trim();
    }
}