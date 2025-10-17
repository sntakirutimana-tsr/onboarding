Feature: Browse products via categories

  Scenario: System to hide pagination controls
    Given the Customer is on the men products page
    When the Customer scrolls down to the bottom of the page
    Then pagination controls shoud not be displayed

  Scenario: Customer to browse results with pagination controls
    Given the Customer is on the store page
    And pagination controls are displayed
    When the Customer scrolls down to the bottom of the page
    And the Customer clicks the forward button on pagination controls
    Then a new set of products should be displayed
    And a message "Showing 9–13 of 13 results" should be displayed
    And pagination controls should have a backward button labelled as "<-"
    And a forward button labelled as "->" should no longer be shown
    And the current page results button labelled as "2" should be in black blackground
