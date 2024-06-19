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

@RestController
@RequestMapping ("/olympicGames/metal")
@Tag(name = "Metals", description = "Operations over metals")
public class MetalController {

    @Autowired
    MetalService metalService;

    @Operation(summary = "Get all metals information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all metals information",
                    content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = MetalModel.class))) }),
            @ApiResponse(responseCode = "404", description = "There are no metals",
                    content = @Content) })
    @GetMapping
    public ResponseEntity<List<MetalModel>> getMetal() {
        if(!this.metalService.getMetal().isEmpty()) {
            return new ResponseEntity<>(this.metalService.getMetal(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get a metal information by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information of the metal with the indicated id",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MetalModel.class)) }),
            @ApiResponse(responseCode = "404", description = "Metal with the indicated id not found",
                    content = @Content) })
    @GetMapping (path = "/id={idMetal}")
    public ResponseEntity<Optional<MetalModel>> getMetalById(@PathVariable("idMetal") @Parameter(description = "Identifier of the metal", required = true) Long idMetal) {
        if(this.metalService.getMetalById(idMetal).isPresent()) {
            return new ResponseEntity<>(this.metalService.getMetalById(idMetal), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get a metal information by its type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information of the metal with the indicated type",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MetalModel.class)) }),
            @ApiResponse(responseCode = "404", description = "Metal with the indicated type not found",
                    content = @Content) })
    @GetMapping (path = "/type={metalType}")
    public ResponseEntity<MetalModel> getMetalByType(@PathVariable("metalType") @Parameter(description = "Type of the metal", required = true) String metalType) {
        if(this.metalService.getMetalByType(metalType) != null) {
            return new ResponseEntity<>(this.metalService.getMetalByType(metalType), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Add a new metal")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "New metal added with correct information",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MetalModel.class)) }),
            @ApiResponse(responseCode = "400", description = "Incorrect information of the new metal",
                    content = @Content) })
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
            if(badKeys) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if(metalType == null){
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

    @Operation(summary = "Update some metal information of a metal by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Information of the metal with indicated id updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MetalModel.class)) }),
            @ApiResponse(responseCode = "400", description = "Incorrect information to be updated on the metal",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Metal with the indicated id not found",
                    content = @Content) })
    @PatchMapping (path = "/id={idMetal}")
    public ResponseEntity<MetalModel> updateMetalById(@PathVariable("idMetal") @Parameter(description = "Identifier of the metal", required = true) Long idMetal, @RequestBody @Parameter(description = "Metal information", required = true, schema = @Schema(implementation = MetalModel.class), examples = @ExampleObject(name = "metalType", summary = "Metal type", value = "Oro")) Map<String, String> metalContent) {
        String metalType = null;
        boolean badKeys = false;
        Optional<MetalModel> oldMetal = this.metalService.getMetalById(idMetal);
        if(oldMetal.isPresent()) {
            try {
                for (Map.Entry<String, String> entry : metalContent.entrySet()) {
                    if (entry.getKey().equals("metalType")) {
                        metalType = entry.getValue();
                    } else {
                        badKeys = true;
                        break;
                    }
                }
                if(badKeys) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                if(metalType == null){
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

    @Operation(summary = "Delete a metal by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Metal with the indicated id deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Metal with the indicated id not found",
                    content = @Content) })
    @DeleteMapping (path = "/id={idMetal}")
    public ResponseEntity<Object> deleteMetal(@PathVariable("idMetal") @Parameter(description = "Identifier of the metal", required = true) Long idMetal) {
        if(this.metalService.getMetalById(idMetal).isPresent()) {
            this.metalService.deleteMetal(idMetal);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}