Feature: Product discovery by search, filtering, and sorting

  Scenario Outline: Customer searches a product by name
    Given a Customer is on "<page_name>" products page
    When a Customer enters search keyword as "<search_keyword>"
    And clicks the "SEARCH" button
    Then only products containing the "<search_keyword>" in their names should be displayed

    Examples:
      | page_name   | search_keyword |
      | Store       | Jeans          |
      | Men         | Shoes          |
      | Women       | Denim          |
      | Accessories | Bracelet       |

  Scenario Outline: System returns no products for unknown product name
    Given a Customer is on "<page_name>" page
    When a Customer enters search keyword as "<search_keyword>"
    And clicks the "SEARCH" button
    Then a message "No products were found matching your selection." should be displayed

    Examples:
      | page_name | search_keyword |
      | Store     | 567            |

  Scenario Outline: Customer sorts products by valid criteria
    Given a Customer is on "<page_name>" products page
    When a Customer sorts products by "<sort_criteria>"
    Then the products should be re-arranged by "<sort_criteria>"

    Examples:
      | page_name   | sort_criteria      |
      | Store       | average rating     |
      | Store       | price: low to high |
      | Store       | price: high to low |
      | Men         | average rating     |
      | Men         | price: low to high |
      | Men         | price: high to low |
      | Women       | average rating     |
      | Women       | price: low to high |
      | Women       | price: high to low |
      | Accessories | average rating     |
      | Accessories | price: low to high |
      | Accessories | price: high to low |

  Scenario Outline: System rejects invalid sorting criterion
    Given a Customer is on "<page_name>" products page
    When a Customer selects an invalid sorting criterion as "<sorting_option>"
    Then the system should ignore the invalid option, maintaining the default products order

    Examples:
      | page_name   | sorting_option      |
      | Store       | RandomInvalidOption |
      | Men         | UnsupportedSort     |
      | Women       | InvalidCriterion    |
      | Accessories | FakeSortOption      |

  Scenario Outline: Customer filters products by valid price range
    Given a Customer is on "<page_name>" products page
    When a Customer sets the price range from <min_price> to <max_price>
    And clicks the "FILTER" button
    Then only products within <min_price> to <max_price> should be displayed

    Examples:
      | page_name   | min_price | max_price |
      | Store       | 10        | 150       |
      | Store       | 50        | 150       |
      | Men         | 10        | 150       |
      | Men         | 50        | 150       |
      | Women       | 10        | 150       |
      | Women       | 50        | 150       |
      | Accessories | 10        | 150       |
      | Accessories | 50        | 150       |

  Scenario Outline: System constrains price filter to allowed range
    Given a Customer is on "<page_name>" products page
    When a Customer sets the price range from <min_price> to <max_price>
    Then the system should clamp the price range to stay within the allowed range of 10 to 150

    Examples:
      | page_name   | min_price | max_price |
      | Store       | 0         | 9         |
      | Store       | 151       | 200       |
      | Store       | 0         | 10        |
      | Store       | 150       | 200       |
      | Men         | 0         | 9         |
      | Men         | 151       | 200       |
      | Men         | 0         | 10        |
      | Men         | 150       | 200       |
      | Women       | 0         | 9         |
      | Women       | 151       | 200       |
      | Women       | 0         | 10        |
      | Women       | 150       | 200       |
      | Accessories | 0         | 9         |
      | Accessories | 151       | 200       |
      | Accessories | 0         | 10        |
      | Accessories | 150       | 200       |

  Scenario Outline: Customer filters products by valid sub-category
    Given a Customer is on store page
    When a Customer selects sub-category as "<category>"
    Then only products in the "<category>" sub-category should be displayed

    Examples:
      | category            |
      | Men's Shirts        |
      | Men's Shoes         |
      | Men's Jeans         |
      | Women's Shirts      |
      | Women's Shoes       |
      | Women's Jeans       |
      | Purses And Handbags |

  Scenario Outline: System rejects invalid category filter
    Given a Customer is on "<page_name>" page
    When a Customer selects the sub-category as "<sub_category>"
    Then the list of products should remain unchanged

    Examples:
      | page_name | sub_category |
      | store     | unknown      |

  Scenario Outline: Customer refines product discovery by combining filters and sorters
    When a Customer selects sub-category as "<category>"
    And sets the price range from <min_price> to <max_price>
    And clicks the "FILTER" button
    And sorts products by "<sort_order>"
    Then only products in "<category>" within the price range of <min_price> to <max_price> should be displayed
    And products should be sorted in "<sort_order>" order

    Examples:
      | category    | min_price | max_price | sort_order         |
      | Men’s Jeans | 30        | 100       | price: low to high |
