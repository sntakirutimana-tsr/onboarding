Feature: View Product Details


  Scenario: System Redirects to product details page and displays product information correctly
    Given the customer is on the homepage
    When the customer clicks on a product's name from the featured products section
    Then the system should redirect to the product details page
    And the breadcrumb navigation should display the path from the Home page to the product’s category and product name
    And the product name should be visible
    And the product image should be displayed with a magnification option
    And the product description should be visible
    And the product category should be displayed with clickable options leading to the respective category or subcategory pages
    And the product quantity field should be visible and defaulted to 1
    And the customer can see the description tab with the description of the product
    And the customer can see the additional information tab
    And the customer can see the reviews tab
    And an "ADD TO CART" button should be displayed


