package com.exampleAPI.olympicGames.controllers;

import com.exampleAPI.olympicGames.models.SportModel;
import com.exampleAPI.olympicGames.services.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping ("/olympicGames/sport")
public class SportController {

    @Autowired
    SportService sportService;

    @DeleteMapping (path = "/id={idSport}")
    public ResponseEntity<Object> deleteSport(@PathVariable("idSport") Long idSport) {
        if(this.sportService.getSportById(idSport).isPresent()) {
            this.sportService.deleteSport(idSport);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<SportModel>> getSport() {
        if(!this.sportService.getSport().isEmpty()) {
            return new ResponseEntity<>(this.sportService.getSport(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping (path = "/id={idSport}")
    public ResponseEntity<Optional<SportModel>> getSportById(@PathVariable("idSport") Long idSport) {
        if(this.sportService.getSportById(idSport).isPresent()) {
            return new ResponseEntity<>(this.sportService.getSportById(idSport), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping (path = "/name={sportName}")
    public ResponseEntity<List<SportModel>> getSportByName(@PathVariable("sportName") String sportName) {
        if(!this.sportService.getSportByName(sportName).isEmpty()) {
            return new ResponseEntity<>(this.sportService.getSportByName(sportName), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping (path = "/category={sportCategoryName}")
    public ResponseEntity<List<SportModel>> getSportByCategoryName(@PathVariable("sportCategoryName") String sportCategoryName) {
        if(!this.sportService.getSportByCategoryName(sportCategoryName).isEmpty()) {
            return new ResponseEntity<>(this.sportService.getSportByCategoryName(sportCategoryName), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<SportModel> saveSport(@RequestBody Map<String, String> sportContent) {
        String sportName = null;
        String sportCategoryName = null;
        boolean badKeys = false;
        try {
            for (Map.Entry<String, String> entry : sportContent.entrySet()) {
                if(!entry.getKey().equals("sportName") && !entry.getKey().equals("sportCategoryName")){
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
            if(badKeys) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if(sportName == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(this.sportService.saveSport(sportName, sportCategoryName), HttpStatus.CREATED);
    }

    @PatchMapping (path = "/id={idSport}")
    public ResponseEntity<SportModel> updateSportById(@PathVariable("idSport") Long idSport, @RequestBody Map<String, String> sportContent) {
        String sportName = null;
        String sportCategoryName = null;
        boolean badKeys = false;
        Optional<SportModel> oldSport = this.sportService.getSportById(idSport);
        if(oldSport.isPresent()) {
            try {
                for (Map.Entry<String, String> entry : sportContent.entrySet()) {
                    if(!entry.getKey().equals("sportName") && !entry.getKey().equals("sportCategoryName")){
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
                if(badKeys) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                if(sportName == null && sportCategoryName == null) {
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

}