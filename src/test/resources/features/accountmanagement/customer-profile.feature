@regression @accountmgt
Feature: Customer Profile Management

  As a logged-in customer,
  I want to create and manage my account details
  So that I can shop more easily and maintain my personal information.

  Scenario Outline: Registered customer can add their profile details
    Given Customer is registered with "<username>", "<email>" and "<password>"
    When Customer navigates to the Profile page
    Then Customer should fill their profile details:
      | Field          | Value            |
      | First name     | <first_name>     |
      | Last name      | <last_name>      |
    And clicks on "Save Changes"
    Then A confirmation message "Account details changed successfully." should be displayed
#    And Updated details should be immediately reflected in the profile

    Examples:
      | username | email                  | password | first_name | last_name | display_name | new_first_name | new_last_name | new_display_name | new_email          |
      | ting     | ting.nong@example.com  | Pass@123 | Ting       | Pradu     | tingpradu    | Jonathan       | Ting          | jonnyt           | jonathan@gmail.com |
      | pradu    | nong.pradu@example.com | Pass@456 | Nong       | Pradu     | nongpradu    | Janet          | Pradu         | janetp           | janet@gmail.com    |
