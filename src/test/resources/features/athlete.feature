Feature: Test metals endpoint

  Scenario: Getting all metals when the list is empty
    Given A list of empty metals
    When we want to see all metals information when the list is empty
    Then no information is shown for the empty list and an error is shown