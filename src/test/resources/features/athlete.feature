Feature: Test athlete endpoint

  Scenario: Getting all athletes
    Given a list of athletes
    When we want to see all athletes information
    Then all athletes information is shown

  #Scenario: Getting all athletes when the list is empty
    #Given a list of empty athletes
    #When we want to see all athletes information when the list is empty
    #Then no athletes information is shown and a not found error is shown

  Scenario: Getting an athlete by its identifier
    Given a list of athletes
    When we want to see the information of a concrete athlete by its identifier
    Then the athlete information is shown

  Scenario: Getting an athlete by a non-existent identifier
    Given a list of athletes
    When we want to see the information of a concrete athlete by a non-existent identifier
    Then no athlete information is shown and a not found error is shown

  Scenario: Getting all athletes by their name
    Given a list of athletes
    When we want to see all athletes information by their name
    Then all athletes information is shown

  Scenario: Getting all athletes by a non-existent name
    Given a list of athletes
    When we want to see all athletes information by a non-existent name
    Then no athletes information is shown and a not found error is shown

  Scenario: Getting all athletes by their surname
    Given a list of athletes
    When we want to see all athletes information by their surname
    Then all athletes information is shown

  Scenario: Getting all athletes by a non-existent surname
    Given a list of athletes
    When we want to see all athletes information by a non-existent surname
    Then no athletes information is shown and a not found error is shown

  Scenario: Getting all athletes by their country
    Given a list of athletes
    When we want to see all athletes information by their country
    Then all athletes information is shown

  Scenario: Getting all athletes by a non-existent country
    Given a list of athletes
    When we want to see all athletes information by a non-existent country
    Then no athletes information is shown and a not found error is shown

  Scenario: Adding a new athlete
    Given a new athlete with name "athlete_name", surname "athlete_surname" and country "athlete_country"
    When we want to add this new athlete
    Then the athlete is correctly added and its information is shown

  Scenario: Adding a new athlete without name
    Given a new athlete without name
    When we want to add this new athlete
    Then a bad request error is shown for the athlete

  Scenario: Updating an existing athlete
    Given an existing athlete
    When we want to update its name to "an_updated", its surname to "as_updated" and its country to "ac_updated"
    Then the athlete is correctly updated and its information is shown

  Scenario: Updating a non-existent athlete
    Given a non-existent athlete
    When we want to update its name to "an_updated", its surname to "as_updated" and its country to "ac_updated"
    Then no athlete information is shown and a not found error is shown

  Scenario: Deleting an exiting athlete
    Given an existing athlete
    When we want to delete this athlete
    Then the athlete is correctly deleted

  Scenario: Deleting a non-existent athlete
    Given a non-existent athlete
    When we want to delete this athlete
    Then no athlete information is shown and a not found error is shown