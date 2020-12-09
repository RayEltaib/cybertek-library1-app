Feature: Table columns names

#  Background: User is on the right url
#    Given I am on the login page

  @Tc_5 @librarians
  Scenario: Table columns names
    Given I am on the login page
    When I login as a Librarian
    And I click on "Users" link
    Then table should have following column names:
      | Actions    |
      | User ID    |
      | Full Name  |
      | Email      |
      | Group      |
      | Status     |