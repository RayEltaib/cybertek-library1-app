package com.cybertek.library.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com/cybertek/library/step_definitions",
        publish = true,
        tags = "@Tc_1 or @Tc_2 or @Tc_3 or @Tc_4 or @Tc_5",
        plugin = {
                "pretty",
                "json:target/default-reports/cucumber2.json"
        }
)
public class CukesRunner2 {
}
