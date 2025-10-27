@regression
Feature: Browse product results with pagination

  Scenario Outline: Customer navigates forward through paginated results
    Given a Customer is on store products page
    When a Customer navigates "<direction>" through page results using "<control>"
    Then a message "<results_message>" should be displayed
    And the corresponding set of products should be displayed
    And the pagination controls should reflect the current page "<current_page>"

    Examples:
      | direction | control        | current_page | results_message            |
      | forward   | forward button | 2            | Showing 9–13 of 13 results |
      | forward   | page number 2  | 2            | Showing 9–13 of 13 results |

  Scenario Outline: Customer navigates backward through paginated results
    Given a Customer is on the second results page of the store page
    When a Customer navigates "<direction>" through page results using "<control>"
    Then a message "<results_message>" should be displayed
    And the corresponding set of products should be displayed
    And the pagination controls should reflect the current page "<current_page>"

    Examples:
      | direction | control         | current_page | results_message           |
      | backward  | backward button | 1            | Showing 1–8 of 13 results |
      | backward  | page number 1   | 1            | Showing 1–8 of 13 results |