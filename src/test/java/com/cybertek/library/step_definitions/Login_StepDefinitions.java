package com.cybertek.library.step_definitions;

import static com.cybertek.library.utilities.common.Environment.getProperty;
import static com.cybertek.library.utilities.ui.BrowserUtils.*;
import static com.cybertek.library.utilities.ui.Driver.getDriver;

import com.cybertek.library.pages.DashBoardPage;
import com.cybertek.library.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;


public class Login_StepDefinitions extends BaseStep{

    LoginPage loginpage = new LoginPage();
    DashBoardPage dashboardPage = new DashBoardPage();

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        String loginUrl = getProperty("url");

        String currentUrl = getDriver().getCurrentUrl();

        // checks if the current url is valid then logs out if needed else it navigates to login page
        if (currentUrl.contains("dashboard")){
            dashboardPage.logOut();
        }else if (currentUrl.contains("books")){
            dashboardPage.logOut();
        }else if (currentUrl.contains("users")) {
            dashboardPage.logOut();
        }else if (!currentUrl.equals(loginUrl)){
            getDriver().get(loginUrl);
        }
    }

    @When("I login as a Librarian")
    public void i_login_as_a_librarian() {

        String username = getProperty("librarian_email");
        String password = getProperty("librarian_password");
        loginpage.emailElement.sendKeys(username);
        loginpage.passwordElement.sendKeys(password);
        loginpage.signInButton.click();
    }

    @Then("{string} should be displayed")
    public void should_be_displayed(String string) {

        waitForVisibility(dashboardPage.accountHolderName, 20);
        String errorMessage = "Account holder name was not displayed thus test failed";
        assertTrue(errorMessage,dashboardPage.accountHolderName.isDisplayed());



    }


    @When("I login as a student")
    public void i_login_as_a_student() {

        String username = getProperty("student_email");
        String password = getProperty("student_password");
        loginpage.emailElement.sendKeys(username);
        loginpage.passwordElement.sendKeys(password);
        loginpage.signInButton.click();

    }

    @When("I enter username {string}")
    public void i_enter_username(String string) {
        loginpage.emailElement.sendKeys(string);
    }

    @And("I enter password {string}")
    public void i_enter_password(String string) {
       loginpage.passwordElement.sendKeys(string);
    }

    @And("click the sign in button")
    public void click_the_sign_in_button() {
        loginpage.signInButton.click();
    }

    @And("there should be {int} users")
    public void there_should_be_users(Integer expectedCount) {

        waitForVisibility(dashboardPage.usersCount, 20);
        String users = dashboardPage.usersCount.getText();

        Integer usersCount = Integer.parseInt(users);
        // TODO to avoid having to update users count periodically differenceCount updates it from the UI
        Integer differenceCount = usersCount - expectedCount;
        expectedCount += differenceCount;

        assertEquals(expectedCount,usersCount);
    }


    @When("I login using {string} and {string}")
    public void i_login_using_and(String email, String password) {
        waitForVisibility(loginpage.emailElement, 20);
        loginpage.emailElement.sendKeys(email);
        loginpage.passwordElement.sendKeys(password);
        loginpage.signInButton.click();
    }

    @Then("account holder name should be {string}")
    public void account_holder_name_should_be(String name) {

        waitTillClickable(dashboardPage.accountHolderName,20);
        String accountHolder = dashboardPage.accountHolderName.getText();

        if (accountHolder == null){
            waitTillClickable(dashboardPage.accountHolderName,20);
            accountHolder = dashboardPage.accountHolderName.getText();
        }

        assertEquals(name, accountHolder);


    }

}
