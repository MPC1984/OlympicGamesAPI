package com.exampleAPI.olympicGames.controllers;

import com.exampleAPI.olympicGames.models.OlympicGamesModel;
import com.exampleAPI.olympicGames.services.OlympicGamesService;
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

//Clase en la que se definen las diferentes peticiones API que se pueden realizar sobre el modelo de datos de información de unos Juegos Olímpicos
@RestController
@RequestMapping("/olympicGames")
@Tag(name = "Olympic Games", description = "Operations over Olympic Games")
public class OlympicGamesController {

    @Autowired
    OlympicGamesService olympicGamesService;

    //Método o petición API de tipo GET para obtener una lista con la información de todos los Juegos Olímpicos
    //Se mostrará un código de respuesta 200 si la lista no está vacía (es decir, contiene información)
    //Se mostrará un código de respuesta 404 si la lista está vacía (es decir, no contiene información)
    @Operation(summary = "Get all Olympic Games information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all Olympic Games information",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = OlympicGamesModel.class)))}),
            @ApiResponse(responseCode = "404", description = "There are no Olympic Games",
                    content = @Content)})
    @GetMapping
    public ResponseEntity<List<OlympicGamesModel>> getOlympicGames() {
        if (!this.olympicGamesService.getOlympicGames().isEmpty()) {
            return new ResponseEntity<>(this.olympicGamesService.getOlympicGames(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Método o petición API de tipo GET para obtener la información de unos Juegos Olímpicos que tienen un determinado identificador
    //Se mostrará un código de respuesta 200 si se encuentran los Juegos Olímpicos y por tanto su información
    //Se mostrará un código de respuesta 404 si no se encuentran los Juegos Olímpicos
    @Operation(summary = "Get an Olympic Games information by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information of the Olympic Games with the indicated id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OlympicGamesModel.class))}),
            @ApiResponse(responseCode = "404", description = "Olympic Games with the indicated id not found",
                    content = @Content)})
    @GetMapping(path = "/id={idOlympicGames}")
    public ResponseEntity<Optional<OlympicGamesModel>> getOlympicGamesById(@PathVariable("idOlympicGames") @Parameter(description = "Identifier of the Olympic Games", required = true) Long idOlympicGames) {
        if (this.olympicGamesService.getOlympicGamesById(idOlympicGames).isPresent()) {
            return new ResponseEntity<>(this.olympicGamesService.getOlympicGamesById(idOlympicGames), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Método o petición API de tipo GET para obtener la información de unos Juegos Olímpicos que se celebraron en un determinado año
    //Se mostrará un código de respuesta 200 si se encuentran los Juegos Olímpicos y por tanto su información
    //Se mostrará un código de respuesta 404 si no se encuentran los Juegos Olímpicos
    @Operation(summary = "Get an Olympic Games information by its year")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information of the Olympic Games with the indicated year",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OlympicGamesModel.class))}),
            @ApiResponse(responseCode = "404", description = "Olympic Games with the indicated year not found",
                    content = @Content)})
    @GetMapping(path = "/year={olympicGamesYear}")
    public ResponseEntity<OlympicGamesModel> getOlympicGamesByYear(@PathVariable("olympicGamesYear") @Parameter(description = "Year of the Olympic Games", required = true) Integer olympicGamesYear) {
        if (this.olympicGamesService.getOlympicGamesByYear(olympicGamesYear) != null) {
            return new ResponseEntity<>(this.olympicGamesService.getOlympicGamesByYear(olympicGamesYear), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Método o petición API de tipo GET para obtener una lista con la información de todos los Juegos Olímpicos que se celebraron en un determinado lugar
    //Se mostrará un código de respuesta 200 si la lista no está vacía (es decir, contiene información)
    //Se mostrará un código de respuesta 404 si la lista está vacía (es decir, no contiene información)
    @Operation(summary = "Get all Olympic Games information by their place")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information of the Olympic Games with the indicated place",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = OlympicGamesModel.class)))}),
            @ApiResponse(responseCode = "404", description = "Olympic Games with the indicated place not found",
                    content = @Content)})
    @GetMapping(path = "/place={olympicGamesPlace}")
    public ResponseEntity<List<OlympicGamesModel>> getOlympicGamesByPlace(@PathVariable("olympicGamesPlace") @Parameter(description = "Place of the Olympic Games", required = true) String olympicGamesPlace) {
        if (!this.olympicGamesService.getOlympicGamesByPlace(olympicGamesPlace).isEmpty()) {
            return new ResponseEntity<>(this.olympicGamesService.getOlympicGamesByPlace(olympicGamesPlace), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Método o petición API de tipo POST para guardar la información de unos Juegos Olímpicos dados el año y el lugar en el que se celebraron
    //Se mostrará un código de respuesta 201 si se pueden guardar los Juegos Olímpicos y por tanto su información
    //Se mostrará un código de respuesta 400 si no se pueden guardar los Juegos Olímpicos porque la información que se quiere guardar no es correcta (la información no tiene los nombres de la claves del body correctos, es nula o 0 o no es del tipo correcto)
    //Se mostrará un código de respuesta 409 si no se pueden guardar los Juegos Olímpicos porque la información que se quiere guardar está duplicada (se quieren guardar unos nuevos Juegos Olímpicos con un año de celebración ya existente)
    @Operation(summary = "Add a new Olympic Games")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "New Olympic Games added with correct information",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OlympicGamesModel.class))}),
            @ApiResponse(responseCode = "400", description = "Incorrect information of the new Olympic Games",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Duplicated information of the new Olympic Games",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<OlympicGamesModel> saveOlympicGames(@RequestBody @Parameter(description = "Olympic Games information", required = true, schema = @Schema(implementation = OlympicGamesModel.class), examples = {@ExampleObject(name = "olympicGamesYear", summary = "Olympic Games year", value = "2020"), @ExampleObject(name = "olympicGamesPlace", summary = "Olympic Games place", value = "Tokyo")}) Map<String, Object> olympicGamesContent) {
        int olympicGamesYear = 0;
        String olympicGamesPlace = null;
        boolean badKeys = false;
        try {
            for (Map.Entry<String, Object> entry : olympicGamesContent.entrySet()) {
                if (!entry.getKey().equals("olympicGamesYear") && !entry.getKey().equals("olympicGamesPlace")) {
                    badKeys = true;
                    break;
                }
                if (entry.getKey().equals("olympicGamesYear")) {
                    olympicGamesYear = Integer.parseInt(entry.getValue().toString());
                }
                if (entry.getKey().equals("olympicGamesPlace")) {
                    olympicGamesPlace = entry.getValue().toString();
                }
            }
            if (badKeys) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (olympicGamesYear == 0 || olympicGamesPlace == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            return new ResponseEntity<>(this.olympicGamesService.saveOlympicGames(olympicGamesYear, olympicGamesPlace), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    //Método o petición API de tipo PATCH para actualizar la información de unos Juegos Olímpicos que tienen un determinado identificador con el nuevo año y/o el nuevo lugar en el que se celebraron
    //Se mostrará un código de respuesta 200 si se pueden actualizar los Juegos Olímpicos y por tanto su información
    //Se mostrará un código de respuesta 400 si no se pueden actualizar los Juegos Olímpicos porque la información que se quiere actualizar no es correcta (la información no tiene los nombres de la claves del body correctos, es nula o 0 o no es del tipo correcto)
    //Se mostrará un código de respuesta 404 si no se encuentran los Juegos Olímpicos cuya información se quiere actualizar
    //Se mostrará un código de respuesta 409 si no se pueden actualizar los Juegos Olímpicos porque la información que se quiere actualizar está duplicada (se quieren actualizar unos nuevos Juegos Olímpicos con un año de celebración ya existente)
    @Operation(summary = "Update some Olympic Games information of an Olympic Games by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information of the Olympic Games with indicated id updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OlympicGamesModel.class))}),
            @ApiResponse(responseCode = "400", description = "Incorrect information to be updated on the Olympic Games",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Olympic Games with the indicated id not found",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Duplicated information of the Olympic Games to be updated",
                    content = @Content)})
    @PatchMapping(path = "/id={idOlympicGames}")
    public ResponseEntity<OlympicGamesModel> updateOlympicGamesById(@PathVariable("idOlympicGames") @Parameter(description = "Identifier of the Olympic Games", required = true) Long idOlympicGames, @RequestBody @Parameter(description = "Olympic Games information", required = true, schema = @Schema(implementation = OlympicGamesModel.class), examples = {@ExampleObject(name = "olympicGamesYear", summary = "Olympic Games year", value = "2020"), @ExampleObject(name = "olympicGamesPlace", summary = "Olympic Games place", value = "Tokyo")}) Map<String, Object> olympicGamesContent) {
        int olympicGamesYear = 0;
        String olympicGamesPlace = null;
        boolean badKeys = false;
        Optional<OlympicGamesModel> oldOlympicGames = this.olympicGamesService.getOlympicGamesById(idOlympicGames);
        if (oldOlympicGames.isPresent()) {
            try {
                for (Map.Entry<String, Object> entry : olympicGamesContent.entrySet()) {
                    if (!entry.getKey().equals("olympicGamesYear") && !entry.getKey().equals("olympicGamesPlace")) {
                        badKeys = true;
                        break;
                    }
                    if (entry.getKey().equals("olympicGamesYear")) {
                        olympicGamesYear = Integer.parseInt(entry.getValue().toString());
                    }
                    if (entry.getKey().equals("olympicGamesPlace")) {
                        olympicGamesPlace = entry.getValue().toString();
                    }
                }
                if (badKeys) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                if (olympicGamesYear == 0 && olympicGamesPlace == null) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            } catch (RuntimeException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            try {
                return new ResponseEntity<>(this.olympicGamesService.updateOlympicGamesById(oldOlympicGames.get(), olympicGamesYear, olympicGamesPlace), HttpStatus.OK);
            } catch (RuntimeException e) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Método o petición API de tipo DELETE para eliminar la información de unos Juegos Olímpicos que tienen un determinado identificador
    //Se mostrará un código de respuesta 204 si existían los Juegos Olímpicos y su información ha sido eliminada
    //Se mostrará un código de respuesta 404 si no se encuentran los Juegos Olímpicos cuya información se quiere eliminar
    @Operation(summary = "Delete an Olympic Games by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Olympic Games with the indicated id deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Olympic Games with the indicated id not found",
                    content = @Content)})
    @DeleteMapping(path = "/id={idOlympicGames}")
    public ResponseEntity<Object> deleteOlympicGames(@PathVariable("idOlympicGames") @Parameter(description = "Identifier of the Olympic Games", required = true) Long idOlympicGames) {
        if (this.olympicGamesService.getOlympicGamesById(idOlympicGames).isPresent()) {
            this.olympicGamesService.deleteOlympicGames(idOlympicGames);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}