package com.exampleAPI.olympicGames.controllers;

import com.exampleAPI.olympicGames.models.OlympicGamesMedalTableModel;
import com.exampleAPI.olympicGames.services.OlympicGamesMedalTableService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/olympicGames/medalTable")
@Tag(name = "Olympic Games medal table", description = "Operations over Olympic Games medal table")
public class OlympicGamesMedalTableController {

    @Autowired
    OlympicGamesMedalTableService olympicGamesMedalTableService;

    @Operation(summary = "Get all records of the Olympic Games medal table information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all records of the Olympic Games medal table information",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = OlympicGamesMedalTableModel.class)))}),
            @ApiResponse(responseCode = "404", description = "There are no records on the Olympic Games medal table",
                    content = @Content)})
    @GetMapping
    public ResponseEntity<List<OlympicGamesMedalTableModel>> getOlympicGamesMedalTable() {
        if (!this.olympicGamesMedalTableService.getOlympicGamesMedalTable().isEmpty()) {
            return new ResponseEntity<>(this.olympicGamesMedalTableService.getOlympicGamesMedalTable(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get a record of the Olympic Games medal table information by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information of the record of the Olympic Games medal table with the indicated id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OlympicGamesMedalTableModel.class))}),
            @ApiResponse(responseCode = "404", description = "Record of the Olympic Games medal table with the indicated id not found",
                    content = @Content)})
    @GetMapping(path = "/id={idOlympicGamesMedalTable}")
    public ResponseEntity<Optional<OlympicGamesMedalTableModel>> getOlympicGamesMedalTableById(@PathVariable("idOlympicGamesMedalTable") @Parameter(description = "Identifier of the record of the Olympic Games medal table", required = true) Long idOlympicGamesMedalTable) {
        if (this.olympicGamesMedalTableService.getOlympicGamesMedalTableById(idOlympicGamesMedalTable).isPresent()) {
            return new ResponseEntity<>(this.olympicGamesMedalTableService.getOlympicGamesMedalTableById(idOlympicGamesMedalTable), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get all records of the Olympic Games medal table information by their Olympic Games id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information of the records of the Olympic Games medal table with the indicated Olympic Games id",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = OlympicGamesMedalTableModel.class)))}),
            @ApiResponse(responseCode = "404", description = "Records of the Olympic Games medal table with the indicated Olympic Games id not found",
                    content = @Content)})
    @GetMapping(path = "/idOlympicGames={idOlympicGames}")
    public ResponseEntity<List<OlympicGamesMedalTableModel>> getOlympicGamesMedalTableByOlympicGamesId(@PathVariable("idOlympicGames") @Parameter(description = "Identifier of the Olympic Games", required = true) Long idOlympicGames) {
        if (!this.olympicGamesMedalTableService.getOlympicGamesMedalTableByOlympicGamesId(idOlympicGames).isEmpty()) {
            return new ResponseEntity<>(this.olympicGamesMedalTableService.getOlympicGamesMedalTableByOlympicGamesId(idOlympicGames), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get all records of the Olympic Games medal table information by their Olympic Games year")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information of the records of the Olympic Games medal table with the indicated Olympic Games year",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = OlympicGamesMedalTableModel.class)))}),
            @ApiResponse(responseCode = "404", description = "Records of the Olympic Games medal table with the indicated Olympic Games year not found",
                    content = @Content)})
    @GetMapping(path = "/olympicGamesYear={olympicGamesYear}")
    public ResponseEntity<List<OlympicGamesMedalTableModel>> getOlympicGamesMedalTableByOlympicGamesYear(@PathVariable("olympicGamesYear") @Parameter(description = "Year of the Olympic Games", required = true) Integer olympicGamesYear) {
        if (!this.olympicGamesMedalTableService.getOlympicGamesMedalTableByOlympicGamesYear(olympicGamesYear).isEmpty()) {
            return new ResponseEntity<>(this.olympicGamesMedalTableService.getOlympicGamesMedalTableByOlympicGamesYear(olympicGamesYear), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get all records of the Olympic Games medal table information by their Olympic Games place")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information of the records of the Olympic Games medal table with the indicated Olympic Games place",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = OlympicGamesMedalTableModel.class)))}),
            @ApiResponse(responseCode = "404", description = "Records of the Olympic Games medal table with the indicated Olympic Games place not found",
                    content = @Content)})
    @GetMapping(path = "/olympicGamesPlace={olympicGamesPlace}")
    public ResponseEntity<List<OlympicGamesMedalTableModel>> getOlympicGamesMedalTableByOlympicGamesPlace(@PathVariable("olympicGamesPlace") @Parameter(description = "Place of the Olympic Games", required = true) String olympicGamesPlace) {
        if (!this.olympicGamesMedalTableService.getOlympicGamesMedalTableByOlympicGamesPlace(olympicGamesPlace).isEmpty()) {
            return new ResponseEntity<>(this.olympicGamesMedalTableService.getOlympicGamesMedalTableByOlympicGamesPlace(olympicGamesPlace), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get all records of the Olympic Games medal table information by their sport id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information of the records of the Olympic Games medal table with the indicated sport id",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = OlympicGamesMedalTableModel.class)))}),
            @ApiResponse(responseCode = "404", description = "Records of the Olympic Games medal table with the indicated sport id not found",
                    content = @Content)})
    @GetMapping(path = "/idSport={idSport}")
    public ResponseEntity<List<OlympicGamesMedalTableModel>> getOlympicGamesMedalTableBySportId(@PathVariable("idSport") @Parameter(description = "Identifier of the sport", required = true) Long idSport) {
        if (!this.olympicGamesMedalTableService.getOlympicGamesMedalTableBySportId(idSport).isEmpty()) {
            return new ResponseEntity<>(this.olympicGamesMedalTableService.getOlympicGamesMedalTableBySportId(idSport), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get all records of the Olympic Games medal table information by their sport name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information of the records of the Olympic Games medal table with the indicated sport name",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = OlympicGamesMedalTableModel.class)))}),
            @ApiResponse(responseCode = "404", description = "Records of the Olympic Games medal table with the indicated sport name not found",
                    content = @Content)})
    @GetMapping(path = "/sportName={sportName}")
    public ResponseEntity<List<OlympicGamesMedalTableModel>> getOlympicGamesMedalTableBySportName(@PathVariable("sportName") @Parameter(description = "Name of the sport", required = true) String sportName) {
        if (!this.olympicGamesMedalTableService.getOlympicGamesMedalTableBySportName(sportName).isEmpty()) {
            return new ResponseEntity<>(this.olympicGamesMedalTableService.getOlympicGamesMedalTableBySportName(sportName), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get all records of the Olympic Games medal table information by their sport category name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information of the records of the Olympic Games medal table with the indicated sport category name",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = OlympicGamesMedalTableModel.class)))}),
            @ApiResponse(responseCode = "404", description = "Records of the Olympic Games medal table with the indicated sport category name not found",
                    content = @Content)})
    @GetMapping(path = "/categoryName={categoryName}")
    public ResponseEntity<List<OlympicGamesMedalTableModel>> getOlympicGamesMedalTableByCategoryName(@PathVariable("categoryName") @Parameter(description = "Category name of the sport", required = true) String categoryName) {
        if (!this.olympicGamesMedalTableService.getOlympicGamesMedalTableByCategoryName(categoryName).isEmpty()) {
            return new ResponseEntity<>(this.olympicGamesMedalTableService.getOlympicGamesMedalTableByCategoryName(categoryName), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get all records of the Olympic Games medal table information by their metal id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information of the records of the Olympic Games medal table with the indicated metal id",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = OlympicGamesMedalTableModel.class)))}),
            @ApiResponse(responseCode = "404", description = "Records of the Olympic Games medal table with the indicated metal id not found",
                    content = @Content)})
    @GetMapping(path = "/idMetal={idMetal}")
    public ResponseEntity<List<OlympicGamesMedalTableModel>> getOlympicGamesMedalTableByMetalId(@PathVariable("idMetal") @Parameter(description = "Identifier of the metal", required = true) Long idMetal) {
        if (!this.olympicGamesMedalTableService.getOlympicGamesMedalTableByMetalId(idMetal).isEmpty()) {
            return new ResponseEntity<>(this.olympicGamesMedalTableService.getOlympicGamesMedalTableByMetalId(idMetal), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get all records of the Olympic Games medal table information by their metal type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information of the records of the Olympic Games medal table with the indicated metal type",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = OlympicGamesMedalTableModel.class)))}),
            @ApiResponse(responseCode = "404", description = "Records of the Olympic Games medal table with the indicated metal type not found",
                    content = @Content)})
    @GetMapping(path = "/metalType={metalType}")
    public ResponseEntity<List<OlympicGamesMedalTableModel>> getOlympicGamesMedalTableByMetalType(@PathVariable("metalType") @Parameter(description = "Type of the metal", required = true) String metalType) {
        if (!this.olympicGamesMedalTableService.getOlympicGamesMedalTableByMetalType(metalType).isEmpty()) {
            return new ResponseEntity<>(this.olympicGamesMedalTableService.getOlympicGamesMedalTableByMetalType(metalType), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get all records of the Olympic Games medal table information by their athlete id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information of the records of the Olympic Games medal table with the indicated athlete id",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = OlympicGamesMedalTableModel.class)))}),
            @ApiResponse(responseCode = "404", description = "Records of the Olympic Games medal table with the indicated athlete id not found",
                    content = @Content)})
    @GetMapping(path = "/idAthlete={idAthlete}")
    public ResponseEntity<List<OlympicGamesMedalTableModel>> getOlympicGamesMedalTableByAthleteId(@PathVariable("idAthlete") @Parameter(description = "Identifier of the athlete", required = true) Long idAthlete) {
        if (!this.olympicGamesMedalTableService.getOlympicGamesMedalTableByAthleteId(idAthlete).isEmpty()) {
            return new ResponseEntity<>(this.olympicGamesMedalTableService.getOlympicGamesMedalTableByAthleteId(idAthlete), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get all records of the Olympic Games medal table information by their athlete name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information of the records of the Olympic Games medal table with the indicated athlete name",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = OlympicGamesMedalTableModel.class)))}),
            @ApiResponse(responseCode = "404", description = "Records of the Olympic Games medal table with the indicated athlete name not found",
                    content = @Content)})
    @GetMapping(path = "/athleteName={athleteName}")
    public ResponseEntity<List<OlympicGamesMedalTableModel>> getOlympicGamesMedalTableByAthleteName(@PathVariable("athleteName") @Parameter(description = "Name of the athlete", required = true) String athleteName) {
        if (!this.olympicGamesMedalTableService.getOlympicGamesMedalTableByAthleteName(athleteName).isEmpty()) {
            return new ResponseEntity<>(this.olympicGamesMedalTableService.getOlympicGamesMedalTableByAthleteName(athleteName), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get all records of the Olympic Games medal table information by their athlete surname")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information of the records of the Olympic Games medal table with the indicated athlete surname",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = OlympicGamesMedalTableModel.class)))}),
            @ApiResponse(responseCode = "404", description = "Records of the Olympic Games medal table with the indicated athlete surname not found",
                    content = @Content)})
    @GetMapping(path = "/athleteSurname={athleteSurname}")
    public ResponseEntity<List<OlympicGamesMedalTableModel>> getOlympicGamesMedalTableByAthleteSurname(@PathVariable("athleteSurname") @Parameter(description = "Surname of the athlete", required = true) String athleteSurname) {
        if (!this.olympicGamesMedalTableService.getOlympicGamesMedalTableByAthleteSurname(athleteSurname).isEmpty()) {
            return new ResponseEntity<>(this.olympicGamesMedalTableService.getOlympicGamesMedalTableByAthleteSurname(athleteSurname), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get all records of the Olympic Games medal table information by their athlete country")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information of the records of the Olympic Games medal table with the indicated athlete country",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = OlympicGamesMedalTableModel.class)))}),
            @ApiResponse(responseCode = "404", description = "Records of the Olympic Games medal table with the indicated athlete country not found",
                    content = @Content)})
    @GetMapping(path = "/athleteCountry={athleteCountry}")
    public ResponseEntity<List<OlympicGamesMedalTableModel>> getOlympicGamesMedalTableByAthleteCountry(@PathVariable("athleteCountry") @Parameter(description = "Country of the athlete", required = true) String athleteCountry) {
        if (!this.olympicGamesMedalTableService.getOlympicGamesMedalTableByAthleteCountry(athleteCountry).isEmpty()) {
            return new ResponseEntity<>(this.olympicGamesMedalTableService.getOlympicGamesMedalTableByAthleteCountry(athleteCountry), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Add a new record in the Olympic Games medal table")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "New record in the Olympic Games medal table added with correct information",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OlympicGamesMedalTableModel.class))}),
            @ApiResponse(responseCode = "400", description = "Incorrect information of the new record for the Olympic Games medal table",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Information of the new record for the Olympic Games medal table not found",
                    content = @Content)})
    @PostMapping
    //public ResponseEntity<OlympicGamesMedalTableModel> saveOlympicGamesMedalTable(@RequestBody @Parameter(description = "Information for the Olympic Games medal table record", required = true, schema = @Schema(implementation = OlympicGamesMedalTableModel.class), examples = { @ExampleObject(name = "idOlympicGames", summary = "Identifier of the Olympic Games", value = "1"), @ExampleObject(name = "idSport", summary = "Identifier of the sport", value = "1"), @ExampleObject(name = "idMetal", summary = "Identifier of the metal", value = "1"), @ExampleObject(name = "idAthlete", summary = "Identifier of the athlete", value = "1") }) Map<String, Object> idsOlympicGamesMedalTable) {
    public ResponseEntity<OlympicGamesMedalTableModel> saveOlympicGamesMedalTable(@RequestBody Map<String, Object> idsOlympicGamesMedalTable) {
        Long idOlympicGames = 0L;
        Long idSport = 0L;
        Long idMetal = 0L;
        Long idAthlete = 0L;
        boolean badKeys = false;
        try {
            for (Map.Entry<String, Object> entry : idsOlympicGamesMedalTable.entrySet()) {
                if (!entry.getKey().equals("idOlympicGames") && !entry.getKey().equals("idSport") && !entry.getKey().equals("idMetal") && !entry.getKey().equals("idAthlete")) {
                    badKeys = true;
                    break;
                }
                if (entry.getKey().equals("idOlympicGames")) {
                    idOlympicGames = Long.parseLong(entry.getValue().toString());
                }
                if (entry.getKey().equals("idSport")) {
                    idSport = Long.parseLong(entry.getValue().toString());
                }
                if (entry.getKey().equals("idMetal")) {
                    idMetal = Long.parseLong(entry.getValue().toString());
                }
                if (entry.getKey().equals("idAthlete")) {
                    idAthlete = Long.parseLong(entry.getValue().toString());
                }
            }
            if (badKeys) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (idOlympicGames == 0L || idSport == 0L || idMetal == 0L || idAthlete == 0L) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        OlympicGamesMedalTableModel olympicGamesMedalTableModelSaved = this.olympicGamesMedalTableService.saveOlympicGamesMedalTable(idOlympicGames, idSport, idMetal, idAthlete);
        if (olympicGamesMedalTableModelSaved != null) {
            return new ResponseEntity<>(olympicGamesMedalTableModelSaved, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Update some information of a record of the Olympic Games medal table by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information of the record of the Olympic Games medal table with indicated id updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OlympicGamesMedalTableModel.class))}),
            @ApiResponse(responseCode = "400", description = "Incorrect information to be updated on the record of the Olympic Games medal table",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Record of the Olympic Games medal table with the indicated id not found",
                    content = @Content)})
    @PatchMapping(path = "/id={idOlympicGamesMedalTable}")
    //public ResponseEntity<OlympicGamesMedalTableModel> updateOlympicGamesMedalTableById(@PathVariable("idOlympicGamesMedalTable") @Parameter(description = "Identifier of the record of the Olympic Games medal table", required = true) Long idOlympicGamesMedalTable, @RequestBody @Parameter(description = "Information for the Olympic Games medal table record", required = true, schema = @Schema(implementation = OlympicGamesMedalTableModel.class), examples = { @ExampleObject(name = "idOlympicGames", summary = "Identifier of the Olympic Games", value = "1"), @ExampleObject(name = "idSport", summary = "Identifier of the sport", value = "1"), @ExampleObject(name = "idMetal", summary = "Identifier of the metal", value = "1"), @ExampleObject(name = "idAthlete", summary = "Identifier of the athlete", value = "1") }) Map<String, Object> idsOlympicGamesMedalTable) {
    public ResponseEntity<OlympicGamesMedalTableModel> updateOlympicGamesMedalTableById(@PathVariable("idOlympicGamesMedalTable") @Parameter(description = "Identifier of the record of the Olympic Games medal table", required = true) Long idOlympicGamesMedalTable, @RequestBody Map<String, Object> idsOlympicGamesMedalTable) {
        Long idOlympicGames = 0L;
        Long idSport = 0L;
        Long idMetal = 0L;
        Long idAthlete = 0L;
        boolean badKeys = false;
        Optional<OlympicGamesMedalTableModel> oldOlympicGamesMedalTable = this.olympicGamesMedalTableService.getOlympicGamesMedalTableById(idOlympicGamesMedalTable);
        if (oldOlympicGamesMedalTable.isPresent()) {
            try {
                for (Map.Entry<String, Object> entry : idsOlympicGamesMedalTable.entrySet()) {
                    if (!entry.getKey().equals("idOlympicGames") && !entry.getKey().equals("idSport") && !entry.getKey().equals("idMetal") && !entry.getKey().equals("idAthlete")) {
                        badKeys = true;
                        break;
                    }
                    if (entry.getKey().equals("idOlympicGames")) {
                        idOlympicGames = Long.parseLong(entry.getValue().toString());
                    }
                    if (entry.getKey().equals("idSport")) {
                        idSport = Long.parseLong(entry.getValue().toString());
                    }
                    if (entry.getKey().equals("idMetal")) {
                        idMetal = Long.parseLong(entry.getValue().toString());
                    }
                    if (entry.getKey().equals("idAthlete")) {
                        idAthlete = Long.parseLong(entry.getValue().toString());
                    }
                }
                if (badKeys) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                if (idOlympicGames == 0L && idSport == 0L && idMetal == 0L && idAthlete == 0L) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            } catch (RuntimeException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            OlympicGamesMedalTableModel olympicGamesMedalTableModelUpdated = this.olympicGamesMedalTableService.updateOlympicGamesMedalTableById(oldOlympicGamesMedalTable.get(), idOlympicGames, idSport, idMetal, idAthlete);
            if (olympicGamesMedalTableModelUpdated != null) {
                return new ResponseEntity<>(olympicGamesMedalTableModelUpdated, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a record of the Olympic Games medal table by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Record of the Olympic Games medal table with the indicated id deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Record of the Olympic Games medal table with the indicated id not found",
                    content = @Content)})
    @DeleteMapping(path = "/id={idOlympicGamesMedalTable}")
    public ResponseEntity<Object> deleteOlympicGamesMedalTable(@PathVariable("idOlympicGamesMedalTable") @Parameter(description = "Identifier of the record of the Olympic Games medal table", required = true) Long idOlympicGamesMedalTable) {
        if (this.olympicGamesMedalTableService.getOlympicGamesMedalTableById(idOlympicGamesMedalTable).isPresent()) {
            this.olympicGamesMedalTableService.deleteOlympicGamesMedalTable(idOlympicGamesMedalTable);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}