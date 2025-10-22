@regression
Feature: Lost Password

  Scenario: Customer navigates to the reset password page
    Given Customer is on the login page
    When Customer clicks on "Forgot Password?"
    Then Customer should be redirected to the reset password page
    And the password reset page should display a form containing:
      | Field Name        |
      | Email or Username |
      | RESET PASSWORD    |

  Scenario Outline: Customer submits valid email or username to reset password
    Given Customer is on the reset password page
    When Customer enters "<email_or_username>"
    And Customer clicks on "RESET PASSWORD"
    Then message "Password reset email has been sent." should be displayed
    And reset password input fields are hidden

    Examples:
      | email_or_username  |
      | email1@example.com |
      | username1          |

  Scenario Outline: System reject submission of reset password
    Given Customer is on the reset password page
    When Customer enters "<email_or_username>" as email or username
    And And Customer clicks on "RESET PASSWORD"
    Then a message "<orror_message>" should be displayed
    And Customer stays on reset passoword page

    Examples:
      | email_or_username    | orror_message                      |
      |                      | Enter a username or email address. |
      | nonexisting_email    | Enter a username or email address. |
      | nonexisting_username | Enter a username or email address. |
