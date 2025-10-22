Feature: Login Form Validation

  Scenario Outline: Customer submits form leaving email_or_username and/or password empty
    Given the customer is on the login page
    When the customer enters email or username as "<email_or_username>"
    And the customer enters password as "<password>"
    And the customer clicks on the "LOG IN" button
    Then the message "<error_message>" is displayed
    And the login form is not submitted
    And the customer remains on the login page

    Examples:
      | email_or_username | password | error_message                 |
      |                   |          | Email or username is required |
      | user@example.com  |          | Password is required          |
      |                   | pass123  | Email or username is required |
