Feature: Calculate the basket total

  Scenario: Price a basket containing 3 tins of soup and 2 loaves of bread, bought today is 3.15

    Given today's date is "2021-01-21"
    And the basket contains following items
      | product | quantity |
      | soup    | 3        |
      | bread   | 2        |
    And the items are eligible for discount from "2021-01-20" to "2021-01-27"
    When the basket is calculated
    Then the total is "3.15"

