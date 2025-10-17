@regression @smoke
Feature: Customer Login

  Background:
    Given Customer is on the login page
    
  Scenario Outline: Customer to log in with the right credentials
    When customer enters email as "<email_or_username>"
    And Customer enters password as "<password>"
    And Customer submits the form
    Then Customer should be redirected to their dashboard
    
    Examples:
      | email_or_username       | password  |
      | dav.ndungutse@gmail.com | Test@123  |
      | user2@example.com       | pass12345 |
      | inyemeramihigo          | pass12345 |

  
  Scenario Outline: System to reject customer login with invalid credentials
    When Customer enters email as "<username_or_email>"
    And Customer enters password as "<password>"
    And Customer submits the form
    Then message "<error_message>" is displayed
    And Customer stays on the login page

    Examples:
      | username_or_email              | password | error_message                            |
      |                                |          | username or email and password required  |
      | wrong@example.com              | Test@123 | Invalid email or password                |
      | wronguser                      | wrongpass| Invalid email or password                |
      | user2@example.com              |          | Password is required                     |