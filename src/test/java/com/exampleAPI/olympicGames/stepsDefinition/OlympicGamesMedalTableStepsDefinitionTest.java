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
public class OlympicGamesMedalTableStepsDefinitionTest extends CucumberSpringConfiguration {

    private RequestSpecification request;
    private Response response;
    private Map<String, Object> uniqueResponseInformation, newOlympicGamesMedalTable, newOlympicGamesMedalTableToUpdate, randomOlympicGamesMedalTable, olympicGamesMedalTableToUpdate;
    private List<Map<String, Object>> allOlympicGames, allSports, allMetals, allAthletes, allOlympicGamesMedalTable, responseInformation;
    private Long randomId, randomOlympicGamesId, randomSportId, randomMetalId, randomAthleteId, idToUpdateDelete;
    private Integer randomOlympicGamesYear;
    private String key, randomOlympicGamesPlace, randomSportName, randomSportCategoryName, randomMetalType, randomAthleteName, randomAthleteSurname, randomAthleteCountry;
    private Object value, valueSport;
    private Boolean olympicGamesFound, sportFound, metalFound, athleteFound;

    @Before
    public void dataGeneration() {
        //Obtenemos todos los Juegos Olímpicos
        request = given().header("Content-Type", "application/json");
        response = request.when().get("http://localhost:8000/olympicGames");
        allOlympicGames = JsonPath.from(response.asString()).get();
        //Obtenemos todos los deportes
        request = given().header("Content-Type", "application/json");
        response = request.when().get("http://localhost:8000/olympicGames/sport");
        allSports = JsonPath.from(response.asString()).get();
        //Obtenemos todos los metales
        request = given().header("Content-Type", "application/json");
        response = request.when().get("http://localhost:8000/olympicGames/metal");
        allMetals = JsonPath.from(response.asString()).get();
        //Obtenemos todos los atletas
        request = given().header("Content-Type", "application/json");
        response = request.when().get("http://localhost:8000/olympicGames/athlete");
        allAthletes = JsonPath.from(response.asString()).get();

        request = given().header("Content-Type", "application/json");
        response = request.when().get("http://localhost:8000/olympicGames/medalTable");
        allOlympicGamesMedalTable = JsonPath.from(response.asString()).get();
        //Obtenemos un registro del medallero olímpico de la lista de manera aleatoria para realizar comprobaciones en los diferentes endpoints
        while (randomSportCategoryName == null) {
            randomOlympicGamesMedalTable = allOlympicGamesMedalTable.get((int) (Math.random() * allOlympicGamesMedalTable.size()));
            for (Map.Entry<String, Object> entry : randomOlympicGamesMedalTable.entrySet()) {
                key = entry.getKey();
                value = entry.getValue();
                if (key.equals("olympicGamesMedalTableId")) {
                    randomId = Long.parseLong(value.toString());
                } else if (key.equals("olympicGames")) {
                    for (Map.Entry<String, Object> entryOlympicGames : ((Map<String, Object>) value).entrySet()) {
                        switch (entryOlympicGames.getKey()) {
                            case "olympicGamesId":
                                randomOlympicGamesId = Long.parseLong(entryOlympicGames.getValue().toString());
                                break;
                            case "olympicGamesYear":
                                randomOlympicGamesYear = Integer.parseInt(entryOlympicGames.getValue().toString());
                                break;
                            default:
                                randomOlympicGamesPlace = entryOlympicGames.getValue().toString();
                                break;
                        }
                    }
                } else if (key.equals("sport")) {
                    for (Map.Entry<String, Object> entrySport : ((Map<String, Object>) value).entrySet()) {
                        valueSport = entrySport.getValue();
                        switch (entrySport.getKey()) {
                            case "sportId":
                                randomSportId = Long.parseLong(valueSport.toString());
                                break;
                            case "sportName":
                                randomSportName = valueSport.toString();
                                break;
                            case "sportCategoryName":
                                if (valueSport != null) {
                                    randomSportCategoryName = valueSport.toString();
                                } else {
                                    randomSportCategoryName = null;
                                }
                                break;
                        }
                    }
                } else if (key.equals("metal")) {
                    for (Map.Entry<String, Object> entryMetal : ((Map<String, Object>) value).entrySet()) {
                        if (entryMetal.getKey().equals("metalId")) {
                            randomMetalId = Long.parseLong(entryMetal.getValue().toString());
                        } else {
                            randomMetalType = entryMetal.getValue().toString();
                        }
                    }
                } else if (key.equals("athlete")) {
                    for (Map.Entry<String, Object> entryAthlete : ((Map<String, Object>) value).entrySet()) {
                        switch (entryAthlete.getKey()) {
                            case "athleteId":
                                randomAthleteId = Long.parseLong(entryAthlete.getValue().toString());
                                break;
                            case "athleteName":
                                randomAthleteName = entryAthlete.getValue().toString();
                                break;
                            case "athleteSurname":
                                randomAthleteSurname = entryAthlete.getValue().toString();
                                break;
                            default:
                                randomAthleteCountry = entryAthlete.getValue().toString();
                                break;
                        }
                    }
                }
            }
        }
        //Obtenemos los Juegos Olímpicos de la lista que se han añadido durante la ejecución de las pruebas
        olympicGamesFound = false;
        sportFound = false;
        metalFound = false;
        athleteFound = false;
        for (Map<String, Object> olympicGamesMedalTable : allOlympicGamesMedalTable) {
            for (Map.Entry<String, Object> entry : olympicGamesMedalTable.entrySet()) {
                key = entry.getKey();
                value = entry.getValue();
                if (key.equals("olympicGames")) {
                    for (Map.Entry<String, Object> entryOlympicGames : ((Map<String, Object>) value).entrySet()) {
                        if (entryOlympicGames.getKey().equals("olympicGamesId") && entryOlympicGames.getValue().equals(1)) {
                            olympicGamesFound = true;
                            break;
                        }
                    }
                } else if (key.equals("sport")) {
                    for (Map.Entry<String, Object> entrySport : ((Map<String, Object>) value).entrySet()) {
                        valueSport = entrySport.getValue();
                        if (entrySport.getKey().equals("sportId") && entrySport.getValue().equals(1)) {
                            sportFound = true;
                            break;
                        }
                    }
                } else if (key.equals("metal")) {
                    for (Map.Entry<String, Object> entryMetal : ((Map<String, Object>) value).entrySet()) {
                        if (entryMetal.getKey().equals("metalId") && entryMetal.getValue().equals(1)) {
                            metalFound = true;
                            break;
                        }
                    }
                } else if (key.equals("athlete")) {
                    for (Map.Entry<String, Object> entryAthlete : ((Map<String, Object>) value).entrySet()) {
                        if (entryAthlete.getKey().equals("athleteId") && entryAthlete.getValue().equals(1)) {
                            athleteFound = true;
                            break;
                        }
                    }
                }
            }
            if (olympicGamesFound && sportFound && metalFound && athleteFound) {
                olympicGamesMedalTableToUpdate = olympicGamesMedalTable;
                break;
            } else {
                olympicGamesFound = false;
                sportFound = false;
                metalFound = false;
                athleteFound = false;
                olympicGamesMedalTableToUpdate = null;
            }
        }
        if (olympicGamesMedalTableToUpdate != null) {
            for (Map.Entry<String, Object> entry : olympicGamesMedalTableToUpdate.entrySet()) {
                if (entry.getKey().equals("olympicGamesMedalTableId")) {
                    idToUpdateDelete = Long.parseLong(entry.getValue().toString());
                    break;
                }
            }
        }
        //Obtenemos los Juegos Olímpicos de la lista que se han modificado durante la ejecución de las pruebas
        olympicGamesFound = false;
        sportFound = false;
        metalFound = false;
        athleteFound = false;
        for (Map<String, Object> olympicGamesMedalTable : allOlympicGamesMedalTable) {
            for (Map.Entry<String, Object> entry : olympicGamesMedalTable.entrySet()) {
                key = entry.getKey();
                value = entry.getValue();
                if (key.equals("olympicGames")) {
                    for (Map.Entry<String, Object> entryOlympicGames : ((Map<String, Object>) value).entrySet()) {
                        if (entryOlympicGames.getKey().equals("olympicGamesId") && entryOlympicGames.getValue().equals(2)) {
                            olympicGamesFound = true;
                            break;
                        }
                    }
                } else if (key.equals("sport")) {
                    for (Map.Entry<String, Object> entrySport : ((Map<String, Object>) value).entrySet()) {
                        valueSport = entrySport.getValue();
                        if (entrySport.getKey().equals("sportId") && entrySport.getValue().equals(2)) {
                            sportFound = true;
                            break;
                        }
                    }
                } else if (key.equals("metal")) {
                    for (Map.Entry<String, Object> entryMetal : ((Map<String, Object>) value).entrySet()) {
                        if (entryMetal.getKey().equals("metalId") && entryMetal.getValue().equals(2)) {
                            metalFound = true;
                            break;
                        }
                    }
                } else if (key.equals("athlete")) {
                    for (Map.Entry<String, Object> entryAthlete : ((Map<String, Object>) value).entrySet()) {
                        if (entryAthlete.getKey().equals("athleteId") && entryAthlete.getValue().equals(2)) {
                            athleteFound = true;
                            break;
                        }
                    }
                }
            }
            if (olympicGamesFound && sportFound && metalFound && athleteFound) {
                olympicGamesMedalTableToUpdate = olympicGamesMedalTable;
                break;
            } else {
                olympicGamesFound = false;
                sportFound = false;
                metalFound = false;
                athleteFound = false;
                olympicGamesMedalTableToUpdate = null;
            }
        }
        if (olympicGamesMedalTableToUpdate != null) {
            for (Map.Entry<String, Object> entry : olympicGamesMedalTableToUpdate.entrySet()) {
                if (entry.getKey().equals("olympicGamesMedalTableId")) {
                    idToUpdateDelete = Long.parseLong(entry.getValue().toString());
                    break;
                }
            }
        }
    }

