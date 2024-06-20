package com.exampleAPI.olympicGames.services;

import com.exampleAPI.olympicGames.models.SportModel;
import com.exampleAPI.olympicGames.repositories.ISportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Clase en la que se definen las diferentes operaciones que se pueden realizar sobre el modelo de datos que tiene la información de un deporte
@Service
public class SportService {

    @Autowired
    ISportRepository sportRepository;

    //Método u operación que permite obtener una lista con la información de todos los deportes
    public List<SportModel> getSport() {
        return this.sportRepository.findAll();
    }

    //Método u operación que permite obtener la información de un deporte que tiene un determinado identificador
    public Optional<SportModel> getSportById(Long idSport) {
        return this.sportRepository.findById(idSport);
    }

    //Método u operación que permite obtener una lista con la información de todos los deportes que tienen un determinado nombre
    public List<SportModel> getSportByName(String sportName) {
        List<SportModel> sportsSearched = new ArrayList<>();
        for (SportModel sport : this.sportRepository.findAll()) {
            if (sport.getSportName().equals(sportName)) {
                sportsSearched.add(sport);
            }
        }
        return sportsSearched;
    }

    //Método u operación que permite obtener una lista con la información de todos los deportes que tienen una determinada categoría
    //Hay que tener en cuenta que un deporte puede no tener categoría (es decir, que la categoría de ese deporte sea nula)
    public List<SportModel> getSportByCategoryName(String sportCategoryName) {
        List<SportModel> sportsSearched = new ArrayList<>();
        for (SportModel sport : this.sportRepository.findAll()) {
            if (sport.getSportCategoryName() != null) {
                if (sport.getSportCategoryName().equals(sportCategoryName)) {
                    sportsSearched.add(sport);
                }
            }
        }
        return sportsSearched;
    }

    //Método u operación que permite guardar la información de un deporte dados el nombre y la categoría
    //Hay que tener en cuenta que un deporte puede no tener categoría (es decir, que la categoría de ese deporte sea nula)
    public SportModel saveSport(String sportName, String sportCategoryName) {
        if (sportCategoryName == null) {
            return this.sportRepository.save(new SportModel(sportName));
        } else {
            return this.sportRepository.save(new SportModel(sportName, sportCategoryName));
        }
    }

    //Método u operación que permite actualizar la información de un deporte que tiene un determinado identificador con el nuevo nombre y/o la nueva categoría
    //Si el nombre o la categoría del deporte no se quieren modificar se pasarán valores nulos
    public SportModel updateSportById(SportModel oldSport, String sportName, String sportCategoryName) {
        if (sportName != null) {
            oldSport.setSportName(sportName);
        }
        if (sportCategoryName != null) {
            oldSport.setSportCategoryName(sportCategoryName);
        }
        return this.sportRepository.save(oldSport);
    }

    //Método u operación que permite eliminar la información de un deporte que tiene un determinado identificador
    public void deleteSport(Long idSport) {
        this.sportRepository.deleteById(idSport);
    }

}