Feature: Browse products via categories

  Background:
    Given Customer is on the homepage 

  Scenario: Customer to browse men products
    When a Customer clicks the "Men" option on navigation menu
    Then a Customer is redirected to the men products page
    And a message "Showing all 7 results" should be displayed
    And products should be displayed
    And each product should have an image, name, category as "Men", rating as stars, price
    And the "Our Best Sellers" section should be displayed
    And only three products should be in the "Our Best Sellers" section
    And each product in the section should have an image, name, rating as stars, price

  Scenario: Customer to browse women products
    When a Customer clicks the "Women" option on navigation menu
    Then a Customer is redirected to the women products page
    And a message "Showing all 7 results" should be displayed
    And products should be displayed
    And each product should have an image, name, category as "Women", rating as stars, price
    And the "Our Best Sellers" section should be displayed
    And only three products should be in the "Our Best Sellers" section
    And each product in the section should have an image, name, rating as stars, price

  Scenario: Customer to browse accessories products
    When a Customer clicks the "Accessories" option on navigation menu
    Then a Customer is redirected to the women products page
    And a message "Showing all 3 results" should be displayed
    And products should be displayed
    And each product should have an image, name, category as "Accessories", rating as stars, price
    And the "Our Best Sellers" section should be displayed
    And only three products should be in the "Our Best Sellers" section
    And each product in the section should have an image, name, rating as stars, price

  Scenario: Customer to browse products on store page
    When a Customer clicks the "Store" option on navigation menu
    Then a Customer is redirected to the store products page
    And a message "Showing 1–8 of 13 results" should be displayed
    And products should be displayed
    And each product should have an image, name, category as "Accessories", rating as stars, price
    And the "Our Best Sellers" section should be displayed
    And only three products should be in the "Our Best Sellers" section
    And each product in the section should have an image, name, rating as stars, price
