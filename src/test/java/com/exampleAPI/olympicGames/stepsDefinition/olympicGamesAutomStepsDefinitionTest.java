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

public class olympicGamesAutomStepsDefinitionTest extends CucumberSpringConfiguration {

    private RequestSpecification request;
    private Response response;
    private Map<String, Object> uniqueResponseInformation, newItem, newItemToUpdate;
    private List<Map<String, Object>> responseInformation;
    private Long id;
    private Integer olympicGamesYear;
    private String olympicGamesPlace;

    @Given("a list of Olympic Games")
    public void test_1() {
        request = given().header("Content-Type", "application/json");
    }

    @When("we want to see all Olympic Games information")
    public void test_2() {
        response = request.when().get("http://localhost:8000/olympicGames");
    }

    @Then("all Olympic Games information is shown")
    public void test_3() {
        Assert.assertEquals(200, response.getStatusCode());
        responseInformation = JsonPath.from(response.asString()).get();
        Assert.assertFalse(responseInformation.isEmpty());
    }

    @Given("a concrete Olympic Games identifier {long}")
    public void test_4(Long id) {
        request = given().header("Content-Type", "application/json");
        this.id = id;
    }

    @When("we want to see the information of the Olympic Games by Olympic Games identifier")
    public void test_5() {
        response = request.when().get("http://localhost:8000/olympicGames/id=" + id);
    }

    @Then("the Olympic Games information by Olympic Games identifier is shown")
    public void test_6() {
        Assert.assertEquals(200, response.getStatusCode());
        uniqueResponseInformation = JsonPath.from(response.asString()).get();
        for (Map.Entry<String, Object> entry : uniqueResponseInformation.entrySet()) {
            if (entry.getKey().equals("olympicGamesId")) {
                Assert.assertEquals(id.toString(), entry.getValue().toString());
                break;
            }
        }
    }

    @Then("a not found error is shown for the Olympic Games")
    public void test_9() {
        Assert.assertEquals(404, response.getStatusCode());
    }

    @Given("a concrete Olympic Games year {int}")
    public void test_10(Integer olympicGamesYear) {
        request = given().header("Content-Type", "application/json");
        this.olympicGamesYear = olympicGamesYear;
    }

    @When("we want to see the information of the Olympic Games by Olympic Games year")
    public void test_11() {
        response = request.when().get("http://localhost:8000/olympicGames/year=" + olympicGamesYear);
    }

    @Then("the Olympic Games information by Olympic Games year is shown")
    public void test_12() {
        Assert.assertEquals(200, response.getStatusCode());
        uniqueResponseInformation = JsonPath.from(response.asString()).get();
        for (Map.Entry<String, Object> entry : uniqueResponseInformation.entrySet()) {
            if (entry.getKey().equals("olympicGamesYear")) {
                Assert.assertEquals(olympicGamesYear.toString(), entry.getValue().toString());
                break;
            }
        }
    }

    @Given("a concrete Olympic Games place {string}")
    public void test_16(String olympicGamesPlace) {
        request = given().header("Content-Type", "application/json");
        this.olympicGamesPlace = olympicGamesPlace;
    }

    @When("we want to see the information of the Olympic Games by Olympic Games place")
    public void test_17() {
        response = request.when().get("http://localhost:8000/olympicGames/place=" + olympicGamesPlace);
    }

    @Then("the Olympic Games information by Olympic Games place is shown")
    public void test_18() {
        Assert.assertEquals(200, response.getStatusCode());
        responseInformation = JsonPath.from(response.asString()).get();
        for (Map<String, Object> item : responseInformation) {
            for (Map.Entry<String, Object> entry : item.entrySet()) {
                if (entry.getKey().equals("olympicGamesPlace")) {
                    Assert.assertEquals(olympicGamesPlace, entry.getValue());
                    break;
                }
            }
        }
    }

    @Given("a new Olympic Games with Olympic Games year {int} and Olympic Games place {string}")
    public void test_22(Integer olympicGamesYear, String olympicGamesPlace) {
        request = given().header("Content-Type", "application/json");
        newItem = new HashMap<>();
        newItem.put("olympicGamesYear", olympicGamesYear);
        newItem.put("olympicGamesPlace", olympicGamesPlace);
    }

    @When("we want to add this new Olympic Games")
    public void test_23() {
        response = request.when().body(newItem).post("http://localhost:8000/olympicGames");
    }

    @Then("the Olympic Games is correctly added and its information is shown")
    public void test_24() {
        Assert.assertEquals(201, response.getStatusCode());
        uniqueResponseInformation = JsonPath.from(response.asString()).get();
        for (Map.Entry<String, Object> entry : uniqueResponseInformation.entrySet()) {
            if (entry.getKey().equals("olympicGamesYear")) {
                Assert.assertEquals((newItem.get("olympicGamesYear")).toString(), entry.getValue().toString());
            }
            if (entry.getKey().equals("olympicGamesPlace")) {
                Assert.assertEquals(newItem.get("olympicGamesPlace"), entry.getValue());
            }
        }
    }

    @Then("a duplicated information error is shown for the Olympic Games")
    public void test_27() {
        Assert.assertEquals(409, response.getStatusCode());
    }

    @When("we want to update Olympic Games place {string}")
    public void test_29(String olympicGamesPlace) {
        newItemToUpdate = new HashMap<>();
        newItemToUpdate.put("olympicGamesPlace", olympicGamesPlace);
        response = request.when().body(newItemToUpdate).patch("http://localhost:8000/olympicGames/id=" + id);
    }

    @Then("the Olympic Games is correctly updated and its information is shown")
    public void test_30() {
        Assert.assertEquals(200, response.getStatusCode());
        uniqueResponseInformation = JsonPath.from(response.asString()).get();
        for (Map.Entry<String, Object> entry : uniqueResponseInformation.entrySet()) {
            if (entry.getKey().equals("olympicGamesId")) {
                Assert.assertEquals(id.toString(), entry.getValue().toString());
            }
            if (entry.getKey().equals("olympicGamesPlace")) {
                Assert.assertEquals(newItemToUpdate.get("olympicGamesPlace"), entry.getValue());
            }
        }
    }

    @When("we want to update Olympic Games year {int} and Olympic Games place {string}")
    public void test_32(Integer olympicGamesYear, String olympicGamesPlace) {
        newItemToUpdate = new HashMap<>();
        newItemToUpdate.put("olympicGamesYear", olympicGamesYear);
        newItemToUpdate.put("olympicGamesPlace", olympicGamesPlace);
        response = request.when().body(newItemToUpdate).patch("http://localhost:8000/olympicGames/id=" + id);
    }

    @When("we want to update Olympic Games year {int}")
    public void test_35(Integer olympicGamesYear) {
        newItemToUpdate = new HashMap<>();
        newItemToUpdate.put("olympicGamesYear", olympicGamesYear);
        response = request.when().body(newItemToUpdate).patch("http://localhost:8000/olympicGames/id=" + id);
    }

    @When("we want to delete this Olympic Games")
    public void test_38() {
        response = request.when().delete("http://localhost:8000/olympicGames/id=" + id);
    }

    @Then("the Olympic Games is correctly deleted")
    public void test_39() {
        Assert.assertEquals(204, response.getStatusCode());
    }

}
