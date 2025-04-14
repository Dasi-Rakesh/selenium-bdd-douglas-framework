Feature: Parfum Product Filtering and Navigation
  As a user
  I want to filter and navigate Parfum products
  So that I can find and view specific products

  Scenario: Filter and view specific Parfum product
    Given I navigate to Douglas home page
    And I accept cookies
    When I click on the "PARFUM" category
    And I apply price filter from "5" to "50"
    And I apply product type filter "Eau de Parfum"
    And I apply brand filter "SOLINOTES"
    And I select the first product
    Then I should be on the product details page