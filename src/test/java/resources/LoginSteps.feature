@LoginTests @BaseFunctionality @SmokeTesting
Feature: Login Functionality

  Scenario: Failed login with empty credentials


    Given I am on the login page
    When I enter the username "standard_user" and the password "secret_sauce"
    And I clear the password field
    And I clear the username field
    And I click the login button
    Then I should see an error message "Epic sadface: Username is required"


  Scenario: Failed login with empty password
    Given I am on the login page
    When I enter the username "standard_user"
    And I enter the password "secret_sauce"
    And I clear the password field
    And I click the login button
    Then I should see an error message "Epic sadface: Password is required"

  Scenario: Successful login with valid credentials and valid Title
    Given I am on the login page
    When I enter the username "standard_user" and the password "secret_sauce"
    And I click the login button
    Then I should see the title Swag Labs

