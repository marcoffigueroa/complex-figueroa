package com.epam.complex.tests;

import com.epam.complex.models.LoginPage;
import com.epam.complex.tests.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LoginSteps {
    private LoginPage loginPage;
    private static final Logger log = LogManager.getLogger(LoginSteps.class);

    @Given("I am on the login page")
    public void navigateToLoginPage() {
        log.info("Navigating to login page");
        loginPage = new LoginPage(Hooks.getDriver());
        loginPage.navigateToLogin();
        log.info("Successfully navigated to login page");
    }

    @When("I enter the username {string} and the password {string}")
    public void performFullLogin(String username, String password) {
        log.info("Attempting full login with username: {}", username);
        loginPage.performLogin(username, password);
        log.info("Login attempt completed");
    }

    @When("I enter the username {string}")
    public void sendUsername(String username) {
        log.info("Entering username: {}", username);
        loginPage.sendUsername(username);
        log.info("Username entered successfully");
    }

    @When("I enter the password {string}")
    public void sendPassword(String password) {
        log.info("Entering password");
        loginPage.sendPassword(password);
        log.info("Password entered successfully");
    }

    @When("I clear the username field")
    public void clearUsername() {
        log.info("Clearing username field");
        loginPage.clearUsername();
        log.info("Username field cleared");
    }

    @When("I clear the password field")
    public void clearPassword() {
        log.info("Clearing password field");
        loginPage.clearPassword();
        log.info("Password field cleared");
    }

    @When("I click the login button")
    public void clickLoginButton() {
        log.info("Clicking login button");
        loginPage.clickLoginButton();
        log.info("Login button clicked");
    }

    @Then("I should see the title Swag Labs")
    public void validateTitle() {
        log.info("Validating page title - Swag Labs");
        String actualTitle = loginPage.getTitle();
        log.debug("Actual title: {}", actualTitle);
        assertThat("Title does not match", actualTitle, equalTo("Swag Labs".trim()));
        log.info("Title validation passed");
    }

    @Then("I should see an error message {string}")
    public void validateError(String errorMessage) {
        log.info("Validating error message: {}", errorMessage);
        String actualMessage = loginPage.getErrorMessage();
        log.debug("Actual error message: {}", actualMessage);
        assertThat("Unexpected error message", actualMessage, equalTo(errorMessage));
        log.info("Error message validation passed");
    }
}