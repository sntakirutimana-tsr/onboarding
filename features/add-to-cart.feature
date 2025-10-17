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


    # To be refactored, as it has actions implied in the observation
    Scenario Outline: Customer can add product to cart from the product detail page
        When Customer clicks on "<product_name>"
        # Must add something specific to test. Eg: Check if the URL has changed, ... HINT: What is the indication?
        And "<product_name>" detail page is open  
        And clicks on "ADD TO CART" button
        And '"<product_name>" has been added to your cart.' message is displayed
        Then cart counter increments by 1
        And Customer clicks on "view cart" button
        Then the cart page is open
        And products "<product_name>" is displayed in cart

        Examples:

            | product_name     |
            | Blue Shoes       |
            | Denim Blue Jeans |


# MISSING TEST CASE: Adding an already existing product, Adding to cart from categories, ???