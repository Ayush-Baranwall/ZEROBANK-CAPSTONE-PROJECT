@BillPayment
Feature: Bill Payment
  @ValidScenario
  Scenario: Pay bills to payees
    Given the user is logged in
    When the user navigates to the bill payment page
    And selects a payee
    Then enters "567" as amount and "17-03-2025"
    And clicks on the pay button
    Then Success message "The payment was successfully submitted." is displayed

  @FutureDate
  Scenario: Pay bills to payees with future date
    Given the user is logged in
    When the user navigates to the bill payment page
    And selects a payee
    Then enters "567" as amount and "17-12-2026"
    And clicks on the pay button
    Then message "Payment should be scheduled successfully." is displayed

  @SkippingAmountSection
  Scenario: Pay bills to payees by skipping amount section and trying to pay
    Given the user is logged in
    When the user navigates to the bill payment page
    And selects a payee
    Then enters "" as amount and "17-03-2025"
    And clicks on the pay button
    Then Error message "Amount field cannot be empty." should be displayed

  @AddingNewPayee
  Scenario: Adding a new Payee
    Given the user is logged in
    When the user navigates to the bill payment page
    Then add new payee
    And add name="Ayush",address="123City",accountNo="1234567890"
    And clicks on the add button
    Then Successful message "Payee should be added successfully." displayed

  @MissingDetails
  Scenario: Adding a new Payee with missing details
    Given the user is logged in
    When the user navigates to the bill payment page
    Then add new payee
    And add the "","123City","123456789","savings"
    And clicks on the add button
    Then Error message "All fields are required." should be displayed