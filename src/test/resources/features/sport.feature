Feature: Test sport endpoint

  Scenario: Getting all sports
    Given a list of sports
    When we want to see all sports information
    Then all sports information is shown

  #Scenario: Getting all sports when the list is empty
    #Given a list of empty sports
    #When we want to see all sports information when the list is empty
    #Then no sports information is shown and a not found error is shown

  Scenario: Getting a sport by its identifier
    Given a list of sports
    When we want to see the information of a concrete sport by its identifier
    Then the sport information is shown

  Scenario: Getting a sport by a non-existent identifier
    Given a list of sports
    When we want to see the information of a concrete sport by a non-existent identifier
    Then no sport information is shown and a not found error is shown

  Scenario: Getting all sports by their name
    Given a list of sports
    When we want to see all sports information by their name
    Then all sports information is shown

  Scenario: Getting all sports by a non-existent name
    Given a list of sports
    When we want to see all sports information by a non-existent name
    Then no sports information is shown and a not found error is shown

  Scenario: Getting all sports by their category
    Given a list of sports
    When we want to see all sports information by their category
    Then all sports information is shown

  Scenario: Getting all sports by a non-existent category
    Given a list of sports
    When we want to see all sports information by a non-existent category
    Then no sports information is shown and a not found error is shown

  Scenario: Adding a new sport
    Given a new sport with name "sport_name" and category "sport_category"
    When we want to add this new sport
    Then the sport is correctly added and its information is shown

  Scenario: Adding a new sport without name
    Given a new sport without name
    When we want to add this new sport
    Then a bad request error is shown for the sport

  Scenario: Updating an existing sport
    Given an existing sport
    When we want to update its name to "sn_updated" and its category to "sc_updated"
    Then the sport is correctly updated and its information is shown

  Scenario: Updating a non-existent sport
    Given a non-existent sport
    When we want to update its name to "sn_updated" and its category to "sc_updated"
    Then no sport information is shown and a not found error is shown

  Scenario: Deleting an exiting sport
    Given an existing sport
    When we want to delete this sport
    Then the sport is correctly deleted

  Scenario: Deleting a non-existent sport
    Given a non-existent sport
    When we want to delete this sport
    Then no sport information is shown and a not found error is shown