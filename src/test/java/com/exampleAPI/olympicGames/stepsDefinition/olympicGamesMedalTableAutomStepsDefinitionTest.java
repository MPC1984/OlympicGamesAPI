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

public class olympicGamesMedalTableAutomStepsDefinitionTest extends CucumberSpringConfiguration {

    private RequestSpecification request;
    private Response response;
    private Map<String, Object> uniqueResponseInformation, newItem, newItemToUpdate;
    private List<Map<String, Object>> responseInformation;
    private Long id, olympicGamesId, sportId, metalId, athleteId;
    private Integer olympicGamesYear;
    private String key, athleteName, athleteSurname, athleteCountry, metalType, olympicGamesPlace, sportName, sportCategoryName;
    private Object value;

    @Given("a list of records of the Olympic Games medal table")
    public void test_1() {
        request = given().header("Content-Type", "application/json");
    }

    @When("we want to see all records of the Olympic Games medal table information")
    public void test_2() {
        response = request.when().get("http://localhost:8000/olympicGames/medalTable");
    }

    @Then("all records of the Olympic Games medal table information is shown")
    public void test_3() {
        Assert.assertEquals(200, response.getStatusCode());
        responseInformation = JsonPath.from(response.asString()).get();
        Assert.assertFalse(responseInformation.isEmpty());
    }

    @Given("a concrete Olympic Games medal table identifier {long}")
    public void test_4(Long id) {
        request = given().header("Content-Type", "application/json");
        this.id = id;
    }

    @When("we want to see the information of the records of the Olympic Games medal table by Olympic Games medal table identifier")
    public void test_5() {
        response = request.when().get("http://localhost:8000/olympicGames/medalTable/id=" + id);
    }

    @Then("the records of the Olympic Games medal table information by Olympic Games medal table identifier is shown")
    public void test_6() {
        Assert.assertEquals(200, response.getStatusCode());
        uniqueResponseInformation = JsonPath.from(response.asString()).get();
        for (Map.Entry<String, Object> entry : uniqueResponseInformation.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            if (key.equals("olympicGamesMedalTableId")) {
                Assert.assertEquals(id.toString(), value.toString());
                break;
            }
        }
    }

    @Then("a not found error is shown for the records of the Olympic Games medal table")
    public void test_9() {
        Assert.assertEquals(404, response.getStatusCode());
    }

    @Given("a concrete Olympic Games medal table Olympic Games identifier {long}")
    public void test_10(Long olympicGamesId) {
        request = given().header("Content-Type", "application/json");
        this.olympicGamesId = olympicGamesId;
    }

    @When("we want to see the information of the records of the Olympic Games medal table by Olympic Games medal table Olympic Games identifier")
    public void test_11() {
        response = request.when().get("http://localhost:8000/olympicGames/medalTable/idOlympicGames=" + olympicGamesId);
    }

    @Then("the records of the Olympic Games medal table information by Olympic Games medal table Olympic Games identifier is shown")
    public void test_12() {
        Assert.assertEquals(200, response.getStatusCode());
        responseInformation = JsonPath.from(response.asString()).get();
        for (Map<String, Object> item : responseInformation) {
            for (Map.Entry<String, Object> entry : item.entrySet()) {
                key = entry.getKey();
                value = entry.getValue();
                if (key.equals("olympicGames")) {
                    for (Map.Entry<String, Object> entryItem : ((Map<String, Object>) value).entrySet()) {
                        if (entryItem.getKey().equals("olympicGamesId")) {
                            Assert.assertEquals(olympicGamesId.toString(), entryItem.getValue().toString());
                            break;
                        }
                    }
                }
            }
        }
    }

    @Given("a concrete Olympic Games medal table Olympic Games year {int}")
    public void test_16(Integer olympicGamesYear) {
        request = given().header("Content-Type", "application/json");
        this.olympicGamesYear = olympicGamesYear;
    }

    @When("we want to see the information of the records of the Olympic Games medal table by Olympic Games medal table Olympic Games year")
    public void test_17() {
        response = request.when().get("http://localhost:8000/olympicGames/medalTable/olympicGamesYear=" + olympicGamesYear);
    }

