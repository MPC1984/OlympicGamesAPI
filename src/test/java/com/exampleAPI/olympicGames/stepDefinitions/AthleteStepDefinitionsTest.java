package com.exampleAPI.olympicGames.stepDefinitions;

import com.exampleAPI.olympicGames.runners.CucumberSpringConfiguration;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static io.restassured.RestAssured.given;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
public class AthleteStepDefinitionsTest extends CucumberSpringConfiguration {

    private RequestSpecification request;
    private Response response;
    private Map<String, Object> responseInformation;
    private List<Map<String, Object>> allMetals;
    private Map<String, String> newMetal, newMetalToUpdate;
    private Map<String, Object> randomMetal, metalToUpdate;
    private Long randomId, idToUpdate;
    private String randomMetalType;

    @Before
    public void dataGeneration() {
        request = given().header("Content-Type", "application/json");
        response = request.when().get("http://localhost:8000/olympicGames/metal");
        allMetals = JsonPath.from(response.asString()).get();
        //Obtenemos un metal de la lista de manera aleatoria para realizar comprobaciones en los diferentes endpoints
        randomMetal = allMetals.get((int) (Math.random() * allMetals.size()));
        for (Map.Entry<String, Object> entry : randomMetal.entrySet()) {
            if(entry.getKey().equals("id")){
                randomId = Long.parseLong(entry.getValue().toString());
            } else {
                randomMetalType = (String) entry.getValue();
            }
        }
    }

    @Given("A list of empty metals")
    public void a_list_of_empty_metals() {
        request = given().header("Content-Type", "application/json");
    }

    @When("we want to see all metals information when the list is empty")
    public void we_want_to_see_all_metals_information_when_the_list_is_empty() {
        response = request.when().get("http://localhost:8000/olympicGames/metal");
    }

    @Then("no information is shown for the empty list and an error is shown")
    public void no_information_is_shown_for_the_empty_list_and_an_error_is_shown() {
        Assert.assertEquals(404, response.getStatusCode());
    }

}
