Feature: Test metals endpoint

  Scenario: Getting all metals
    Given A list of metals
    When we want to see all metals information
    Then all metals information is shown

  Scenario: Getting a metal by its identifier
    Given A list of metals
    When we want to see the information of a concrete metal by its identifier
    Then the metal information is shown

  Scenario: Getting a metal by a non-existent identifier
    Given A list of metals
    When we want to see the information of a concrete metal by a non-existent identifier
    Then no information is shown and an error is shown

  Scenario: Getting a metal by its metal type
    Given A list of metals
    When we want to see the information of a concrete metal by its metal type
    Then the metal information is shown

  Scenario: Getting a metal by a non-existent metal type
    Given A list of metals
    When we want to see the information of a concrete metal by a non-existent metal type
    Then no information is shown and an error is shown

  Scenario: Adding a new metal
    Given A new metal with metal type "metal_type"
    When we want to add this new metal
    Then the metal is correctly added and its information is shown

  Scenario: Adding a new metal without metal type
    Given A new metal without metal type
    When we want to add this new metal
    Then an error is shown

  Scenario: Updating an existing metal
    Given An existing metal
    When we want to update its metal type to "mt_updated"
    Then the metal is correctly updated and its information is shown

  Scenario: Updating a non-existent metal
    Given A non-existent metal
    When we want to update its metal type to "mt_updated"
    Then no information is shown and an error is shown

  Scenario: Deleting an exiting metal
    Given An existing metal
    When we want to delete this metal
    Then the metal is correctly deleted

  Scenario: Deleting a non-existent metal
    Given A non-existent metal
    When we want to delete this metal
    Then no information is shown and an error is shown