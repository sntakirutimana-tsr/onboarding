Feature: Change shipping address
  As a customer
  I want to ship my order to a different address if needed
  So that the order can be delivered to someone else or a different location

  Background:
    Given Customer is on the checkout page
    And the "Ship to a different address" checkbox is checked
    And the shipping address form is displayed
  
  @smoke @regression
  Scenario: Submitting with empty required fields in the shipping address form should show validation errors
    When Customer clicks on the "Place Order" button without filling the required fields:
      | Field              |
      | First name         |
      | Last name          |
      | Country / Region   |
      | Street address     |
      | Town / City        |
      | State / County     |
      | Postcode / ZIP     |
      | Email address      |
    Then Customer should see the following error messages displayed at the top of the billing form:
      | Message                                      |
      | Billing First name is a required field.      |
      | Billing Last name is a required field.       |
      | Billing Street address is a required field.  |
      | Billing Town / City is a required field.     |
      | Billing State / County is a required field.  |
      | Billing Postcode / ZIP is a required field.  |
      | Billing Email address is a required field.   |