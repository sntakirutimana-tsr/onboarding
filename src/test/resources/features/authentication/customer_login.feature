@authentication @regression
Feature: Customer Login

  Background:
    Given Customer is on the login page

  Scenario Outline: Customer to log in with the right credentials
    When customer enters valid credentials
      | username_or_email   | password   |
      | <username_or_email> | <password> |
    And Customer submit login form
    Then Customer, "<username>" is logged in

    Examples:
      | username_or_email          | password | username       |
      | _                          | Test@123 | _              |
      | eric.ndungutse@example.com | Test@123 | eric_ndungutse |

  Scenario Outline: System to reject customer login with invalid credentials
    When customer enters invalid credentials
      | username_or_email   | password   |
      | <username_or_email> | <password> |
    And Customer submit login form
    Then Login fails with message "<error_message>" displayed
    And Customer stays login form

    Examples:
      | username_or_email       | password       | error_message                                                                                                                   |
      | sdnfdnf                 | sdlkfnlkds     | Error: The username sdnfdnf is not registered on this site. If you are unsure of your username, try your email address instead. |
      | dav.ndungutse@gmail.com | Test@123skldfd | Error: The password you entered for the email address dav.ndungutse@gmail.com is incorrect. Lost your password?                 |
      | eric@example.com        |                | Error: The password field is empty.                                                                                             |
      |                         | TestPassword   | Error: Username is required.                                                                                                    |
