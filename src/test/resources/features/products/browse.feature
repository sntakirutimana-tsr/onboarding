Feature: Browse products by category and store view

  Background:
    Given a Customer is on the homepage

  Scenario Outline: Customer browses products by category or store page
    When a Customer navigates to "<page_name>" products page
    Then a message "<results_message>" should be displayed
    And a list of products should be displayed, each showing an image, name, category as "<category>", rating as stars, and price
    And the ❝Our Best Sellers❞ section should display three products, each showing an image, name, rating as stars, and price

    Examples:
      | page_name   | results_message           | category                |
      | men's       | Showing all 7 results     | Men                     |
      | women's     | Showing all 7 results     | Women                   |
      | accessories | Showing all 3 results     | Accessories             |
      | store       | Showing 1–8 of 13 results | Accessories, Men, Women |
