@regression
Feature: Customer registration

  Background:
    Given Customer is on the registration form

  @smoke
  Scenario Outline: Customer to register with valid credentials
    When Customer provides valid credentials
      | email   | username   | password   |
      | <email> | <username> | <password> |
    And Customer submit registration form
    Then Customer should be registered with "<username>" displayed

    Examples:
      | email                 | username | password |
      | 123@gmail.com         | Eric     | password |
      | hello@outlook.com     | Sandra   | !Secret  |
      | hello.new@outlook.com | _4Sandra | !Secret  |

  Scenario Outline: System to reject customer registration with invalid credentials
    When Customer provides invalid credentials
      | email   | username   | password   |
      | <email> | <username> | <password> |
    And Customer submit registration form
    Then Registration fails with message "<error_message>" displayed
    And Customer remains on the registration form

    Examples:
      | email          | username | password | error_message                          |
      | test@gmail.com |          | test123  | Please enter a valid account username. |
      |                | eric     | test123  | Please provide a valid email address.  |
      | test@gmail.com | eric     |          | Please enter an account password.      |
      | user@com       | eric     | test123  | Please provide a valid email address.  |
      | test@gmail.com | *        | test123  | Please enter a valid account username. |
      # Browser Validations
