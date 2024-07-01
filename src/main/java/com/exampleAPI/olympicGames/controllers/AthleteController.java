package com.exampleAPI.olympicGames.controllers;

import com.exampleAPI.olympicGames.models.AthleteModel;
import com.exampleAPI.olympicGames.services.AthleteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
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

//Clase en la que se definen las diferentes peticiones API que se pueden realizar sobre el modelo de datos que tiene la información de un atleta
@RestController
@RequestMapping("/olympicGames/athlete")
@Tag(name = "Athletes", description = "Operations over athletes")
public class AthleteController {

    @Autowired
    AthleteService athleteService;

    //Método o petición API de tipo GET para obtener una lista con la información de todos los atletas
    //Se mostrará un código de respuesta 200 si la lista no está vacía (es decir, contiene información)
    //Se mostrará un código de respuesta 404 si la lista está vacía (es decir, no contiene información)
    @Operation(summary = "Get all athletes information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all athletes information",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AthleteModel.class)))}),
            @ApiResponse(responseCode = "404", description = "There are no athletes",
                    content = @Content)})
    @GetMapping
    public ResponseEntity<List<AthleteModel>> getAthlete() {
        if (!this.athleteService.getAthlete().isEmpty()) {
            return new ResponseEntity<>(this.athleteService.getAthlete(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Método o petición API de tipo GET para obtener la información de un atleta que tiene un determinado identificador
    //Se mostrará un código de respuesta 200 si se encuentra el atleta y por tanto su información
    //Se mostrará un código de respuesta 404 si no se encuentra el atleta
    @Operation(summary = "Get an athlete information by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information of the athlete with the indicated id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AthleteModel.class))}),
            @ApiResponse(responseCode = "404", description = "Athlete with the indicated id not found",
                    content = @Content)})
    @GetMapping(path = "/id={idAthlete}")
    public ResponseEntity<Optional<AthleteModel>> getAthleteById(@PathVariable("idAthlete") @Parameter(description = "Identifier of the athlete", required = true) Long idAthlete) {
        if (this.athleteService.getAthleteById(idAthlete).isPresent()) {
            return new ResponseEntity<>(this.athleteService.getAthleteById(idAthlete), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Método o petición API de tipo GET para obtener una lista con la información de todos los atletas que tienen un determinado nombre
    //Se mostrará un código de respuesta 200 si la lista no está vacía (es decir, contiene información)
    //Se mostrará un código de respuesta 404 si la lista está vacía (es decir, no contiene información)
    @Operation(summary = "Get all athletes information by their name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information of the athletes with the indicated name",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AthleteModel.class)))}),
            @ApiResponse(responseCode = "404", description = "Athletes with the indicated name not found",
                    content = @Content)})
    @GetMapping(path = "/name={athleteName}")
    public ResponseEntity<List<AthleteModel>> getAthleteByName(@PathVariable("athleteName") @Parameter(description = "Name of the athlete", required = true) String athleteName) {
        if (!this.athleteService.getAthleteByName(athleteName).isEmpty()) {
            return new ResponseEntity<>(this.athleteService.getAthleteByName(athleteName), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Método o petición API de tipo GET para obtener una lista con la información de todos los atletas que tienen unos determinados apellidos
    //Se mostrará un código de respuesta 200 si la lista no está vacía (es decir, contiene información)
    //Se mostrará un código de respuesta 404 si la lista está vacía (es decir, no contiene información)
    @Operation(summary = "Get all athletes information by their surname")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information of the athletes with the indicated surname",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AthleteModel.class)))}),
            @ApiResponse(responseCode = "404", description = "Athletes with the indicated surname not found",
                    content = @Content)})
    @GetMapping(path = "/surname={athleteSurname}")
    public ResponseEntity<List<AthleteModel>> getAthleteBySurname(@PathVariable("athleteSurname") @Parameter(description = "Surname of the athlete", required = true) String athleteSurname) {
        if (!this.athleteService.getAthleteBySurname(athleteSurname).isEmpty()) {
            return new ResponseEntity<>(this.athleteService.getAthleteBySurname(athleteSurname), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Método o petición API de tipo GET para obtener una lista con la información de todos los atletas que representan a un determinado país
    //Se mostrará un código de respuesta 200 si la lista no está vacía (es decir, contiene información)
    //Se mostrará un código de respuesta 404 si la lista está vacía (es decir, no contiene información)
    @Operation(summary = "Get all athletes information by their country")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information of the athletes with the indicated country",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AthleteModel.class)))}),
            @ApiResponse(responseCode = "404", description = "Athletes with the indicated country not found",
                    content = @Content)})
    @GetMapping(path = "/country={athleteCountry}")
    public ResponseEntity<List<AthleteModel>> getAthleteByCountry(@PathVariable("athleteCountry") @Parameter(description = "Country of the athlete", required = true) String athleteCountry) {
        if (!this.athleteService.getAthleteByCountry(athleteCountry).isEmpty()) {
            return new ResponseEntity<>(this.athleteService.getAthleteByCountry(athleteCountry), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Método o petición API de tipo POST para guardar la información de un atleta dados el nombre, los apellidos y país al que representa
    //Se mostrará un código de respuesta 201 si se puede guardar el atleta y por tanto su información
    //Se mostrará un código de respuesta 400 si no se puede guardar el atleta porque la información que se quiere guardar no es correcta (la información no tiene los nombres de la claves del body correctos, es nula o no es del tipo correcto)
    @Operation(summary = "Add a new athlete")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "New athlete added with correct information",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AthleteModel.class))}),
            @ApiResponse(responseCode = "400", description = "Incorrect information of the new athlete",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<AthleteModel> saveAthlete(@RequestBody @Parameter(description = "Athlete information", required = true, schema = @Schema(implementation = AthleteModel.class), examples = {@ExampleObject(name = "athleteName", summary = "Athlete name", value = "Sandra"), @ExampleObject(name = "athleteSurname", summary = "Athlete surname", value = "Sánchez Jaime"), @ExampleObject(name = "athleteCountry", summary = "Athlete country", value = "España")}) Map<String, String> athleteContent) {
        String athleteName = null;
        String athleteSurname = null;
        String athleteCountry = null;
        boolean badKeys = false;
        try {
            for (Map.Entry<String, String> entry : athleteContent.entrySet()) {
                if (!entry.getKey().equals("athleteName") && !entry.getKey().equals("athleteSurname") && !entry.getKey().equals("athleteCountry")) {
                    badKeys = true;
                    break;
                }
                if (entry.getKey().equals("athleteName")) {
                    athleteName = entry.getValue();
                }
                if (entry.getKey().equals("athleteSurname")) {
                    athleteSurname = entry.getValue();
                }
                if (entry.getKey().equals("athleteCountry")) {
                    athleteCountry = entry.getValue();
                }
            }
            if (badKeys) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (athleteName == null || athleteSurname == null || athleteCountry == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(this.athleteService.saveAthlete(athleteName, athleteSurname, athleteCountry), HttpStatus.CREATED);
    }

    //Método o petición API de tipo PATCH para actualizar la información de un atleta que tiene un determinado identificador con el nuevo nombre y/o los nuevos apellidos y/o el nuevo país al que representa
    //Se mostrará un código de respuesta 200 si se puede actualizar el atleta y por tanto su información
    //Se mostrará un código de respuesta 400 si no se puede actualizar el atleta porque la información que se quiere actualizar no es correcta (la información no tiene los nombres de la claves del body correctos, es nula o no es del tipo correcto)
    //Se mostrará un código de respuesta 404 si no se encuentra el atleta cuya información se quiere actualizar
    @Operation(summary = "Update some athlete information of an athlete by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information of the athlete with indicated id updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = AthleteModel.class))}),
            @ApiResponse(responseCode = "400", description = "Incorrect information to be updated on the athlete",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Athlete with the indicated id not found",
                    content = @Content)})
    @PatchMapping(path = "/id={idAthlete}")
    public ResponseEntity<AthleteModel> updateAthleteById(@PathVariable("idAthlete") @Parameter(description = "Identifier of the athlete", required = true) Long idAthlete, @RequestBody @Parameter(description = "Athlete information", required = true, schema = @Schema(implementation = AthleteModel.class), examples = {@ExampleObject(name = "athleteName", summary = "Athlete name", value = "Sandra"), @ExampleObject(name = "athleteSurname", summary = "Athlete surname", value = "Sánchez Jaime"), @ExampleObject(name = "athleteCountry", summary = "Athlete country", value = "España")}) Map<String, String> athleteContent) {
        String athleteName = null;
        String athleteSurname = null;
        String athleteCountry = null;
        boolean badKeys = false;
        Optional<AthleteModel> oldAthlete = this.athleteService.getAthleteById(idAthlete);
        if (oldAthlete.isPresent()) {
            try {
                for (Map.Entry<String, String> entry : athleteContent.entrySet()) {
                    if (!entry.getKey().equals("athleteName") && !entry.getKey().equals("athleteSurname") && !entry.getKey().equals("athleteCountry")) {
                        badKeys = true;
                        break;
                    }
                    if (entry.getKey().equals("athleteName")) {
                        athleteName = entry.getValue();
                    }
                    if (entry.getKey().equals("athleteSurname")) {
                        athleteSurname = entry.getValue();
                    }
                    if (entry.getKey().equals("athleteCountry")) {
                        athleteCountry = entry.getValue();
                    }
                }
                if (badKeys) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                if (athleteName == null && athleteSurname == null && athleteCountry == null) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            } catch (RuntimeException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(this.athleteService.updateAthleteById(oldAthlete.get(), athleteName, athleteSurname, athleteCountry), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Método o petición API de tipo DELETE para eliminar la información de un atleta que tiene un determinado identificador
    //Se mostrará un código de respuesta 204 si existía el atleta y su información ha sido eliminada
    //Se mostrará un código de respuesta 404 si no se encuentra el atleta cuya información se quiere eliminar
    @Operation(summary = "Delete an athlete by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Athlete with the indicated id deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Athlete with the indicated id not found",
                    content = @Content)})
    @DeleteMapping(path = "/id={idAthlete}")
    public ResponseEntity<Object> deleteAthlete(@PathVariable("idAthlete") @Parameter(description = "Identifier of the athlete", required = true) Long idAthlete) {
        if (this.athleteService.getAthleteById(idAthlete).isPresent()) {
            this.athleteService.deleteAthlete(idAthlete);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}