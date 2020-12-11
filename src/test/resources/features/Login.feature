@Tc_1
Feature: Login
  As a user, I should be able to login

   @librarians
  Scenario: Login as a librarian
    Given I am on the login page
    When I login as a Librarian
    Then "dashboard" should be displayed
  @students
  Scenario: Login as a student
    Given I am on the login page
    When I login as a student
    Then "books" should be displayed

  @smoke @regression
  Scenario: Terminate Browser
    Then I close the browser