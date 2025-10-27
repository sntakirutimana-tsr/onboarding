@regression
Feature: Adding Product to Cart

  @smoke
  Scenario Outline: Customer to add product to cart from the homepage
    Given Customer is on "<page_name>"
    When clicks on ADD TO CART button of "<product_name>"
    Then a "View cart" link appears for "<product_name>"
    And cart counter increments by 1
    And the "<product_name>" should be in the cart with "<product_price>", "<product_quantity>", and "<subtotal>"

    Examples:
      | product_name     | product_price | product_quantity | subtotal |
      | Blue Shoes       | $45.00        |                1 | $45.00   |
      | Anchor Bracelet  |   $10.00      |       1          | $10.00   |

