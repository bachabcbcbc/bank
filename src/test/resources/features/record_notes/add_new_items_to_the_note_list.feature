@cucumber @todos
Feature: Add new todos

  Scenario: Adding an item to an empty list in Cucumber
    When "Jacob" notes "Buy some milk" to his list
    And "Jacob" creates a new note with title "Buy some milk" and description "Buy some milk"
    Then "Jacob" should see the note with title "Buy some milk" and description "Buy some milk"
