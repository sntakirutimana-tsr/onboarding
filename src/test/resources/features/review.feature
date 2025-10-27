Feature: view product details

  Scenario Outline: Customer submits a review with valid inputs
    Given the customer is on the reviews tab
    When the customer selects rating as "<rating>"
    And the customer enters a review as "<review>"
    And the customer enters name as "<name>"
    And the customer enters email as "<email>"
    And the customer clicks on the submit button
    Then the system displays a review card
    And the review card displays a message "<message>" should be displayed
    And the review card displays the rating as "<rating>"
    And the review card displays the review as "<review>"

    Examples:
      | rating | review  | name   | email            | message                         |
      | 2      | ugly    | ken    | ken@gmail.com    | your review is waiting approval |
      | 3      | not bad | barbie | barbie@gmail.com | your review is waiting approval |

  Scenario Outline: system rejects review submission with invalid inputs
    Given the customer is on the reviews tab
    When the customer selects rating as "<rating>"
    And the customer enters a review as "<review>"
    And the customer enters name as "<name>"
    And the customer enters email as "<email>"
    And the customer clicks on the submit button
    Then an error message "<error-message>" should be displayed

    Examples:
      | rating  | review | name | email        | error-message                                                            |
      |         |        |      |              | please select a rating                                             |
      |         | kkk    | kk   | kk@gmail.com | please select a rating                                             |
      | 4  |        |      |                   | please fill in this field                                          |
      | 4  | kkk    |      |                   | please fill in this field                                          |
      | 3  | kkk    | kk   |                   | please fill in this field                                          |
      | 3  | kkk    | kk   | san               | please include an '@'in the email address.'san' is missing an '@'. |
      | 3  | kkk    | kk   | san@              | please enter a part following '@'. 'san@' is incomplete            |
      | 5  | kkk    | kk   | san@gmail         | Error: Please enter a valid email address.                         |
