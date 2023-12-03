Feature: Creating and retrieving menu items

  Scenario: Create a menu item and retrieve it
    Given a test restaurant exists with ID "testRestaurantId" and name "Test Restaurant"
    When a menu item with name "Test Item", description "Test Description", price "9.99", and restaurant ID "testRestaurantId" is created
    Then the menu item with name "Test Item" should be retrieved successfully
