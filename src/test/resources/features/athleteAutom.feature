Feature: Test athlete endpoint

  Scenario: Getting all athletes
    Given a list of athletes
    When we want to see all athletes information
    Then all athletes information is shown

  Scenario: Getting athletes by athlete identifier
    Given a concrete athlete identifier 1
    When we want to see the information of the athletes by athlete identifier
    Then the athletes information by athlete identifier is shown

  Scenario: Getting athletes by non-existent athlete identifier
    Given a concrete athlete identifier 9999
    When we want to see the information of the athletes by athlete identifier
    Then a not found error is shown for the athletes

  Scenario: Getting athletes by athlete name
    Given a concrete athlete name "Ana"
    When we want to see the information of the athletes by athlete name
    Then the athletes information by athlete name is shown

  Scenario: Getting athletes by non-existent athlete name
    Given a concrete athlete name "Test"
    When we want to see the information of the athletes by athlete name
    Then a not found error is shown for the athletes

  Scenario: Getting athletes by athlete surname
    Given a concrete athlete surname "Fernández"
    When we want to see the information of the athletes by athlete surname
    Then the athletes information by athlete surname is shown

  Scenario: Getting athletes by non-existent athlete surname
    Given a concrete athlete surname "Test"
    When we want to see the information of the athletes by athlete surname
    Then a not found error is shown for the athletes

  Scenario: Getting athletes by athlete country
    Given a concrete athlete country "España"
    When we want to see the information of the athletes by athlete country
    Then the athletes information by athlete country is shown

  Scenario: Getting athletes by non-existent athlete country
    Given a concrete athlete country "Test"
    When we want to see the information of the athletes by athlete country
    Then a not found error is shown for the athletes

  Scenario: Adding a new athlete
    Given a new athlete with athlete name "Michael" athlete surname "Jordan" and athlete country "EE.UU."
    When we want to add this new athlete
    Then the athlete is correctly added and its information is shown

  Scenario: Updating an existing athlete
    Given a concrete athlete identifier 8
    When we want to update athlete name "Michael" athlete and athlete country "EE.UU."
    Then the athlete is correctly updated and its information is shown

  Scenario: Updating a non-existent athlete
    Given a concrete athlete identifier 9999
    When we want to update athlete name "LeBron" athlete surname "James" and athlete country "EE.UU."
    Then a not found error is shown for the athletes

  Scenario: Deleting an exiting athlete
    Given a concrete athlete identifier 9
    When we want to delete this athlete
    Then the athlete is correctly deleted

  Scenario: Deleting a non-existent athlete
    Given a concrete athlete identifier 9999
    When we want to delete this athlete
    Then a not found error is shown for the athletes