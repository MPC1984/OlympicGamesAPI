Feature: Test athletes endpoint

  Scenario: Getting all athletes
    Given A list of athletes
    When we want to see all athletes information
    Then all athletes information is shown