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
public class OlympicGamesStepsDefinitionTest extends CucumberSpringConfiguration {

    private RequestSpecification request;
    private Response response;
    private Map<String, Object> uniqueResponseInformation;
    private List<Map<String, Object>> allOlympicGames, responseInformation;
    private Map<String, String> newOlympicGames, newOlympicGamesToUpdate;
    private Map<String, Object> randomOlympicGames, olympicGamesToUpdate;
    private Long randomId, idToUpdate;
    private String randomOlympicGamesYear, randomOlympicGamesPlace;

    @Before
    public void dataGeneration() {
        request = given().header("Content-Type", "application/json");
        response = request.when().get("http://localhost:8000/olympicGames");
        allOlympicGames = JsonPath.from(response.asString()).get();
        //Obtenemos unos Juegos Olímpicos de la lista de manera aleatoria para realizar comprobaciones en los diferentes endpoints
        randomOlympicGames = allOlympicGames.get((int) (Math.random() * allOlympicGames.size()));
        for (Map.Entry<String, Object> entry : randomOlympicGames.entrySet()) {
            switch (entry.getKey()) {
                case "id":
                    randomId = Long.parseLong(entry.getValue().toString());
                case "olympicGamesYear":
                    randomOlympicGamesYear = entry.getValue().toString();
                default:
                    randomOlympicGamesPlace = entry.getValue().toString();
            }
        }
        //Obtenemos los Juegos Olímpicos de la lista que se han añadido durante la ejecución de las pruebas
        for (Map<String, Object> olympicGames : allOlympicGames) {
            for (Map.Entry<String, Object> entry : olympicGames.entrySet()) {
                if (entry.getKey().equals("olympicGamesPlace") && entry.getValue().equals("olympicGames_place")) {
                    olympicGamesToUpdate = olympicGames;
                    break;
                }
            }
        }
        if (olympicGamesToUpdate != null) {
            for (Map.Entry<String, Object> entry : olympicGamesToUpdate.entrySet()) {
                if (entry.getKey().equals("id")) {
                    idToUpdate = Long.parseLong(entry.getValue().toString());
                }
            }
        }
        //Obtenemos los Juegos Olímpicos de la lista que se han modificado durante la ejecución de las pruebas
        for (Map<String, Object> olympicGames : allOlympicGames) {
            for (Map.Entry<String, Object> entry : olympicGames.entrySet()) {
                if (entry.getKey().equals("olympicGamesPlace") && entry.getValue().equals("ogp_updated")) {
                    olympicGamesToUpdate = olympicGames;
                    break;
                }
            }
        }
        if (olympicGamesToUpdate != null) {
            for (Map.Entry<String, Object> entry : olympicGamesToUpdate.entrySet()) {
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
        if (allOlympicGames != null) {
            allOlympicGames.clear();
        }
        if (responseInformation != null) {
            responseInformation.clear();
        }
        if (newOlympicGames != null) {
            newOlympicGames.clear();
        }
        if (newOlympicGamesToUpdate != null) {
            newOlympicGamesToUpdate.clear();
        }
        if (randomOlympicGames != null) {
            randomOlympicGames.clear();
        }
        if (olympicGamesToUpdate != null) {
            olympicGamesToUpdate.clear();
        }
    }

    @Given("a list of Olympic Games")
    public void a_list_of_olympic_games() {

    }

    @Given("a new Olympic Games with year {int} and place {string}")
    public void a_new_olympic_games_with_year_and_place(Integer olympicGamesYear, String olympicGamesPlace) {

    }

    @Given("a non-existent Olympic Games")
    public void a_non_existent_olympic_games() {

    }

    @Given("a new Olympic Games without place")
    public void a_new_olympic_games_without_place() {

    }

    @Given("a new Olympic Games with year {int}")
    public void a_new_olympic_games_with_year(Integer olympicGamesYear) {

    }

    @Given("an existing Olympic Games")
    public void an_existing_olympic_games() {

    }

    @When("we want to see all Olympic Games information")
    public void we_want_to_see_all_olympic_games_information() {

    }

    @When("we want to see the information of a concrete Olympic Games by its identifier")
    public void we_want_to_see_the_information_of_a_concrete_olympic_games_by_its_identifier() {

    }

    @When("we want to see the information of a concrete Olympic Games by a non-existent identifier")
    public void we_want_to_see_the_information_of_a_concrete_olympic_games_by_a_non_existent_identifier() {

    }

    @When("we want to see the information of a concrete Olympic Games by its year")
    public void we_want_to_see_the_information_of_a_concrete_olympic_games_by_its_year() {

    }

    @When("we want to see the information of a concrete Olympic Games by a non-existent year")
    public void we_want_to_see_the_information_of_a_concrete_olympic_games_by_a_non_existent_year() {

    }

    @When("we want to see all Olympic Games information by their place")
    public void we_want_to_see_all_olympic_games_information_by_their_place() {

    }

    @When("we want to see all Olympic Games information by a non-existent place")
    public void we_want_to_see_all_olympic_games_information_by_a_non_existent_place() {

    }

    @When("we want to add this new Olympic Games")
    public void we_want_to_add_this_new_olympic_games() {

    }

    @When("we want to update its year to {int} and its place to {string}")
    public void we_want_to_update_its_year_to_and_its_place_to(Integer olympicGamesYear, String olympicGamesPlace) {

    }

    @When("we want to update its year to {int}")
    public void we_want_to_update_its_year_to(Integer olympicGamesYear) {

    }

    @When("we want to delete this Olympic Games")
    public void we_want_to_delete_this_olympic_games() {

    }

    @Then("all Olympic Games information is shown")
    public void all_olympic_games_information_is_shown() {

    }

    @Then("the Olympic Games information is shown")
    public void the_olympic_games_information_is_shown() {

    }

    @Then("no Olympic Games information is shown and a not found error is shown")
    public void no_olympic_games_information_is_shown_and_a_not_found_error_is_shown() {

    }

    @Then("the Olympic Games is correctly added and its information is shown")
    public void the_olympic_games_is_correctly_added_and_its_information_is_shown() {

    }

    @Then("a bad request error is shown for the Olympic Games")
    public void a_bad_request_error_is_shown_for_the_olympic_games() {

    }

    @Then("no Olympic Games information is shown and a duplicated information error is shown")
    public void no_olympic_games_information_is_shown_and_a_duplicated_information_error_is_shown() {

    }

    @Then("the Olympic Games is correctly updated and its information is shown")
    public void the_olympic_games_is_correctly_updated_and_its_information_is_shown() {

    }

    @Then("the Olympic Games is correctly deleted")
    public void the_olympic_games_is_correctly_deleted() {

    }

}