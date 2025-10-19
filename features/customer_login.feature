@regression @smoke
Feature: Customer Login

  Background:
    Given Customer is on the login page

  Scenario Outline: Customer to log in with the right credentials
    When customer enters email as "<email_or_username>"
    And Customer enters password as "<password>"
    And Customer clicks on "LOG IN" button
    Then Customer should be redirected to their dashboard
    And And a message "<welcome_msg>" should be displayed on dashboard

    Examples:
      | email_or_username | password | username       | welcome_msg                                       |
      | dav@gmail.com     | Test@12  | dav            | Hello dav (not dav? Log out                       |
      | user2@example.com | pass123  | user2          | Hello user2 (not user2? Log out                   |
      | inyemeramihigo    | pass123  | inyemeramihigo | Hello inyemeramihigo (not inyemeramihigo? Log out |

  Scenario Outline: System to reject customer login with invalid credentials
    When Customer enters email as "<username_or_email>"
    And Customer enters password as "<password>"
    And Customer submits the form
    Then message "<error_message>" is displayed
    And Customer stays on the login page

    Examples:
      | username_or_email | password  | error_message                           |
      |                   |           | username or email and password required |
      | wrong@example.com | Test@123  | Invalid email or password               |
      | wronguser         | wrongpass | Invalid email or password               |
      | user2@example.com |           | Password is required                    |