    @After
    public void deleteDataGeneration() {
        if (uniqueResponseInformation != null) {
            uniqueResponseInformation.clear();
        }
        if (newOlympicGamesMedalTable != null) {
            newOlympicGamesMedalTable.clear();
        }
        if (newOlympicGamesMedalTableToUpdate != null) {
            newOlympicGamesMedalTableToUpdate.clear();
        }
        if (randomOlympicGamesMedalTable != null) {
            randomOlympicGamesMedalTable.clear();
        }
        if (olympicGamesMedalTableToUpdate != null) {
            olympicGamesMedalTableToUpdate.clear();
        }
        if (allOlympicGames != null) {
            allOlympicGames.clear();
        }
        if (allSports != null) {
            allSports.clear();
        }
        if (allMetals != null) {
            allMetals.clear();
        }
        if (allAthletes != null) {
            allAthletes.clear();
        }
        if (allOlympicGamesMedalTable != null) {
            allOlympicGamesMedalTable.clear();
        }
        if (responseInformation != null) {
            responseInformation.clear();
        }
    }

    @Given("a list of records of the Olympic Games medal table")
    public void a_list_of_records_of_the_olympic_games_medal_table() {
        request = given().header("Content-Type", "application/json");
    }

    @Given("a new record of the Olympic Games medal table with Olympic Games identifier {long}, sport identifier {long}, metal identifier {long} and athlete identifier {long}")
    public void a_new_record_of_the_olympic_games_medal_table_with_olympic_games_identifier_sport_identifier_metal_identifier_and_athlete_identifier(Long olympicGamesId, Long sportId, Long metalId, Long athleteId) {
        newOlympicGamesMedalTable = new HashMap<>();
        newOlympicGamesMedalTable.put("idOlympicGames", olympicGamesId);
        newOlympicGamesMedalTable.put("idSport", sportId);
        newOlympicGamesMedalTable.put("idMetal", metalId);
        newOlympicGamesMedalTable.put("idAthlete", athleteId);
        request = given().header("Content-Type", "application/json");
    }

