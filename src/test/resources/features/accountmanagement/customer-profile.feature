@regression
Feature: Customer Profile Management

  As a logged-in customer,
  I want to create and manage my account details
  So that I can shop more easily and maintain my personal information.

  Scenario Outline: Registered customer can view and update their profile details
    Given Customer is registered with "<username>", "<email>" and "<password>"
    When Customer navigates to the Profile page
    Then Customer should see their current profile details:
      | Field          | Value            |
      | First name     | <first_name>     |
      | Last name      | <last_name>      |
      | Display name   | <display_name>   |
      | Email address  | <email>          |

    When Customer updates profile details to:
      | Field          | Value                  |
      | First name     | <new_first_name>       |
      | Last name      | <new_last_name>        |
      | Display name   | <new_display_name>     |
      | Email address  | <new_email>            |
    And clicks on "Save Changes"
    Then A confirmation message "Account details changed successfully." should be displayed
    And Updated details should be immediately reflected in the profile

    Examples:
      | username | email                  | password | first_name | last_name | display_name | new_first_name | new_last_name | new_display_name | new_email          |
      | ting     | ting.nong@example.com  | Pass@123 | Ting       | Pradu     | tingpradu    | Jonathan       | Ting          | jonnyt           | jonathan@gmail.com |
      | pradu    | nong.pradu@example.com | Pass@456 | Nong       | Pradu     | nongpradu    | Janet          | Pradu         | janetp           | janet@gmail.com    |

  Scenario Outline: Customer profile is validated
    Given Customer is logged in and on the Profile page
    When Customer enters invalid profile details:
      | Field         | Value          |
      | Email address | <invalid_email> |
    And clicks on "Save Changes"
    Then A validation error message should be displayed for:
      | Field         | Message                  |
      | Email address | Please enter a valid email. |

    Examples:
      | invalid_email  |
      | john.doe@123   |
      | janedoe@       |
      | @gmail.com     |
