package com.exampleAPI.olympicGames.controllers;

import com.exampleAPI.olympicGames.models.MetalModel;
import com.exampleAPI.olympicGames.services.MetalService;
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

//Clase en la que se definen las diferentes peticiones API que se pueden realizar sobre el modelo de datos que tiene la información de un metal
@RestController
@RequestMapping("/olympicGames/metal")
@Tag(name = "Metals", description = "Operations over metals")
public class MetalController {

    @Autowired
    MetalService metalService;

    //Método o petición API de tipo GET para obtener una lista con la información de todos los metales
    //Se mostrará un código de respuesta 200 si la lista no está vacía (es decir, contiene información)
    //Se mostrará un código de respuesta 404 si la lista está vacía (es decir, no contiene información)
    @Operation(summary = "Get all metals information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all metals information",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = MetalModel.class)))}),
            @ApiResponse(responseCode = "404", description = "There are no metals",
                    content = @Content)})
    @GetMapping
    public ResponseEntity<List<MetalModel>> getMetal() {
        if (!this.metalService.getMetal().isEmpty()) {
            return new ResponseEntity<>(this.metalService.getMetal(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Método o petición API de tipo GET para obtener la información de un metal que tiene un determinado identificador
    //Se mostrará un código de respuesta 200 si se encuentra el metal y por tanto su información
    //Se mostrará un código de respuesta 404 si no se encuentra el metal
    @Operation(summary = "Get a metal information by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information of the metal with the indicated id",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MetalModel.class))}),
            @ApiResponse(responseCode = "404", description = "Metal with the indicated id not found",
                    content = @Content)})
    @GetMapping(path = "/id={idMetal}")
    public ResponseEntity<Optional<MetalModel>> getMetalById(@PathVariable("idMetal") @Parameter(description = "Identifier of the metal", required = true) Long idMetal) {
        if (this.metalService.getMetalById(idMetal).isPresent()) {
            return new ResponseEntity<>(this.metalService.getMetalById(idMetal), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Método o petición API de tipo GET para obtener la información de un metal que tiene un determinado tipo
    //Se mostrará un código de respuesta 200 si se encuentra el metal y por tanto su información
    //Se mostrará un código de respuesta 404 si no se encuentra el metal
    @Operation(summary = "Get a metal information by its type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information of the metal with the indicated type",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MetalModel.class))}),
            @ApiResponse(responseCode = "404", description = "Metal with the indicated type not found",
                    content = @Content)})
    @GetMapping(path = "/type={metalType}")
    public ResponseEntity<MetalModel> getMetalByType(@PathVariable("metalType") @Parameter(description = "Type of the metal", required = true) String metalType) {
        if (this.metalService.getMetalByType(metalType) != null) {
            return new ResponseEntity<>(this.metalService.getMetalByType(metalType), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Método o petición API de tipo POST para guardar la información de un metal dado el tipo
    //Se mostrará un código de respuesta 201 si se puede guardar el metal y por tanto su información
    //Se mostrará un código de respuesta 400 si no se puede guardar el metal porque la información que se quiere guardar no es correcta (la información no tiene el nombre de la clave del body correcto, es nula o no es del tipo correcto)
    //Se mostrará un código de respuesta 409 si no se puede guardar el metal porque la información que se quiere guardar está duplicada (se quiere guardar un nuevo metal con un tipo de metal ya existente)
    @Operation(summary = "Add a new metal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "New metal added with correct information",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MetalModel.class))}),
            @ApiResponse(responseCode = "400", description = "Incorrect information of the new metal",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Duplicated information of the new metal",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<MetalModel> saveMetal(@RequestBody @Parameter(description = "Metal information", required = true, schema = @Schema(implementation = MetalModel.class), examples = @ExampleObject(name = "metalType", summary = "Metal type", value = "Oro")) Map<String, String> metalContent) {
        String metalType = null;
        boolean badKeys = false;
        try {
            for (Map.Entry<String, String> entry : metalContent.entrySet()) {
                if (entry.getKey().equals("metalType")) {
                    metalType = entry.getValue();
                } else {
                    badKeys = true;
                    break;
                }
            }
            if (badKeys) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (metalType == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            return new ResponseEntity<>(this.metalService.saveMetal(metalType), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    //Método o petición API de tipo PATCH para actualizar la información de un metal que tiene un determinado identificador con el nuevo tipo
    //Se mostrará un código de respuesta 200 si se puede actualizar el metal y por tanto su información
    //Se mostrará un código de respuesta 400 si no se puede actualizar el metal porque la información que se quiere actualizar no es correcta (la información no tiene el nombre de la clave del body correcto, es nula o no es del tipo correcto)
    //Se mostrará un código de respuesta 404 si no se encuentra el metal cuya información se quiere actualizar
    //Se mostrará un código de respuesta 409 si no se puede actualizar el metal porque la información que se quiere actualizar está duplicada (se quiere actualizar un metal con un tipo de metal ya existente)
    @Operation(summary = "Update some metal information of a metal by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information of the metal with indicated id updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = MetalModel.class))}),
            @ApiResponse(responseCode = "400", description = "Incorrect information to be updated on the metal",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Metal with the indicated id not found",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Duplicated information of the metal to be updated",
                    content = @Content)})
    @PatchMapping(path = "/id={idMetal}")
    public ResponseEntity<MetalModel> updateMetalById(@PathVariable("idMetal") @Parameter(description = "Identifier of the metal", required = true) Long idMetal, @RequestBody @Parameter(description = "Metal information", required = true, schema = @Schema(implementation = MetalModel.class), examples = @ExampleObject(name = "metalType", summary = "Metal type", value = "Oro")) Map<String, String> metalContent) {
        String metalType = null;
        boolean badKeys = false;
        Optional<MetalModel> oldMetal = this.metalService.getMetalById(idMetal);
        if (oldMetal.isPresent()) {
            try {
                for (Map.Entry<String, String> entry : metalContent.entrySet()) {
                    if (entry.getKey().equals("metalType")) {
                        metalType = entry.getValue();
                    } else {
                        badKeys = true;
                        break;
                    }
                }
                if (badKeys) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                if (metalType == null) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            } catch (RuntimeException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            try {
                return new ResponseEntity<>(this.metalService.updateMetalById(oldMetal.get(), metalType), HttpStatus.OK);
            } catch (RuntimeException e) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Método o petición API de tipo DELETE para eliminar la información de un metal que tiene un determinado identificador
    //Se mostrará un código de respuesta 204 si existía el metal y su información ha sido eliminada
    //Se mostrará un código de respuesta 404 si no se encuentra el metal cuya información se quiere eliminar
    @Operation(summary = "Delete a metal by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Metal with the indicated id deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Metal with the indicated id not found",
                    content = @Content)})
    @DeleteMapping(path = "/id={idMetal}")
    public ResponseEntity<Object> deleteMetal(@PathVariable("idMetal") @Parameter(description = "Identifier of the metal", required = true) Long idMetal) {
        if (this.metalService.getMetalById(idMetal).isPresent()) {
            this.metalService.deleteMetal(idMetal);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}