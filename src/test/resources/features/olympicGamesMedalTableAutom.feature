Feature: Test Olympic Games medal table endpoint

  Scenario: Getting all records of the Olympic Games medal table
    Given a list of records of the Olympic Games medal table
    When we want to see all records of the Olympic Games medal table information
    Then all records of the Olympic Games medal table information is shown

  Scenario: Getting records of the Olympic Games medal table by Olympic Games medal table identifier
    Given a concrete Olympic Games medal table identifier 10
    When we want to see the information of the records of the Olympic Games medal table by Olympic Games medal table identifier
    Then the records of the Olympic Games medal table information by Olympic Games medal table identifier is shown

  Scenario: Getting records of the Olympic Games medal table by non-existent Olympic Games medal table identifier
    Given a concrete Olympic Games medal table identifier 9999
    When we want to see the information of the records of the Olympic Games medal table by Olympic Games medal table identifier
    Then a not found error is shown for the records of the Olympic Games medal table

  Scenario: Getting records of the Olympic Games medal table by Olympic Games medal table Olympic Games identifier
    Given a concrete Olympic Games medal table Olympic Games identifier 7
    When we want to see the information of the records of the Olympic Games medal table by Olympic Games medal table Olympic Games identifier
    Then the records of the Olympic Games medal table information by Olympic Games medal table Olympic Games identifier is shown

  Scenario: Getting records of the Olympic Games medal table by non-existent Olympic Games medal table Olympic Games identifier
    Given a concrete Olympic Games medal table Olympic Games identifier 9999
    When we want to see the information of the records of the Olympic Games medal table by Olympic Games medal table Olympic Games identifier
    Then a not found error is shown for the records of the Olympic Games medal table

  Scenario: Getting records of the Olympic Games medal table by Olympic Games medal table Olympic Games year
    Given a concrete Olympic Games medal table Olympic Games year 2020
    When we want to see the information of the records of the Olympic Games medal table by Olympic Games medal table Olympic Games year
    Then the records of the Olympic Games medal table information by Olympic Games medal table Olympic Games year is shown

  Scenario: Getting records of the Olympic Games medal table by non-existent Olympic Games medal table Olympic Games year
    Given a concrete Olympic Games medal table Olympic Games year 9999
    When we want to see the information of the records of the Olympic Games medal table by Olympic Games medal table Olympic Games year
    Then a not found error is shown for the records of the Olympic Games medal table

  Scenario: Getting records of the Olympic Games medal table by Olympic Games medal table Olympic Games place
    Given a concrete Olympic Games medal table Olympic Games place "Tokyo"
    When we want to see the information of the records of the Olympic Games medal table by Olympic Games medal table Olympic Games place
    Then the records of the Olympic Games medal table information by Olympic Games medal table Olympic Games place is shown

  Scenario: Getting records of the Olympic Games medal table by non-existent Olympic Games medal table Olympic Games place
    Given a concrete Olympic Games medal table Olympic Games place "Test"
    When we want to see the information of the records of the Olympic Games medal table by Olympic Games medal table Olympic Games place
    Then a not found error is shown for the records of the Olympic Games medal table

  Scenario: Getting records of the Olympic Games medal table by Olympic Games medal table sport identifier
    Given a concrete Olympic Games medal table sport identifier 1
    When we want to see the information of the records of the Olympic Games medal table by Olympic Games medal table sport identifier
    Then the records of the Olympic Games medal table information by Olympic Games medal table sport identifier is shown

  Scenario: Getting records of the Olympic Games medal table by non-existent Olympic Games medal table sport identifier
    Given a concrete Olympic Games medal table sport identifier 9999
    When we want to see the information of the records of the Olympic Games medal table by Olympic Games medal table sport identifier
    Then a not found error is shown for the records of the Olympic Games medal table

  Scenario: Getting records of the Olympic Games medal table by Olympic Games medal table sport name
    Given a concrete Olympic Games medal table sport name "Atletismo"
    When we want to see the information of the records of the Olympic Games medal table by Olympic Games medal table sport name
    Then the records of the Olympic Games medal table information by Olympic Games medal table sport name is shown

  Scenario: Getting records of the Olympic Games medal table by non-existent Olympic Games medal table sport name
    Given a concrete Olympic Games medal table sport name "Test"
    When we want to see the information of the records of the Olympic Games medal table by Olympic Games medal table sport name
    Then a not found error is shown for the records of the Olympic Games medal table

  Scenario: Getting records of the Olympic Games medal table by Olympic Games medal table sport category
    Given a concrete Olympic Games medal table sport category "100m Masculinos"
    When we want to see the information of the records of the Olympic Games medal table by Olympic Games medal table sport category
    Then the records of the Olympic Games medal table information by Olympic Games medal table sport category is shown

  Scenario: Getting records of the Olympic Games medal table by non-existent Olympic Games medal table sport category
    Given a concrete Olympic Games medal table sport category "Test"
    When we want to see the information of the records of the Olympic Games medal table by Olympic Games medal table sport category
    Then a not found error is shown for the records of the Olympic Games medal table

  Scenario: Getting records of the Olympic Games medal table by Olympic Games medal table metal identifier
    Given a concrete Olympic Games medal table metal identifier 1
    When we want to see the information of the records of the Olympic Games medal table by Olympic Games medal table metal identifier
    Then the records of the Olympic Games medal table information by Olympic Games medal table metal identifier is shown

  Scenario: Getting records of the Olympic Games medal table by non-existent Olympic Games medal table metal identifier
    Given a concrete Olympic Games medal table metal identifier 9999
    When we want to see the information of the records of the Olympic Games medal table by Olympic Games medal table metal identifier
    Then a not found error is shown for the records of the Olympic Games medal table

  Scenario: Getting records of the Olympic Games medal table by Olympic Games medal table metal type
    Given a concrete Olympic Games medal table metal type "Oro"
    When we want to see the information of the records of the Olympic Games medal table by Olympic Games medal table metal type
    Then the records of the Olympic Games medal table information by Olympic Games medal table metal type is shown

  Scenario: Getting records of the Olympic Games medal table by non-existent Olympic Games medal table metal type
    Given a concrete Olympic Games medal table metal type "Test"
    When we want to see the information of the records of the Olympic Games medal table by Olympic Games medal table metal type
    Then a not found error is shown for the records of the Olympic Games medal table

  Scenario: Getting records of the Olympic Games medal table by Olympic Games medal table athlete identifier
    Given a concrete Olympic Games medal table athlete identifier 1
    When we want to see the information of the records of the Olympic Games medal table by Olympic Games medal table athlete identifier
    Then the records of the Olympic Games medal table information by Olympic Games medal table athlete identifier is shown

  Scenario: Getting records of the Olympic Games medal table by non-existent Olympic Games medal table athlete identifier
    Given a concrete Olympic Games medal table athlete identifier 9999
    When we want to see the information of the records of the Olympic Games medal table by Olympic Games medal table athlete identifier
    Then a not found error is shown for the records of the Olympic Games medal table

  Scenario: Getting records of the Olympic Games medal table by Olympic Games medal table athlete name
    Given a list of records of the Olympic Games medal table athlete name "Usain"
    When we want to see the information of the records of the Olympic Games medal table by Olympic Games medal table athlete name
    Then the records of the Olympic Games medal table information by Olympic Games medal table athlete name is shown

  Scenario: Getting records of the Olympic Games medal table by non-existent Olympic Games medal table athlete name
    Given a list of records of the Olympic Games medal table athlete name "Test"
    When we want to see the information of the records of the Olympic Games medal table by Olympic Games medal table athlete name
    Then a not found error is shown for the records of the Olympic Games medal table

  Scenario: Getting records of the Olympic Games medal table by Olympic Games medal table athlete surname
    Given a list of records of the Olympic Games medal table athlete surname "Bolt"
    When we want to see the information of the records of the Olympic Games medal table by Olympic Games medal table athlete surname
    Then the records of the Olympic Games medal table information by Olympic Games medal table athlete surname is shown

  Scenario: Getting records of the Olympic Games medal table by non-existent Olympic Games medal table athlete surname
    Given a list of records of the Olympic Games medal table athlete surname "Test"
    When we want to see the information of the records of the Olympic Games medal table by Olympic Games medal table athlete surname
    Then a not found error is shown for the records of the Olympic Games medal table

  Scenario: Getting records of the Olympic Games medal table by Olympic Games medal table athlete country
    Given a list of records of the Olympic Games medal table athlete country "Jamaica"
    When we want to see the information of the records of the Olympic Games medal table by Olympic Games medal table athlete country
    Then the records of the Olympic Games medal table information by Olympic Games medal table athlete country is shown

  Scenario: Getting records of the Olympic Games medal table by non-existent Olympic Games medal table athlete country
    Given a list of records of the Olympic Games medal table athlete country "Test"
    When we want to see the information of the records of the Olympic Games medal table by Olympic Games medal table athlete country
    Then a not found error is shown for the records of the Olympic Games medal table

  Scenario: Adding a new record of the Olympic Games medal table
    Given a new record of the Olympic Games medal table with Olympic Games medal table Olympic Games identifier 2 Olympic Games medal table sport identifier 6 Olympic Games medal table metal identifier 1 and Olympic Games medal table athlete identifier 14
    When we want to add this new record of the Olympic Games medal table
    Then the record of the Olympic Games medal table is correctly added and its information is shown

  Scenario: Adding a new record of the Olympic Games medal table with a non-existent Olympic Games medal table Olympic Games identifier
    Given a new record of the Olympic Games medal table with Olympic Games medal table Olympic Games identifier 9999 Olympic Games medal table sport identifier 1 Olympic Games medal table metal identifier 1 and Olympic Games medal table athlete identifier 13
    When we want to add this new record of the Olympic Games medal table
    Then a not found error is shown for the records of the Olympic Games medal table

  Scenario: Updating an existing record of the Olympic Games medal table
    Given a concrete Olympic Games medal table identifier 9
    When we want to update Olympic Games medal table athlete identifier 13
    Then the record of the Olympic Games medal table is correctly updated and its information is shown

  Scenario: Updating an existing record of the Olympic Games medal table with non-existent data
    Given a concrete Olympic Games medal table identifier 12
    When we want to update Olympic Games medal table Olympic Games identifier 9999 Olympic Games medal table sport identifier 9999 Olympic Games medal table metal identifier 9999 and Olympic Games medal table athlete identifier 9999
    Then a not found error is shown for the records of the Olympic Games medal table

  Scenario: Updating a non-existent record of the Olympic Games medal table
    Given a concrete Olympic Games medal table identifier 9999
    When we want to update Olympic Games medal table Olympic Games identifier 7 Olympic Games medal table sport identifier 1 Olympic Games medal table metal identifier 3 and Olympic Games medal table athlete identifier 10
    Then a not found error is shown for the records of the Olympic Games medal table

  Scenario: Deleting an exiting record of the Olympic Games medal table
    Given a concrete Olympic Games medal table identifier 11
    When we want to delete this record of the Olympic Games medal table
    Then the record of the Olympic Games medal table is correctly deleted

  Scenario: Deleting a non-existent record of the Olympic Games medal table
    Given a concrete Olympic Games medal table identifier 9999
    When we want to delete this record of the Olympic Games medal table
    Then a not found error is shown for the records of the Olympic Games medal table