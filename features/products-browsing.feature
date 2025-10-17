Feature: Browse products via categories

  Scenario: Customer to browse men products
    Given Customer is on the homepage
    And the navigation menu is shown
    And the "Men option is available on navigation menu
    When the Customer clicks the "Men" option on navigation menu
    Then the Customer is redirected to the men products page
    And a message "Showing all 7 results" should be displayed
    And products should be displayed
    And each product should have an image, name, category as "Men", rating as stars, price
    And the "Our Best Sellers" section should be displayed
    And only three products should be in the "Our Best Sellers" section
    And each product in the section should have an image, name, rating as stars, price

  Scenario: Customer to browse women products
    Given the Customer is on the homepage
    And the navigation menu is shown
    And the "Women" option is available on navigation menu
    When the Customer clicks the "Women" option on navigation menu
    Then the Customer is redirected to the women products page
    And a message "Showing all 7 results" should be displayed
    And products should be displayed
    And each product should have an image, name, category as "Women", rating as stars, price
    And the "Our Best Sellers" section should be displayed
    And only three products should be in the "Our Best Sellers" section
    And each product in the section should have an image, name, rating as stars, price

  Scenario: Customer to browse accessories products
    Given the Customer is on the homepage
    And the navigation menu is shown
    And the "Accessories" option is available on navigation menu
    When the Customer clicks the "Accessories" option on navigation menu
    Then the Customer is redirected to the women products page
    And a message "Showing all 3 results" should be displayed
    And products should be displayed
    And each product should have an image, name, category as "Accessories", rating as stars, price
    And the "Our Best Sellers" section should be displayed
    And only three products should be in the "Our Best Sellers" section
    And each product in the section should have an image, name, rating as stars, price

  Scenario: Customer to browse products on store page
    Given the Customer is on the homepage
    And the navigation menu is shown
    And the "Store" option is available on navigation menu
    When the Customer clicks the "Store" option on navigation menu
    Then the Customer is redirected to the store products page
    And a message "Showing 1–8 of 13 results" should be displayed
    And products should be displayed
    And each product should have an image, name, category as "Accessories", rating as stars, price
    And the "Our Best Sellers" section should be displayed
    And only three products should be in the "Our Best Sellers" section
    And each product in the section should have an image, name, rating as stars, price

  Scenario: System to hide pagination controls
    Given the Customer is on the men products page
    When the Customer scrolls down to the bottom of the page
    Then pagination controls shoud not be displayed

  Scenario: Customer to browse results with pagination controls
    Given the Customer is on store page
    When the Customer scrolls down to the bottom of the page
    And the Customer clicks the forward button on pagination controls
    Then a new set of products should be displayed
    And a message "Showing 9–13 of 13 results" should be displayed
    And pagination controls should have a backward button
    And a forward button should no longer be shown
    And page 2 button should be in black blackground
