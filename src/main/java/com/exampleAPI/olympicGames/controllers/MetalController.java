package com.exampleAPI.olympicGames.controllers;

import com.exampleAPI.olympicGames.models.MetalModel;
import com.exampleAPI.olympicGames.services.MetalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping ("/olympicGames/metal")
public class MetalController {

    @Autowired
    MetalService metalService;

    @DeleteMapping (path = "/id={idMetal}")
    public ResponseEntity<Object> deleteMetal(@PathVariable("idMetal") Long idMetal) {
        if(this.metalService.getMetalById(idMetal).isPresent()) {
            this.metalService.deleteMetal(idMetal);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<MetalModel>> getMetal() {
        if(!this.metalService.getMetal().isEmpty()) {
            return new ResponseEntity<>(this.metalService.getMetal(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping (path = "/id={idMetal}")
    public ResponseEntity<Optional<MetalModel>> getMetalById(@PathVariable("idMetal") Long idMetal) {
        if(this.metalService.getMetalById(idMetal).isPresent()) {
            return new ResponseEntity<>(this.metalService.getMetalById(idMetal), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping (path = "/type={metalType}")
    public ResponseEntity<MetalModel> getMetalByType(@PathVariable("metalType") String metalType) {
        if(this.metalService.getMetalByType(metalType) != null) {
            return new ResponseEntity<>(this.metalService.getMetalByType(metalType), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<MetalModel> saveMetal(@RequestBody Map<String, String> metalContent) {
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
        return new ResponseEntity<>(this.metalService.saveMetal(metalType), HttpStatus.CREATED);
    }

    @PatchMapping (path = "/id={idMetal}")
    public ResponseEntity<MetalModel> updateMetalById(@PathVariable("idMetal") Long idMetal, @RequestBody Map<String, String> metalContent) {
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
            return new ResponseEntity<>(this.metalService.updateMetalById(oldMetal.get(), metalType), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}