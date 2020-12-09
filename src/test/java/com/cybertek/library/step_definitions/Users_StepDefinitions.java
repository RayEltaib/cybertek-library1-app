package com.cybertek.library.step_definitions;

import static com.cybertek.library.utilities.ui.BrowserUtils.*;

import com.cybertek.library.pages.DashBoardPage;
import com.cybertek.library.pages.UsersPage;
import io.cucumber.java.en.Then;

import static org.junit.Assert.*;

import java.util.List;

public class Users_StepDefinitions {
    DashBoardPage dashBoardPage = new DashBoardPage();
    UsersPage usersPage = new UsersPage();

    @Then("table should have following column names:")
    public void table_should_have_following_column_names(List<String> names) {
        waitForVisibility(usersPage.showRecords,20);
        List<String> tableHeaders = getElementsText(usersPage.usersTableHeaders);

            assertEquals(names, tableHeaders);




    }
}
