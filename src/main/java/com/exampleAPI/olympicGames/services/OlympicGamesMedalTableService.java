package com.exampleAPI.olympicGames.services;

import com.exampleAPI.olympicGames.models.*;
import com.exampleAPI.olympicGames.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OlympicGamesMedalTableService {

    @Autowired
    IOlympicGamesRepository olympicGamesRepository;

    @Autowired
    ISportRepository sportRepository;

    @Autowired
    IMetalRepository metalRepository;

    @Autowired
    IAthleteRepository athleteRepository;

    @Autowired
    IOlympicGamesMedalTableRepository olympicGamesMedalTableRepository;

    public void deleteOlympicGamesMedalTable(Long idOlympicGamesMedalTable) {
        this.olympicGamesMedalTableRepository.deleteById(idOlympicGamesMedalTable);
    }

    public List<OlympicGamesMedalTableModel> getOlympicGamesMedalTable() {
        return this.olympicGamesMedalTableRepository.findAll();
    }

    public Optional<OlympicGamesMedalTableModel> getOlympicGamesMedalTableById(Long idOlympicGamesMedalTable) {
        return this.olympicGamesMedalTableRepository.findById(idOlympicGamesMedalTable);
    }

    public List<OlympicGamesMedalTableModel> getOlympicGamesMedalTableByOlympicGamesId(Long idOlympicGames) {
        List<OlympicGamesMedalTableModel> olympicGamesMedalTableSearched = new ArrayList<>();
        for(OlympicGamesMedalTableModel olympicGamesMedalTable : this.olympicGamesMedalTableRepository.findAll()) {
            if(olympicGamesMedalTable.getOlympicGames().getId().equals(idOlympicGames)) {
                olympicGamesMedalTableSearched.add(olympicGamesMedalTable);
            }
        }
        return olympicGamesMedalTableSearched;
    }

    public List<OlympicGamesMedalTableModel> getOlympicGamesMedalTableByOlympicGamesYear(Integer olympicGamesYear) {
        List<OlympicGamesMedalTableModel> olympicGamesMedalTableSearched = new ArrayList<>();
        for(OlympicGamesMedalTableModel olympicGamesMedalTable : this.olympicGamesMedalTableRepository.findAll()) {
            if(olympicGamesMedalTable.getOlympicGames().getOlympicGamesYear().equals(olympicGamesYear)) {
                olympicGamesMedalTableSearched.add(olympicGamesMedalTable);
            }
        }
        return olympicGamesMedalTableSearched;
    }

    public List<OlympicGamesMedalTableModel> getOlympicGamesMedalTableByOlympicGamesPlace(String olympicGamesPlace) {
        List<OlympicGamesMedalTableModel> olympicGamesMedalTableSearched = new ArrayList<>();
        for(OlympicGamesMedalTableModel olympicGamesMedalTable : this.olympicGamesMedalTableRepository.findAll()) {
            if(olympicGamesMedalTable.getOlympicGames().getOlympicGamesPlace().equals(olympicGamesPlace)) {
                olympicGamesMedalTableSearched.add(olympicGamesMedalTable);
            }
        }
        return olympicGamesMedalTableSearched;
    }

    public List<OlympicGamesMedalTableModel> getOlympicGamesMedalTableBySportId(Long idSport) {
        List<OlympicGamesMedalTableModel> olympicGamesMedalTableSearched = new ArrayList<>();
        for(OlympicGamesMedalTableModel olympicGamesMedalTable : this.olympicGamesMedalTableRepository.findAll()) {
            if(olympicGamesMedalTable.getSport().getId().equals(idSport)) {
                olympicGamesMedalTableSearched.add(olympicGamesMedalTable);
            }
        }
        return olympicGamesMedalTableSearched;
    }

    public List<OlympicGamesMedalTableModel> getOlympicGamesMedalTableBySportName(String sportName) {
        List<OlympicGamesMedalTableModel> olympicGamesMedalTableSearched = new ArrayList<>();
        for(OlympicGamesMedalTableModel olympicGamesMedalTable : this.olympicGamesMedalTableRepository.findAll()) {
            if(olympicGamesMedalTable.getSport().getSportName().equals(sportName)) {
                olympicGamesMedalTableSearched.add(olympicGamesMedalTable);
            }
        }
        return olympicGamesMedalTableSearched;
    }

    public List<OlympicGamesMedalTableModel> getOlympicGamesMedalTableByCategoryName(String categoryName) {
        List<OlympicGamesMedalTableModel> olympicGamesMedalTableSearched = new ArrayList<>();
        for(OlympicGamesMedalTableModel olympicGamesMedalTable : this.olympicGamesMedalTableRepository.findAll()) {
            if(olympicGamesMedalTable.getSport().getSportCategoryName() != null && olympicGamesMedalTable.getSport().getSportCategoryName().equals(categoryName)) {
                olympicGamesMedalTableSearched.add(olympicGamesMedalTable);
            }
        }
        return olympicGamesMedalTableSearched;
    }

    public List<OlympicGamesMedalTableModel> getOlympicGamesMedalTableByMetalId(Long idMetal) {
        List<OlympicGamesMedalTableModel> olympicGamesMedalTableSearched = new ArrayList<>();
        for(OlympicGamesMedalTableModel olympicGamesMedalTable : this.olympicGamesMedalTableRepository.findAll()) {
            if(olympicGamesMedalTable.getMetal().getId().equals(idMetal)) {
                olympicGamesMedalTableSearched.add(olympicGamesMedalTable);
            }
        }
        return olympicGamesMedalTableSearched;
    }

    public List<OlympicGamesMedalTableModel> getOlympicGamesMedalTableByMetalType(String metalType) {
        List<OlympicGamesMedalTableModel> olympicGamesMedalTableSearched = new ArrayList<>();
        for(OlympicGamesMedalTableModel olympicGamesMedalTable : this.olympicGamesMedalTableRepository.findAll()) {
            if(olympicGamesMedalTable.getMetal().getMetalType().equals(metalType)) {
                olympicGamesMedalTableSearched.add(olympicGamesMedalTable);
            }
        }
        return olympicGamesMedalTableSearched;
    }

    public List<OlympicGamesMedalTableModel> getOlympicGamesMedalTableByAthleteId(Long idAthlete) {
        List<OlympicGamesMedalTableModel> olympicGamesMedalTableSearched = new ArrayList<>();
        for(OlympicGamesMedalTableModel olympicGamesMedalTable : this.olympicGamesMedalTableRepository.findAll()) {
            if(olympicGamesMedalTable.getAthlete().getId().equals(idAthlete)) {
                olympicGamesMedalTableSearched.add(olympicGamesMedalTable);
            }
        }
        return olympicGamesMedalTableSearched;
    }

    public List<OlympicGamesMedalTableModel> getOlympicGamesMedalTableByAthleteName(String athleteName) {
        List<OlympicGamesMedalTableModel> olympicGamesMedalTableSearched = new ArrayList<>();
        for(OlympicGamesMedalTableModel olympicGamesMedalTable : this.olympicGamesMedalTableRepository.findAll()) {
            if(olympicGamesMedalTable.getAthlete().getAthleteName().equals(athleteName)) {
                olympicGamesMedalTableSearched.add(olympicGamesMedalTable);
            }
        }
        return olympicGamesMedalTableSearched;
    }

    public List<OlympicGamesMedalTableModel> getOlympicGamesMedalTableByAthleteSurname(String athleteSurname) {
        List<OlympicGamesMedalTableModel> olympicGamesMedalTableSearched = new ArrayList<>();
        for(OlympicGamesMedalTableModel olympicGamesMedalTable : this.olympicGamesMedalTableRepository.findAll()) {
            if(olympicGamesMedalTable.getAthlete().getAthleteSurname().equals(athleteSurname)) {
                olympicGamesMedalTableSearched.add(olympicGamesMedalTable);
            }
        }
        return olympicGamesMedalTableSearched;
    }

    public List<OlympicGamesMedalTableModel> getOlympicGamesMedalTableByAthleteCountry(String athleteCountry) {
        List<OlympicGamesMedalTableModel> olympicGamesMedalTableSearched = new ArrayList<>();
        for(OlympicGamesMedalTableModel olympicGamesMedalTable : this.olympicGamesMedalTableRepository.findAll()) {
            if(olympicGamesMedalTable.getAthlete().getAthleteCountry().equals(athleteCountry)) {
                olympicGamesMedalTableSearched.add(olympicGamesMedalTable);
            }
        }
        return olympicGamesMedalTableSearched;
    }

    public OlympicGamesMedalTableModel saveOlympicGamesMedalTable(Long idOlympicGames, Long idSport, Long idMetal, Long idAthlete) {
        OlympicGamesModel olympicGamesOlympicGamesMedalTable = null;
        SportModel sportOlympicGamesMedalTable = null;
        MetalModel metalOlympicGamesMedalTable = null;
        AthleteModel athleteOlympicGamesMedalTable = null;
        for (OlympicGamesModel olympicGames : this.olympicGamesRepository.findAll()) {
            if(olympicGames.getId().equals(idOlympicGames)) {
                olympicGamesOlympicGamesMedalTable = olympicGames;
                break;
            }
        }
        if(olympicGamesOlympicGamesMedalTable != null) {
            for (SportModel sport : this.sportRepository.findAll()) {
                if(sport.getId().equals(idSport)) {
                    sportOlympicGamesMedalTable = sport;
                    break;
                }
            }
            if(sportOlympicGamesMedalTable != null) {
                for (MetalModel metal : this.metalRepository.findAll()) {
                    if (metal.getId().equals(idMetal)) {
                        metalOlympicGamesMedalTable = metal;
                        break;
                    }
                }
                if(metalOlympicGamesMedalTable != null) {
                    for (AthleteModel athlete : this.athleteRepository.findAll()) {
                        if (athlete.getId().equals(idAthlete)) {
                            athleteOlympicGamesMedalTable = athlete;
                            break;
                        }
                    }
                    if (athleteOlympicGamesMedalTable != null) {
                        return this.olympicGamesMedalTableRepository.save(new OlympicGamesMedalTableModel(olympicGamesOlympicGamesMedalTable, sportOlympicGamesMedalTable, metalOlympicGamesMedalTable, athleteOlympicGamesMedalTable));
                    }
                }
            }
        }
        return null;
    }

    public OlympicGamesMedalTableModel updateOlympicGamesMedalTableById(OlympicGamesMedalTableModel oldOlympicGamesMedalTable, Long idOlympicGames, Long idSport, Long idMetal, Long idAthlete) {
        OlympicGamesModel olympicGamesOlympicGamesMedalTable = null;
        SportModel sportOlympicGamesMedalTable = null;
        MetalModel metalOlympicGamesMedalTable = null;
        AthleteModel athleteOlympicGamesMedalTable = null;
        if(idOlympicGames != 0L) {
            for (OlympicGamesModel olympicGames : this.olympicGamesRepository.findAll()) {
                if(olympicGames.getId().equals(idOlympicGames)) {
                    olympicGamesOlympicGamesMedalTable = olympicGames;
                    break;
                }
            }
            if(olympicGamesOlympicGamesMedalTable != null) {
                oldOlympicGamesMedalTable.setOlympicGames(olympicGamesOlympicGamesMedalTable);
            } else {
                return null;
            }
        }
        if(idSport != 0L) {
            for (SportModel sport : this.sportRepository.findAll()) {
                if(sport.getId().equals(idSport)) {
                    sportOlympicGamesMedalTable = sport;
                    break;
                }
            }
            if(sportOlympicGamesMedalTable != null) {
                oldOlympicGamesMedalTable.setSport(sportOlympicGamesMedalTable);
            } else {
                return null;
            }
        }
        if(idMetal != 0L) {
            for (MetalModel metal : this.metalRepository.findAll()) {
                if(metal.getId().equals(idMetal)) {
                    metalOlympicGamesMedalTable = metal;
                    break;
                }
            }
            if(metalOlympicGamesMedalTable != null) {
                oldOlympicGamesMedalTable.setMetal(metalOlympicGamesMedalTable);
            } else {
                return null;
            }
        }
        if(idAthlete != 0L) {
            for (AthleteModel athlete : this.athleteRepository.findAll()) {
                if(athlete.getId().equals(idAthlete)) {
                    athleteOlympicGamesMedalTable = athlete;
                    break;
                }
            }
            if(athleteOlympicGamesMedalTable != null) {
                oldOlympicGamesMedalTable.setAthlete(athleteOlympicGamesMedalTable);
            } else {
                return null;
            }
        }
        return this.olympicGamesMedalTableRepository.save(oldOlympicGamesMedalTable);
    }

}