Feature: Login with parameters

  @Tc_2
  Scenario: Login as librarian 11
    Given I am on the login page
    When I enter username "librarian11@library"
    And I enter password "I61FFPPf"
    And click the sign in button
    Then "dashboard" should be displayed

  @Tc_2
  Scenario: Login as librarian 12
    Given I am on the login page
    When I enter username "librarian12@library"
    And I enter password "AOYKYTMJ"
    And click the sign in button
    And there should be 2362 users
    Then "dashboard" should be displayed
    @Tc_3
  Scenario: Login as librarian same line
    Given I am on the login page
    When I login using "librarian11@library" and "I61FFPPf"
    And there should be 2362 users
    Then "dashboard" should be displayed