    @Given("a new record of the Olympic Games medal table with Olympic Games identifier {long}")
    public void a_new_record_of_the_olympic_games_medal_table_with_olympic_games_identifier(Long olympicGamesId) {
        newOlympicGamesMedalTable = new HashMap<>();
        newOlympicGamesMedalTable.put("idOlympicGames", olympicGamesId);
        newOlympicGamesMedalTable.put("idSport", 1L);
        newOlympicGamesMedalTable.put("idMetal", 1L);
        newOlympicGamesMedalTable.put("idAthlete", 1L);
        request = given().header("Content-Type", "application/json");
    }

    @Given("a new record of the Olympic Games medal table with a non-existent Olympic Games identifier")
    public void a_new_record_of_the_olympic_games_medal_table_with_a_non_existent_olympic_games_identifier() {
        newOlympicGamesMedalTable = new HashMap<>();
        newOlympicGamesMedalTable.put("idOlympicGames", 9999L);
        newOlympicGamesMedalTable.put("idSport", 1L);
        newOlympicGamesMedalTable.put("idMetal", 1L);
        newOlympicGamesMedalTable.put("idAthlete", 1L);
        request = given().header("Content-Type", "application/json");
    }

    @Given("an existing record of the Olympic Games medal table")
    public void an_existing_record_of_the_olympic_games_medal_table() {
        request = given().header("Content-Type", "application/json");
    }