    @Then("the records of the Olympic Games medal table information by Olympic Games medal table Olympic Games year is shown")
    public void test_18() {
        Assert.assertEquals(200, response.getStatusCode());
        responseInformation = JsonPath.from(response.asString()).get();
        for (Map<String, Object> item : responseInformation) {
            for (Map.Entry<String, Object> entry : item.entrySet()) {
                key = entry.getKey();
                value = entry.getValue();
                if (key.equals("olympicGames")) {
                    for (Map.Entry<String, Object> entryItem : ((Map<String, Object>) value).entrySet()) {
                        if (entryItem.getKey().equals("olympicGamesYear")) {
                            Assert.assertEquals(olympicGamesYear.toString(), entryItem.getValue().toString());
                            break;
                        }
                    }
                }
            }
        }
    }

    @Given("a concrete Olympic Games medal table Olympic Games place {string}")
    public void test_22(String olympicGamesPlace) {
        request = given().header("Content-Type", "application/json");
        this.olympicGamesPlace = olympicGamesPlace;
    }

    @When("we want to see the information of the records of the Olympic Games medal table by Olympic Games medal table Olympic Games place")
    public void test_23() {
        response = request.when().get("http://localhost:8000/olympicGames/medalTable/olympicGamesPlace=" + olympicGamesPlace);
    }

    @Then("the records of the Olympic Games medal table information by Olympic Games medal table Olympic Games place is shown")
    public void test_24() {
        Assert.assertEquals(200, response.getStatusCode());
        responseInformation = JsonPath.from(response.asString()).get();
        for (Map<String, Object> item : responseInformation) {
            for (Map.Entry<String, Object> entry : item.entrySet()) {
                key = entry.getKey();
                value = entry.getValue();
                if (key.equals("olympicGames")) {
                    for (Map.Entry<String, Object> entryItem : ((Map<String, Object>) value).entrySet()) {
                        if (entryItem.getKey().equals("olympicGamesPlace")) {
                            Assert.assertEquals(olympicGamesPlace, entryItem.getValue());
                            break;
                        }
                    }
                }
            }
        }
    }

    @Given("a concrete Olympic Games medal table sport identifier {long}")
    public void test_28(Long sportId) {
        request = given().header("Content-Type", "application/json");
        this.sportId = sportId;
    }

    @When("we want to see the information of the records of the Olympic Games medal table by Olympic Games medal table sport identifier")
    public void test_29() {
        response = request.when().get("http://localhost:8000/olympicGames/medalTable/idSport=" + sportId);
    }

    @Then("the records of the Olympic Games medal table information by Olympic Games medal table sport identifier is shown")
    public void test_30() {
        Assert.assertEquals(200, response.getStatusCode());
        responseInformation = JsonPath.from(response.asString()).get();
        for (Map<String, Object> item : responseInformation) {
            for (Map.Entry<String, Object> entry : item.entrySet()) {
                key = entry.getKey();
                value = entry.getValue();
                if (key.equals("sport")) {
                    for (Map.Entry<String, Object> entryItem : ((Map<String, Object>) value).entrySet()) {
                        if (entryItem.getKey().equals("sportId")) {
                            Assert.assertEquals(sportId.toString(), entryItem.getValue().toString());
                            break;
                        }
                    }
                }
            }
        }
    }

    @Given("a concrete Olympic Games medal table sport name {string}")
    public void test_34(String sportName) {
        request = given().header("Content-Type", "application/json");
        this.sportName = sportName;
    }

    @When("we want to see the information of the records of the Olympic Games medal table by Olympic Games medal table sport name")
    public void test_35() {
        response = request.when().get("http://localhost:8000/olympicGames/medalTable/sportName=" + sportName);
    }

    @Then("the records of the Olympic Games medal table information by Olympic Games medal table sport name is shown")
    public void test_36() {
        Assert.assertEquals(200, response.getStatusCode());
        responseInformation = JsonPath.from(response.asString()).get();
        for (Map<String, Object> item : responseInformation) {
            for (Map.Entry<String, Object> entry : item.entrySet()) {
                key = entry.getKey();
                value = entry.getValue();
                if (key.equals("sport")) {
                    for (Map.Entry<String, Object> entryItem : ((Map<String, Object>) value).entrySet()) {
                        if (entryItem.getKey().equals("sportName")) {
                            Assert.assertEquals(sportName, entryItem.getValue());
                            break;
                        }
                    }
                }
            }
        }
    }

