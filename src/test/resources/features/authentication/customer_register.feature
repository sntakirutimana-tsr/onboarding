@regression @authentication
Feature: Customer registration

  Background:
    Given Customer is on the registration form

  @smoke
  Scenario Outline: Customer to register with valid credentials
    When Customer provides valid credentials
      | email   | username   | password   |
      | <email> | <username> | <password> |
    And Customer clicks REGISTER button
    Then Customer with "<username>" should be registered with a welcome message displayed

    Examples:
      | email                 | username | password |
      | 123@gmail.com         | Eric     | password |
      | hello@outlook.com     | Sandra   | !Secret  |
      | hello.new@outlook.com | _4Sandra | !Secret  |

  Scenario Outline: System to reject customer registration with invalid credentials
    When Customer provides invalid credentials
      | email   | username   | password   |
      | <email> | <username> | <password> |
    And Customer clicks REGISTER button
    Then Registration fails with message "<error_message>" displayed
    And Customer remains on the registration form

    Examples:
      | email          | username | password | error_message                                 |
      | test@gmail.com |          | test123  | Error: Please enter a valid account username. |
      |                | eric     | test123  | Error: Please provide a valid email address.  |
      | test@gmail.com | eric     |          | Error: Please enter an account password.      |
      | user@com       | eric     | test123  | Error: Please provide a valid email address.  |
      | test@gmail.com | *        | test123  | Error: Please enter a valid account username. |


  # Browser Validations
  Scenario Outline: Browser prevents registration with invalid email format
    When Customer provides invalid credentials
      | email   | username   | password   |
      | <email> | <username> | <password> |
    And Customer clicks REGISTER button
    Then Browser should show email validation message "<validation_message>"
    And Customer remains on the registration form

    Examples:
      | email      | username | password | validation_message                                                    |
      | test@      | test     | test123  | Please enter a part following '@'. 'test@' is incomplete.             |
      | test       | test     | test123  | Please include an '@' in the email address. 'test' is missing an '@'. |
      | test@ffds. | test     | test123  | '.' is used at a wrong position in 'ffds.'.                           |
      | test@ffds$ | test     | test123  | A part following '@' should not contain the symbol '$'.               |