    @Given("a non-existent record of the Olympic Games medal table")
    public void a_non_existent_record_of_the_olympic_games_medal_table() {
        idToUpdateDelete = 9999L;
        request = given().header("Content-Type", "application/json");
    }

    @When("we want to see all records of the Olympic Games medal table information")
    public void we_want_to_see_all_records_of_the_olympic_games_medal_table_information() {
        response = request.when().get("http://localhost:8000/olympicGames/medalTable");
        responseInformation = JsonPath.from(response.asString()).get();
    }

    @When("we want to see the information of a concrete record of the Olympic Games medal table by its identifier")
    public void we_want_to_see_the_information_of_a_concrete_record_of_the_olympic_games_medal_table_by_its_identifier() {
        response = request.when().get("http://localhost:8000/olympicGames/medalTable/id=" + randomId);
        uniqueResponseInformation = JsonPath.from(response.asString()).get();
    }

    @When("we want to see the information of a concrete record of the Olympic Games medal table by a non-existent identifier")
    public void we_want_to_see_the_information_of_a_concrete_record_of_the_olympic_games_medal_table_by_a_non_existent_identifier() {
        response = request.when().get("http://localhost:8000/olympicGames/medalTable/id=9999");
    }

    @When("we want to see all records of the Olympic Games medal table information by their Olympic Games identifier")
    public void we_want_to_see_all_records_of_the_olympic_games_medal_table_information_by_their_olympic_games_identifier() {
        response = request.when().get("http://localhost:8000/olympicGames/medalTable/idOlympicGames=" + randomOlympicGamesId);
        responseInformation = JsonPath.from(response.asString()).get();
    }

    @When("we want to see all records of the Olympic Games medal table information by a non-existent Olympic Games identifier")
    public void we_want_to_see_all_records_of_the_olympic_games_medal_table_information_by_a_non_existent_olympic_games_identifier() {
        response = request.when().get("http://localhost:8000/olympicGames/medalTable/idOlympicGames=9999");
    }

    @When("we want to see all records of the Olympic Games medal table information by their Olympic Games year")
    public void we_want_to_see_all_records_of_the_olympic_games_medal_table_information_by_their_olympic_games_year() {
        response = request.when().get("http://localhost:8000/olympicGames/medalTable/olympicGamesYear=" + randomOlympicGamesYear);
        responseInformation = JsonPath.from(response.asString()).get();
    }

    @When("we want to see all records of the Olympic Games medal table information by a non-existent Olympic Games year")
    public void we_want_to_see_all_records_of_the_olympic_games_medal_table_information_by_a_non_existent_olympic_games_year() {
        response = request.when().get("http://localhost:8000/olympicGames/medalTable/olympicGamesYear=9999");
    }

