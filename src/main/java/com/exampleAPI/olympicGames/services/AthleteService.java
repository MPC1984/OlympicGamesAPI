package com.exampleAPI.olympicGames.services;

import com.exampleAPI.olympicGames.models.AthleteModel;
import com.exampleAPI.olympicGames.repositories.IAthleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AthleteService {

    @Autowired
    IAthleteRepository athleteRepository;

    public void deleteAthlete(Long idAthlete) {
        this.athleteRepository.deleteById(idAthlete);
    }

    public List<AthleteModel> getAthlete() {
        return this.athleteRepository.findAll();
    }

    public Optional<AthleteModel> getAthleteById(Long idAthlete) {
        return this.athleteRepository.findById(idAthlete);
    }

    public List<AthleteModel> getAthleteByName(String athleteName) {
        List<AthleteModel> athleteSearched = new ArrayList<>();
        for (AthleteModel athlete : this.athleteRepository.findAll()){
            if(athlete.getAthleteName().equals(athleteName)) {
                athleteSearched.add(athlete);
            }
        }
        return athleteSearched;
    }

    public List<AthleteModel> getAthleteBySurname(String athleteSurname) {
        List<AthleteModel> athleteSearched = new ArrayList<>();
        for (AthleteModel athlete : this.athleteRepository.findAll()){
            if(athlete.getAthleteSurname().equals(athleteSurname)) {
                athleteSearched.add(athlete);
            }
        }
        return athleteSearched;
    }

    public List<AthleteModel> getAthleteByCountry(String athleteCountry) {
        List<AthleteModel> athleteSearched = new ArrayList<>();
        for (AthleteModel athlete : this.athleteRepository.findAll()){
            if(athlete.getAthleteCountry().equals(athleteCountry)) {
                athleteSearched.add(athlete);
            }
        }
        return athleteSearched;
    }

    public AthleteModel saveAthlete(String athleteName, String athleteSurname, String athleteCountry) {
        return this.athleteRepository.save(new AthleteModel(athleteName, athleteSurname, athleteCountry));
    }

    public AthleteModel updateAthleteById(AthleteModel oldAthlete, String athleteName, String athleteSurname, String athleteCountry) {
        if(athleteName != null) {
            oldAthlete.setAthleteName(athleteName);
        }
        if(athleteSurname != null) {
            oldAthlete.setAthleteSurname(athleteSurname);
        }
        if(athleteCountry != null) {
            oldAthlete.setAthleteCountry(athleteCountry);
        }
        return this.athleteRepository.save(oldAthlete);
    }

}