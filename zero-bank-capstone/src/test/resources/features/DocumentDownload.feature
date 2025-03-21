@PdfDownloading
Feature: Download Account Statement

  Scenario: Download Account Statement pdf
    Given user login with "username" and "password"
    When user navigate to Statements & Documents
    Then user select an account and a date range
    And click on Download PDF button
    And user clicks on Keep in the download permission
    Then the user should verify the downloaded PDF file in the download section