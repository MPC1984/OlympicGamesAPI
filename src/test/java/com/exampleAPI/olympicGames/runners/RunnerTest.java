package com.exampleAPI.olympicGames.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

//Clase que permite que se ejecuten los tests de Cucumber de la aplicación (indicando dónde se van a dejar los reportes con los resultados de dichos tests)
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/default-cucumber-reports.html",
                "json:target/json-reports/cucumber.json",
                "junit:target/xml-report/cucumber.xml",
        },
        features = "./src/test/resources/features",
        glue = {"com/exampleAPI/olympicGames/stepsDefinition", "com/exampleAPI/olympicGames/runners"}
)
public class RunnerTest {
}