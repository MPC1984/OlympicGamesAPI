package com.exampleAPI.olympicGames.stepsDefinition;

import com.exampleAPI.olympicGames.runners.CucumberSpringConfiguration;
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

public class athleteAutomStepsDefinitionTest extends CucumberSpringConfiguration {

    private RequestSpecification request;
    private Response response;
    private Map<String, Object> uniqueResponseInformation, newItem, newItemToUpdate;
    private List<Map<String, Object>> responseInformation;
    private Long id;
    private String athleteName;
    private String athleteSurname;
    private String athleteCountry;

    @Given("a list of athletes")
    public void test_1() {
        request = given().header("Content-Type", "application/json");
    }

    @When("we want to see all athletes information")
    public void test_2() {
        response = request.when().get("http://localhost:8000/olympicGames/athlete");
    }

    @Then("all athletes information is shown")
    public void test_3() {
        Assert.assertEquals(200, response.getStatusCode());
        responseInformation = JsonPath.from(response.asString()).get();
        Assert.assertFalse(responseInformation.isEmpty());
    }

    @Given("a concrete athlete identifier {long}")
    public void test_4(Long id) {
        request = given().header("Content-Type", "application/json");
        this.id = id;
    }

    @When("we want to see the information of the athletes by athlete identifier")
    public void test_5() {
        response = request.when().get("http://localhost:8000/olympicGames/athlete/id=" + id);
    }

    @Then("the athletes information by athlete identifier is shown")
    public void test_6() {
        Assert.assertEquals(200, response.getStatusCode());
        uniqueResponseInformation = JsonPath.from(response.asString()).get();
        for (Map.Entry<String, Object> entry : uniqueResponseInformation.entrySet()) {
            if (entry.getKey().equals("athleteId")) {
                Assert.assertEquals(id.toString(), entry.getValue().toString());
                break;
            }
        }
    }

    @Then("a not found error is shown for the athletes")
    public void test_9() {
        Assert.assertEquals(404, response.getStatusCode());
    }

    @Given("a concrete athlete name {string}")
    public void test_10(String athleteName) {
        request = given().header("Content-Type", "application/json");
        this.athleteName = athleteName;
    }

    @When("we want to see the information of the athletes by athlete name")
    public void test_11() {
        response = request.when().get("http://localhost:8000/olympicGames/athlete/name=" + athleteName);
    }

    @Then("the athletes information by athlete name is shown")
    public void test_12() {
        Assert.assertEquals(200, response.getStatusCode());
        responseInformation = JsonPath.from(response.asString()).get();
        for (Map<String, Object> item : responseInformation) {
            for (Map.Entry<String, Object> entry : item.entrySet()) {
                if (entry.getKey().equals("athleteName")) {
                    Assert.assertEquals(athleteName, entry.getValue());
                    break;
                }
            }
        }
    }

    @Given("a concrete athlete surname {string}")
    public void test_16(String athleteSurname) {
        request = given().header("Content-Type", "application/json");
        this.athleteSurname = athleteSurname;
    }

    @When("we want to see the information of the athletes by athlete surname")
    public void test_17() {
        response = request.when().get("http://localhost:8000/olympicGames/athlete/surname=" + athleteSurname);
    }

    @Then("the athletes information by athlete surname is shown")
    public void test_18() {
        Assert.assertEquals(200, response.getStatusCode());
        responseInformation = JsonPath.from(response.asString()).get();
        for (Map<String, Object> item : responseInformation) {
            for (Map.Entry<String, Object> entry : item.entrySet()) {
                if (entry.getKey().equals("athleteSurname")) {
                    Assert.assertEquals(athleteSurname, entry.getValue());
                    break;
                }
            }
        }
    }

    @Given("a concrete athlete country {string}")
    public void test_22(String athleteCountry) {
        request = given().header("Content-Type", "application/json");
        this.athleteCountry = athleteCountry;
    }

