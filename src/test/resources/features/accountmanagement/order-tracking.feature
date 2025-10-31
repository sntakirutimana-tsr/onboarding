@regression
Feature: Customer Order Tracking and History

  As a logged-in customer,
  I want to view and track my past and current orders
  So that I can stay informed about my purchases.

  # -----------------------
  # Navigation and Links
  # -----------------------

  Scenario: Order tracking links are displayed and clickable
    Given Customer is logged in with the following credentials:
      | email                 | password    |
      | john.doe@example.com  | Pass@123    |
    When Customer navigates to the Orders section
    Then The following tracking links should be displayed:
      | Link Name  |
      | Dashboard  |
      | Orders     |
      | Downloads  |
    And Each displayed link should be clickable

  # -----------------------
  # Orders List
  # -----------------------

  Scenario Outline: Orders are listed in a table format with details
    Given Customer is on the Orders page
    When Customer views the list of orders
    Then Orders should be displayed in a table with the following columns:
      | Column Name |
      | Order       |
      | Date        |
      | Status      |
      | Total       |
      | Actions     |
    And Each order should have a "VIEW" button in the Actions column
    And The "VIEW" button should be clickable

    Examples:
      | order_id | date       | status     | total  |
      | #1001    | 2025-10-20 | Completed  | $120.00 |
      | #1002    | 2025-10-25 | Pending    | $89.50  |
      | #1003    | 2025-10-28 | Cancelled  | $45.00  |

  # -----------------------
  # Order Details
  # -----------------------

  Scenario Outline: Clicking "VIEW" displays order details
    Given Customer is on the Orders page
    When Customer clicks the "VIEW" button for order "<order_id>"
    Then The system should display the order details page showing:
      | Section Name        |
      | Order Details       |
      | Shipping Address    |
      | Billing Address     |
    And Each section should contain the correct data for "<order_id>"

    Examples:
      | order_id |
      | #1001    |
      | #1002    |
      | #1003    |
