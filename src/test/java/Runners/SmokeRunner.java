package Runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        //features we use to provide the path of all the feature files
        features = "src/test/resources/features/", //one path for all features files
        glue = "Steps",
        //dryRun = true it stops actual execution; it will quickly scan all the gherkin steps whether they are implemented or not
        //it is temporary solution

        dryRun = false, //false - start execution again
        //tags="@sprint3 or @sprint1",
        tags = "@tc1101",
        //to remove irrelevant information from console, you need to set monochrome to true
        monochrome = true,
        //to generate the report we need plugin of runner class

        plugin = {"pretty", "html:target/cucumber.html", "json:target/cucumber.json", "rerun:target/failed.txt"} //pretty keywords prints the steps in console to increase readability (more info)
       //failed.txt file holds all the scenarios which are failed during the execution

)
public class SmokeRunner {

}
