Feature:  Checkout and Payment Process

    Background:
    Given Customer is on the "checkout" page
    

    @smoke @regression 
    Scenario Outline: Customer place an order with valid billing details
        When Customer enters First Name as "<first_name>"
        And Customer enters Last Name as "<last_name>"
        And Customer enters Company Name as "<company_name>"
        And Customer selects Country / Region as "<country>"
        And Customer enters Street Address as "<street_address>"
        And Customer enters Apartment, Suite, Unit as "<apartment>"
        And Customer enters Town / City as "<city>"
        And Customer selects State as "<state>"
        And Customer enters ZIP Code as "<zip_code>"
        And Customer enters Phone as "<phone>"
        And Customer enters Email Address as "<email>"
        And Customer clicks on "Place Order" button
        Then Customer is redirected to the order confirmation page
        And the message "<confirmation_msg>" should be displayed
        And the confirmation page should display detailed order information
    

        Examples:
      | first_name  | last_name       | company_name | country            | street_address  | apartment | city        | state      | zip_code | phone       | email               | confirmation_msg                          |
      | Annie       | Uwamahoro       | Example Corp | United States (US) | 123 Main Street | Apt 2B    | Los Angeles | California | 90001    | +2507833657 | annie@example.com   | Thank you. Your order has been received.  |
    
    @smoke
    Scenario Outline: System rejects invalid billing details
        When Customer enters First Name as "<first_name>"
        And Customer enters Last Name as "<last_name>"
        And Customer enters Company Name as "<company_name>"
        And Customer selects Country / Region as "<country>"
        And Customer enters Street Address as "<street_address>"
        And Customer enters Apartment, Suite, Unit as "<apartment>"
        And Customer enters Town / City as "<city>"
        And Customer selects State as "<state>"
        And Customer enters ZIP Code as "<zip_code>"
        And Customer enters Phone as "<phone>"
        And Customer enters Email Address as "<email>"
        And Customer clicks on "Place Order" button
        Then the error message "<error_message>" should be displayed

        Examples:
        | first_name   | last_name       | company_name | country          | street_address | apartment | city         | state       | zip_code | phone        | email             | error_message                                  |
        |              | Uwamahoro       | ABC Corp     | United States () | 123 Main St    | Apt 4A    | Los Angeles  | California  | 90001    | +2507833657  | annie@gmail.com   | Billing First name is a required field.        |
        | Annie        |                 | ABC Corp     | United States () | 123 Main St    | Apt 4A    | Los Angeles  | California  | 90001    | +2507833657  | annie@gmail.com   | Billing Last name is a required field.         |
        | Annie        | Uwamahoro       | ABC Corp     |                  | 123 Main St    | Apt 4A    | Los Angeles  | California  | 90001    | +2507833657  | annie@gmail.com   | Billing Country / Region is a required field.  |
        | Annie        | Uwamahoro       | ABC Corp     | United States () |                | Apt 4A    | Los Angeles  | California  | 90001    | +2507833657  | annie@gmail.com   | Billing Street Address is a required field.    |
        | Annie        | Uwamahoro       | ABC Corp     | United States () | 123 Main St    | Apt 4A    |              | California  | 90001    | +2507833657  | annie@gmail.com   | Billing Town / City is a required field.       |
        | Annie        | Uwamahoro       | ABC Corp     | United States () | 123 Main St    | Apt 4A    | Los Angeles  |             | 90001    | +2507833657  | annie@gmail.com   | Billing State / County is a required field.    |
        | Annie        | Uwamahoro       | ABC Corp     | United States () | 123 Main St    | Apt 4A    | Los Angeles  | California  |          | +2507833657  | annie@gmail.com   | Billing Postcode / ZIP is a required field.    |
        | Annie        | Uwamahoro       | ABC Corp     | United States () | 123 Main St    | Apt 4A    | Los Angeles  | California  | ABC12    | +2507833657  | annie@gmail.com   | ZIP Code must contain only digits.             |
        | Annie        | Uwamahoro       | ABC Corp     | United States () | 123 Main St    | Apt 4A    | Los Angeles  | California  | 90001    | +2507833657  |                   | Billing Email address is a required field.     |
        | Annie        | Uwamahoro       | ABC Corp     | United States () | 123 Main St    | Apt 4A    | Los Angeles  | California  | 90001    | +2507833657  | annie             | Invalid billing email address.                 |




    @regression
    Scenario: Customer selects preferred payment method
        Given  System displays the available payment options section
        When Customer selects " <payment_method>"
        Then "<payment_method>" is selected
        And test mode warning message "<test_mode_warning>" is displayed

        Examples:
        | payment_method        | test_mode_warning                                                                |
        | Direct Bank Transfer  | TEST MODE ENABLED. DO NOT ENTER YOUR PERSONAL DATA. NO ORDER WILL BE FULLFILLED  |
        | Cash on Delivery      | TEST MODE ENABLED. DO NOT ENTER YOUR PERSONAL DATA. NO ORDER WILL BE FULLFILLED  |

