Feature: Test metal endpoint

  Scenario: Getting all metals
    Given a list of metals
    When we want to see all metals information
    Then all metals information is shown

  Scenario: Getting a metal by its identifier
    Given a list of metals
    When we want to see the information of a concrete metal by its identifier
    Then the metal information is shown

  Scenario: Getting a metal by a non-existent identifier
    Given a list of metals
    When we want to see the information of a concrete metal by a non-existent identifier
    Then no metal information is shown and a not found error is shown

  Scenario: Getting a metal by its metal type
    Given a list of metals
    When we want to see the information of a concrete metal by its metal type
    Then the metal information is shown

  Scenario: Getting a metal by a non-existent metal type
    Given a list of metals
    When we want to see the information of a concrete metal by a non-existent metal type
    Then no metal information is shown and a not found error is shown

  Scenario: Adding a new metal
    Given a new metal with metal type "metal_type"
    When we want to add this new metal
    Then the metal is correctly added and its information is shown

  Scenario: Adding a new metal without metal type
    Given a new metal without metal type
    When we want to add this new metal
    Then a bad request error is shown for the metal

  Scenario: Adding a duplicated metal
    Given a new metal with metal type "Oro"
    When we want to add this new metal
    Then no metal information is shown and a duplicated information error is shown

  Scenario: Updating an existing metal
    Given an existing metal
    When we want to update its metal type to "mt_updated"
    Then the metal is correctly updated and its information is shown

  Scenario: Updating a non-existent metal
    Given a non-existent metal
    When we want to update its metal type to "mt_updated"
    Then no metal information is shown and a not found error is shown

  Scenario: Updating an existing metal with a duplicated metal
    Given an existing metal
    When we want to update its metal type to "Oro"
    Then no metal information is shown and a duplicated information error is shown

  Scenario: Deleting an exiting metal
    Given an existing metal
    When we want to delete this metal
    Then the metal is correctly deleted

  Scenario: Deleting a non-existent metal
    Given a non-existent metal
    When we want to delete this metal
    Then no metal information is shown and a not found error is shown