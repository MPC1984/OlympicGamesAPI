Feature: Test metal endpoint

  Scenario: Getting all metals
    Given a list of metals
    When we want to see all metals information
    Then all metals information is shown

  Scenario: Getting metals by metal identifier
    Given a concrete metal identifier 1
    When we want to see the information of the metals by metal identifier
    Then the metals information by metal identifier is shown

  Scenario: Getting metals by non-existent metal identifier
    Given a concrete metal identifier 9999
    When we want to see the information of the metals by metal identifier
    Then a not found error is shown for the metals

  Scenario: Getting metals by metal type
    Given a concrete metal type "Plata"
    When we want to see the information of the metals by metal type
    Then the metals information by metal type is shown

  Scenario: Getting metals by non-existent metal type
    Given a concrete metal type "Test"
    When we want to see the information of the metals by metal type
    Then a not found error is shown for the metals

  Scenario: Adding a new metal
    Given a new metal with metal type "NewType"
    When we want to add this new metal
    Then the metal is correctly added and its information is shown

  Scenario: Adding a duplicated metal
    Given a new metal with metal type "Oro"
    When we want to add this new metal
    Then a duplicated information error is shown for the metals

  Scenario: Updating an existing metal
    Given a concrete metal identifier 3
    When we want to update metal type "Bronce"
    Then the metal is correctly updated and its information is shown

  Scenario: Updating a non-existent metal
    Given a concrete metal identifier 9999
    When we want to update metal type "OtherType"
    Then a not found error is shown for the metals

  Scenario: Updating an existing metal with duplicated metal type
    Given a concrete metal identifier 2
    When we want to update metal type "Oro"
    Then a duplicated information error is shown for the metals

  Scenario: Deleting an exiting metal
    Given a concrete metal identifier 2
    When we want to delete this metal
    Then the metal is correctly deleted

  Scenario: Deleting a non-existent metal
    Given a concrete metal identifier 9999
    When we want to delete this metal
    Then a not found error is shown for the metals