    @When("we want to see all records of the Olympic Games medal table information by their Olympic Games place")
    public void we_want_to_see_all_records_of_the_olympic_games_medal_table_information_by_their_olympic_games_place() {
        response = request.when().get("http://localhost:8000/olympicGames/medalTable/olympicGamesPlace=" + randomOlympicGamesPlace);
        responseInformation = JsonPath.from(response.asString()).get();
    }

    @When("we want to see all records of the Olympic Games medal table information by a non-existent Olympic Games place")
    public void we_want_to_see_all_records_of_the_olympic_games_medal_table_information_by_a_non_existent_olympic_games_place() {
        response = request.when().get("http://localhost:8000/olympicGames/medalTable/olympicGamesPlace=Test");
    }

    @When("we want to see all records of the Olympic Games medal table information by their sport identifier")
    public void we_want_to_see_all_records_of_the_olympic_games_medal_table_information_by_their_sport_identifier() {
        response = request.when().get("http://localhost:8000/olympicGames/medalTable/idSport=" + randomSportId);
        responseInformation = JsonPath.from(response.asString()).get();
    }

    @When("we want to see all records of the Olympic Games medal table information by a non-existent sport identifier")
    public void we_want_to_see_all_records_of_the_olympic_games_medal_table_information_by_a_non_existent_sport_identifier() {
        response = request.when().get("http://localhost:8000/olympicGames/medalTable/idSport=9999");
    }

    @When("we want to see all records of the Olympic Games medal table information by their sport name")
    public void we_want_to_see_all_records_of_the_olympic_games_medal_table_information_by_their_sport_name() {
        response = request.when().get("http://localhost:8000/olympicGames/medalTable/sportName=" + randomSportName);
        responseInformation = JsonPath.from(response.asString()).get();
    }

    @When("we want to see all records of the Olympic Games medal table information by a non-existent sport name")
    public void we_want_to_see_all_records_of_the_olympic_games_medal_table_information_by_a_non_existent_sport_name() {
        response = request.when().get("http://localhost:8000/olympicGames/medalTable/sportName=Test");
    }

    @When("we want to see all records of the Olympic Games medal table information by their sport category")
    public void we_want_to_see_all_records_of_the_olympic_games_medal_table_information_by_their_sport_category() {
        response = request.when().get("http://localhost:8000/olympicGames/medalTable/categoryName=" + randomSportCategoryName);
        responseInformation = JsonPath.from(response.asString()).get();
    }

    @When("we want to see all records of the Olympic Games medal table information by a non-existent sport category")
    public void we_want_to_see_all_records_of_the_olympic_games_medal_table_information_by_a_non_existent_sport_category() {
        response = request.when().get("http://localhost:8000/olympicGames/medalTable/categoryName=Test");
    }

    @When("we want to see all records of the Olympic Games medal table information by their metal identifier")
    public void we_want_to_see_all_records_of_the_olympic_games_medal_table_information_by_their_metal_identifier() {
        response = request.when().get("http://localhost:8000/olympicGames/medalTable/idMetal=" + randomMetalId);
        responseInformation = JsonPath.from(response.asString()).get();
    }

    @When("we want to see all records of the Olympic Games medal table information by a non-existent metal identifier")
    public void we_want_to_see_all_records_of_the_olympic_games_medal_table_information_by_a_non_existent_metal_identifier() {
        response = request.when().get("http://localhost:8000/olympicGames/medalTable/idMetal=9999");
    }

    @When("we want to see all records of the Olympic Games medal table information by their metal ype")
    public void we_want_to_see_all_records_of_the_olympic_games_medal_table_information_by_their_metal_ype() {
        response = request.when().get("http://localhost:8000/olympicGames/medalTable/metalType=" + randomMetalType);
        responseInformation = JsonPath.from(response.asString()).get();
    }

    @When("we want to see all records of the Olympic Games medal table information by a non-existent metal type")
    public void we_want_to_see_all_records_of_the_olympic_games_medal_table_information_by_a_non_existent_metal_type() {
        response = request.when().get("http://localhost:8000/olympicGames/medalTable/metalType=Test");
    }

