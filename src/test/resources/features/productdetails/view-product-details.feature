Feature: View Product Details

  Scenario Outline: Customer views product's details
    Given the customer is on a  "<page>"
    When the customer clicks on a "<product_name>" from the product card
    Then the customer is redirected to the product details page with header as "<product_name>"
    And the "<breadcrumb>"that has the "<product name>"
    And the product image should be displayed
    And the product description should be displayed
    And the product category should be displayed
    And the product quantity field should be displayed and defaulted to 1
    And the customer can see the description tab with the description of the product
    And the customer can see the additional information tab
    And the customer can see the reviews tab with reviews count
    And an "ADD TO CART" button should be displayed

    Examples:
      | page     | product_name |
      | homepage | Blue Shoes   |
      | store    | Blue Shoes   |