    @Given("a concrete Olympic Games medal table sport category {string}")
    public void test_40(String sportCategoryName) {
        request = given().header("Content-Type", "application/json");
        this.sportCategoryName = sportCategoryName;
    }

    @When("we want to see the information of the records of the Olympic Games medal table by Olympic Games medal table sport category")
    public void test_41() {
        response = request.when().get("http://localhost:8000/olympicGames/medalTable/categoryName=" + sportCategoryName);
    }

    @Then("the records of the Olympic Games medal table information by Olympic Games medal table sport category is shown")
    public void test_42() {
        Assert.assertEquals(200, response.getStatusCode());
        responseInformation = JsonPath.from(response.asString()).get();
        for (Map<String, Object> item : responseInformation) {
            for (Map.Entry<String, Object> entry : item.entrySet()) {
                key = entry.getKey();
                value = entry.getValue();
                if (key.equals("sport")) {
                    for (Map.Entry<String, Object> entryItem : ((Map<String, Object>) value).entrySet()) {
                        if (entryItem.getKey().equals("sportCategoryName")) {
                            Assert.assertEquals(sportCategoryName, entryItem.getValue());
                            break;
                        }
                    }
                }
            }
        }
    }

    @Given("a concrete Olympic Games medal table metal identifier {long}")
    public void test_46(Long metalId) {
        request = given().header("Content-Type", "application/json");
        this.metalId = metalId;
    }

    @When("we want to see the information of the records of the Olympic Games medal table by Olympic Games medal table metal identifier")
    public void test_47() {
        response = request.when().get("http://localhost:8000/olympicGames/medalTable/idMetal=" + metalId);
    }

    @Then("the records of the Olympic Games medal table information by Olympic Games medal table metal identifier is shown")
    public void test_48() {
        Assert.assertEquals(200, response.getStatusCode());
        responseInformation = JsonPath.from(response.asString()).get();
        for (Map<String, Object> item : responseInformation) {
            for (Map.Entry<String, Object> entry : item.entrySet()) {
                key = entry.getKey();
                value = entry.getValue();
                if (key.equals("metal")) {
                    for (Map.Entry<String, Object> entryItem : ((Map<String, Object>) value).entrySet()) {
                        if (entryItem.getKey().equals("metalId")) {
                            Assert.assertEquals(metalId.toString(), entryItem.getValue().toString());
                            break;
                        }
                    }
                }
            }
        }
    }

    @Given("a concrete Olympic Games medal table metal type {string}")
    public void test_52(String metalType) {
        request = given().header("Content-Type", "application/json");
        this.metalType = metalType;
    }

    @When("we want to see the information of the records of the Olympic Games medal table by Olympic Games medal table metal type")
    public void test_53() {
        response = request.when().get("http://localhost:8000/olympicGames/medalTable/metalType=" + metalType);
    }

    @Then("the records of the Olympic Games medal table information by Olympic Games medal table metal type is shown")
    public void test_54() {
        Assert.assertEquals(200, response.getStatusCode());
        responseInformation = JsonPath.from(response.asString()).get();
        for (Map<String, Object> item : responseInformation) {
            for (Map.Entry<String, Object> entry : item.entrySet()) {
                key = entry.getKey();
                value = entry.getValue();
                if (key.equals("metal")) {
                    for (Map.Entry<String, Object> entryItem : ((Map<String, Object>) value).entrySet()) {
                        if (entryItem.getKey().equals("metalType")) {
                            Assert.assertEquals(metalType, entryItem.getValue());
                            break;
                        }
                    }
                }
            }
        }
    }

    @Given("a concrete Olympic Games medal table athlete identifier {long}")
    public void test_58(Long athleteId) {
        request = given().header("Content-Type", "application/json");
        this.athleteId = athleteId;
    }

    @When("we want to see the information of the records of the Olympic Games medal table by Olympic Games medal table athlete identifier")
    public void test_59() {
        response = request.when().get("http://localhost:8000/olympicGames/medalTable/idAthlete=" + athleteId);
    }

    @Then("the records of the Olympic Games medal table information by Olympic Games medal table athlete identifier is shown")
    public void test_60() {
        Assert.assertEquals(200, response.getStatusCode());
        responseInformation = JsonPath.from(response.asString()).get();
        for (Map<String, Object> item : responseInformation) {
            for (Map.Entry<String, Object> entry : item.entrySet()) {
                key = entry.getKey();
                value = entry.getValue();
                if (key.equals("athlete")) {
                    for (Map.Entry<String, Object> entryItem : ((Map<String, Object>) value).entrySet()) {
                        if (entryItem.getKey().equals("athleteId")) {
                            Assert.assertEquals(athleteId.toString(), entryItem.getValue().toString());
                            break;
                        }
                    }
                }
            }
        }
    }

