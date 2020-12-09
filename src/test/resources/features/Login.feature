Feature: Login
  As a user, I should be able to login
  Background: User is on the right url
    Given I am on the login page

  @Tc_1 @librarians
  Scenario: Login as a librarian
    Given I am on the login page
    When I login as a Librarian
    Then "dashboard" should be displayed
  @Tc_1 @students
  Scenario: Login as a student
    Given I am on the login page
    When I login as a student
    Then "books" should be displayed