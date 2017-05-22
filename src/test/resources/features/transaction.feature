Feature: Transaction tests

  Scenario: Make a bet with insufficient balance
    Given home page is open
    When user clicks on available selection bet
    Then betslip with the selected selection is displayed
    When user logs in with LOW_BALANCE_ACCOUNT
    Then filled betslip is displayed
    And user data is displayed
    When user fills stake field
    Then place bet button is disabled
    When user clicks deposit button
    Then fast deposit popup appears
    When user fills in deposit amount
    And clicks continue button
    And fills in credit card info
    And clicks next button
    Then successful deposit message is displayed
    When user places a bet with chosen selection
    Then betslip is filled with bet info
    When user opens my account view
    Then account page is displayed
    When user opens transactions view
    Then list of users transactions is displayed
    And placed bet and deposit are displayed in transaction list


    Scenario: Make a bet with sufficient balance
      Given home page is open
      When user clicks on available selection bet
      Then betslip with the selected selection is displayed
      When user logs in with LOW_BALANCE_ACCOUNT
      Then filled betslip is displayed
      And user data is displayed
      When user places a bet with chosen selection
      Then betslip is filled with bet info
      When user opens my account view
      Then account page is displayed
      When user opens transactions view
      Then list of users transactions is displayed
      And placed bet and deposit are displayed in transaction list