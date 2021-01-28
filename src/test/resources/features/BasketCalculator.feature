Feature: Calculate the basket total

  Background: Current date is 2021-01-21

  Scenario: Price a basket containing 3 tins of soup and 2 loaves of bread, bought today is 3.15
    Given billing date is "2021-01-21"
    And the basket contains following items
      | product | quantity |
      | soup    | 3        |
      | bread   | 2        |
    And the item "soup" is eligible for discount from "2021-01-20" to "2021-01-27"
    When the basket is calculated
    Then the total is "3.15"

  Scenario: Price a basket containing 6 apples and bottle of milk, bought today is 1.90
    Given billing date is "2021-01-21"
    And the basket contains following items
      | product | quantity |
      | apples  | 6        |
      | milk    | 1        |
    And the item "apples" is eligible for discount from "2021-01-24" to "2021-02-28"
    When the basket is calculated
    Then the total is "1.90"

  Scenario: Price a basket containing 6 apples and bottle of milk, bought in 5 days time
    Given billing date is "2021-01-26"
    And the basket contains following items
      | product | quantity |
      | apples  | 6        |
      | milk    | 1        |
    And the item "apple" is eligible for discount from "2021-01-24" to "2021-02-28"
    When the basket is calculated
    Then the total is "1.84"

