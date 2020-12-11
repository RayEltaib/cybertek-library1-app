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
        features = "src/test/resources/features",
        glue = "com/cybertek/library/step_definitions",
        tags ="@Tc_6",
        publish = true,
        //CUCUMBER_PUBLISH_TOKEN=c4567dcc-3f62-4bf1-be19-0501548c81f7
        plugin = {
                "pretty",
                "rerun:target/default-reports/rerun.txt",
                "json:target/default-reports/cucumber.json"
//                "timeline:target/default-reports/timeline-report"
//                "html:target/default-reports/default-html-reports.html"
        }
)
public class CukesRunner {

}
