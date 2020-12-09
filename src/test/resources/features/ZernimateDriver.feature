Feature: Terminate Driver
  The main use of this feature is to close the browser  after the testing is done
  In other words it works as @AfterAll hook

  @smoke @regression
  Scenario: Terminate Browser
    Then I close the browser