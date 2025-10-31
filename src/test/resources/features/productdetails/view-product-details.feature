@regression
Feature: View Product Details

  Scenario Outline: Customer views product's details
    Given the customer is on a  "<page>"
    When the customer clicks on a "<product_name>" with "<link>" from the product card
    Then the customer is redirected to the product details page with header as "<product_name>"
    And the "<breadcrumb>"that has the "<product_name>"
    And the product image should be displayed
    And the product description should be displayed
    And the product category should be displayed
    And the product quantity field should be displayed and defaulted to one
    And the customer can see the description tab with the description of the product
    And the customer can see the additional information tab
    And the customer can see the reviews tab with reviews count
    And an "ADD TO CART" button should be displayed

    Examples:
      | page        | product_name      | breadcrumb                           | link                                            |
      | Home        | Blue Shoes        | Home / Men / Blue Shoes              | https://askomdch.com/product/blue-shoes/        |
      | Store       | Blue denim shorts | Home / Women / Blue Denim Shorts     | https://askomdch.com/product/blue-denim-shorts/ |
      | Women       | Denim Blue Jeans  | Home / Women / Denim Blue Jeans      | https://askomdch.com/product/denim-blue-jeans/  |
      | Men         | Red Shoes         | Home / Men / Red Shoes               | https://askomdch.com/product/red-shoes/         |
      | Accessories | Anchor Bracelet   | Home / Accessories / Anchor Bracelet | https://askomdch.com/product/anchor-bracelet/   |





