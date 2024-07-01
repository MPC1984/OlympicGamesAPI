package com.exampleAPI.olympicGames.controllers;

import com.exampleAPI.olympicGames.models.SportModel;
import com.exampleAPI.olympicGames.services.SportService;
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

//Clase en la que se definen las diferentes peticiones API que se pueden realizar sobre el modelo de datos que tiene la información de un deporte
@RestController
@RequestMapping("/olympicGames/sport")
@Tag(name = "Sports", description = "Operations over sports")
public class SportController {

    @Autowired
    SportService sportService;

    //Método o petición API de tipo GET para obtener una lista con la información de todos los deportes
    //Se mostrará un código de respuesta 200 si la lista no está vacía (es decir, contiene información)
    //Se mostrará un código de respuesta 404 si la lista está vacía (es decir, no contiene información)
    @Operation(summary = "Get all sports information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all sports information",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = SportModel.class)))}),
            @ApiResponse(responseCode = "404", description = "There are no sports",
                    content = @Content)})
    @GetMapping
    public ResponseEntity<List<SportModel>> getSport() {
        if (!this.sportService.getSport().isEmpty()) {
            return new ResponseEntity<>(this.sportService.getSport(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Método o petición API de tipo GET para obtener la información de un deporte que tiene un determinado identificador
    //Se mostrará un código de respuesta 200 si se encuentra el deporte y por tanto su información
    //Se mostrará un código de respuesta 404 si no se encuentra el deporte
    @Operation(summary = "Get a sport information by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information of the sport with the indicated id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SportModel.class))}),
            @ApiResponse(responseCode = "404", description = "Sport with the indicated id not found",
                    content = @Content)})
    @GetMapping(path = "/id={idSport}")
    public ResponseEntity<Optional<SportModel>> getSportById(@PathVariable("idSport") @Parameter(description = "Identifier of the sport", required = true) Long idSport) {
        if (this.sportService.getSportById(idSport).isPresent()) {
            return new ResponseEntity<>(this.sportService.getSportById(idSport), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Método o petición API de tipo GET para obtener una lista con la información de todos los deportes que tienen un determinado nombre
    //Se mostrará un código de respuesta 200 si la lista no está vacía (es decir, contiene información)
    //Se mostrará un código de respuesta 404 si la lista está vacía (es decir, no contiene información)
    @Operation(summary = "Get all sports information by their name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information of the sports with the indicated name",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = SportModel.class)))}),
            @ApiResponse(responseCode = "404", description = "Sports with the indicated name not found",
                    content = @Content)})
    @GetMapping(path = "/name={sportName}")
    public ResponseEntity<List<SportModel>> getSportByName(@PathVariable("sportName") @Parameter(description = "Name of the sport", required = true) String sportName) {
        if (!this.sportService.getSportByName(sportName).isEmpty()) {
            return new ResponseEntity<>(this.sportService.getSportByName(sportName), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Método o petición API de tipo GET para obtener una lista con la información de todos los deportes que tienen una determinada categoría
    //Se mostrará un código de respuesta 200 si la lista no está vacía (es decir, contiene información)
    //Se mostrará un código de respuesta 404 si la lista está vacía (es decir, no contiene información)
    @Operation(summary = "Get all sports information by their category name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information of the sports with the indicated category name",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = SportModel.class)))}),
            @ApiResponse(responseCode = "404", description = "Sports with the indicated category name not found",
                    content = @Content)})
    @GetMapping(path = "/category={sportCategoryName}")
    public ResponseEntity<List<SportModel>> getSportByCategoryName(@PathVariable("sportCategoryName") @Parameter(description = "Category name of the sport", required = true) String sportCategoryName) {
        if (!this.sportService.getSportByCategoryName(sportCategoryName).isEmpty()) {
            return new ResponseEntity<>(this.sportService.getSportByCategoryName(sportCategoryName), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Método o petición API de tipo POST para guardar la información de un deporte dados el nombre y la categoría
    //Se mostrará un código de respuesta 201 si se puede guardar el deporte y por tanto su información
    //Se mostrará un código de respuesta 400 si no se puede guardar el deporte porque la información que se quiere guardar no es correcta (la información no tiene los nombres de la claves del body correctos, es nula o no es del tipo correcto)
    @Operation(summary = "Add a new sport")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "New sport added with correct information",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SportModel.class))}),
            @ApiResponse(responseCode = "400", description = "Incorrect information of the new sport",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<SportModel> saveSport(@RequestBody @Parameter(description = "Sport information", required = true, schema = @Schema(implementation = SportModel.class), examples = {@ExampleObject(name = "sportName", summary = "Sport name", value = "Kárate"), @ExampleObject(name = "sportCategoryName", summary = "Sport category name", value = "Kata")}) Map<String, String> sportContent) {
        String sportName = null;
        String sportCategoryName = null;
        boolean badKeys = false;
        try {
            for (Map.Entry<String, String> entry : sportContent.entrySet()) {
                if (!entry.getKey().equals("sportName") && !entry.getKey().equals("sportCategoryName")) {
                    badKeys = true;
                    break;
                }
                if (entry.getKey().equals("sportName")) {
                    sportName = entry.getValue();
                }
                if (entry.getKey().equals("sportCategoryName")) {
                    sportCategoryName = entry.getValue();
                }
            }
            if (badKeys) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (sportName == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(this.sportService.saveSport(sportName, sportCategoryName), HttpStatus.CREATED);
    }

    //Método o petición API de tipo PATCH para actualizar la información de un deporte que tiene un determinado identificador con el nuevo nombre y/o la nueva categoría
    //Se mostrará un código de respuesta 200 si se puede actualizar el deporte y por tanto su información
    //Se mostrará un código de respuesta 400 si no se puede actualizar el deporte porque la información que se quiere actualizar no es correcta (la información no tiene los nombres de la claves del body correctos, no es del tipo correcto o el nuevo nombre es nulo)
    //Se mostrará un código de respuesta 404 si no se encuentra el deporte cuya información se quiere actualizar
    @Operation(summary = "Update some sport information of a sport by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information of the sport with indicated id updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SportModel.class))}),
            @ApiResponse(responseCode = "400", description = "Incorrect information to be updated on the sport",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Sport with the indicated id not found",
                    content = @Content)})
    @PatchMapping(path = "/id={idSport}")
    public ResponseEntity<SportModel> updateSportById(@PathVariable("idSport") @Parameter(description = "Identifier of the sport", required = true) Long idSport, @RequestBody @Parameter(description = "Sport information", required = true, schema = @Schema(implementation = SportModel.class), examples = {@ExampleObject(name = "sportName", summary = "Sport name", value = "Kárate"), @ExampleObject(name = "sportCategoryName", summary = "Sport category name", value = "Kata")}) Map<String, String> sportContent) {
        String sportName = null;
        String sportCategoryName = null;
        boolean badKeys = false;
        Optional<SportModel> oldSport = this.sportService.getSportById(idSport);
        if (oldSport.isPresent()) {
            try {
                for (Map.Entry<String, String> entry : sportContent.entrySet()) {
                    if (!entry.getKey().equals("sportName") && !entry.getKey().equals("sportCategoryName")) {
                        badKeys = true;
                        break;
                    }
                    if (entry.getKey().equals("sportName")) {
                        sportName = entry.getValue();
                    }
                    if (entry.getKey().equals("sportCategoryName")) {
                        sportCategoryName = entry.getValue();
                    }
                }
                if (badKeys) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                if (sportName == null && sportCategoryName == null) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            } catch (RuntimeException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(this.sportService.updateSportById(oldSport.get(), sportName, sportCategoryName), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Método o petición API de tipo DELETE para eliminar la información de un deporte que tiene un determinado identificador
    //Se mostrará un código de respuesta 204 si existía el deporte y su información ha sido eliminada
    //Se mostrará un código de respuesta 404 si no se encuentra el deporte cuya información se quiere eliminar
    @Operation(summary = "Delete a sport by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sport with the indicated id deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Sport with the indicated id not found",
                    content = @Content)})
    @DeleteMapping(path = "/id={idSport}")
    public ResponseEntity<Object> deleteSport(@PathVariable("idSport") @Parameter(description = "Identifier of the sport", required = true) Long idSport) {
        if (this.sportService.getSportById(idSport).isPresent()) {
            this.sportService.deleteSport(idSport);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}