    @When("we want to see all records of the Olympic Games medal table information by their athlete identifier")
    public void we_want_to_see_all_records_of_the_olympic_games_medal_table_information_by_their_athlete_identifier() {
        response = request.when().get("http://localhost:8000/olympicGames/medalTable/idAthlete=" + randomAthleteId);
        responseInformation = JsonPath.from(response.asString()).get();
    }

    @When("we want to see all records of the Olympic Games medal table information by a non-existent athlete identifier")
    public void we_want_to_see_all_records_of_the_olympic_games_medal_table_information_by_a_non_existent_athlete_identifier() {
        response = request.when().get("http://localhost:8000/olympicGames/medalTable/idAthlete=9999");
    }

    @When("we want to see all records of the Olympic Games medal table information by their athlete name")
    public void we_want_to_see_all_records_of_the_olympic_games_medal_table_information_by_their_athlete_name() {
        response = request.when().get("http://localhost:8000/olympicGames/medalTable/athleteName=" + randomAthleteName);
        responseInformation = JsonPath.from(response.asString()).get();
    }

    @When("we want to see all records of the Olympic Games medal table information by a non-existent athlete name")
    public void we_want_to_see_all_records_of_the_olympic_games_medal_table_information_by_a_non_existent_athlete_name() {
        response = request.when().get("http://localhost:8000/olympicGames/medalTable/athleteName=Test");
    }

    @When("we want to see all records of the Olympic Games medal table information by their athlete surname")
    public void we_want_to_see_all_records_of_the_olympic_games_medal_table_information_by_their_athlete_surname() {
        response = request.when().get("http://localhost:8000/olympicGames/medalTable/athleteSurname=" + randomAthleteSurname);
        responseInformation = JsonPath.from(response.asString()).get();
    }

    @When("we want to see all records of the Olympic Games medal table information by a non-existent athlete surname")
    public void we_want_to_see_all_records_of_the_olympic_games_medal_table_information_by_a_non_existent_athlete_surname() {
        response = request.when().get("http://localhost:8000/olympicGames/medalTable/athleteSurname=Test");
    }

    @When("we want to see all records of the Olympic Games medal table information by their athlete country")
    public void we_want_to_see_all_records_of_the_olympic_games_medal_table_information_by_their_athlete_country() {
        response = request.when().get("http://localhost:8000/olympicGames/medalTable/athleteCountry=" + randomAthleteCountry);
        responseInformation = JsonPath.from(response.asString()).get();
    }

    @When("we want to see all records of the Olympic Games medal table information by a non-existent athlete country")
    public void we_want_to_see_all_records_of_the_olympic_games_medal_table_information_by_a_non_existent_athlete_country() {
        response = request.when().get("http://localhost:8000/olympicGames/medalTable/athleteCountry=Test");
    }

    @When("we want to add this new record of the Olympic Games medal table")
    public void we_want_to_add_this_new_record_of_the_olympic_games_medal_table() {
        response = request.when().body(newOlympicGamesMedalTable).post("http://localhost:8000/olympicGames/medalTable");
    }

    @When("we want to update its Olympic Games identifier to {long}, sport identifier to {long}, metal identifier to {long} and athlete identifier to {long}")
    public void we_want_to_update_its_olympic_games_identifier_sport_identifier_metal_identifier_and_athlete_identifier(Long olympicGamesId, Long sportId, Long metalId, Long athleteId) {
        newOlympicGamesMedalTableToUpdate = new HashMap<>();
        newOlympicGamesMedalTableToUpdate.put("idOlympicGames", olympicGamesId);
        newOlympicGamesMedalTableToUpdate.put("idSport", sportId);
        newOlympicGamesMedalTableToUpdate.put("idMetal", metalId);
        newOlympicGamesMedalTableToUpdate.put("idAthlete", athleteId);
        response = request.when().body(newOlympicGamesMedalTableToUpdate).patch("http://localhost:8000/olympicGames/medalTable/id=" + idToUpdateDelete);
    }

