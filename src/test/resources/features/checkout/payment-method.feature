Feature: Payment method

  @regression
  Scenario Outline: Customer select preferred payment method
    Given Customer is on the checkout page
    When Customer selects "<payment_method>"
    Then "<payment_method>" is selected
    And test mode warning message "<test_mode_warning>" is displayed

    Examples:
      | payment_method       | test_mode_warning                                                                |
      | Direct Bank Transfer | TEST MODE ENABLED. DO NOT ENTER YOUR PERSONAL DATA. NO ORDER WILL BE FULLFILLED. |
      | Cash on Delivery     | TEST MODE ENABLED. DO NOT ENTER YOUR PERSONAL DATA. NO ORDER WILL BE FULLFILLED. |

