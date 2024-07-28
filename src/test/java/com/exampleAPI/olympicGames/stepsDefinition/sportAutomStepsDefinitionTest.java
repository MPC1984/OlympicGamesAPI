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

public class sportAutomStepsDefinitionTest extends CucumberSpringConfiguration {

    private RequestSpecification request;
    private Response response;
    private Map<String, Object> uniqueResponseInformation, newItem, newItemToUpdate;
    private List<Map<String, Object>> responseInformation;
    private Long id;
    private String sportName;
    private String sportCategoryName;

    @Given("a list of sports")
    public void test_1() {
        request = given().header("Content-Type", "application/json");
    }

    @When("we want to see all sports information")
    public void test_2() {
        response = request.when().get("http://localhost:8000/olympicGames/sport");
    }

    @Then("all sports information is shown")
    public void test_3() {
        Assert.assertEquals(200, response.getStatusCode());
        responseInformation = JsonPath.from(response.asString()).get();
        Assert.assertFalse(responseInformation.isEmpty());
    }

    @Given("a concrete sport identifier {long}")
    public void test_4(Long id) {
        request = given().header("Content-Type", "application/json");
        this.id = id;
    }

    @When("we want to see the information of the sports by sport identifier")
    public void test_5() {
        response = request.when().get("http://localhost:8000/olympicGames/sport/id=" + id);
    }

    @Then("the sports information by sport identifier is shown")
    public void test_6() {
        Assert.assertEquals(200, response.getStatusCode());
        uniqueResponseInformation = JsonPath.from(response.asString()).get();
        for (Map.Entry<String, Object> entry : uniqueResponseInformation.entrySet()) {
            if (entry.getKey().equals("sportId")) {
                Assert.assertEquals(id.toString(), entry.getValue().toString());
                break;
            }
        }
    }

    @Then("a not found error is shown for the sports")
    public void test_9() {
        Assert.assertEquals(404, response.getStatusCode());
    }

    @Given("a concrete sport name {string}")
    public void test_10(String sportName) {
        request = given().header("Content-Type", "application/json");
        this.sportName = sportName;
    }

    @When("we want to see the information of the sports by sport name")
    public void test_11() {
        response = request.when().get("http://localhost:8000/olympicGames/sport/name=" + sportName);
    }

    @Then("the sports information by sport name is shown")
    public void test_12() {
        Assert.assertEquals(200, response.getStatusCode());
        responseInformation = JsonPath.from(response.asString()).get();
        for (Map<String, Object> item : responseInformation) {
            for (Map.Entry<String, Object> entry : item.entrySet()) {
                if (entry.getKey().equals("sportName")) {
                    Assert.assertEquals(sportName, entry.getValue());
                    break;
                }
            }
        }
    }

    @Given("a concrete sport category {string}")
    public void test_16(String sportCategoryName) {
        request = given().header("Content-Type", "application/json");
        this.sportCategoryName = sportCategoryName;
    }

    @When("we want to see the information of the sports by sport category")
    public void test_17() {
        response = request.when().get("http://localhost:8000/olympicGames/sport/category=" + sportCategoryName);
    }

    @Then("the sports information by sport category is shown")
    public void test_18() {
        Assert.assertEquals(200, response.getStatusCode());
        responseInformation = JsonPath.from(response.asString()).get();
        for (Map<String, Object> item : responseInformation) {
            for (Map.Entry<String, Object> entry : item.entrySet()) {
                if (entry.getKey().equals("sportCategoryName")) {
                    Assert.assertEquals(sportCategoryName, entry.getValue());
                    break;
                }
            }
        }
    }

    @Given("a new sport with sport name {string} and sport category {string}")
    public void test_22(String sportName, String sportCategoryName) {
        request = given().header("Content-Type", "application/json");
        newItem = new HashMap<>();
        newItem.put("sportName", sportName);
        newItem.put("sportCategoryName", sportCategoryName);
    }

    @When("we want to add this new sport")
    public void test_23() {
        response = request.when().body(newItem).post("http://localhost:8000/olympicGames/sport");
    }

    @Then("the sport is correctly added and its information is shown")
    public void test_24() {
        Assert.assertEquals(201, response.getStatusCode());
        uniqueResponseInformation = JsonPath.from(response.asString()).get();
        for (Map.Entry<String, Object> entry : uniqueResponseInformation.entrySet()) {
            if (entry.getKey().equals("sportName")) {
                Assert.assertEquals(newItem.get("sportName"), entry.getValue());
            }
            if (entry.getKey().equals("sportCategoryName")) {
                if (entry.getValue() != null) {
                    Assert.assertEquals(newItem.get("sportCategoryName"), entry.getValue());
                } else {
                    Assert.assertNull(entry.getValue());
                }
            }
        }
    }

    @When("we want to update sport name {string}")
    public void test_26(String sportName) {
        newItemToUpdate = new HashMap<>();
        newItemToUpdate.put("sportName", sportName);
        response = request.when().body(newItemToUpdate).patch("http://localhost:8000/olympicGames/sport/id=" + id);
    }

    @Then("the sport is correctly updated and its information is shown")
    public void test_27() {
        Assert.assertEquals(200, response.getStatusCode());
        uniqueResponseInformation = JsonPath.from(response.asString()).get();
        for (Map.Entry<String, Object> entry : uniqueResponseInformation.entrySet()) {
            if (entry.getKey().equals("sportId")) {
                Assert.assertEquals(id.toString(), entry.getValue().toString());
            }
            if (entry.getKey().equals("sportName")) {
                Assert.assertEquals(newItemToUpdate.get("sportName"), entry.getValue());
            }
        }
    }

    @When("we want to update sport name {string} and sport category {string}")
    public void test_29(String sportName, String sportCategoryName) {
        newItemToUpdate = new HashMap<>();
        newItemToUpdate.put("sportName", sportName);
        newItemToUpdate.put("sportCategoryName", sportCategoryName);
        response = request.when().body(newItemToUpdate).patch("http://localhost:8000/olympicGames/sport/id=" + id);
    }

    @When("we want to delete this sport")
    public void test_32() {
        response = request.when().delete("http://localhost:8000/olympicGames/sport/id=" + id);
    }

    @Then("the sport is correctly deleted")
    public void test_33() {
        Assert.assertEquals(204, response.getStatusCode());
    }

}
