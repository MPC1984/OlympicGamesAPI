package com.exampleAPI.olympicGames.controllers;

import com.exampleAPI.olympicGames.models.AthleteModel;
import com.exampleAPI.olympicGames.models.OlympicGamesMedalTableModel;
import com.exampleAPI.olympicGames.services.OlympicGamesMedalTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/olympicGames/medalTable")
public class OlympicGamesMedalTableController {

    @Autowired
    OlympicGamesMedalTableService olympicGamesMedalTableService;

    @DeleteMapping (path = "/id={idOlympicGamesMedalTable}")
    public ResponseEntity<Object> deleteOlympicGamesMedalTable(@PathVariable("idOlympicGamesMedalTable") Long idOlympicGamesMedalTable) {
        if(this.olympicGamesMedalTableService.getOlympicGamesMedalTableById(idOlympicGamesMedalTable).isPresent()) {
            this.olympicGamesMedalTableService.deleteOlympicGamesMedalTable(idOlympicGamesMedalTable);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<OlympicGamesMedalTableModel>> getOlympicGamesMedalTable() {
        if(!this.olympicGamesMedalTableService.getOlympicGamesMedalTable().isEmpty()) {
            return new ResponseEntity<>(this.olympicGamesMedalTableService.getOlympicGamesMedalTable(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping (path = "/id={idOlympicGamesMedalTable}")
    public ResponseEntity<Optional<OlympicGamesMedalTableModel>> getOlympicGamesMedalTableById(@PathVariable("idOlympicGamesMedalTable") Long idOlympicGamesMedalTable) {
        if(this.olympicGamesMedalTableService.getOlympicGamesMedalTableById(idOlympicGamesMedalTable).isPresent()) {
            return new ResponseEntity<>(this.olympicGamesMedalTableService.getOlympicGamesMedalTableById(idOlympicGamesMedalTable), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping (path = "/idOlympicGames={idOlympicGames}")
    public ResponseEntity<List<OlympicGamesMedalTableModel>> getOlympicGamesMedalTableByOlympicGamesId(@PathVariable("idOlympicGames") Long idOlympicGames) {
        if(!this.olympicGamesMedalTableService.getOlympicGamesMedalTableByOlympicGamesId(idOlympicGames).isEmpty()) {
            return new ResponseEntity<>(this.olympicGamesMedalTableService.getOlympicGamesMedalTableByOlympicGamesId(idOlympicGames), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping (path = "/olympicGamesYear={olympicGamesYear}")
    public ResponseEntity<List<OlympicGamesMedalTableModel>> getOlympicGamesMedalTableByOlympicGamesYear(@PathVariable("olympicGamesYear") Integer olympicGamesYear) {
        if(!this.olympicGamesMedalTableService.getOlympicGamesMedalTableByOlympicGamesYear(olympicGamesYear).isEmpty()) {
            return new ResponseEntity<>(this.olympicGamesMedalTableService.getOlympicGamesMedalTableByOlympicGamesYear(olympicGamesYear), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping (path = "/olympicGamesPlace={olympicGamesPlace}")
    public ResponseEntity<List<OlympicGamesMedalTableModel>> getOlympicGamesMedalTableByOlympicGamesPlace(@PathVariable("olympicGamesPlace") String olympicGamesPlace) {
        if(!this.olympicGamesMedalTableService.getOlympicGamesMedalTableByOlympicGamesPlace(olympicGamesPlace).isEmpty()) {
            return new ResponseEntity<>(this.olympicGamesMedalTableService.getOlympicGamesMedalTableByOlympicGamesPlace(olympicGamesPlace), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping (path = "/idSport={idSport}")
    public ResponseEntity<List<OlympicGamesMedalTableModel>> getOlympicGamesMedalTableBySportId(@PathVariable("idSport") Long idSport) {
        if(!this.olympicGamesMedalTableService.getOlympicGamesMedalTableBySportId(idSport).isEmpty()) {
            return new ResponseEntity<>(this.olympicGamesMedalTableService.getOlympicGamesMedalTableBySportId(idSport), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping (path = "/sportName={sportName}")
    public ResponseEntity<List<OlympicGamesMedalTableModel>> getOlympicGamesMedalTableBySportName(@PathVariable("sportName") String sportName) {
        if(!this.olympicGamesMedalTableService.getOlympicGamesMedalTableBySportName(sportName).isEmpty()) {
            return new ResponseEntity<>(this.olympicGamesMedalTableService.getOlympicGamesMedalTableBySportName(sportName), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping (path = "/categoryName={categoryName}")
    public ResponseEntity<List<OlympicGamesMedalTableModel>> getOlympicGamesMedalTableByCategoryName(@PathVariable("categoryName") String categoryName) {
        if(!this.olympicGamesMedalTableService.getOlympicGamesMedalTableByCategoryName(categoryName).isEmpty()) {
            return new ResponseEntity<>(this.olympicGamesMedalTableService.getOlympicGamesMedalTableByCategoryName(categoryName), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping (path = "/idMetal={idMetal}")
    public ResponseEntity<List<OlympicGamesMedalTableModel>> getOlympicGamesMedalTableByMetalId(@PathVariable("idMetal") Long idMetal) {
        if(!this.olympicGamesMedalTableService.getOlympicGamesMedalTableByMetalId(idMetal).isEmpty()) {
            return new ResponseEntity<>(this.olympicGamesMedalTableService.getOlympicGamesMedalTableByMetalId(idMetal), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping (path = "/metalType={metalType}")
    public ResponseEntity<List<OlympicGamesMedalTableModel>> getOlympicGamesMedalTableByMetalType(@PathVariable("metalType") String metalType) {
        if(!this.olympicGamesMedalTableService.getOlympicGamesMedalTableByMetalType(metalType).isEmpty()) {
            return new ResponseEntity<>(this.olympicGamesMedalTableService.getOlympicGamesMedalTableByMetalType(metalType), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping (path = "/idAthlete={idAthlete}")
    public ResponseEntity<List<OlympicGamesMedalTableModel>> getOlympicGamesMedalTableByAthleteId(@PathVariable("idAthlete") Long idAthlete) {
        if(!this.olympicGamesMedalTableService.getOlympicGamesMedalTableByAthleteId(idAthlete).isEmpty()) {
            return new ResponseEntity<>(this.olympicGamesMedalTableService.getOlympicGamesMedalTableByAthleteId(idAthlete), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping (path = "/athleteName={athleteName}")
    public ResponseEntity<List<OlympicGamesMedalTableModel>> getOlympicGamesMedalTableByAthleteName(@PathVariable("athleteName") String athleteName) {
        if(!this.olympicGamesMedalTableService.getOlympicGamesMedalTableByAthleteName(athleteName).isEmpty()) {
            return new ResponseEntity<>(this.olympicGamesMedalTableService.getOlympicGamesMedalTableByAthleteName(athleteName), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping (path = "/athleteSurname={athleteSurname}")
    public ResponseEntity<List<OlympicGamesMedalTableModel>> getOlympicGamesMedalTableByAthleteSurname(@PathVariable("athleteSurname") String athleteSurname) {
        if(!this.olympicGamesMedalTableService.getOlympicGamesMedalTableByAthleteSurname(athleteSurname).isEmpty()) {
            return new ResponseEntity<>(this.olympicGamesMedalTableService.getOlympicGamesMedalTableByAthleteSurname(athleteSurname), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping (path = "/athleteCountry={athleteCountry}")
    public ResponseEntity<List<OlympicGamesMedalTableModel>> getOlympicGamesMedalTableByAthleteCountry(@PathVariable("athleteCountry") String athleteCountry) {
        if(!this.olympicGamesMedalTableService.getOlympicGamesMedalTableByAthleteCountry(athleteCountry).isEmpty()) {
            return new ResponseEntity<>(this.olympicGamesMedalTableService.getOlympicGamesMedalTableByAthleteCountry(athleteCountry), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<OlympicGamesMedalTableModel> saveOlympicGamesMedalTable(@RequestBody Map<String, String> idsOlympicGamesMedalTable) {
        Long idOlympicGames = 0L;
        Long idSport = 0L;
        Long idMetal = 0L;
        Long idAthlete = 0L;
        boolean badKeys = false;
        try {
            for (Map.Entry<String, String> entry : idsOlympicGamesMedalTable.entrySet()) {
                if(!entry.getKey().equals("idOlympicGames") && !entry.getKey().equals("idSport") && !entry.getKey().equals("idMetal") && !entry.getKey().equals("idAthlete")){
                    badKeys = true;
                    break;
                }
                if (entry.getKey().equals("idOlympicGames")) {
                    idOlympicGames = Long.parseLong(entry.getValue());
                }
                if (entry.getKey().equals("idSport")) {
                    idSport = Long.parseLong(entry.getValue());
                }
                if (entry.getKey().equals("idMetal")) {
                    idMetal = Long.parseLong(entry.getValue());
                }
                if (entry.getKey().equals("idAthlete")) {
                    idAthlete = Long.parseLong(entry.getValue());
                }
            }
            if(badKeys) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if(idOlympicGames == 0L || idSport == 0L || idMetal == 0L || idAthlete == 0L){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        OlympicGamesMedalTableModel olympicGamesMedalTableModelSaved = this.olympicGamesMedalTableService.saveOlympicGamesMedalTable(idOlympicGames, idSport, idMetal, idAthlete);
        if(olympicGamesMedalTableModelSaved != null) {
            return new ResponseEntity<>(olympicGamesMedalTableModelSaved, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping (path = "/id={idOlympicGamesMedalTable}")
    public ResponseEntity<OlympicGamesMedalTableModel> updateOlympicGamesMedalTableById(@PathVariable("idOlympicGamesMedalTable") Long idOlympicGamesMedalTable, @RequestBody Map<String, String> idsOlympicGamesMedalTable) {
        Long idOlympicGames = 0L;
        Long idSport = 0L;
        Long idMetal = 0L;
        Long idAthlete = 0L;
        boolean badKeys = false;
        Optional<OlympicGamesMedalTableModel> oldOlympicGamesMedalTable = this.olympicGamesMedalTableService.getOlympicGamesMedalTableById(idOlympicGamesMedalTable);
        if(oldOlympicGamesMedalTable.isPresent()) {
            try {
                for (Map.Entry<String, String> entry : idsOlympicGamesMedalTable.entrySet()) {
                    if(!entry.getKey().equals("idOlympicGames") && !entry.getKey().equals("idSport") && !entry.getKey().equals("idMetal") && !entry.getKey().equals("idAthlete")){
                        badKeys = true;
                        break;
                    }
                    if (entry.getKey().equals("idOlympicGames")) {
                        idOlympicGames = Long.parseLong(entry.getValue());
                    }
                    if (entry.getKey().equals("idSport")) {
                        idSport = Long.parseLong(entry.getValue());
                    }
                    if (entry.getKey().equals("idMetal")) {
                        idMetal = Long.parseLong(entry.getValue());
                    }
                    if (entry.getKey().equals("idAthlete")) {
                        idAthlete = Long.parseLong(entry.getValue());
                    }
                }
                if(badKeys) {
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
                if(idOlympicGames == 0L && idSport == 0L && idMetal == 0L && idAthlete == 0L){
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            } catch (RuntimeException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            OlympicGamesMedalTableModel olympicGamesMedalTableModelUpdated = this.olympicGamesMedalTableService.updateOlympicGamesMedalTableById(oldOlympicGamesMedalTable.get(), idOlympicGames, idSport, idMetal, idAthlete);
            if(olympicGamesMedalTableModelUpdated != null) {
                return new ResponseEntity<>(olympicGamesMedalTableModelUpdated, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}