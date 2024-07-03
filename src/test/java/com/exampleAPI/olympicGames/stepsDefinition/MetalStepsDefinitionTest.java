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
public class MetalStepsDefinitionTest extends CucumberSpringConfiguration {

    private RequestSpecification request;
    private Response response;
    private Map<String, Object> uniqueResponseInformation, newMetal, newMetalToUpdate, randomMetal, metalToUpdate;
    private List<Map<String, Object>> allMetals, responseInformation;
    private Long randomId, idToUpdateDelete;
    private String randomMetalType;

    @Before
    public void dataGeneration() {
        request = given().header("Content-Type", "application/json");
        response = request.when().get("http://localhost:8000/olympicGames/metal");
        allMetals = JsonPath.from(response.asString()).get();
        //Obtenemos un metal de la lista de manera aleatoria para realizar comprobaciones en los diferentes endpoints
        randomMetal = allMetals.get((int) (Math.random() * allMetals.size()));
        for (Map.Entry<String, Object> entry : randomMetal.entrySet()) {
            if (entry.getKey().equals("metalId")) {
                randomId = Long.parseLong(entry.getValue().toString());
            } else {
                randomMetalType = entry.getValue().toString();
            }
        }
        //Obtenemos el metal de la lista que se ha añadido durante la ejecución de las pruebas
        for (Map<String, Object> metal : allMetals) {
            for (Map.Entry<String, Object> entry : metal.entrySet()) {
                if (entry.getKey().equals("metalType") && entry.getValue().equals("metal_type")) {
                    metalToUpdate = metal;
                    break;
                }
            }
        }
        if (metalToUpdate != null) {
            for (Map.Entry<String, Object> entry : metalToUpdate.entrySet()) {
                if (entry.getKey().equals("metalId")) {
                    idToUpdateDelete = Long.parseLong(entry.getValue().toString());
                }
            }
        }
        //Obtenemos el metal de la lista que se ha modificado durante la ejecución de las pruebas
        for (Map<String, Object> metal : allMetals) {
            for (Map.Entry<String, Object> entry : metal.entrySet()) {
                if (entry.getKey().equals("metalType") && entry.getValue().equals("mt_updated")) {
                    metalToUpdate = metal;
                    break;
                }
            }
        }
        if (metalToUpdate != null) {
            for (Map.Entry<String, Object> entry : metalToUpdate.entrySet()) {
                if (entry.getKey().equals("metalId")) {
                    idToUpdateDelete = Long.parseLong(entry.getValue().toString());
                }
            }
        }
    }

    @After
    public void deleteDataGeneration() {
        if (responseInformation != null) {
            responseInformation.clear();
        }
        if (allMetals != null) {
            allMetals.clear();
        }
        if (newMetal != null) {
            newMetal.clear();
        }
        if (newMetalToUpdate != null) {
            newMetalToUpdate.clear();
        }
        if (randomMetal != null) {
            randomMetal.clear();
        }
        if (metalToUpdate != null) {
            metalToUpdate.clear();
        }
    }

    @Given("a list of metals")
    public void a_list_of_metals() {
        request = given().header("Content-Type", "application/json");
    }

//    @Given("a list of empty metals")
//    public void a_list_of_empty_metals() {
//        request = given().header("Content-Type", "application/json");
//    }

    @Given("a new metal with metal type {string}")
    public void a_new_metal_with_metal_type(String metalType) {
        newMetal = new HashMap<>();
        newMetal.put("metalType", metalType);
        request = given().header("Content-Type", "application/json");
    }

    @Given("a new metal without metal type")
    public void a_new_metal_without_metal_type() {
        newMetal = new HashMap<>();
        newMetal.put("metalType", null);
        request = given().header("Content-Type", "application/json");
    }

    @Given("an existing metal")
    public void an_existing_metal() {
        request = given().header("Content-Type", "application/json");
    }

    @Given("a non-existent metal")
    public void a_non_existent_metal() {
        idToUpdateDelete = 9999L;
        request = given().header("Content-Type", "application/json");
    }

    @When("we want to see all metals information")
    public void we_want_to_see_all_metals_information() {
        response = request.when().get("http://localhost:8000/olympicGames/metal");
        responseInformation = JsonPath.from(response.asString()).get();
    }

//    @When("we want to see all metals information when the list is empty")
//    public void we_want_to_see_all_metals_information_when_the_list_is_empty() {
//        response = request.when().get("http://localhost:8000/olympicGames/metal");
//    }

    @When("we want to see the information of a concrete metal by its identifier")
    public void we_want_to_see_the_information_of_a_concrete_metal_by_its_identifier() {
        response = request.when().get("http://localhost:8000/olympicGames/metal/id=" + randomId);
        uniqueResponseInformation = JsonPath.from(response.asString()).get();
    }

