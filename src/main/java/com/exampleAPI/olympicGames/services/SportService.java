package com.exampleAPI.olympicGames.services;

import com.exampleAPI.olympicGames.models.SportModel;
import com.exampleAPI.olympicGames.repositories.ISportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SportService {

    @Autowired
    ISportRepository sportRepository;

    public void deleteSport(Long idSport) {
        this.sportRepository.deleteById(idSport);

    }

    public List<SportModel> getSport() {
        return this.sportRepository.findAll();
    }

    public Optional<SportModel> getSportById(Long idSport) {
        return this.sportRepository.findById(idSport);
    }

    public List<SportModel> getSportByName(String sportName) {
        List<SportModel> sportSearched = new ArrayList<>();
        for (SportModel sport : this.sportRepository.findAll()){
            if(sport.getSportName().equals(sportName)) {
                sportSearched.add(sport);
            }
        }
        return sportSearched;
    }

    public List<SportModel> getSportByCategoryName(String sportCategoryName) {
        List<SportModel> sportSearched = new ArrayList<>();
        for (SportModel sport : this.sportRepository.findAll()){
            if(sport.getSportCategoryName() != null) {
                if(sport.getSportCategoryName().equals(sportCategoryName)) {
                    sportSearched.add(sport);
                }
            }
        }
        return sportSearched;
    }

    public SportModel saveSport(String sportName, String sportCategoryName) {
        if(sportCategoryName == null) {
            return this.sportRepository.save(new SportModel(sportName));
        } else {
            return this.sportRepository.save(new SportModel(sportName, sportCategoryName));
        }
    }

    public SportModel updateSportById(SportModel oldSport, String sportName, String sportCategoryName) {
        if(sportName != null) {
            oldSport.setSportName(sportName);
        }
        if(sportCategoryName != null) {
            oldSport.setSportCategoryName(sportCategoryName);
        }
        return this.sportRepository.save(oldSport);

    }

}