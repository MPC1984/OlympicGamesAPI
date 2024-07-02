Feature: Test olympicGames endpoint

  Scenario: Getting all Olympic Games
    Given a list of Olympic Games
    When we want to see all Olympic Games information
    Then all Olympic Games information is shown

  #Scenario: Getting all Olympic Games when the list is empty
    #Given a list of empty Olympic Games
    #When we want to see all Olympic Games information when the list is empty
    #Then no Olympic Games information is shown and a not found error is shown

  Scenario: Getting an Olympic Games by its identifier
    Given a list of Olympic Games
    When we want to see the information of a concrete Olympic Games by its identifier
    Then the Olympic Games information is shown

  Scenario: Getting an Olympic Games by a non-existent identifier
    Given a list of Olympic Games
    When we want to see the information of a concrete Olympic Games by a non-existent identifier
    Then no Olympic Games information is shown and a not found error is shown

  Scenario: Getting an Olympic Games by its year
    Given a list of Olympic Games
    When we want to see the information of a concrete Olympic Games by its year
    Then the Olympic Games information is shown

  Scenario: Getting an Olympic Games by a non-existent year
    Given a list of Olympic Games
    When we want to see the information of a concrete Olympic Games by a non-existent year
    Then no Olympic Games information is shown and a not found error is shown

  Scenario: Getting all Olympic Games by their place
    Given a list of Olympic Games
    When we want to see all Olympic Games information by their place
    Then all Olympic Games information is shown

  Scenario: Getting all Olympic Games by a non-existent place
    Given a list of Olympic Games
    When we want to see all Olympic Games information by a non-existent place
    Then no Olympic Games information is shown and a not found error is shown

  Scenario: Adding a new Olympic Games
    Given a new Olympic Games with year 2222 and place "olympicGames_place"
    When we want to add this new Olympic Games
    Then the Olympic Games is correctly added and its information is shown

  Scenario: Adding a new Olympic Games without place
    Given a new Olympic Games without place
    When we want to add this new Olympic Games
    Then a bad request error is shown for the Olympic Games

  Scenario: Adding a duplicated Olympic Games
    Given a new Olympic Games with year 2020
    When we want to add this new Olympic Games
    Then no Olympic Games information is shown and a duplicated information error is shown

  Scenario: Updating an existing Olympic Games
    Given an existing Olympic Games
    When we want to update its year to 1111 and its place to "ogp_updated"
    Then the Olympic Games is correctly updated and its information is shown

  Scenario: Updating a non-existent Olympic Games
    Given a non-existent Olympic Games
    When we want to update its year to 1111 and its place to "ogp_updated"
    Then no Olympic Games information is shown and a not found error is shown

  Scenario: Updating an existing Olympic Games with a duplicated Olympic Games
    Given an existing Olympic Games
    When we want to update its year to 2020
    Then no Olympic Games information is shown and a duplicated information error is shown

  Scenario: Deleting an exiting Olympic Games
    Given an existing Olympic Games
    When we want to delete this Olympic Games
    Then the Olympic Games is correctly deleted

  Scenario: Deleting a non-existent Olympic Games
    Given a non-existent Olympic Games
    When we want to delete this Olympic Games
    Then no Olympic Games information is shown and a not found error is shown