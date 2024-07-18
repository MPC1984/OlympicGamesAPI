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

public class metalAutomStepsDefinitionTest extends CucumberSpringConfiguration {

    private RequestSpecification request;
    private Response response;
    private Map<String, Object> uniqueResponseInformation, newItem, newItemToUpdate;
    private List<Map<String, Object>> responseInformation;
    private Long id;
    private String metalType;

    @Given("a list of metals")
    public void test_1() {
        request = given().header("Content-Type", "application/json");
    }

    @When("we want to see all metals information")
    public void test_2() {
        response = request.when().get("http://localhost:8000/olympicGames/metal");
    }

    @Then("all metals information is shown")
    public void test_3() {
        Assert.assertEquals(200, response.getStatusCode());
        responseInformation = JsonPath.from(response.asString()).get();
        Assert.assertFalse(responseInformation.isEmpty());
    }

    @Given("a concrete metal identifier {long}")
    public void test_4(Long id) {
        request = given().header("Content-Type", "application/json");
        this.id = id;
    }

    @When("we want to see the information of the metals by metal identifier")
    public void test_5() {
        response = request.when().get("http://localhost:8000/olympicGames/metal/id=" + id);
    }

    @Then("the metals information by metal identifier is shown")
    public void test_6() {
        Assert.assertEquals(200, response.getStatusCode());
        uniqueResponseInformation = JsonPath.from(response.asString()).get();
        for (Map.Entry<String, Object> entry : uniqueResponseInformation.entrySet()) {
            if (entry.getKey().equals("metalId")) {
                Assert.assertEquals(id.toString(), entry.getValue().toString());
                break;
            }
        }
    }

    @Then("a not found error is shown for the metals")
    public void test_9() {
        Assert.assertEquals(404, response.getStatusCode());
    }

    @Given("a concrete metal type {string}")
    public void test_10(String metalType) {
        request = given().header("Content-Type", "application/json");
        this.metalType = metalType;
    }

    @When("we want to see the information of the metals by metal type")
    public void test_11() {
        response = request.when().get("http://localhost:8000/olympicGames/metal/type=" + metalType);
    }

    @Then("the metals information by metal type is shown")
    public void test_12() {
        Assert.assertEquals(200, response.getStatusCode());
        uniqueResponseInformation = JsonPath.from(response.asString()).get();
        for (Map.Entry<String, Object> entry : uniqueResponseInformation.entrySet()) {
            if (entry.getKey().equals("metalType")) {
                Assert.assertEquals(metalType, entry.getValue());
                break;
            }
        }
    }

    @Given("a new metal with metal type {string}")
    public void test_16(String metalType) {
        request = given().header("Content-Type", "application/json");
        newItem = new HashMap<>();
        newItem.put("metalType", metalType);
    }

    @When("we want to add this new metal")
    public void test_17() {
        response = request.when().body(newItem).post("http://localhost:8000/olympicGames/metal");
    }

    @Then("the metal is correctly added and its information is shown")
    public void test_18() {
        Assert.assertEquals(201, response.getStatusCode());
        uniqueResponseInformation = JsonPath.from(response.asString()).get();
        for (Map.Entry<String, Object> entry : uniqueResponseInformation.entrySet()) {
            if (entry.getKey().equals("metalType")) {
                Assert.assertEquals(newItem.get("metalType"), entry.getValue());
            }
        }
    }

    @Then("a duplicated information error is shown for the metals")
    public void test_21() {
        Assert.assertEquals(409, response.getStatusCode());
    }

    @When("we want to update metal type {string}")
    public void test_23(String metalType) {
        newItemToUpdate = new HashMap<>();
        newItemToUpdate.put("metalType", metalType);
        response = request.when().body(newItemToUpdate).patch("http://localhost:8000/olympicGames/metal/id=" + id);
    }

    @Then("the metal is correctly updated and its information is shown")
    public void test_24() {
        Assert.assertEquals(200, response.getStatusCode());
        uniqueResponseInformation = JsonPath.from(response.asString()).get();
        for (Map.Entry<String, Object> entry : uniqueResponseInformation.entrySet()) {
            if (entry.getKey().equals("metalId")) {
                Assert.assertEquals(id.toString(), entry.getValue().toString());
            }
            if (entry.getKey().equals("metalType")) {
                Assert.assertEquals(newItemToUpdate.get("metalType"), entry.getValue());
            }
        }
    }

    @When("we want to delete this metal")
    public void test_32() {
        response = request.when().delete("http://localhost:8000/olympicGames/metal/id=" + id);
    }

    @Then("the metal is correctly deleted")
    public void test_33() {
        Assert.assertEquals(204, response.getStatusCode());
    }

}
