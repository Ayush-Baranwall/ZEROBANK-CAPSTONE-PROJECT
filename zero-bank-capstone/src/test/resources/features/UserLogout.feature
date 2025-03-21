@PurgeLogout
Feature: Logout Functionality

  @LogoutChecking
  Scenario: Logout Successfully
    Given The user is logged into the Zero Bank platform with "username" and "password"
    When The user clicks on the Logout button
    Then The user should be redirected to the login page

  @TryingToGetBackAfterLogout
  Scenario: Verify Session Expiry After Logout
    Given The user is logged into the Zero Bank platform with "username" and "password"
    And The user logs out of the application
    When The user clicks the Back button in the browser
    Then The user should not be able to access the previous page