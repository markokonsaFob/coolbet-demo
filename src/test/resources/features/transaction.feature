Feature: Transaction tests

  Background:
    Given home page is open
    And live sports betting view is selected

  Scenario: Make a bet with insufficient balance
    When user clicks on first available bet selection
    Then betslip with the selected selection is displayed
    When user logs in with VALID account
    Then user data is displayed
    And filled betslip is displayed
    When user enters 2 into stake field
    Then place bet button is disabled
    When user clicks deposit button
    Then fast deposit popup appears
    When user enters 75 into deposit field
    And user selects deposit method VISA
    And clicks continue button
    And fills in INVALID_CREDITCARD info
    And clicks next button
    Then error message is displayed


  Scenario: Make a bet with sufficient balance
    When user clicks on first available bet selection
    Then betslip with the selected selection is displayed
    When user logs in with VALID account
    Then filled betslip is displayed
    And user data is displayed
    When user places a bet with chosen selection
    Then betslip is filled with bet info
    When user opens my account view
    Then account page is displayed
    When user opens transactions view
    Then list of users transactions is displayed
    And placed bet and deposit are displayed in transaction list