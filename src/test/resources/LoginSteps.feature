@LoginTests @BaseFunctionality @SmokeTesting
Feature: Login Functionality

  @TC01
  Scenario: Failed login with empty credentials


    Given I am on the login page
    When I enter the username "standard_user" and the password "secret_sauce"
    And I clear the password field
    And I clear the username field
    And I click the login button
    Then I should see an error message "Epic sadface: Username is required"

  @TC02
  Scenario: Failed login with empty password
    Given I am on the login page
    When I enter the username "standard_user"
    And I enter the password "secret_sauce"
    And I clear the password field
    And I click the login button
    Then I should see an error message "Epic sadface: Password is required"

  @TC03
  Scenario Outline: Successful login with valid credentials and valid Title
    Given I am on the login page
    When I enter the username "<user>" and the password "secret_sauce"
    And I click the login button
    Then I should see the title Swag Labs

    Examples:
      | user                    |
      | standard_user           |
      | problem_user            |
      | performance_glitch_user |
      | error_user              |
      | visual_user             |

