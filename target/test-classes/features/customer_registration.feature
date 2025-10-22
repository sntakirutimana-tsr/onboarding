@regression
Feature: Customer registration

  Background:
    Given Customer is on the registration form

  @smoke
  Scenario Outline: Customer to register with valid credentials
    When Customer enters email as "<email>"
    And Customer enters username as "<username>"
    And Customer enters passoword as "<password>"
    And Customer clisks on "REGISTER BUTTON"
    Then Customer is redirected to their dashboard
    And a message "<welcome_msg>" should be displayed on dashboard

    Examples:
      | email             | username | password | welcome_msg                       |
      |     123@gmail.com | Eric     | password | Hello Eric (not Eric? Log out     |
      | hello@outlook.com | Sandra   | !Secret  | Hello Sandra (not Sandra? Log out |

  Scenario Outline: System to reject customer registration with invalid credentials
    When Customer enters email as "<email>"
    And Customer enters username as "<username>"
    And Customer enters password as "<password>"
    And Customer clisks on "REGISTER BUTTON"
    Then message 'Error: "<error_message>"' should be displayed
    And Customer remains on the registration form

    Examples:
      | email            | username | password   | error_message                                |
      |                  | eric     | test123    | Email is required                            |
      | nosymbol         | eric     | test123    | "@" symbol missing in the email address.     |
      | user@com         | eric     | test123    | Missing top-level domain (e.g., .com, .org). |
      | @gmail.de        | eric     | test123    | Email missing username part                  |
      | test@gmail.com   |          | test123    | Username is required                         |
      | test@gmail.com   | *        | test123    | Username cannot contain special characters   |
      | test@gmail.com   | eric     |            | Password is required                         |
      | test@gmail.com   | eric     | pass       | Password must be at least 8 chars            |
      | test@gmail.com   | eric     | password   | Password must contain a number               |
      | test@gmail.com   | eric     | password1  | Password must contain a special char         |
      | test@gmail.com   | eric     | PASSWORD1! | Password must contain a lowercase char       |
      | test@gmail.com   | eric     | testtest!  | Password must contain a number               |
      | test@gmail.com   | eric     | test123    | Password must contain a special char         |
      | test@gmail.com   | eric     |   12345678 | Password must contain a letter               |
      | existing@ds.com  | eric     | test123!   | Email already exists                         |
      | email2@gmail.com | exist    | test123!   | Email already exists                         |
