@money-transfer @web-to-mobile
Feature: Money Transfer from Web to Mobile App

  Background:
    Given "John" is logged into the web banking application
    And "John" has a linked mobile banking app
    And "John" has sufficient funds in his account

  @smoke @critical
  Scenario: Successful money transfer from web to mobile app
    When "John" initiates a transfer of "$100" from web to mobile app
    And "John" receives a notification on his mobile app
    And "John" approves the transfer on his mobile app
    Then the transfer should be completed successfully
    And "John" should see the updated balance on both web and mobile
    And a confirmation message should be displayed

  @regression
  Scenario: Transfer with insufficient funds
    When "John" attempts to transfer "$10000" from web to mobile app
    Then the transfer should be rejected due to insufficient funds
    And an appropriate error message should be displayed

  @regression
  Scenario: Transfer with invalid amount
    When "John" attempts to transfer "$-50" from web to mobile app
    Then the transfer should be rejected due to invalid amount
    And an appropriate validation message should be displayed

  @security
  Scenario: Transfer with expired session
    When "John"'s web session expires during transfer
    And "John" tries to complete the transfer on mobile
    Then the transfer should be blocked for security reasons
    And "John" should be prompted to re-authenticate 