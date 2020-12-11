package com.cybertek.library.step_definitions;

import com.cybertek.library.pages.DashBoardPage;
import com.cybertek.library.utilities.common.Environment;
import com.cybertek.library.utilities.db.DBUtils;
import com.cybertek.library.utilities.ui.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;




public class Hooks {

    DashBoardPage dashBoardPage = new DashBoardPage();


    @Before(value = "@db")
    public void connectDataBase() {
        DBUtils.createConnection();
    }

    @After(value = "@db")
    public void closeConnection(){
        DBUtils.destroy();
    }

    @Before
    public void setUp(Scenario scenario){
        if(scenario.isFailed()) {
            Driver.getDriver().get(Environment.getProperty("url"));
        }
    }

    @After
    public void tearDownScenario(Scenario scenario) {

       if(scenario.getName().contains("Terminate")) {
           System.out.println("<<< Driver Terminated >>>");
           Driver.closeDriver();
       }else {

           if (scenario.isFailed()) {
               byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
               scenario.attach(screenshot, "image/png", "Screenshot " + scenario.getName());
               Driver.closeDriver();
//            An Attempt on taking gif images
//            GifWebDriver gifDriver =(GifWebDriver)Driver.getDriver();
//            byte[] gif = ((TakesScreenshot) gifDriver).getScreenshotAs(OutputType.BYTES);
//            scenario.attach(gif, "image/gif", scenario.getName());
               System.out.printf("Test Failed %s " +
                       "\n 1.check your locator" +
                       "\n 2.check synchronization" +
                       "\n 3.check the screenshots", scenario.getName());
               System.out.println("\n___________________________________");
           }else {
               System.out.printf("Test Passed %s", scenario.getName());
               System.out.println("\n___________________________________");
           }
           //instead of closing the driver after every test it's saves more time to log out
           //Driver.closeDriver();
           dashBoardPage.logOut();

       }
    }





}
