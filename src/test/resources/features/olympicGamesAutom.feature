Feature: Test Olympic Games endpoint

  Scenario: Getting all Olympic Games
    Given a list of Olympic Games
    When we want to see all Olympic Games information
    Then all Olympic Games information is shown

  Scenario: Getting an Olympic Games by Olympic Games identifier
    Given a concrete Olympic Games identifier 3
    When we want to see the information of the Olympic Games by Olympic Games identifier
    Then the Olympic Games information by Olympic Games identifier is shown

  Scenario: Getting an Olympic Games by non-existent Olympic Games identifier
    Given a concrete Olympic Games identifier 9999
    When we want to see the information of the Olympic Games by Olympic Games identifier
    Then a not found error is shown for the Olympic Games

  Scenario: Getting an Olympic Games by Olympic Games year
    Given a concrete Olympic Games year 1992
    When we want to see the information of the Olympic Games by Olympic Games year
    Then the Olympic Games information by Olympic Games year is shown

  Scenario: Getting an Olympic Games by non-existent Olympic Games year
    Given a concrete Olympic Games year 9999
    When we want to see the information of the Olympic Games by Olympic Games year
    Then a not found error is shown for the Olympic Games

  Scenario: Getting Olympic Games by Olympic Games place
    Given a concrete Olympic Games place "Tokyo"
    When we want to see the information of the Olympic Games by Olympic Games place
    Then the Olympic Games information by Olympic Games place is shown

  Scenario: Getting Olympic Games by non-existent Olympic Games place
    Given a concrete Olympic Games place "Test"
    When we want to see the information of the Olympic Games by Olympic Games place
    Then a not found error is shown for the Olympic Games

  Scenario: Adding a new Olympic Games
    Given a new Olympic Games with Olympic Games year 2024 and Olympic Games place "París"
    When we want to add this new Olympic Games
    Then the Olympic Games is correctly added and its information is shown

  Scenario: Adding a duplicated Olympic Games
    Given a new Olympic Games with Olympic Games year 1992 and Olympic Games place "Albertville"
    When we want to add this new Olympic Games
    Then a duplicated information error is shown for the Olympic Games

  Scenario: Updating an existing Olympic Games
    Given a concrete Olympic Games identifier 6
    When we want to update Olympic Games place "Río de Janeiro"
    Then the Olympic Games is correctly updated and its information is shown

  Scenario: Updating a non-existent Olympic Games
    Given a concrete Olympic Games identifier 9999
    When we want to update Olympic Games year 2028 and Olympic Games place "Los Ángeles"
    Then a not found error is shown for the Olympic Games

  Scenario: Updating an existing Olympic Games with duplicated Olympic Games year
    Given a concrete Olympic Games identifier 1
    When we want to update Olympic Games year 2020
    Then a duplicated information error is shown for the Olympic Games

  Scenario: Deleting an exiting Olympic Games
    Given a concrete Olympic Games identifier 4
    When we want to delete this Olympic Games
    Then the Olympic Games is correctly deleted

  Scenario: Deleting a non-existent Olympic Games
    Given a concrete Olympic Games identifier 9999
    When we want to delete this Olympic Games
    Then a not found error is shown for the Olympic Games