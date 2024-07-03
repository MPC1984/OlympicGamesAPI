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
import org.junit.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

//@RunWith(SpringRunner.class)
public class OlympicGamesStepsDefinitionTest extends CucumberSpringConfiguration {

    private RequestSpecification request;
    private Response response;
    private Map<String, Object> uniqueResponseInformation, newOlympicGames, newOlympicGamesToUpdate, randomOlympicGames, olympicGamesToUpdate;
    private List<Map<String, Object>> allOlympicGames, responseInformation;
    private Long randomId, idToUpdateDelete;
    private Integer randomOlympicGamesYear;
    private String randomOlympicGamesPlace;

    @Before
    public void dataGeneration() {
        request = given().header("Content-Type", "application/json");
        response = request.when().get("http://localhost:8000/olympicGames");
        allOlympicGames = JsonPath.from(response.asString()).get();
        //Obtenemos unos Juegos Olímpicos de la lista de manera aleatoria para realizar comprobaciones en los diferentes endpoints
        randomOlympicGames = allOlympicGames.get((int) (Math.random() * allOlympicGames.size()));
        for (Map.Entry<String, Object> entry : randomOlympicGames.entrySet()) {
            switch (entry.getKey()) {
                case "olympicGamesId":
                    randomId = Long.parseLong(entry.getValue().toString());
                case "olympicGamesYear":
                    randomOlympicGamesYear = Integer.parseInt(entry.getValue().toString());
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
                if (entry.getKey().equals("olympicGamesId")) {
                    idToUpdateDelete = Long.parseLong(entry.getValue().toString());
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
                if (entry.getKey().equals("olympicGamesId")) {
                    idToUpdateDelete = Long.parseLong(entry.getValue().toString());
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
        request = given().header("Content-Type", "application/json");
    }

//    @Given("a list of empty Olympic Games")
//    public void a_list_of_empty_olympic_games() {
//        request = given().header("Content-Type", "application/json");
//    }

    @Given("a new Olympic Games with year {int} and place {string}")
    public void a_new_olympic_games_with_year_and_place(Integer olympicGamesYear, String olympicGamesPlace) {
        newOlympicGames = new HashMap<>();
        newOlympicGames.put("olympicGamesYear", olympicGamesYear);
        newOlympicGames.put("olympicGamesPlace", olympicGamesPlace);
        request = given().header("Content-Type", "application/json");
    }

    @Given("a new Olympic Games without place")
    public void a_new_olympic_games_without_place() {
        newOlympicGames = new HashMap<>();
        newOlympicGames.put("olympicGamesYear", 2222);
        newOlympicGames.put("olympicGamesPlace", null);
        request = given().header("Content-Type", "application/json");
    }

    @Given("a new Olympic Games with year {int}")
    public void a_new_olympic_games_with_year(Integer olympicGamesYear) {
        newOlympicGames = new HashMap<>();
        newOlympicGames.put("olympicGamesYear", olympicGamesYear);
        newOlympicGames.put("olympicGamesPlace", "olympicGames_place");
        request = given().header("Content-Type", "application/json");
    }

    @Given("an existing Olympic Games")
    public void an_existing_olympic_games() {
        request = given().header("Content-Type", "application/json");
    }

    @Given("a non-existent Olympic Games")
    public void a_non_existent_olympic_games() {
        idToUpdateDelete = 9999L;
        request = given().header("Content-Type", "application/json");
    }

    @When("we want to see all Olympic Games information")
    public void we_want_to_see_all_olympic_games_information() {
        response = request.when().get("http://localhost:8000/olympicGames");
        responseInformation = JsonPath.from(response.asString()).get();
    }

//    @When("we want to see all Olympic Games information when the list is empty")
//    public void we_want_to_see_all_olympic_games_information_when_the_list_is_empty() {
//        response = request.when().get("http://localhost:8000/olympicGames");
//    }

    @When("we want to see the information of a concrete Olympic Games by its identifier")
    public void we_want_to_see_the_information_of_a_concrete_olympic_games_by_its_identifier() {
        response = request.when().get("http://localhost:8000/olympicGames/id=" + randomId);
        uniqueResponseInformation = JsonPath.from(response.asString()).get();
    }

    @When("we want to see the information of a concrete Olympic Games by a non-existent identifier")
    public void we_want_to_see_the_information_of_a_concrete_olympic_games_by_a_non_existent_identifier() {
        response = request.when().get("http://localhost:8000/olympicGames/id=9999");
    }

    @When("we want to see the information of a concrete Olympic Games by its year")
    public void we_want_to_see_the_information_of_a_concrete_olympic_games_by_its_year() {
        response = request.when().get("http://localhost:8000/olympicGames/year=" + randomOlympicGamesYear);
        uniqueResponseInformation = JsonPath.from(response.asString()).get();
    }

    @When("we want to see the information of a concrete Olympic Games by a non-existent year")
    public void we_want_to_see_the_information_of_a_concrete_olympic_games_by_a_non_existent_year() {
        response = request.when().get("http://localhost:8000/olympicGames/year=9999");
    }

    @When("we want to see all Olympic Games information by their place")
    public void we_want_to_see_all_olympic_games_information_by_their_place() {
        response = request.when().get("http://localhost:8000/olympicGames/place=" + randomOlympicGamesPlace);
        responseInformation = JsonPath.from(response.asString()).get();
    }

    @When("we want to see all Olympic Games information by a non-existent place")
    public void we_want_to_see_all_olympic_games_information_by_a_non_existent_place() {
        response = request.when().get("http://localhost:8000/olympicGames/place=Test");
    }

    @When("we want to add this new Olympic Games")
    public void we_want_to_add_this_new_olympic_games() {
        response = request.when().body(newOlympicGames).post("http://localhost:8000/olympicGames");
    }

    @When("we want to update its year to {int} and its place to {string}")
    public void we_want_to_update_its_year_and_its_place(Integer olympicGamesYear, String olympicGamesPlace) {
        newOlympicGamesToUpdate = new HashMap<>();
        newOlympicGamesToUpdate.put("olympicGamesYear", olympicGamesYear);
        newOlympicGamesToUpdate.put("olympicGamesPlace", olympicGamesPlace);
        response = request.when().body(newOlympicGamesToUpdate).patch("http://localhost:8000/olympicGames/id=" + idToUpdateDelete);
    }

    @When("we want to update its year and its place with null data")
    public void we_want_to_update_its_year_and_its_place_with_null_data() {
        newOlympicGamesToUpdate = new HashMap<>();
        newOlympicGamesToUpdate.put("olympicGamesYear", null);
        newOlympicGamesToUpdate.put("olympicGamesPlace", null);
        response = request.when().body(newOlympicGamesToUpdate).patch("http://localhost:8000/olympicGames/id=" + idToUpdateDelete);
    }

    @When("we want to update its year to {int}")
    public void we_want_to_update_its_year(Integer olympicGamesYear) {
        newOlympicGamesToUpdate = new HashMap<>();
        newOlympicGamesToUpdate.put("olympicGamesYear", olympicGamesYear);
        newOlympicGamesToUpdate.put("olympicGamesPlace", "olympicGames_place");
        response = request.when().body(newOlympicGamesToUpdate).patch("http://localhost:8000/olympicGames/id=" + idToUpdateDelete);
    }

    @When("we want to delete this Olympic Games")
    public void we_want_to_delete_this_olympic_games() {
        response = request.when().delete("http://localhost:8000/olympicGames/id=" + idToUpdateDelete);
    }

    @Then("all Olympic Games information is shown")
    public void all_olympic_games_information_is_shown() {
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertFalse(responseInformation.isEmpty());
    }

    @Then("the Olympic Games information is shown")
    public void the_olympic_games_information_is_shown() {
        Assert.assertEquals(200, response.getStatusCode());
        for (Map.Entry<String, Object> entry : uniqueResponseInformation.entrySet()) {
            if (entry.getKey().equals("olympicGamesId")) {
                Assert.assertEquals(randomId.toString(), entry.getValue().toString());
            } else if (entry.getKey().equals("olympicGamesYear")) {
                Assert.assertEquals(randomOlympicGamesYear.toString(), entry.getValue().toString());
            } else {
                Assert.assertEquals(randomOlympicGamesPlace, entry.getValue());
            }
        }
    }

    @Then("no Olympic Games information is shown and a not found error is shown")
    public void no_olympic_games_information_is_shown_and_a_not_found_error_is_shown() {
        Assert.assertEquals(404, response.getStatusCode());
    }

    @Then("the Olympic Games is correctly added and its information is shown")
    public void the_olympic_games_is_correctly_added_and_its_information_is_shown() {
        Assert.assertEquals(201, response.getStatusCode());
        uniqueResponseInformation = JsonPath.from(response.asString()).get();
        for (Map.Entry<String, Object> entry : uniqueResponseInformation.entrySet()) {
            if (entry.getKey().equals("olympicGamesYear")) {
                Assert.assertEquals(2222, entry.getValue());
            } else if (entry.getKey().equals("olympicGamesPlace")) {
                Assert.assertEquals("olympicGames_place", entry.getValue());
            }
        }
    }

    @Then("a bad request error is shown for the Olympic Games")
    public void a_bad_request_error_is_shown_for_the_olympic_games() {
        Assert.assertEquals(400, response.getStatusCode());
    }

    @Then("no Olympic Games information is shown and a duplicated information error is shown")
    public void no_olympic_games_information_is_shown_and_a_duplicated_information_error_is_shown() {
        Assert.assertEquals(409, response.getStatusCode());
    }

    @Then("the Olympic Games is correctly updated and its information is shown")
    public void the_olympic_games_is_correctly_updated_and_its_information_is_shown() {
        Assert.assertEquals(200, response.getStatusCode());
        uniqueResponseInformation = JsonPath.from(response.asString()).get();
        for (Map.Entry<String, Object> entry : uniqueResponseInformation.entrySet()) {
            if (entry.getKey().equals("olympicGamesYear")) {
                Assert.assertEquals(1111, entry.getValue());
            } else if (entry.getKey().equals("olympicGamesPlace")) {
                Assert.assertEquals("ogp_updated", entry.getValue());
            } else {
                Assert.assertEquals(idToUpdateDelete.toString(), entry.getValue().toString());
            }
        }
    }

    @Then("the Olympic Games is correctly deleted")
    public void the_olympic_games_is_correctly_deleted() {
        Assert.assertEquals(204, response.getStatusCode());
    }

}