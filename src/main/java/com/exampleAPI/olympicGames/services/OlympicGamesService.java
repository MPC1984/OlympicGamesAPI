package com.exampleAPI.olympicGames.services;

import com.exampleAPI.olympicGames.models.OlympicGamesModel;
import com.exampleAPI.olympicGames.repositories.IOlympicGamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OlympicGamesService {

    @Autowired
    IOlympicGamesRepository olympicGamesRepository;

    public void deleteOlympicGames(Long idOlympicGames) {
        this.olympicGamesRepository.deleteById(idOlympicGames);
    }

    public List<OlympicGamesModel> getOlympicGames() {
        return this.olympicGamesRepository.findAll();
    }

    public Optional<OlympicGamesModel> getOlympicGamesById(Long idOlympicGames) {
        return this.olympicGamesRepository.findById(idOlympicGames);
    }

    public OlympicGamesModel getOlympicGamesByYear(Integer olympicGamesYear) {
        OlympicGamesModel olympicGamesSearched = null;
        for (OlympicGamesModel olympicGames : this.olympicGamesRepository.findAll()){
            if(olympicGames.getOlympicGamesYear().equals(olympicGamesYear)) {
                olympicGamesSearched = olympicGames;
                break;
            }
        }
        return olympicGamesSearched;
    }

    public List<OlympicGamesModel> getOlympicGamesByPlace(String olympicGamesPlace) {
        List<OlympicGamesModel> olympicGamesSearched = new ArrayList<>();
        for (OlympicGamesModel olympicGames : this.olympicGamesRepository.findAll()){
            if(olympicGames.getOlympicGamesPlace().equals(olympicGamesPlace)) {
                olympicGamesSearched.add(olympicGames);
            }
        }
        return olympicGamesSearched;
    }

    public OlympicGamesModel saveOlympicGames(int olympicGamesYear, String olympicGamesPlace) {
        return this.olympicGamesRepository.save(new OlympicGamesModel(olympicGamesYear, olympicGamesPlace));
    }

    public OlympicGamesModel updateOlympicGamesById(OlympicGamesModel oldOlympicGames, int olympicGamesYear, String olympicGamesPlace) {
        if(olympicGamesYear != 0) {
            oldOlympicGames.setOlympicGamesYear(olympicGamesYear);
        }
        if(olympicGamesPlace != null) {
            oldOlympicGames.setOlympicGamesPlace(olympicGamesPlace);
        }
        return this.olympicGamesRepository.save(oldOlympicGames);
    }

}