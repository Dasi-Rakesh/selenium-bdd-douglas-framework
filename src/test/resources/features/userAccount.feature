#Feature: User Account Management
#
#  Scenario: Successful account creation
#    Given user is on the signup page
#    When user enters first name "rakesh", last name "sdet", email "email", password "Password123", and confirm password "Password123"
#    And clicks on the signup button
#    Then the account is created successfully
#
#  Scenario: Successful sign-in with existing account
#    Given user is on the sign-in page
#    When user enters email "email" and password "Password123"
#    And clicks on the sign-in button
#    Then the user is successfully signed in