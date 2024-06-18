package com.exampleAPI.olympicGames.stepDefinitions;

import com.exampleAPI.olympicGames.runners.CucumberSpringConfiguration;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static io.restassured.RestAssured.given;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
public class MetalStepDefinitionsTest extends CucumberSpringConfiguration {

    private RequestSpecification request;
    private Response response;
    private Map<String, Object> responseInformation;
    private List<Map<String, Object>> allMetals;
    private Map<String, String> newMetal, newMetalToUpdate;
    private Map<String, Object> randomMetal, metalToUpdate;
    private Long randomId, idToUpdate;
    private String randomMetalType;

    @Before
    public void dataGeneration() {
        request = given().header("Content-Type", "application/json");
        response = request.when().get("http://localhost:8000/olympicGames/metal");
        allMetals = JsonPath.from(response.asString()).get();
        //Obtenemos un metal de la lista de manera aleatoria para realizar comprobaciones en los diferentes endpoints
        randomMetal = allMetals.get((int) (Math.random() * allMetals.size()));
        for (Map.Entry<String, Object> entry : randomMetal.entrySet()) {
            if(entry.getKey().equals("id")){
                randomId = Long.parseLong(entry.getValue().toString());
            } else {
                randomMetalType = (String) entry.getValue();
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
        if(metalToUpdate != null) {
            for (Map.Entry<String, Object> entry : metalToUpdate.entrySet()) {
                if (entry.getKey().equals("id")) {
                    idToUpdate = Long.parseLong(entry.getValue().toString());
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
        if(metalToUpdate != null) {
            for (Map.Entry<String, Object> entry : metalToUpdate.entrySet()) {
                if (entry.getKey().equals("id")) {
                    idToUpdate = Long.parseLong(entry.getValue().toString());
                }
            }
        }
    }

    @Given("A list of metals")
    public void a_list_of_metals() {
        request = given().header("Content-Type", "application/json");
    }

    @Given("A new metal with metal type {string}")
    public void a_new_metal_with_metal_type(String metalType) {
        newMetal = new HashMap<>();
        newMetal.put("metalType", metalType);
        request = given().header("Content-Type", "application/json");
    }

    @Given("A new metal without metal type")
    public void a_new_metal_without_metal_type() {
        newMetal = new HashMap<>();
        newMetal.put("metalType", null);
        request = given().header("Content-Type", "application/json");
    }

    @Given("An existing metal")
    public void an_existing_metal() {
        request = given().header("Content-Type", "application/json");
    }

    @Given("A non-existent metal")
    public void a_non_existent_metal() {
        idToUpdate = 9999L;
        request = given().header("Content-Type", "application/json");
    }

    @When("we want to see all metals information")
    public void we_want_to_see_all_metals_information() {
        response = request.when().get("http://localhost:8000/olympicGames/metal");
    }

//    @When("we want to see all metals information when the list is empty")
//    public void we_want_to_see_all_metals_information_when_the_list_is_empty() {
//        response = request.when().get("http://localhost:8000/olympicGames/metal");
//    }

    @When("we want to see the information of a concrete metal by its identifier")
    public void we_want_to_see_the_information_of_a_concrete_metal_by_its_identifier() {
        response = request.when().get("http://localhost:8000/olympicGames/metal/id=" + randomId);
        responseInformation = JsonPath.from(response.asString()).get();
    }

    @When("we want to see the information of a concrete metal by a non-existent identifier")
    public void we_want_to_see_the_information_of_a_concrete_metal_by_a_non_existent_identifier() {
        response = request.when().get("http://localhost:8000/olympicGames/metal/id=9999");
    }

    @When("we want to see the information of a concrete metal by its metal type")
    public void we_want_to_see_the_information_of_a_concrete_metal_by_its_metal_type() {
        response = request.when().get("http://localhost:8000/olympicGames/metal/type=" + randomMetalType);
        responseInformation = JsonPath.from(response.asString()).get();
    }

    @When("we want to see the information of a concrete metal by a non-existent metal type")
    public void we_want_to_see_the_information_of_a_concrete_metal_by_a_non_existent_metal_type() {
        response = request.when().get("http://localhost:8000/olympicGames/metal/type=9999");
    }

    @When("we want to add this new metal")
    public void we_want_to_add_this_new_metal() {
        response = request.when().body(newMetal).post("http://localhost:8000/olympicGames/metal");
    }

    @When("we want to update its metal type to {string}")
    public void we_want_to_update_its_metal_type(String metalType) {
        newMetalToUpdate = new HashMap<>();
        newMetalToUpdate.put("metalType", metalType);
        response = request.when().body(newMetalToUpdate).patch("http://localhost:8000/olympicGames/metal/id=" + idToUpdate);
    }

    @When("we want to delete this metal")
    public void we_want_to_delete_it() {
        response = request.when().delete("http://localhost:8000/olympicGames/metal/id=" + idToUpdate);
    }

    @Then("all metals information is shown")
    public void all_metals_information_is_shown() {
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertFalse(allMetals.isEmpty());
    }

    @Then("no information is shown and an error is shown")
    public void no_information_is_shown_and_an_error_is_shown() {
        Assert.assertEquals(404, response.getStatusCode());
    }

    @Then("the metal information is shown")
    public void the_metal_information_is_shown() {
        Assert.assertEquals(200, response.getStatusCode());
        for (Map.Entry<String, Object> entry : responseInformation.entrySet()) {
            if (entry.getKey().equals("id")) {
                Assert.assertEquals(randomId.toString(), entry.getValue().toString());
            } else {
                Assert.assertEquals(randomMetalType, entry.getValue());
            }
        }
    }

    @Then("the metal is correctly added and its information is shown")
    public void the_metal_is_correctly_added_and_its_information_is_shown() {
        Assert.assertEquals(201, response.getStatusCode());
        responseInformation = JsonPath.from(response.asString()).get();
        for (Map.Entry<String, Object> entry : responseInformation.entrySet()) {
            if (entry.getKey().equals("metalType")) {
                Assert.assertEquals("metal_type", entry.getValue());
            }
        }
        newMetal.clear();
    }

    @Then("an error is shown")
    public void an_error_is_shown() {
        Assert.assertEquals(400, response.getStatusCode());
        newMetal.clear();
    }

    @Then("the metal is correctly updated and its information is shown")
    public void the_metal_is_correctly_updated_anbd_its_information_is_shown() {
        Assert.assertEquals(200, response.getStatusCode());
        responseInformation = JsonPath.from(response.asString()).get();
        for (Map.Entry<String, Object> entry : responseInformation.entrySet()) {
            if (entry.getKey().equals("metalType")) {
                Assert.assertEquals("mt_updated", entry.getValue());
            } else {
                Assert.assertEquals(idToUpdate.toString(), entry.getValue().toString());
            }
        }
        newMetalToUpdate.clear();
    }

    @Then("the metal is correctly deleted")
    public void the_metal_is_correctly_deleted() {
        Assert.assertEquals(204, response.getStatusCode());
    }

}
