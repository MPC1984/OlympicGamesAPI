package com.exampleAPI.olympicGames.services;

import com.exampleAPI.olympicGames.models.AthleteModel;
import com.exampleAPI.olympicGames.repositories.IAthleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Clase en la que se definen las diferentes operaciones que se pueden realizar sobre el modelo de datos que tiene la información de un atleta
@Service
public class AthleteService {

    @Autowired
    IAthleteRepository athleteRepository;

    //Método u operación que permite obtener una lista con la información de todos los atletas
    public List<AthleteModel> getAthlete() {
        return this.athleteRepository.findAll();
    }

    //Método u operación que permite obtener la información de un atleta que tiene un determinado identificador
    public Optional<AthleteModel> getAthleteById(Long idAthlete) {
        return this.athleteRepository.findById(idAthlete);
    }

    //Método u operación que permite obtener una lista con la información de todos los atletas que tienen un determinado nombre
    public List<AthleteModel> getAthleteByName(String athleteName) {
        List<AthleteModel> athletesSearched = new ArrayList<>();
        for (AthleteModel athlete : this.athleteRepository.findAll()) {
            if (athlete.getAthleteName().equals(athleteName)) {
                athletesSearched.add(athlete);
            }
        }
        return athletesSearched;
    }

    //Método u operación que permite obtener una lista con la información de todos los atletas que tienen unos determinados apellidos
    public List<AthleteModel> getAthleteBySurname(String athleteSurname) {
        List<AthleteModel> athletesSearched = new ArrayList<>();
        for (AthleteModel athlete : this.athleteRepository.findAll()) {
            if (athlete.getAthleteSurname().equals(athleteSurname)) {
                athletesSearched.add(athlete);
            }
        }
        return athletesSearched;
    }

    //Método u operación que permite obtener una lista con la información de todos los atletas que representan a un determinado país
    public List<AthleteModel> getAthleteByCountry(String athleteCountry) {
        List<AthleteModel> athletesSearched = new ArrayList<>();
        for (AthleteModel athlete : this.athleteRepository.findAll()) {
            if (athlete.getAthleteCountry().equals(athleteCountry)) {
                athletesSearched.add(athlete);
            }
        }
        return athletesSearched;
    }

    //Método u operación que permite guardar la información de un atleta dados el nombre, los apellidos y país al que representa
    public AthleteModel saveAthlete(String athleteName, String athleteSurname, String athleteCountry) {
        return this.athleteRepository.save(new AthleteModel(athleteName, athleteSurname, athleteCountry));
    }

    //Método u operación que permite actualizar la información de un atleta que tiene un determinado identificador con el nuevo nombre y/o los nuevos apellidos y/o el nuevo país al que representa
    //Si el nombre, los apellidos o el país al que representa el atleta no se quieren modificar se pasarán valores nulos
    public AthleteModel updateAthleteById(AthleteModel oldAthlete, String athleteName, String athleteSurname, String athleteCountry) {
        if (athleteName != null) {
            oldAthlete.setAthleteName(athleteName);
        }
        if (athleteSurname != null) {
            oldAthlete.setAthleteSurname(athleteSurname);
        }
        if (athleteCountry != null) {
            oldAthlete.setAthleteCountry(athleteCountry);
        }
        return this.athleteRepository.save(oldAthlete);
    }

    //Método u operación que permite eliminar la información de un atleta que tiene un determinado identificador
    public void deleteAthlete(Long idAthlete) {
        this.athleteRepository.deleteById(idAthlete);
    }

}