    @When("we want to see the information of the athletes by athlete country")
    public void test_23() {
        response = request.when().get("http://localhost:8000/olympicGames/athlete/country=" + athleteCountry);
    }

    @Then("the athletes information by athlete country is shown")
    public void test_24() {
        Assert.assertEquals(200, response.getStatusCode());
        responseInformation = JsonPath.from(response.asString()).get();
        for (Map<String, Object> item : responseInformation) {
            for (Map.Entry<String, Object> entry : item.entrySet()) {
                if (entry.getKey().equals("athleteCountry")) {
                    Assert.assertEquals(athleteCountry, entry.getValue());
                    break;
                }
            }
        }
    }

    @Given("a new athlete with athlete name {string} athlete surname {string} and athlete country {string}")
    public void test_28(String athleteName, String athleteSurname, String athleteCountry) {
        request = given().header("Content-Type", "application/json");
        newItem = new HashMap<>();
        newItem.put("athleteName", athleteName);
        newItem.put("athleteSurname", athleteSurname);
        newItem.put("athleteCountry", athleteCountry);
    }

    @When("we want to add this new athlete")
    public void test_29() {
        response = request.when().body(newItem).post("http://localhost:8000/olympicGames/athlete");
    }

    @Then("the athlete is correctly added and its information is shown")
    public void test_30() {
        Assert.assertEquals(201, response.getStatusCode());
        uniqueResponseInformation = JsonPath.from(response.asString()).get();
        for (Map.Entry<String, Object> entry : uniqueResponseInformation.entrySet()) {
            if (entry.getKey().equals("athleteName")) {
                Assert.assertEquals(newItem.get("athleteName"), entry.getValue());
            }
            if (entry.getKey().equals("athleteSurname")) {
                Assert.assertEquals(newItem.get("athleteSurname"), entry.getValue());
            }
            if (entry.getKey().equals("athleteCountry")) {
                Assert.assertEquals(newItem.get("athleteCountry"), entry.getValue());
            }
        }
    }

    @When("we want to update athlete name {string} athlete and athlete country {string}")
    public void test_32(String athleteName, String athleteCountry) {
        newItemToUpdate = new HashMap<>();
        newItemToUpdate.put("athleteName", athleteName);
        newItemToUpdate.put("athleteCountry", athleteCountry);
        response = request.when().body(newItemToUpdate).patch("http://localhost:8000/olympicGames/athlete/id=" + id);
    }

    @Then("the athlete is correctly updated and its information is shown")
    public void test_33() {
        Assert.assertEquals(200, response.getStatusCode());
        uniqueResponseInformation = JsonPath.from(response.asString()).get();
        for (Map.Entry<String, Object> entry : uniqueResponseInformation.entrySet()) {
            if (entry.getKey().equals("athleteId")) {
                Assert.assertEquals(id.toString(), entry.getValue().toString());
            }
            if (entry.getKey().equals("athleteName")) {
                Assert.assertEquals(newItemToUpdate.get("athleteName"), entry.getValue());
            }
            if (entry.getKey().equals("athleteCountry")) {
                Assert.assertEquals(newItemToUpdate.get("athleteCountry"), entry.getValue());
            }
        }
    }

    @When("we want to update athlete name {string} athlete surname {string} and athlete country {string}")
    public void test_35(String athleteName, String athleteSurname, String athleteCountry) {
        newItemToUpdate = new HashMap<>();
        newItemToUpdate.put("athleteName", athleteName);
        newItemToUpdate.put("athleteSurname", athleteSurname);
        newItemToUpdate.put("athleteCountry", athleteCountry);
        response = request.when().body(newItemToUpdate).patch("http://localhost:8000/olympicGames/athlete/id=" + id);
    }

    @When("we want to delete this athlete")
    public void test_38() {
        response = request.when().delete("http://localhost:8000/olympicGames/athlete/id=" + id);
    }

    @Then("the athlete is correctly deleted")
    public void test_39() {
        Assert.assertEquals(204, response.getStatusCode());
    }

}