    @When("we want to delete this record of the Olympic Games medal table")
    public void we_want_to_delete_this_record_of_the_olympic_games_medal_table() {
        response = request.when().delete("http://localhost:8000/olympicGames/medalTable/id=" + idToUpdateDelete);
    }

    @Then("all records of the Olympic Games medal table information is shown")
    public void all_records_of_the_olympic_games_medal_table_information_is_shown() {
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertFalse(responseInformation.isEmpty());
    }

    @Then("the record of the Olympic Games medal table of the Olympic Games medal table information is shown")
    public void the_record_of_the_olympic_games_medal_table_of_the_olympic_games_medal_table_information_is_shown() {
        Assert.assertEquals(200, response.getStatusCode());
        for (Map.Entry<String, Object> entry : uniqueResponseInformation.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            if (key.equals("olympicGamesMedalTableId")) {
                Assert.assertEquals(randomId.toString(), value.toString());
            } else if (key.equals("olympicGames")) {
                for (Map.Entry<String, Object> entryOlympicGames : ((Map<String, Object>) value).entrySet()) {
                    switch (entryOlympicGames.getKey()) {
                        case "olympicGamesId":
                            Assert.assertEquals(randomOlympicGamesId.toString(), entryOlympicGames.getValue().toString());
                            break;
                        case "olympicGamesYear":
                            Assert.assertEquals(randomOlympicGamesYear.toString(), entryOlympicGames.getValue().toString());
                            break;
                        default:
                            Assert.assertEquals(randomOlympicGamesPlace, entryOlympicGames.getValue().toString());
                            break;
                    }
                }
            } else if (key.equals("sport")) {
                for (Map.Entry<String, Object> entrySport : ((Map<String, Object>) value).entrySet()) {
                    valueSport = entrySport.getValue();
                    switch (entrySport.getKey()) {
                        case "sportId":
                            Assert.assertEquals(randomSportId.toString(), valueSport.toString());
                            break;
                        case "sportName":
                            Assert.assertEquals(randomSportName, valueSport.toString());
                            break;
                        case "sportCategoryName":
                            if (valueSport != null) {
                                Assert.assertEquals(randomSportCategoryName, valueSport.toString());
                            } else {
                                Assert.assertNull(randomSportCategoryName);
                            }
                            break;
                    }
                }
            } else if (key.equals("metal")) {
                for (Map.Entry<String, Object> entryMetal : ((Map<String, Object>) value).entrySet()) {
                    if (entryMetal.getKey().equals("metalId")) {
                        Assert.assertEquals(randomMetalId.toString(), entryMetal.getValue().toString());
                    } else {
                        Assert.assertEquals(randomMetalType, entryMetal.getValue().toString());
                    }
                }
            } else if (key.equals("athlete")) {
                for (Map.Entry<String, Object> entryAthlete : ((Map<String, Object>) value).entrySet()) {
                    switch (entryAthlete.getKey()) {
                        case "athleteId":
                            Assert.assertEquals(randomAthleteId.toString(), entryAthlete.getValue().toString());
                            break;
                        case "athleteName":
                            Assert.assertEquals(randomAthleteName, entryAthlete.getValue().toString());
                            break;
                        case "athleteSurname":
                            Assert.assertEquals(randomAthleteSurname, entryAthlete.getValue().toString());
                            break;
                        default:
                            Assert.assertEquals(randomAthleteCountry, entryAthlete.getValue().toString());
                            break;
                    }
                }
            }
        }
    }

    @Then("no record of the Olympic Games medal table information is shown and a not found error is shown")
    public void no_record_of_the_olympic_games_medal_table_information_is_shown_and_a_not_found_error_is_shown() {
        Assert.assertEquals(404, response.getStatusCode());
    }

    @Then("no records of the Olympic Games medal table information is shown and a not found error is shown")
    public void no_records_of_the_olympic_games_medal_table_information_is_shown_and_a_not_found_error_is_shown() {
        Assert.assertEquals(404, response.getStatusCode());
    }

