package com.exampleAPI.olympicGames.services;

import com.exampleAPI.olympicGames.models.*;
import com.exampleAPI.olympicGames.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Clase en la que se definen las diferentes operaciones que se pueden realizar sobre el modelo de datos que tiene la información del medallero olímpico de todos los Juegos Olímpicos
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

    //Método u operación que permite obtener una lista con la información de todos los registros del medallero olímpico de todos los Juegos Olímpicos
    public List<OlympicGamesMedalTableModel> getOlympicGamesMedalTable() {
        return this.olympicGamesMedalTableRepository.findAll();
    }

    //Método u operación que permite obtener la información de un registro del medallero olímpico de todos los Juegos Olímpicos que tiene un determinado identificador
    public Optional<OlympicGamesMedalTableModel> getOlympicGamesMedalTableById(Long idOlympicGamesMedalTable) {
        return this.olympicGamesMedalTableRepository.findById(idOlympicGamesMedalTable);
    }

    //Método u operación que permite obtener una lista con la información de todos los registros del medallero olímpico de todos los Juegos Olímpicos que tienen un determinado identificador de Juegos Olímpicos
    public List<OlympicGamesMedalTableModel> getOlympicGamesMedalTableByOlympicGamesId(Long idOlympicGames) {
        List<OlympicGamesMedalTableModel> olympicGamesMedalTableSearched = new ArrayList<>();
        for (OlympicGamesMedalTableModel olympicGamesMedalTable : this.olympicGamesMedalTableRepository.findAll()) {
            if (olympicGamesMedalTable.getOlympicGames().getOlympicGamesId().equals(idOlympicGames)) {
                olympicGamesMedalTableSearched.add(olympicGamesMedalTable);
            }
        }
        return olympicGamesMedalTableSearched;
    }

    //Método u operación que permite obtener una lista con la información de todos los registros del medallero olímpico de todos los Juegos Olímpicos que tienen un determinado año de celebración de Juegos Olímpicos
    public List<OlympicGamesMedalTableModel> getOlympicGamesMedalTableByOlympicGamesYear(Integer olympicGamesYear) {
        List<OlympicGamesMedalTableModel> olympicGamesMedalTableSearched = new ArrayList<>();
        for (OlympicGamesMedalTableModel olympicGamesMedalTable : this.olympicGamesMedalTableRepository.findAll()) {
            if (olympicGamesMedalTable.getOlympicGames().getOlympicGamesYear().equals(olympicGamesYear)) {
                olympicGamesMedalTableSearched.add(olympicGamesMedalTable);
            }
        }
        return olympicGamesMedalTableSearched;
    }

    //Método u operación que permite obtener una lista con la información de todos los registros del medallero olímpico de todos los Juegos Olímpicos que tienen un determinado lugar de celebración de Juegos Olímpicos
    public List<OlympicGamesMedalTableModel> getOlympicGamesMedalTableByOlympicGamesPlace(String olympicGamesPlace) {
        List<OlympicGamesMedalTableModel> olympicGamesMedalTableSearched = new ArrayList<>();
        for (OlympicGamesMedalTableModel olympicGamesMedalTable : this.olympicGamesMedalTableRepository.findAll()) {
            if (olympicGamesMedalTable.getOlympicGames().getOlympicGamesPlace().equals(olympicGamesPlace)) {
                olympicGamesMedalTableSearched.add(olympicGamesMedalTable);
            }
        }
        return olympicGamesMedalTableSearched;
    }

    //Método u operación que permite obtener una lista con la información de todos los registros del medallero olímpico de todos los Juegos Olímpicos que tienen un determinado identificador de deporte
    public List<OlympicGamesMedalTableModel> getOlympicGamesMedalTableBySportId(Long idSport) {
        List<OlympicGamesMedalTableModel> olympicGamesMedalTableSearched = new ArrayList<>();
        for (OlympicGamesMedalTableModel olympicGamesMedalTable : this.olympicGamesMedalTableRepository.findAll()) {
            if (olympicGamesMedalTable.getSport().getSportId().equals(idSport)) {
                olympicGamesMedalTableSearched.add(olympicGamesMedalTable);
            }
        }
        return olympicGamesMedalTableSearched;
    }

    //Método u operación que permite obtener una lista con la información de todos los registros del medallero olímpico de todos los Juegos Olímpicos que tienen un determinado nombre de deporte
    public List<OlympicGamesMedalTableModel> getOlympicGamesMedalTableBySportName(String sportName) {
        List<OlympicGamesMedalTableModel> olympicGamesMedalTableSearched = new ArrayList<>();
        for (OlympicGamesMedalTableModel olympicGamesMedalTable : this.olympicGamesMedalTableRepository.findAll()) {
            if (olympicGamesMedalTable.getSport().getSportName().equals(sportName)) {
                olympicGamesMedalTableSearched.add(olympicGamesMedalTable);
            }
        }
        return olympicGamesMedalTableSearched;
    }

    //Método u operación que permite obtener una lista con la información de todos los registros del medallero olímpico de todos los Juegos Olímpicos que tienen una determinada categoría de deporte
    //Hay que tener en cuenta que un deporte puede no tener categoría (es decir, que la categoría de ese deporte sea nula)
    public List<OlympicGamesMedalTableModel> getOlympicGamesMedalTableByCategoryName(String categoryName) {
        List<OlympicGamesMedalTableModel> olympicGamesMedalTableSearched = new ArrayList<>();
        for (OlympicGamesMedalTableModel olympicGamesMedalTable : this.olympicGamesMedalTableRepository.findAll()) {
            if (olympicGamesMedalTable.getSport().getSportCategoryName() != null && olympicGamesMedalTable.getSport().getSportCategoryName().equals(categoryName)) {
                olympicGamesMedalTableSearched.add(olympicGamesMedalTable);
            }
        }
        return olympicGamesMedalTableSearched;
    }

    //Método u operación que permite obtener una lista con la información de todos los registros del medallero olímpico de todos los Juegos Olímpicos que tienen un determinado identificador de metal
    public List<OlympicGamesMedalTableModel> getOlympicGamesMedalTableByMetalId(Long idMetal) {
        List<OlympicGamesMedalTableModel> olympicGamesMedalTableSearched = new ArrayList<>();
        for (OlympicGamesMedalTableModel olympicGamesMedalTable : this.olympicGamesMedalTableRepository.findAll()) {
            if (olympicGamesMedalTable.getMetal().getMetalId().equals(idMetal)) {
                olympicGamesMedalTableSearched.add(olympicGamesMedalTable);
            }
        }
        return olympicGamesMedalTableSearched;
    }

    //Método u operación que permite obtener una lista con la información de todos los registros del medallero olímpico de todos los Juegos Olímpicos que tienen un determinado tipo de metal
    public List<OlympicGamesMedalTableModel> getOlympicGamesMedalTableByMetalType(String metalType) {
        List<OlympicGamesMedalTableModel> olympicGamesMedalTableSearched = new ArrayList<>();
        for (OlympicGamesMedalTableModel olympicGamesMedalTable : this.olympicGamesMedalTableRepository.findAll()) {
            if (olympicGamesMedalTable.getMetal().getMetalType().equals(metalType)) {
                olympicGamesMedalTableSearched.add(olympicGamesMedalTable);
            }
        }
        return olympicGamesMedalTableSearched;
    }

    //Método u operación que permite obtener una lista con la información de todos los registros del medallero olímpico de todos los Juegos Olímpicos que tienen un determinado identificador de atleta
    public List<OlympicGamesMedalTableModel> getOlympicGamesMedalTableByAthleteId(Long idAthlete) {
        List<OlympicGamesMedalTableModel> olympicGamesMedalTableSearched = new ArrayList<>();
        for (OlympicGamesMedalTableModel olympicGamesMedalTable : this.olympicGamesMedalTableRepository.findAll()) {
            if (olympicGamesMedalTable.getAthlete().getAthleteId().equals(idAthlete)) {
                olympicGamesMedalTableSearched.add(olympicGamesMedalTable);
            }
        }
        return olympicGamesMedalTableSearched;
    }

    //Método u operación que permite obtener una lista con la información de todos los registros del medallero olímpico de todos los Juegos Olímpicos que tienen un determinado nombre de atleta
    public List<OlympicGamesMedalTableModel> getOlympicGamesMedalTableByAthleteName(String athleteName) {
        List<OlympicGamesMedalTableModel> olympicGamesMedalTableSearched = new ArrayList<>();
        for (OlympicGamesMedalTableModel olympicGamesMedalTable : this.olympicGamesMedalTableRepository.findAll()) {
            if (olympicGamesMedalTable.getAthlete().getAthleteName().equals(athleteName)) {
                olympicGamesMedalTableSearched.add(olympicGamesMedalTable);
            }
        }
        return olympicGamesMedalTableSearched;
    }

    //Método u operación que permite obtener una lista con la información de todos los registros del medallero olímpico de todos los Juegos Olímpicos que tienen unos determinados apellidos de atleta
    public List<OlympicGamesMedalTableModel> getOlympicGamesMedalTableByAthleteSurname(String athleteSurname) {
        List<OlympicGamesMedalTableModel> olympicGamesMedalTableSearched = new ArrayList<>();
        for (OlympicGamesMedalTableModel olympicGamesMedalTable : this.olympicGamesMedalTableRepository.findAll()) {
            if (olympicGamesMedalTable.getAthlete().getAthleteSurname().equals(athleteSurname)) {
                olympicGamesMedalTableSearched.add(olympicGamesMedalTable);
            }
        }
        return olympicGamesMedalTableSearched;
    }

    //Método u operación que permite obtener una lista con la información de todos los registros del medallero olímpico de todos los Juegos Olímpicos que tienen un determinado país al que representa el atleta
    public List<OlympicGamesMedalTableModel> getOlympicGamesMedalTableByAthleteCountry(String athleteCountry) {
        List<OlympicGamesMedalTableModel> olympicGamesMedalTableSearched = new ArrayList<>();
        for (OlympicGamesMedalTableModel olympicGamesMedalTable : this.olympicGamesMedalTableRepository.findAll()) {
            if (olympicGamesMedalTable.getAthlete().getAthleteCountry().equals(athleteCountry)) {
                olympicGamesMedalTableSearched.add(olympicGamesMedalTable);
            }
        }
        return olympicGamesMedalTableSearched;
    }

    //Método u operación que permite guardar la información de un registro del medallero olímpico de todos los Juegos Olímpicos dados el identificador de los Juegos Olímpicos, el identificador del deporte, el identificador del metal y el identificador del atleta
    //Hay que tener en cuenta que se pueden dar identificadores de Juegos Olímpicos, deporte, metal y atleta que no existan, en cuyo caso no se guardará el nuevo registro
    public OlympicGamesMedalTableModel saveOlympicGamesMedalTable(Long idOlympicGames, Long idSport, Long idMetal, Long idAthlete) {
        OlympicGamesModel olympicGamesOlympicGamesMedalTable = null;
        SportModel sportOlympicGamesMedalTable = null;
        MetalModel metalOlympicGamesMedalTable = null;
        AthleteModel athleteOlympicGamesMedalTable = null;
        for (OlympicGamesModel olympicGames : this.olympicGamesRepository.findAll()) {
            if (olympicGames.getOlympicGamesId().equals(idOlympicGames)) {
                olympicGamesOlympicGamesMedalTable = olympicGames;
                break;
            }
        }
        if (olympicGamesOlympicGamesMedalTable != null) {
            for (SportModel sport : this.sportRepository.findAll()) {
                if (sport.getSportId().equals(idSport)) {
                    sportOlympicGamesMedalTable = sport;
                    break;
                }
            }
            if (sportOlympicGamesMedalTable != null) {
                for (MetalModel metal : this.metalRepository.findAll()) {
                    if (metal.getMetalId().equals(idMetal)) {
                        metalOlympicGamesMedalTable = metal;
                        break;
                    }
                }
                if (metalOlympicGamesMedalTable != null) {
                    for (AthleteModel athlete : this.athleteRepository.findAll()) {
                        if (athlete.getAthleteId().equals(idAthlete)) {
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

    //Método u operación que permite actualizar la información de un registro del medallero olímpico de todos los Juegos Olímpicos que tiene un determinado identificador con el nuevo identificador de Juegos Olímpicos y/o el nuevo identificador de deporte y/o el nuevo identificador de metal y/o el nuevo identificador de atleta
    //Si el identificador de Juegos Olímpicos, el identificador de deporte, el identificador de metal o el identificador de atleta no se quieren modificar se pasarán valores 0
    //Hay que tener en cuenta que se pueden dar identificadores de Juegos Olímpicos, deporte, metal y atleta que no existan, en cuyo caso no se actualizará el registro
    public OlympicGamesMedalTableModel updateOlympicGamesMedalTableById(OlympicGamesMedalTableModel oldOlympicGamesMedalTable, Long idOlympicGames, Long idSport, Long idMetal, Long idAthlete) {
        OlympicGamesModel olympicGamesOlympicGamesMedalTable = null;
        SportModel sportOlympicGamesMedalTable = null;
        MetalModel metalOlympicGamesMedalTable = null;
        AthleteModel athleteOlympicGamesMedalTable = null;
        if (idOlympicGames != 0L) {
            for (OlympicGamesModel olympicGames : this.olympicGamesRepository.findAll()) {
                if (olympicGames.getOlympicGamesId().equals(idOlympicGames)) {
                    olympicGamesOlympicGamesMedalTable = olympicGames;
                    break;
                }
            }
            if (olympicGamesOlympicGamesMedalTable != null) {
                oldOlympicGamesMedalTable.setOlympicGames(olympicGamesOlympicGamesMedalTable);
            } else {
                return null;
            }
        }
        if (idSport != 0L) {
            for (SportModel sport : this.sportRepository.findAll()) {
                if (sport.getSportId().equals(idSport)) {
                    sportOlympicGamesMedalTable = sport;
                    break;
                }
            }
            if (sportOlympicGamesMedalTable != null) {
                oldOlympicGamesMedalTable.setSport(sportOlympicGamesMedalTable);
            } else {
                return null;
            }
        }
        if (idMetal != 0L) {
            for (MetalModel metal : this.metalRepository.findAll()) {
                if (metal.getMetalId().equals(idMetal)) {
                    metalOlympicGamesMedalTable = metal;
                    break;
                }
            }
            if (metalOlympicGamesMedalTable != null) {
                oldOlympicGamesMedalTable.setMetal(metalOlympicGamesMedalTable);
            } else {
                return null;
            }
        }
        if (idAthlete != 0L) {
            for (AthleteModel athlete : this.athleteRepository.findAll()) {
                if (athlete.getAthleteId().equals(idAthlete)) {
                    athleteOlympicGamesMedalTable = athlete;
                    break;
                }
            }
            if (athleteOlympicGamesMedalTable != null) {
                oldOlympicGamesMedalTable.setAthlete(athleteOlympicGamesMedalTable);
            } else {
                return null;
            }
        }
        return this.olympicGamesMedalTableRepository.save(oldOlympicGamesMedalTable);
    }

    //Método u operación que permite eliminar la información de un registro del medallero olímpico de todos los Juegos Olímpicos que tiene un determinado identificador
    public void deleteOlympicGamesMedalTable(Long idOlympicGamesMedalTable) {
        this.olympicGamesMedalTableRepository.deleteById(idOlympicGamesMedalTable);
    }

}