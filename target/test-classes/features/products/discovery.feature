Feature: Product discovery by search, filtering, and sorting

  Scenario Outline: Customer searches a product by name
    Given a Customer is on "<page_name>" products page
    When a Customer enters search keyword as "<search_keyword>"
    And clicks the ❝SEARCH❞ button
    Then only products containing the "<search_keyword>" in their names should be displayed

    Examples:
      | page_name   | search_keyword |
      | store       | Jeans          |
      | men's       | Shoes          |
      | women's     | Denim          |
      | accessories | Bracelet       |

  Scenario Outline: System returns no products for unknown product name
    Given a Customer is on "<page_name>" products page
    When a Customer enters search keyword as "<search_keyword>"
    And clicks the ❝SEARCH❞ button
    Then a message ❝No products were found matching your selection.❞ should be displayed

    Examples:
      | page_name | search_keyword |
      | store     | 567            |

  Scenario Outline: Customer sorts products by valid criteria
    Given a Customer is on "<page_name>" products page
    When a Customer sorts products by "<sort_criteria>"
    Then the products should be re-arranged by "<sort_criteria>"

    Examples:
      | page_name   | sort_criteria      |
      | store       | average rating     |
      | store       | price: low to high |
      | store       | price: high to low |
      | men's       | average rating     |
      | men's       | price: low to high |
      | men's       | price: high to low |
      | women's     | average rating     |
      | women's     | price: low to high |
      | women's     | price: high to low |
      | accessories | average rating     |
      | accessories | price: low to high |
      | accessories | price: high to low |

  Scenario Outline: System rejects invalid sorting criterion
    Given a Customer is on "<page_name>" products page
    When a Customer selects an invalid sorting criterion as "<sorting_option>"
    Then the system should ignore the invalid option, maintaining the default products order

    Examples:
      | page_name   | sorting_option      |
      | store       | RandomInvalidOption |
      | men's       | UnsupportedSort     |
      | men's       | InvalidCriterion    |
      | accessories | FakeSortOption      |

  Scenario Outline: Customer filters products by valid price range
    Given a Customer is on "<page_name>" products page
    When a Customer sets the price range from <min_price> to <max_price>
    And clicks the ❝FILTER❞ button
    Then only products within <min_price> to <max_price> should be displayed

    Examples:
      | page_name   | min_price | max_price |
      | store       | 10        | 150       |
      | store       | 50        | 150       |
      | men's       | 10        | 150       |
      | men's       | 50        | 150       |
      | women's     | 10        | 150       |
      | women's     | 50        | 150       |
      | accessories | 10        | 150       |
      | accessories | 50        | 150       |

  Scenario Outline: System constrains price filter to allowed range
    Given a Customer is on "<page_name>" products page
    When a Customer sets the price range from <min_price> to <max_price>
    Then the system should clamp the price range to stay within the allowed range of 10 to 150

    Examples:
      | page_name   | min_price | max_price |
      | store       | 0         | 9         |
      | store       | 151       | 200       |
      | store       | 0         | 10        |
      | store       | 150       | 200       |
      | men's       | 0         | 9         |
      | men's       | 151       | 200       |
      | men's       | 0         | 10        |
      | men's       | 150       | 200       |
      | women's     | 0         | 9         |
      | women's     | 151       | 200       |
      | women's     | 0         | 10        |
      | women's     | 150       | 200       |
      | accessories | 0         | 9         |
      | accessories | 151       | 200       |
      | accessories | 0         | 10        |
      | accessories | 150       | 200       |

  Scenario Outline: Customer filters products by valid sub-category
    Given a Customer is on "store" products page
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
    Given a Customer is on "<page_name>" products page
    When a Customer selects sub-category as "<sub_category>"
    Then the list of products should remain unchanged

    Examples:
      | page_name | sub_category |
      | store     | unknown      |

  Scenario Outline: Customer refines product discovery by combining filters and sorters
    Given a Customer is on "store" products page
    When a Customer selects sub-category as "<category>"
    And sets the price range from <min_price> to <max_price>
    And clicks the ❝FILTER❞ button
    And sorts products by "<sort_order>"
    Then only products in "<category>" within the price range of <min_price> to <max_price> should be displayed
    And products should be sorted in "<sort_order>" order

    Examples:
      | category    | min_price | max_price | sort_order         |
      | Men’s Jeans | 30        | 100       | price: low to high |
