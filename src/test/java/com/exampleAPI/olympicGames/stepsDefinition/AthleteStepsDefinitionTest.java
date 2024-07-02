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
public class AthleteStepsDefinitionTest extends CucumberSpringConfiguration {

    private RequestSpecification request;
    private Response response;
    private Map<String, Object> uniqueResponseInformation;
    private List<Map<String, Object>> allAthletes, responseInformation;
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
                case "id":
                    randomId = Long.parseLong(entry.getValue().toString());
                case "athleteName":
                    randomAthleteName = entry.getValue().toString();
                case "athleteSurname":
                    randomAthleteSurname = entry.getValue().toString();
                default:
                    randomAthleteCountry = entry.getValue().toString();
            }
        }
        //Obtenemos el atleta de la lista que se ha añadido durante la ejecución de las pruebas
        for (Map<String, Object> athlete : allAthletes) {
            for (Map.Entry<String, Object> entry : athlete.entrySet()) {
                if (entry.getKey().equals("athleteName") && entry.getValue().equals("athlete_name")) {
                    athleteToUpdate = athlete;
                    break;
                }
            }
        }
        if (athleteToUpdate != null) {
            for (Map.Entry<String, Object> entry : athleteToUpdate.entrySet()) {
                if (entry.getKey().equals("id")) {
                    idToUpdate = Long.parseLong(entry.getValue().toString());
                }
            }
        }
        //Obtenemos el atleta de la lista que se ha modificado durante la ejecución de las pruebas
        for (Map<String, Object> athlete : allAthletes) {
            for (Map.Entry<String, Object> entry : athlete.entrySet()) {
                if (entry.getKey().equals("athleteName") && entry.getValue().equals("an_updated")) {
                    athleteToUpdate = athlete;
                    break;
                }
            }
        }
        if (athleteToUpdate != null) {
            for (Map.Entry<String, Object> entry : athleteToUpdate.entrySet()) {
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
        if (allAthletes != null) {
            allAthletes.clear();
        }
        if (responseInformation != null) {
            responseInformation.clear();
        }
        if (newAthlete != null) {
            newAthlete.clear();
        }
        if (newAthleteToUpdate != null) {
            newAthleteToUpdate.clear();
        }
        if (randomAthlete != null) {
            randomAthlete.clear();
        }
        if (athleteToUpdate != null) {
            athleteToUpdate.clear();
        }
    }

    @Given("a list of athletes")
    public void a_list_of_athletes() {
        request = given().header("Content-Type", "application/json");
    }

//    @Given("a list of empty athletes")
//    public void a_list_of_empty_athletes() {
//        request = given().header("Content-Type", "application/json");
//    }

    @Given("a new athlete with name {string}, surname {string} and country {string}")
    public void a_new_athlete_with_name_surname_and_country(String athleteName, String athleteSurname, String athleteCountry) {
        newAthlete = new HashMap<>();
        newAthlete.put("athleteName", athleteName);
        newAthlete.put("athleteSurname", athleteSurname);
        newAthlete.put("athleteCountry", athleteCountry);
        request = given().header("Content-Type", "application/json");
    }

    @Given("a new athlete without name")
    public void a_new_athlete_without_name() {
        newAthlete = new HashMap<>();
        newAthlete.put("athleteName", null);
        newAthlete.put("athleteSurname", "athlete_surname");
        newAthlete.put("athleteCountry", "athlete_country");
        request = given().header("Content-Type", "application/json");
    }

    @Given("an existing athlete")
    public void an_existing_athlete() {
        request = given().header("Content-Type", "application/json");
    }

    @Given("a non-existent athlete")
    public void a_non_existent_athlete() {
        idToUpdate = 9999L;
        request = given().header("Content-Type", "application/json");
    }

    @When("we want to see all athletes information")
    public void we_want_to_see_all_athletes_information() {
        response = request.when().get("http://localhost:8000/olympicGames/athlete");
    }

//    @When("we want to see all athletes information when the list is empty")
//    public void we_want_to_see_all_athletes_information_when_the_list_is_empty() {
//        response = request.when().get("http://localhost:8000/olympicGames/athlete");
//    }

    @When("we want to see the information of a concrete athlete by its identifier")
    public void we_want_to_see_the_information_of_a_concrete_athlete_by_its_identifier() {
        response = request.when().get("http://localhost:8000/olympicGames/athlete/id=" + randomId);
        uniqueResponseInformation = JsonPath.from(response.asString()).get();
    }

    @When("we want to see the information of a concrete athlete by a non-existent identifier")
    public void we_want_to_see_the_information_of_a_concrete_athlete_by_a_non_existent_identifier() {
        response = request.when().get("http://localhost:8000/olympicGames/athlete/id=9999");
    }

    @When("we want to see all athletes information by their name")
    public void we_want_to_see_all_athletes_information_by_their_name() {
        response = request.when().get("http://localhost:8000/olympicGames/athlete/name=" + randomAthleteName);
        responseInformation = JsonPath.from(response.asString()).get();
    }

    @When("we want to see all athletes information by a non-existent name")
    public void we_want_to_see_all_athletes_information_by_a_non_existent_name() {
        response = request.when().get("http://localhost:8000/olympicGames/athlete/name=Test");
    }

    @When("we want to see all athletes information by their surname")
    public void we_want_to_see_all_athletes_information_by_their_surname() {
        response = request.when().get("http://localhost:8000/olympicGames/athlete/surname=" + randomAthleteSurname);
        responseInformation = JsonPath.from(response.asString()).get();
    }

    @When("we want to see all athletes information by a non-existent surname")
    public void we_want_to_see_all_athletes_information_by_a_non_existent_surname() {
        response = request.when().get("http://localhost:8000/olympicGames/athlete/surname=Test");
    }

    @When("we want to see all athletes information by their country")
    public void we_want_to_see_all_athletes_information_by_their_country() {
        response = request.when().get("http://localhost:8000/olympicGames/athlete/country=" + randomAthleteCountry);
        responseInformation = JsonPath.from(response.asString()).get();
    }

    @When("we want to see all athletes information by a non-existent country")
    public void we_want_to_see_all_athletes_information_by_a_non_existent_country() {
        response = request.when().get("http://localhost:8000/olympicGames/athlete/country=Test");
    }

    @When("we want to add this new athlete")
    public void we_want_to_add_this_new_athlete() {
        response = request.when().body(newAthlete).post("http://localhost:8000/olympicGames/athlete");
    }

    @When("we want to update its name to {string}, its surname to {string} and its country to {string}")
    public void we_want_to_update_its_name_its_surname_and_its_country(String athleteName, String athleteSurname, String athleteCountry) {
        newAthleteToUpdate = new HashMap<>();
        newAthleteToUpdate.put("athleteName", athleteName);
        newAthleteToUpdate.put("athleteSurname", athleteSurname);
        newAthleteToUpdate.put("athleteCountry", athleteCountry);
        response = request.when().body(newAthleteToUpdate).patch("http://localhost:8000/olympicGames/athlete/id=" + idToUpdate);
    }

    @When("we want to delete this athlete")
    public void we_want_to_delete_this_athlete() {
        response = request.when().delete("http://localhost:8000/olympicGames/athlete/id=" + idToUpdate);
    }

    @Then("all athletes information is shown")
    public void all_athletes_information_is_shown() {
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertFalse(allAthletes.isEmpty());
    }

    @Then("the athlete information is shown")
    public void the_athlete_information_is_shown() {
        Assert.assertEquals(200, response.getStatusCode());
        for (Map.Entry<String, Object> entry : uniqueResponseInformation.entrySet()) {
            if (entry.getKey().equals("id")) {
                Assert.assertEquals(randomId.toString(), entry.getValue().toString());
            } else if (entry.getKey().equals("athleteName")) {
                Assert.assertEquals(randomAthleteName, entry.getValue());
            } else if (entry.getKey().equals("athleteSurname")) {
                Assert.assertEquals(randomAthleteSurname, entry.getValue());
            } else {
                Assert.assertEquals(randomAthleteCountry, entry.getValue());
            }
        }
    }

    @Then("no athlete information is shown and a not found error is shown")
    public void no_athlete_information_is_shown_and_a_not_found_error_is_shown() {
        Assert.assertEquals(404, response.getStatusCode());
    }

    @Then("no athletes information is shown and a not found error is shown")
    public void no_athletes_information_is_shown_and_a_not_found_error_is_shown() {
        Assert.assertEquals(404, response.getStatusCode());
    }

    @Then("the athlete is correctly added and its information is shown")
    public void the_athlete_is_correctly_added_and_its_information_is_shown() {
        Assert.assertEquals(201, response.getStatusCode());
        uniqueResponseInformation = JsonPath.from(response.asString()).get();
        for (Map.Entry<String, Object> entry : uniqueResponseInformation.entrySet()) {
            if (entry.getKey().equals("athleteName")) {
                Assert.assertEquals("athlete_name", entry.getValue());
            } else if (entry.getKey().equals("athleteSurname")) {
                Assert.assertEquals("athlete_surname", entry.getValue());
            } else if (entry.getKey().equals("athleteCountry")) {
                Assert.assertEquals("athlete_country", entry.getValue());
            }
        }
        newAthlete.clear();
    }

    @Then("a bad request error is shown for the athlete")
    public void a_bad_request_error_is_shown_for_the_athlete() {
        Assert.assertEquals(400, response.getStatusCode());
    }

    @Then("the athlete is correctly updated and its information is shown")
    public void the_athlete_is_correctly_updated_and_its_information_is_shown() {
        Assert.assertEquals(200, response.getStatusCode());
        uniqueResponseInformation = JsonPath.from(response.asString()).get();
        for (Map.Entry<String, Object> entry : uniqueResponseInformation.entrySet()) {
            if (entry.getKey().equals("athleteName")) {
                Assert.assertEquals("an_updated", entry.getValue());
            } else if (entry.getKey().equals("athleteSurname")) {
                Assert.assertEquals("as_updated", entry.getValue());
            } else if (entry.getKey().equals("athleteCountry")) {
                Assert.assertEquals("ac_updated", entry.getValue());
            } else {
                Assert.assertEquals(idToUpdate.toString(), entry.getValue().toString());
            }
        }
        newAthleteToUpdate.clear();
    }

    @Then("the athlete is correctly deleted")
    public void the_athlete_is_correctly_deleted() {
        Assert.assertEquals(204, response.getStatusCode());
    }

}