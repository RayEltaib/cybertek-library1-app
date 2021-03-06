Feature: Show records functionality


  @Tc_4 @librarians
  Scenario: Verify default values in users page
    Given I am on the login page
    When I login as a Librarian
    When I click on "Users" link
    Then show records default value should be 10
    And show records should have following options:
    | 5   |
    | 15  |
    | 15  |
    | 50  |
    | 100 |
    | 200 |
    | 500 |




