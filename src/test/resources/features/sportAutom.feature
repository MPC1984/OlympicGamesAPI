Feature: Test sport endpoint

  Scenario: Getting all sports
    Given a list of sports
    When we want to see all sports information
    Then all sports information is shown

  Scenario: Getting sports by sport identifier
    Given a concrete sport identifier 8
    When we want to see the information of the sports by sport identifier
    Then the sports information by sport identifier is shown

  Scenario: Getting sports by non-existent sport identifier
    Given a concrete sport identifier 9999
    When we want to see the information of the sports by sport identifier
    Then a not found error is shown for the sports

  Scenario: Getting sports by sport name
    Given a concrete sport name "Atletismo"
    When we want to see the information of the sports by sport name
    Then the sports information by sport name is shown

  Scenario: Getting sports by non-existent sport name
    Given a concrete sport name "Test"
    When we want to see the information of the sports by sport name
    Then a not found error is shown for the sports

  Scenario: Getting sports by sport category
    Given a concrete sport category "Dobles Femeninos"
    When we want to see the information of the sports by sport category
    Then the sports information by sport category is shown

  Scenario: Getting sports by non-existent sport category
    Given a concrete sport category "Test"
    When we want to see the information of the sports by sport category
    Then a not found error is shown for the sports

  Scenario: Adding a new sport
    Given a new sport with sport name "Baloncesto" and sport category "Masculino"
    When we want to add this new sport
    Then the sport is correctly added and its information is shown

  Scenario: Updating an existing sport
    Given a concrete sport identifier 6
    When we want to update sport name "Fútbol"
    Then the sport is correctly updated and its information is shown

  Scenario: Updating a non-existent sport
    Given a concrete sport identifier 9999
    When we want to update sport name "Waterpolo" and sport category "Femenino"
    Then a not found error is shown for the sports

  Scenario: Deleting an exiting sport
    Given a concrete sport identifier 7
    When we want to delete this sport
    Then the sport is correctly deleted

  Scenario: Deleting a non-existent sport
    Given a concrete sport identifier 9999
    When we want to delete this sport
    Then a not found error is shown for the sports