Feature: Adding Product to Cart

    Background: Customer navigate to the store homepage

        Given a Customer is on home store page


    Scenario Outline: Customer to add product to cart from the home page

        When Customer clicks the "ADD TO CART" button for "<product_name>"
        And a confirmation tick (✔) appears on "ADD TO CART" button with "view cart" link displayed
        And the cart counter increments by 1
        And Customer clicks on "view cart" link
        Then the cart page is open
        And product "<product_name>" is displayed in cart


        Examples:

            | product_name |
            | Blue Shoes |
            | Denim Blue Jeans |


    Scenario Outline: Customer can add product to cart from the product detail page
        When Customer clicks on "<product_name"
        And "<product_name" detail page is open
        And clicks on "ADD TO CART" button
        And '"<product_name" has been added to your cart.' message is displayed
        Then cart counter increments by 1
        And Customer clicks on "view cart" button
        Then the cart page is open
        And products "<product_name>" is displayed in cart

        Examples:

            | product_name |
            | Blue Shoes |
            | Denim Blue Jeans |

