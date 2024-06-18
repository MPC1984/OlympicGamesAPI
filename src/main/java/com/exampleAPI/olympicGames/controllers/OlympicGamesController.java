package com.exampleAPI.olympicGames.controllers;

import com.exampleAPI.olympicGames.models.OlympicGamesModel;
import com.exampleAPI.olympicGames.services.OlympicGamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping ("/olympicGames")
public class OlympicGamesController {

    @Autowired
    OlympicGamesService olympicGamesService;

    @DeleteMapping (path = "/id={idOlympicGames}")
    public ResponseEntity<Object> deleteOlympicGames(@PathVariable("idOlympicGames") Long idOlympicGames) {
        if(this.olympicGamesService.getOlympicGamesById(idOlympicGames).isPresent()) {
            this.olympicGamesService.deleteOlympicGames(idOlympicGames);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<OlympicGamesModel>> getOlympicGames() {
        if(!this.olympicGamesService.getOlympicGames().isEmpty()) {
            return new ResponseEntity<>(this.olympicGamesService.getOlympicGames(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping (path = "/id={idOlympicGames}")
    public ResponseEntity<Optional<OlympicGamesModel>> getOlympicGamesById(@PathVariable("idOlympicGames") Long idOlympicGames) {
        if(this.olympicGamesService.getOlympicGamesById(idOlympicGames).isPresent()) {
            return new ResponseEntity<>(this.olympicGamesService.getOlympicGamesById(idOlympicGames), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping (path = "/year={olympicGamesYear}")
    public ResponseEntity<OlympicGamesModel> getOlympicGamesByYear(@PathVariable("olympicGamesYear") Integer olympicGamesYear) {
        if(this.olympicGamesService.getOlympicGamesByYear(olympicGamesYear) != null) {
            return new ResponseEntity<>(this.olympicGamesService.getOlympicGamesByYear(olympicGamesYear), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping (path = "/place={olympicGamesPlace}")
    public ResponseEntity<List<OlympicGamesModel>> getOlympicGamesByPlace(@PathVariable("olympicGamesPlace") String olympicGamesPlace) {
        if(!this.olympicGamesService.getOlympicGamesByPlace(olympicGamesPlace).isEmpty()) {
            return new ResponseEntity<>(this.olympicGamesService.getOlympicGamesByPlace(olympicGamesPlace), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<OlympicGamesModel> saveOlympicGames(@RequestBody Map<String, String> olympicGamesContent) {
        int olympicGamesYear = 0;
        String olympicGamesPlace = null;
        boolean badKeys = false;
        try {
            for (Map.Entry<String, String> entry : olympicGamesContent.entrySet()) {
                if(!entry.getKey().equals("olympicGamesYear") && !entry.getKey().equals("olympicGamesPlace")){
                    badKeys = true;
                    break;
                }
                if (entry.getKey().equals("olympicGamesYear")) {
                    olympicGamesYear = Integer.parseInt(entry.getValue());
                }
                if (entry.getKey().equals("olympicGamesPlace")) {
                    olympicGamesPlace = entry.getValue();
                }
            }
            if(badKeys) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if(olympicGamesYear == 0 || olympicGamesPlace == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(this.olympicGamesService.saveOlympicGames(olympicGamesYear, olympicGamesPlace), HttpStatus.CREATED);
    }

    @PatchMapping (path = "/id={idOlympicGames}")
    public ResponseEntity<OlympicGamesModel> updateOlympicGamesById(@PathVariable("idOlympicGames") Long idOlympicGames, @RequestBody Map<String, String> olympicGamesContent) {
        int olympicGamesYear = 0;
        String olympicGamesPlace = null;
        boolean badKeys = false;
        Optional<OlympicGamesModel> oldOlympicGames = this.olympicGamesService.getOlympicGamesById(idOlympicGames);
        if(oldOlympicGames.isPresent()) {
            try {
                for (Map.Entry<String, String> entry : olympicGamesContent.entrySet()) {
                    if(!entry.getKey().equals("olympicGamesYear") && !entry.getKey().equals("olympicGamesPlace")){
                        badKeys = true;
                        break;
                    }
                    if (entry.getKey().equals("olympicGamesYear")) {
                        olympicGamesYear = Integer.parseInt(entry.getValue());
                    }
                    if (entry.getKey().equals("olympicGamesPlace")) {
                        olympicGamesPlace = entry.getValue();
                    }
                }
                if(badKeys) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                if(olympicGamesYear == 0 && olympicGamesPlace == null){
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            } catch (RuntimeException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(this.olympicGamesService.updateOlympicGamesById(oldOlympicGames.get(), olympicGamesYear, olympicGamesPlace), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}