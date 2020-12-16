package com.cybertek.library.step_definitions;

import static com.cybertek.library.utilities.ui.BrowserUtils.*;

import com.cybertek.library.pages.DashBoardPage;
import com.cybertek.library.pages.UsersPage;
import com.cybertek.library.utilities.ui.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.Assert.*;


public class PageNavigation_StepDefinitions extends BaseStep {
    UsersPage usersPage = new UsersPage();
    DashBoardPage dashBoardPage = new DashBoardPage();
    Select select ;



    @When("I click on {string} link")
    public void i_click_on_link(String link) {
        dashBoardPage.navigateTo(link);
    }


    @Then("show records default value should be {int}")
    public void show_records_default_value_should_be(Integer int1) {

        waitTillClickable(usersPage.showRecords, 5);
        Integer usersPerPage = Integer.parseInt(getDropDownSelection(usersPage.showRecords));
        assertEquals(int1,usersPerPage);

    }

    @And("show records should have following options:")
    public void show_records_should_have_following_options(List<String> options) {

        List<String> dropDownOptions = getDropDownOptions(usersPage.showRecords);
        usersPage.showRecords.click();

        assertEquals(options, dropDownOptions);


    }

    @Then("I close the browser")
    public void i_close_the_browser() {
        Driver.closeDriver();
    }
}
