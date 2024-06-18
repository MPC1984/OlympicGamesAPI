package com.exampleAPI.olympicGames.controllers;

import com.exampleAPI.olympicGames.models.AthleteModel;
import com.exampleAPI.olympicGames.services.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping ("/olympicGames/athlete")
public class AthleteController {

    @Autowired
    AthleteService athleteService;

    @DeleteMapping (path = "/id={idAthlete}")
    public ResponseEntity<Object> deleteAthlete(@PathVariable("idAthlete") Long idAthlete) {
        if(this.athleteService.getAthleteById(idAthlete).isPresent()) {
            this.athleteService.deleteAthlete(idAthlete);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<AthleteModel>> getAthlete() {
        if(!this.athleteService.getAthlete().isEmpty()) {
            return new ResponseEntity<>(this.athleteService.getAthlete(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping (path = "/id={idAthlete}")
    public ResponseEntity<Optional<AthleteModel>> getAthleteById(@PathVariable("idAthlete") Long idAthlete) {
        if(this.athleteService.getAthleteById(idAthlete).isPresent()) {
            return new ResponseEntity<>(this.athleteService.getAthleteById(idAthlete), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping (path = "/name={athleteName}")
    public ResponseEntity<List<AthleteModel>> getAthleteByName(@PathVariable("athleteName") String athleteName) {
        if(!this.athleteService.getAthleteByName(athleteName).isEmpty()) {
            return new ResponseEntity<>(this.athleteService.getAthleteByName(athleteName), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping (path = "/surname={athleteSurname}")
    public ResponseEntity<List<AthleteModel>> getAthleteBySurname(@PathVariable("athleteSurname") String athleteSurname) {
        if(!this.athleteService.getAthleteBySurname(athleteSurname).isEmpty()) {
            return new ResponseEntity<>(this.athleteService.getAthleteBySurname(athleteSurname), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping (path = "/country={athleteCountry}")
    public ResponseEntity<List<AthleteModel>> getAthleteByCountry(@PathVariable("athleteCountry") String athleteCountry) {
        if(!this.athleteService.getAthleteByCountry(athleteCountry).isEmpty()) {
            return new ResponseEntity<>(this.athleteService.getAthleteByCountry(athleteCountry), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<AthleteModel> saveAthlete(@RequestBody Map<String, String> athleteContent) {
        String athleteName = null;
        String athleteSurname = null;
        String athleteCountry = null;
        boolean badKeys = false;
        try {
            for (Map.Entry<String, String> entry : athleteContent.entrySet()) {
                if(!entry.getKey().equals("athleteName") && !entry.getKey().equals("athleteSurname") && !entry.getKey().equals("athleteCountry")){
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
            if(badKeys) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if(athleteName == null || athleteSurname == null || athleteCountry == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(this.athleteService.saveAthlete(athleteName, athleteSurname, athleteCountry), HttpStatus.CREATED);
    }

    @PatchMapping (path = "/id={idAthlete}")
    public ResponseEntity<AthleteModel> updateAthleteById(@PathVariable("idAthlete") Long idAthlete, @RequestBody Map<String, String> athleteContent) {
        String athleteName = null;
        String athleteSurname = null;
        String athleteCountry = null;
        boolean badKeys = false;
        Optional<AthleteModel> oldAthlete = this.athleteService.getAthleteById(idAthlete);
        if(oldAthlete.isPresent()) {
            try {
                for (Map.Entry<String, String> entry : athleteContent.entrySet()) {
                    if(!entry.getKey().equals("athleteName") && !entry.getKey().equals("athleteSurname") && !entry.getKey().equals("athleteCountry")){
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
                if(badKeys) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                if(athleteName == null && athleteSurname == null && athleteCountry == null){
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

}