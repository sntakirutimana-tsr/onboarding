@regression
Feature: Adding Product to Cart

        
    @smoke
    Scenario Outline: Customer to add product to cart from the home page
        Given Customer is on home page
        When Customer clicks the "ADD TO CART" button for "<product_name>"
        Then a confirmation tick (✔) appears on "ADD TO CART" button with "view cart" link displayed
        And the cart counter increments by 1
        Then Customer clicks on "view cart" link
        And the cart page is open
        And product "<product_name>" is displayed in cart

        Examples:

            | product_name     |
            | Blue Shoes       |
            | Denim Blue Jeans |

    @smoke
    Scenario Outline: Customer can add product to cart from the product detail page
        Given Customer is on product detail page
        When clicks on "ADD TO CART" button
        Then '"<product_name" has been added to your cart.' message is displayed
        And cart counter increments by 1
        Then Customer clicks on "view cart" button
        And the cart page is open
        And products "<product_name>" is displayed in cart

        Examples:

            | product_name     |
            | Blue Shoes       |
            | Denim Blue Jeans |

