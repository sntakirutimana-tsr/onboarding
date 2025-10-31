@regression
Feature: Customer Address Management

  As a logged-in customer,
  I want to create and manage my account details
  So that I can shop more easily and maintain my personal information.

  Scenario Outline: Verify adding a new shipping or billing address when none exists
    Given Customer is logged in with "<email>" and "<password>"
    And Customer is on the Address Book page with no <address_type> address
    When Customer adds a new <address_type> address with the following details:
      | Full Name    | Street Address      | City         | State | Zip Code | Country | Phone Number  |
      | <full_name>  | <street>            | <city>       | <state> | <zip>    | <country> | <phone>     |
    And clicks "Save Address"
    Then A confirmation message "Address changed successfully." should be displayed
    And The following <address_type> address should appear in the Address Book:
      | Full Name    | Street Address      | City         | State | Zip Code | Country |
      | <full_name>  | <street>            | <city>       | <state> | <zip>    | <country> |

    Examples:
      | email                | password | address_type | full_name | street              | city        | state | zip   | country | phone        |
      | john.doe@example.com | Pass@123 | shipping     | John Doe  | 12 Palm Avenue      | Miami       | FL    | 33101 | USA     | +13051234567 |
      | jane.doe@example.com | Pass@456 | billing      | Jane Doe  | 5 Maple Street      | New York    | NY    | 10001 | USA     | +12125550123 |


  Scenario Outline: Verify editing an existing shipping or billing address
    Given Customer has an existing <address_type> address:
      | Full Name    | Street Address     | City        | State | Zip Code | Country |
      | <full_name>  | <old_street>       | <old_city>  | <old_state> | <old_zip> | <old_country> |
    When Customer updates the <address_type> address to:
      | Full Name    | Street Address     | City          | State | Zip Code | Country |
      | <full_name>  | <new_street>       | <new_city>    | <new_state> | <new_zip> | <new_country> |
    And clicks "Save Address"
    Then A confirmation message "Address changed successfully." should be displayed
    And The Address Book should display the updated <address_type> address:
      | Full Name    | Street Address     | City          | State | Zip Code | Country |
      | <full_name>  | <new_street>       | <new_city>    | <new_state> | <new_zip> | <new_country> |

    Examples:
      | address_type | full_name | old_street         | old_city   | old_state | old_zip | old_country | new_street          | new_city      | new_state | new_zip | new_country |
      | shipping     | John Doe  | 12 Palm Avenue     | Miami      | FL        | 33101   | USA         | 99 Sunset Boulevard | Los Angeles  | CA        | 90001   | USA         |
      | billing      | Jane Doe  | 5 Maple Street     | New York   | NY        | 10001   | USA         | 45 River Avenue     | San Francisco| CA        | 94105   | USA         |
