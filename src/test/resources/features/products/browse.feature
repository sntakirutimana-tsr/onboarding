Feature: Browse products by category and store view

  Background:
    Given a Customer is on the homepage

  Scenario Outline: Customer browses products by category or store page
    When a Customer clicks the "<menu_option>" option in the navigation menu
    Then a Customer should be redirected to the "<page_name>" products page
    And a message "<results_message>" should be displayed
    And a list of products should be displayed, each showing an image, name, category as <category_label>, rating as stars, and price
    And the "Our Best Sellers" section should display three products, each showing an image, name, rating as stars, and price

    Examples:
      | menu_option | page_name   | results_message           | category_label                   |
      | Men         | men's       | Showing all 7 results     | "Men"                            |
      | Women       | women's     | Showing all 7 results     | "Women"                          |
      | Accessories | accessories | Showing all 3 results     | "Accessories"                    |
      | Store       | store       | Showing 1–8 of 13 results | "Accessories", "Men", or "Women" |
