@regression
Feature: Lost Password

  Scenario: Customer navigates to the reset password page
    Given Customer is on the login page
    When Customer clicks on "Forgot Password?"
    Then Customer should be redirected to the reset password page
    And the password reset page should display a form containing:
      | Field Name        |
      | Email or Username |
      | Submit Button     |

  Scenario Outline: Customer submits valid email or username to reset password
    Given Customer is on the reset password page
    When Customer enters "<email_or_username>"
    And Customer submits the form
    Then message "Password reset email has been sent." should be displayed

    Examples:
      | email_or_username  |
      | email1@example.com |
      | username1          |
