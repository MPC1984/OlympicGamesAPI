package com.exampleAPI.olympicGames.stepsDefinition;

import com.exampleAPI.olympicGames.runners.CucumberSpringConfiguration;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

//@RunWith(SpringRunner.class)
public class SportStepsDefinitionTest extends CucumberSpringConfiguration {

    private RequestSpecification request;
    private Response response;
    private Map<String, Object> uniqueResponseInformation;
    private List<Map<String, Object>> allSports, responseInformation;
    private Map<String, String> newSport, newSportToUpdate;
    private Map<String, Object> randomSport, sportToUpdate;
    private Long randomId, idToUpdate;
    private String randomSportName, randomSportCategory;

    @Before
    public void dataGeneration() {
        request = given().header("Content-Type", "application/json");
        response = request.when().get("http://localhost:8000/olympicGames/sport");
        allSports = JsonPath.from(response.asString()).get();
        //Obtenemos un deporte de la lista de manera aleatoria para realizar comprobaciones en los diferentes endpoints
        randomSport = allSports.get((int) (Math.random() * allSports.size()));
        for (Map.Entry<String, Object> entry : randomSport.entrySet()) {
            switch (entry.getKey()) {
                case "id":
                    randomId = Long.parseLong(entry.getValue().toString());
                case "sportName":
                    randomSportName = entry.getValue().toString();
                default:
                    if (entry.getValue() != null) {
                        randomSportCategory = entry.getValue().toString();
                    } else {
                        randomSportCategory = null;
                    }
            }
        }
        //Obtenemos el deporte de la lista que se ha añadido durante la ejecución de las pruebas
        for (Map<String, Object> sport : allSports) {
            for (Map.Entry<String, Object> entry : sport.entrySet()) {
                if (entry.getKey().equals("sportName") && entry.getValue().equals("sport_name")) {
                    sportToUpdate = sport;
                    break;
                }
            }
        }
        if (sportToUpdate != null) {
            for (Map.Entry<String, Object> entry : sportToUpdate.entrySet()) {
                if (entry.getKey().equals("id")) {
                    idToUpdate = Long.parseLong(entry.getValue().toString());
                }
            }
        }
        //Obtenemos el deporte de la lista que se ha modificado durante la ejecución de las pruebas
        for (Map<String, Object> sport : allSports) {
            for (Map.Entry<String, Object> entry : sport.entrySet()) {
                if (entry.getKey().equals("sportName") && entry.getValue().equals("sn_updated")) {
                    sportToUpdate = sport;
                    break;
                }
            }
        }
        if (sportToUpdate != null) {
            for (Map.Entry<String, Object> entry : sportToUpdate.entrySet()) {
                if (entry.getKey().equals("id")) {
                    idToUpdate = Long.parseLong(entry.getValue().toString());
                }
            }
        }
    }

    @After
    public void deleteDataGeneration() {
        if (uniqueResponseInformation != null) {
            uniqueResponseInformation.clear();
        }
        if (allSports != null) {
            allSports.clear();
        }
        if (responseInformation != null) {
            responseInformation.clear();
        }
        if (newSport != null) {
            newSport.clear();
        }
        if (newSportToUpdate != null) {
            newSportToUpdate.clear();
        }
        if (randomSport != null) {
            randomSport.clear();
        }
        if (sportToUpdate != null) {
            sportToUpdate.clear();
        }
    }

    @Given("a list of sports")
    public void a_list_of_sports() {

    }

    @Given("a new sport with name {string} and category {string}")
    public void a_new_sport_with_name_and_category(String sportName, String sportCategory) {

    }

    @Given("a new sport without name")
    public void a_new_sport_without_name() {

    }

    @Given("an existing sport")
    public void an_existing_sport() {

    }

    @Given("a non-existent sport")
    public void a_non_existent_sport() {

    }

    @When("we want to see all sports information")
    public void we_want_to_see_all_sports_information() {

    }

    @When("we want to see the information of a concrete sport by its identifier")
    public void we_want_to_see_the_information_of_a_concrete_sport_by_its_identifier() {

    }

    @When("we want to see the information of a concrete sport by a non-existent identifier")
    public void we_want_to_see_the_information_of_a_concrete_sport_by_a_non_existent_identifier() {

    }

    @When("we want to see all sports information by their name")
    public void we_want_to_see_all_sports_information_by_their_name() {

    }

    @When("we want to see all sports information by a non-existent name")
    public void we_want_to_see_all_sports_information_by_a_non_existent_name() {

    }

    @When("we want to see all sports information by their category")
    public void we_want_to_see_all_sports_information_by_their_category() {

    }

    @When("we want to see all sports information by a non-existent category")
    public void we_want_to_see_all_sports_information_by_a_non_existent_category() {

    }

    @When("we want to add this new sport")
    public void we_want_to_add_this_new_sport() {

    }

    @When("we want to update its name to {string} and its category to {string}")
    public void we_want_to_update_its_name_to_and_its_category_to(String sportName, String sportCategory) {

    }

    @When("we want to delete this sport")
    public void we_want_to_delete_this_sport() {

    }

    @Then("all sports information is shown")
    public void all_sports_information_is_shown() {

    }

    @Then("the sport information is shown")
    public void the_sport_information_is_shown() {

    }

    @Then("no sport information is shown and a not found error is shown")
    public void no_sport_information_is_shown_and_a_not_found_error_is_shown() {

    }

    @Then("no sports information is shown and a not found error is shown")
    public void no_sports_information_is_shown_and_a_not_found_error_is_shown() {

    }

    @Then("the sport is correctly added and its information is shown")
    public void the_sport_is_correctly_added_and_its_information_is_shown() {

    }

    @Then("a bad request error is shown for the sport")
    public void a_bad_request_error_is_shown_for_the_sport() {

    }

    @Then("the sport is correctly updated and its information is shown")
    public void the_sport_is_correctly_updated_and_its_information_is_shown() {

    }

    @Then("the sport is correctly deleted")
    public void the_sport_is_correctly_deleted() {

    }

}