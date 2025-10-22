Feature: view product details

  Scenario Outline: Customer submits a review with valid inputs
    Given the customer is on the reviews tab
    When the customer selects rating as "<rating>"
    And the customer enters a review as "<review>"
    And the customer enters name as "<name>"
    And the customer enters email as "<email>"
    And customer submits the form
    Then the system displays a review card
    And the riew card displays a message "<message>" should be displayed
    And the review card displays the rating as "<rating>"
    And the review card displays the review as "<review>"

    Examples:
      | rating  | review  | name   | email            | message                         |
      | 5 stars | nice    | ken    | ken@gmail.com    | your review is waiting approval |
      | 3 stars | not bad | barbie | barbie@gmail.com | your review is waiting approval |

  Scenario Outline: system rejects review submission with invalid inputs
    Given the customer is on the reviews tab
    When the customer enters rating as "<rating>"
    And the customer enters a review as "<review>"
    And the customer enters name as "<name>"
    And the customer enters email as "<email>"
    And customer submit the form
    Then an error message "<error-messagee>" should be displayed

    Examples:
      | rating  | review | name | email        | message                                                            |
      |         |        |      |              | please select a rating                                             |
      |         | kkk    | kk   | kk@gmail.com | please select a rating                                             |
      | 4 stars |        |      |              | please fill in this field                                          |
      | 4 stars | kkk    |      |              | please fill in this field                                          |
      | 3 stars | kkk    | kk   |              | please fill in this field                                          |
      | 3 stars | kkk    | kk   | san          | please include an '@'in the email address.'san' is missing an '@'. |
      | 3 stars | kkk    | kk   | san@         | please enter a part following '@'. 'san@' is incomplete            |
      | 5 stars | kkk    | kk   | san@gmail    | Error: Please enter a valid email address.                         |