    @Given("a list of records of the Olympic Games medal table athlete name {string}")
    public void test_64(String athleteName) {
        request = given().header("Content-Type", "application/json");
        this.athleteName = athleteName;
    }

    @When("we want to see the information of the records of the Olympic Games medal table by Olympic Games medal table athlete name")
    public void test_65() {
        response = request.when().get("http://localhost:8000/olympicGames/medalTable/athleteName=" + athleteName);
    }

    @Then("the records of the Olympic Games medal table information by Olympic Games medal table athlete name is shown")
    public void test_66() {
        Assert.assertEquals(200, response.getStatusCode());
        responseInformation = JsonPath.from(response.asString()).get();
        for (Map<String, Object> item : responseInformation) {
            for (Map.Entry<String, Object> entry : item.entrySet()) {
                key = entry.getKey();
                value = entry.getValue();
                if (key.equals("athlete")) {
                    for (Map.Entry<String, Object> entryItem : ((Map<String, Object>) value).entrySet()) {
                        if (entryItem.getKey().equals("athleteName")) {
                            Assert.assertEquals(athleteName, entryItem.getValue());
                            break;
                        }
                    }
                }
            }
        }
    }

    @Given("a list of records of the Olympic Games medal table athlete surname {string}")
    public void test_70(String athleteSurname) {
        request = given().header("Content-Type", "application/json");
        this.athleteSurname = athleteSurname;
    }

    @When("we want to see the information of the records of the Olympic Games medal table by Olympic Games medal table athlete surname")
    public void test_71() {
        response = request.when().get("http://localhost:8000/olympicGames/medalTable/athleteSurname=" + athleteSurname);
    }

    @Then("the records of the Olympic Games medal table information by Olympic Games medal table athlete surname is shown")
    public void test_72() {
        Assert.assertEquals(200, response.getStatusCode());
        responseInformation = JsonPath.from(response.asString()).get();
        for (Map<String, Object> item : responseInformation) {
            for (Map.Entry<String, Object> entry : item.entrySet()) {
                key = entry.getKey();
                value = entry.getValue();
                if (key.equals("athlete")) {
                    for (Map.Entry<String, Object> entryItem : ((Map<String, Object>) value).entrySet()) {
                        if (entryItem.getKey().equals("athleteSurname")) {
                            Assert.assertEquals(athleteSurname, entryItem.getValue());
                            break;
                        }
                    }
                }
            }
        }
    }

    @Given("a list of records of the Olympic Games medal table athlete country {string}")
    public void test_76(String athleteCountry) {
        request = given().header("Content-Type", "application/json");
        this.athleteCountry = athleteCountry;
    }

    @When("we want to see the information of the records of the Olympic Games medal table by Olympic Games medal table athlete country")
    public void test_77() {
        response = request.when().get("http://localhost:8000/olympicGames/medalTable/athleteCountry=" + athleteCountry);
    }

    @Then("the records of the Olympic Games medal table information by Olympic Games medal table athlete country is shown")
    public void test_78() {
        Assert.assertEquals(200, response.getStatusCode());
        responseInformation = JsonPath.from(response.asString()).get();
        for (Map<String, Object> item : responseInformation) {
            for (Map.Entry<String, Object> entry : item.entrySet()) {
                key = entry.getKey();
                value = entry.getValue();
                if (key.equals("athlete")) {
                    for (Map.Entry<String, Object> entryItem : ((Map<String, Object>) value).entrySet()) {
                        if (entryItem.getKey().equals("athleteCountry")) {
                            Assert.assertEquals(athleteCountry, entryItem.getValue());
                            break;
                        }
                    }
                }
            }
        }
    }

    @Given("a new record of the Olympic Games medal table with Olympic Games medal table Olympic Games identifier {long} Olympic Games medal table sport identifier {long} Olympic Games medal table metal identifier {long} and Olympic Games medal table athlete identifier {long}")
    public void test_82(Long olympicGamesId, Long sportId, Long metalId, Long athleteId) {
        request = given().header("Content-Type", "application/json");
        newItem = new HashMap<>();
        newItem.put("idAthlete", athleteId);
        newItem.put("idMetal", metalId);
        newItem.put("idOlympicGames", olympicGamesId);
        newItem.put("idSport", sportId);
    }

