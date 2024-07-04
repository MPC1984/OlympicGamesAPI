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
public class SportStepsDefinitionTest extends CucumberSpringConfiguration {

    private RequestSpecification request;
    private Response response;
    private Map<String, Object> uniqueResponseInformation, newSport, newSportToUpdate, randomSport, sportToUpdate;
    private List<Map<String, Object>> allSports, responseInformation;
    private Object value;
    private Long randomId, idToUpdateDelete;
    private String randomSportName, randomSportCategoryName;

    @Before
    public void dataGeneration() {
        request = given().header("Content-Type", "application/json");
        response = request.when().get("http://localhost:8000/olympicGames/sport");
        allSports = JsonPath.from(response.asString()).get();
        //Obtenemos un deporte de la lista de manera aleatoria para realizar comprobaciones en los diferentes endpoints
        while (randomSportCategoryName == null) {
            randomSport = allSports.get((int) (Math.random() * allSports.size()));
            for (Map.Entry<String, Object> entry : randomSport.entrySet()) {
                value = entry.getValue();
                switch (entry.getKey()) {
                    case "sportId":
                        randomId = Long.parseLong(value.toString());
                        break;
                    case "sportName":
                        randomSportName = value.toString();
                        break;
                    case "sportCategoryName":
                        if (value != null) {
                            randomSportCategoryName = value.toString();
                        } else {
                            randomSportCategoryName = null;
                        }
                        break;
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
                if (entry.getKey().equals("sportId")) {
                    idToUpdateDelete = Long.parseLong(entry.getValue().toString());
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
                if (entry.getKey().equals("sportId")) {
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
        request = given().header("Content-Type", "application/json");
    }

//    @Given("a list of empty sports")
//    public void a_list_of_empty_sports() {
//        request = given().header("Content-Type", "application/json");
//    }

    @Given("a new sport with name {string} and category {string}")
    public void a_new_sport_with_name_and_category(String sportName, String sportCategoryName) {
        newSport = new HashMap<>();
        newSport.put("sportName", sportName);
        newSport.put("sportCategoryName", sportCategoryName);
        request = given().header("Content-Type", "application/json");
    }

    @Given("a new sport without name")
    public void a_new_sport_without_name() {
        newSport = new HashMap<>();
        newSport.put("sportName", null);
        newSport.put("sportCategoryName", "sport_category");
        request = given().header("Content-Type", "application/json");
    }

    @Given("an existing sport")
    public void an_existing_sport() {
        request = given().header("Content-Type", "application/json");
    }

    @Given("a non-existent sport")
    public void a_non_existent_sport() {
        idToUpdateDelete = 9999L;
        request = given().header("Content-Type", "application/json");
    }

    @When("we want to see all sports information")
    public void we_want_to_see_all_sports_information() {
        response = request.when().get("http://localhost:8000/olympicGames/sport");
        responseInformation = JsonPath.from(response.asString()).get();
    }

//    @When("we want to see all sports information when the list is empty")
//    public void we_want_to_see_all_sports_information_when_the_list_is_empty() {
//        response = request.when().get("http://localhost:8000/olympicGames/sport");
//    }

    @When("we want to see the information of a concrete sport by its identifier")
    public void we_want_to_see_the_information_of_a_concrete_sport_by_its_identifier() {
        response = request.when().get("http://localhost:8000/olympicGames/sport/id=" + randomId);
        uniqueResponseInformation = JsonPath.from(response.asString()).get();
    }

    @When("we want to see the information of a concrete sport by a non-existent identifier")
    public void we_want_to_see_the_information_of_a_concrete_sport_by_a_non_existent_identifier() {
        response = request.when().get("http://localhost:8000/olympicGames/sport/id=9999");
    }

    @When("we want to see all sports information by their name")
    public void we_want_to_see_all_sports_information_by_their_name() {
        response = request.when().get("http://localhost:8000/olympicGames/sport/name=" + randomSportName);
        responseInformation = JsonPath.from(response.asString()).get();
    }

    @When("we want to see all sports information by a non-existent name")
    public void we_want_to_see_all_sports_information_by_a_non_existent_name() {
        response = request.when().get("http://localhost:8000/olympicGames/sport/name=Test");
    }

    @When("we want to see all sports information by their category")
    public void we_want_to_see_all_sports_information_by_their_category() {
        response = request.when().get("http://localhost:8000/olympicGames/sport/category=" + randomSportCategoryName);
        responseInformation = JsonPath.from(response.asString()).get();
    }

    @When("we want to see all sports information by a non-existent category")
    public void we_want_to_see_all_sports_information_by_a_non_existent_category() {
        response = request.when().get("http://localhost:8000/olympicGames/sport/category=Test");
    }

    @When("we want to add this new sport")
    public void we_want_to_add_this_new_sport() {
        response = request.when().body(newSport).post("http://localhost:8000/olympicGames/sport");
    }

    @When("we want to update its name to {string} and its category to {string}")
    public void we_want_to_update_its_name_and_its_category(String sportName, String sportCategoryName) {
        newSportToUpdate = new HashMap<>();
        newSportToUpdate.put("sportName", sportName);
        newSportToUpdate.put("sportCategoryName", sportCategoryName);
        response = request.when().body(newSportToUpdate).patch("http://localhost:8000/olympicGames/sport/id=" + idToUpdateDelete);
    }

    @When("we want to update its name and its category with null data")
    public void we_want_to_update_its_name_and_its_category_with_null_data() {
        newSportToUpdate = new HashMap<>();
        newSportToUpdate.put("sportName", null);
        newSportToUpdate.put("sportCategoryName", null);
        response = request.when().body(newSportToUpdate).patch("http://localhost:8000/olympicGames/sport/id=" + idToUpdateDelete);
    }

    @When("we want to delete this sport")
    public void we_want_to_delete_this_sport() {
        response = request.when().delete("http://localhost:8000/olympicGames/sport/id=" + idToUpdateDelete);
    }

    @Then("all sports information is shown")
    public void all_sports_information_is_shown() {
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertFalse(responseInformation.isEmpty());
    }

    @Then("the sport information is shown")
    public void the_sport_information_is_shown() {
        Assert.assertEquals(200, response.getStatusCode());
        for (Map.Entry<String, Object> entry : uniqueResponseInformation.entrySet()) {
            if (entry.getKey().equals("sportId")) {
                Assert.assertEquals(randomId.toString(), entry.getValue().toString());
            } else if (entry.getKey().equals("sportName")) {
                Assert.assertEquals(randomSportName, entry.getValue());
            } else {
                if (entry.getValue() != null) {
                    Assert.assertEquals(randomSportCategoryName, entry.getValue());
                } else {
                    Assert.assertNull(entry.getValue());
                }
            }
        }
    }

    @Then("no sport information is shown and a not found error is shown")
    public void no_sport_information_is_shown_and_a_not_found_error_is_shown() {
        Assert.assertEquals(404, response.getStatusCode());
    }

    @Then("no sports information is shown and a not found error is shown")
    public void no_sports_information_is_shown_and_a_not_found_error_is_shown() {
        Assert.assertEquals(404, response.getStatusCode());
    }

    @Then("the sport is correctly added and its information is shown")
    public void the_sport_is_correctly_added_and_its_information_is_shown() {
        Assert.assertEquals(201, response.getStatusCode());
        uniqueResponseInformation = JsonPath.from(response.asString()).get();
        for (Map.Entry<String, Object> entry : uniqueResponseInformation.entrySet()) {
            if (entry.getKey().equals("sportName")) {
                Assert.assertEquals("sport_name", entry.getValue());
            } else if (entry.getKey().equals("sportCategoryName")) {
                Assert.assertEquals("sport_category", entry.getValue());
            }
        }
    }

    @Then("a bad request error is shown for the sport")
    public void a_bad_request_error_is_shown_for_the_sport() {
        Assert.assertEquals(400, response.getStatusCode());
    }

    @Then("the sport is correctly updated and its information is shown")
    public void the_sport_is_correctly_updated_and_its_information_is_shown() {
        Assert.assertEquals(200, response.getStatusCode());
        uniqueResponseInformation = JsonPath.from(response.asString()).get();
        for (Map.Entry<String, Object> entry : uniqueResponseInformation.entrySet()) {
            if (entry.getKey().equals("sportName")) {
                Assert.assertEquals("sn_updated", entry.getValue());
            } else if (entry.getKey().equals("sportCategoryName")) {
                Assert.assertEquals("sc_updated", entry.getValue());
            } else {
                Assert.assertEquals(idToUpdateDelete.toString(), entry.getValue().toString());
            }
        }
    }

    @Then("the sport is correctly deleted")
    public void the_sport_is_correctly_deleted() {
        Assert.assertEquals(204, response.getStatusCode());
    }

}