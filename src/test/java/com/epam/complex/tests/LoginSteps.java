package com.epam.complex.tests;

import com.epam.complex.models.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.Logger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LoginSteps {
    private LoginPage loginPage;
    Logger log = org.apache.logging.log4j.LogManager.getLogger(LoginSteps.class);


    @Given("I am on the login page")
    public void navigateToLoginPage() {
        log.info("Navigating to login page");
        loginPage.navigateToLogin();
    }

    @When("I enter the username {string} and the password {string}")
    public void performFullLogin(String username, String password) {
        log.info("Performing full login with username: {} and password: {}", username, password);
        loginPage.performLogin(username, password);
    }

    @When("I enter the username {string}")
    public void sendUsername(String username) {
        log.info("Sending username: {}", username);
        loginPage.sendUsername(username);
    }

    @When("I enter the password {string}")
    public void sendPassword(String password) {
        log.info("Sending password: {}", password);
        loginPage.sendPassword(password);
    }

    @When("I clear the username field")
    public void clearUsername() {
        log.info("Leaving username field empty");
        loginPage.clearusername();
    }

    @When("I clear the password field")
    public void leavePasswordEmpty() {
        log.info("Leaving password field empty");
        loginPage.clearPassword();
    }

    @When("I click the login button")
    public void clickLoginButton() {
        log.info("Clicking login button");
        loginPage.clickLoginButton();
    }

    @Then("I should see the title Swag Labs")
    public void validateTitle() {
        log.info("I should see the title Swag Labs");
        String actualTitle = loginPage.getTitle();
        assertThat("Title does not match", actualTitle, equalTo("Swag Labs".trim()));    }

    @Then("I should see an error message {string}")
    public void validateError(String errorMessage) {
        log.info("Validating login error message: {}", errorMessage);
        String actualMessage = loginPage.getErrorMessage();
        assertThat("Unexpected error message", actualMessage, equalTo(errorMessage));
    }
}