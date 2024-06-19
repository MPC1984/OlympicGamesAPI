package com.exampleAPI.olympicGames.stepsDefinition;

import com.exampleAPI.olympicGames.runners.CucumberSpringConfiguration;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

//@RunWith(SpringRunner.class)
public class AthleteStepsDefinitionTest  extends CucumberSpringConfiguration {

    private RequestSpecification request;
    private Response response;
    private Map<String, Object> responseInformation;
    private List<Map<String, Object>> allAthletes;
    private Map<String, String> newAthlete, newAthleteToUpdate;
    private Map<String, Object> randomAthlete, athleteToUpdate;
    private Long randomId, idToUpdate;
    private String randomAthleteName, randomAthleteSurname, randomAthleteCountry;

    @Before
    public void dataGeneration() {
        request = given().header("Content-Type", "application/json");
        response = request.when().get("http://localhost:8000/olympicGames/athlete");
        allAthletes = JsonPath.from(response.asString()).get();
        //Obtenemos un atleta de la lista de manera aleatoria para realizar comprobaciones en los diferentes endpoints
        randomAthlete = allAthletes.get((int) (Math.random() * allAthletes.size()));
        for (Map.Entry<String, Object> entry : randomAthlete.entrySet()) {
            switch (entry.getKey()) {
                case "id" :
                    randomId = Long.parseLong(entry.getValue().toString());
                case "athleteName" :
                    randomAthleteName = entry.getValue().toString();
                case "athleteSurname" :
                    randomAthleteSurname = entry.getValue().toString();
                default :
                    randomAthleteCountry = entry.getValue().toString();
            }
        }
    }

    @Given("A list of athletes")
    public void a_list_of_athletes() {
        request = given().header("Content-Type", "application/json");
    }

    @When("we want to see all athletes information")
    public void we_want_to_see_all_athletes_information() {
        response = request.when().get("http://localhost:8000/olympicGames/athlete");
    }

    @Then("all athletes information is shown")
    public void all_athletes_information_is_shown() {
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertFalse(allAthletes.isEmpty());
    }

}