    @When("we want to add this new record of the Olympic Games medal table")
    public void test_83() {
        response = request.when().body(newItem).post("http://localhost:8000/olympicGames/medalTable");
    }

    @Then("the record of the Olympic Games medal table is correctly added and its information is shown")
    public void test_84() {
        Assert.assertEquals(201, response.getStatusCode());
        uniqueResponseInformation = JsonPath.from(response.asString()).get();
        for (Map.Entry<String, Object> entry : uniqueResponseInformation.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            if (key.equals("olympicGames")) {
                for (Map.Entry<String, Object> entryItem : ((Map<String, Object>) value).entrySet()) {
                    if (entryItem.getKey().equals("olympicGamesId")) {
                        Assert.assertEquals((newItem.get("idOlympicGames")).toString(), entryItem.getValue().toString());
                    }
                }
            } else if (key.equals("sport")) {
                for (Map.Entry<String, Object> entryItem : ((Map<String, Object>) value).entrySet()) {
                    if (entryItem.getKey().equals("sportId")) {
                        Assert.assertEquals((newItem.get("idSport")).toString(), entryItem.getValue().toString());
                    }
                }
            } else if (key.equals("metal")) {
                for (Map.Entry<String, Object> entryItem : ((Map<String, Object>) value).entrySet()) {
                    if (entryItem.getKey().equals("metalId")) {
                        Assert.assertEquals((newItem.get("idMetal")).toString(), entryItem.getValue().toString());
                    }
                }
            } else if (key.equals("athlete")) {
                for (Map.Entry<String, Object> entryItem : ((Map<String, Object>) value).entrySet()) {
                    if (entryItem.getKey().equals("athleteId")) {
                        Assert.assertEquals((newItem.get("idAthlete")).toString(), entryItem.getValue().toString());
                    }
                }
            }
        }
    }

    @When("we want to update Olympic Games medal table athlete identifier {long}")
    public void test_89(Long athleteId) {
        newItemToUpdate = new HashMap<>();
        newItemToUpdate.put("idAthlete", athleteId);
        response = request.when().body(newItemToUpdate).patch("http://localhost:8000/olympicGames/medalTable/id=" + id);
    }

    @Then("the record of the Olympic Games medal table is correctly updated and its information is shown")
    public void test_90() {
        Assert.assertEquals(200, response.getStatusCode());
        uniqueResponseInformation = JsonPath.from(response.asString()).get();
        for (Map.Entry<String, Object> entry : uniqueResponseInformation.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            if (key.equals("olympicGamesMedalTableId")) {
                Assert.assertEquals(id.toString(), value.toString());
            }
            if (key.equals("athlete")) {
                for (Map.Entry<String, Object> entryItem : ((Map<String, Object>) value).entrySet()) {
                    if (entryItem.getKey().equals("athleteId")) {
                        Assert.assertEquals((newItemToUpdate.get("idAthlete")).toString(), entryItem.getValue().toString());
                    }
                }
            }
        }
    }

    @When("we want to update Olympic Games medal table Olympic Games identifier {long} Olympic Games medal table sport identifier {long} Olympic Games medal table metal identifier {long} and Olympic Games medal table athlete identifier {long}")
    public void test_92(Long olympicGamesId, Long sportId, Long metalId, Long athleteId) {
        newItemToUpdate = new HashMap<>();
        newItemToUpdate.put("idOlympicGames", olympicGamesId);
        newItemToUpdate.put("idSport", sportId);
        newItemToUpdate.put("idMetal", metalId);
        newItemToUpdate.put("idAthlete", athleteId);
        response = request.when().body(newItemToUpdate).patch("http://localhost:8000/olympicGames/medalTable/id=" + id);
    }

    @When("we want to delete this record of the Olympic Games medal table")
    public void test_98() {
        response = request.when().delete("http://localhost:8000/olympicGames/medalTable/id=" + id);
    }

    @Then("the record of the Olympic Games medal table is correctly deleted")
    public void test_99() {
        Assert.assertEquals(204, response.getStatusCode());
    }

}
