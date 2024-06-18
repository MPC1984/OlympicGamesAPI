package com.exampleAPI.olympicGames.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * RunnerTest class
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/default-cucumber-reports.html",
                "json:target/json-reports/cucumber.json",
                "junit:target/xml-report/cucumber.xml",
        },
        features = "./src/test/resources/features",
        glue = {"com/exampleAPI/olympicGames/stepDefinitions", "com/exampleAPI/olympicGames/runners"}
)
public class RunnerTest {
}