    @When("we want to see the information of a concrete metal by a non-existent identifier")
    public void we_want_to_see_the_information_of_a_concrete_metal_by_a_non_existent_identifier() {
        response = request.when().get("http://localhost:8000/olympicGames/metal/id=9999");
    }

    @When("we want to see the information of a concrete metal by its metal type")
    public void we_want_to_see_the_information_of_a_concrete_metal_by_its_metal_type() {
        response = request.when().get("http://localhost:8000/olympicGames/metal/type=" + randomMetalType);
        uniqueResponseInformation = JsonPath.from(response.asString()).get();
    }

    @When("we want to see the information of a concrete metal by a non-existent metal type")
    public void we_want_to_see_the_information_of_a_concrete_metal_by_a_non_existent_metal_type() {
        response = request.when().get("http://localhost:8000/olympicGames/metal/type=Test");
    }

    @When("we want to add this new metal")
    public void we_want_to_add_this_new_metal() {
        response = request.when().body(newMetal).post("http://localhost:8000/olympicGames/metal");
    }

    @When("we want to update its metal type to {string}")
    public void we_want_to_update_its_metal_type(String metalType) {
        newMetalToUpdate = new HashMap<>();
        newMetalToUpdate.put("metalType", metalType);
        response = request.when().body(newMetalToUpdate).patch("http://localhost:8000/olympicGames/metal/id=" + idToUpdateDelete);
    }

    @When("we want to update its metal type with null data")
    public void we_want_to_update_its_metal_type_with_null_data() {
        newMetalToUpdate = new HashMap<>();
        newMetalToUpdate.put("metalType", null);
        response = request.when().body(newMetalToUpdate).patch("http://localhost:8000/olympicGames/metal/id=" + idToUpdateDelete);
    }

    @When("we want to delete this metal")
    public void we_want_to_delete_it() {
        response = request.when().delete("http://localhost:8000/olympicGames/metal/id=" + idToUpdateDelete);
    }

    @Then("all metals information is shown")
    public void all_metals_information_is_shown() {
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertFalse(responseInformation.isEmpty());
    }

//    @Then("no metals information is shown and a not found error is shown")
//    public void no_metals_information_is_shown_and_a_not_found_error_is_shown() {
//        Assert.assertEquals(404, response.getStatusCode());
//    }

    @Then("the metal information is shown")
    public void the_metal_information_is_shown() {
        Assert.assertEquals(200, response.getStatusCode());
        for (Map.Entry<String, Object> entry : uniqueResponseInformation.entrySet()) {
            if (entry.getKey().equals("metalId")) {
                Assert.assertEquals(randomId.toString(), entry.getValue().toString());
            } else {
                Assert.assertEquals(randomMetalType, entry.getValue());
            }
        }
    }

    @Then("no metal information is shown and a not found error is shown")
    public void no_metal_information_is_shown_and_a_not_found_error_is_shown() {
        Assert.assertEquals(404, response.getStatusCode());
    }

    @Then("the metal is correctly added and its information is shown")
    public void the_metal_is_correctly_added_and_its_information_is_shown() {
        Assert.assertEquals(201, response.getStatusCode());
        uniqueResponseInformation = JsonPath.from(response.asString()).get();
        for (Map.Entry<String, Object> entry : uniqueResponseInformation.entrySet()) {
            if (entry.getKey().equals("metalType")) {
                Assert.assertEquals("metal_type", entry.getValue());
            }
        }
    }

    @Then("a bad request error is shown for the metal")
    public void a_bad_request_error_is_shown_for_the_metal() {
        Assert.assertEquals(400, response.getStatusCode());
    }

    @Then("no metal information is shown and a duplicated information error is shown")
    public void no_metal_information_is_shown_and_a_duplicated_information_error_is_shown() {
        Assert.assertEquals(409, response.getStatusCode());
    }

    @Then("the metal is correctly updated and its information is shown")
    public void the_metal_is_correctly_updated_and_its_information_is_shown() {
        Assert.assertEquals(200, response.getStatusCode());
        uniqueResponseInformation = JsonPath.from(response.asString()).get();
        for (Map.Entry<String, Object> entry : uniqueResponseInformation.entrySet()) {
            if (entry.getKey().equals("metalType")) {
                Assert.assertEquals("mt_updated", entry.getValue());
            } else {
                Assert.assertEquals(idToUpdateDelete.toString(), entry.getValue().toString());
            }
        }
    }

    @Then("the metal is correctly deleted")
    public void the_metal_is_correctly_deleted() {
        Assert.assertEquals(204, response.getStatusCode());
    }

}