    @Then("the record of the Olympic Games medal table is correctly added and its information is shown")
    public void the_record_of_the_olympic_games_medal_table_is_correctly_added_and_its_information_is_shown() {
        Assert.assertEquals(201, response.getStatusCode());
        uniqueResponseInformation = JsonPath.from(response.asString()).get();
        for (Map.Entry<String, Object> entry : uniqueResponseInformation.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            if (key.equals("olympicGames")) {
                for (Map.Entry<String, Object> entryOlympicGames : ((Map<String, Object>) value).entrySet()) {
                    switch (entryOlympicGames.getKey()) {
                        case "olympicGamesId":
                            Assert.assertEquals("1", entryOlympicGames.getValue().toString());
                            break;
                    }
                }
            } else if (key.equals("sport")) {
                for (Map.Entry<String, Object> entrySport : ((Map<String, Object>) value).entrySet()) {
                    valueSport = entrySport.getValue();
                    switch (entrySport.getKey()) {
                        case "sportId":
                            Assert.assertEquals("1", valueSport.toString());
                            break;
                    }
                }
            } else if (key.equals("metal")) {
                for (Map.Entry<String, Object> entryMetal : ((Map<String, Object>) value).entrySet()) {
                    if (entryMetal.getKey().equals("metalId")) {
                        Assert.assertEquals("1", entryMetal.getValue().toString());
                    }
                }
            } else if (key.equals("athlete")) {
                for (Map.Entry<String, Object> entryAthlete : ((Map<String, Object>) value).entrySet()) {
                    switch (entryAthlete.getKey()) {
                        case "athleteId":
                            Assert.assertEquals("1", entryAthlete.getValue().toString());
                            break;
                    }
                }
            }
        }
    }

    @Then("a bad request error is shown for the record of the Olympic Games medal table")
    public void a_bad_request_error_is_shown_for_the_record_of_the_olympic_games_medal_table() {
        Assert.assertEquals(400, response.getStatusCode());
    }

    @Then("a not found error is shown for the record of the Olympic Games medal table")
    public void a_not_found_error_is_shown_for_the_record_of_the_olympic_games_medal_table() {
        Assert.assertEquals(404, response.getStatusCode());
    }

    @Then("the record of the Olympic Games medal table is correctly updated and its information is shown")
    public void the_record_of_the_olympic_games_medal_table_is_correctly_updated_and_its_information_is_shown() {
        Assert.assertEquals(200, response.getStatusCode());
        uniqueResponseInformation = JsonPath.from(response.asString()).get();
        for (Map.Entry<String, Object> entry : uniqueResponseInformation.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            if (key.equals("olympicGames")) {
                for (Map.Entry<String, Object> entryOlympicGames : ((Map<String, Object>) value).entrySet()) {
                    switch (entryOlympicGames.getKey()) {
                        case "olympicGamesId":
                            Assert.assertEquals("2", entryOlympicGames.getValue().toString());
                            break;
                    }
                }
            } else if (key.equals("sport")) {
                for (Map.Entry<String, Object> entrySport : ((Map<String, Object>) value).entrySet()) {
                    valueSport = entrySport.getValue();
                    switch (entrySport.getKey()) {
                        case "sportId":
                            Assert.assertEquals("2", valueSport.toString());
                            break;
                    }
                }
            } else if (key.equals("metal")) {
                for (Map.Entry<String, Object> entryMetal : ((Map<String, Object>) value).entrySet()) {
                    if (entryMetal.getKey().equals("metalId")) {
                        Assert.assertEquals("2", entryMetal.getValue().toString());
                    }
                }
            } else if (key.equals("athlete")) {
                for (Map.Entry<String, Object> entryAthlete : ((Map<String, Object>) value).entrySet()) {
                    switch (entryAthlete.getKey()) {
                        case "athleteId":
                            Assert.assertEquals("2", entryAthlete.getValue().toString());
                            break;
                    }
                }
            }
        }
    }

    @Then("the record of the Olympic Games medal table is correctly deleted")
    public void the_record_of_the_olympic_games_medal_table_is_correctly_deleted() {
        Assert.assertEquals(204, response.getStatusCode());
    }

}