Feature: Browse products via categories
  Scenario: Customer to browse men products
    Given Customer is on the homepage
    And the navigation menu is shown
    And the "Men option is available on navigation menu
    When Customer clicks the "Men" option on navigation menu
    Then Customer is redirected to the men products page
    And a message "Showing all 7 results" should be displayed
    And products should be displayed
    And each product should have an image, name, category as "Men", rating as stars, price
    And the "Our Best Sellers" section should be displayed
    And only three products should be in the "Our Best Sellers" section
    And each product in the section should have an image, name, rating as stars, price
