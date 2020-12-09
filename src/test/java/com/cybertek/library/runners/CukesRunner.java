package com.cybertek.library.runners;

import com.cybertek.library.utilities.common.Environment;
import com.cybertek.library.utilities.ui.Driver;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        features = "src/test/resources/features",
        glue = "com/cybertek/library/step_definitions",
        tags ="",
        publish = true,
        //CUCUMBER_PUBLISH_TOKEN=c4567dcc-3f62-4bf1-be19-0501548c81f7
        plugin = {
                "pretty",
                "rerun:target/default-reports/rerun.txt",
                "timeline:target/default-reports/timeline-report",
                "json:target/default-reports/cucumber.json",
                "html:target/default-reports/default-html-reports.html"
        }
)
public class CukesRunner {



    @BeforeClass
    public static void startApplication(){
        System.out.println(" .:: Application loading  ::.");
        Driver.getDriver().get(Environment.getProperty("url"));
    }

   // @AfterClass
//    public static void terminateDriver(){
//        System.out.println("   .:: End of Testing closing the browser!!! ::.");
//        Driver.closeDriver();
//    }
}
