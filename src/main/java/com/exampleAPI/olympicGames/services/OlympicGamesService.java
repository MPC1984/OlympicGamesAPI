package com.exampleAPI.olympicGames.services;

import com.exampleAPI.olympicGames.models.OlympicGamesModel;
import com.exampleAPI.olympicGames.repositories.IOlympicGamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Clase en la que se definen las diferentes operaciones que se pueden realizar sobre el modelo de datos que tiene la información de unos Juegos Olímpicos
@Service
public class OlympicGamesService {

    @Autowired
    IOlympicGamesRepository olympicGamesRepository;

    //Método u operación que permite obtener una lista con la información de todos los Juegos Olímpicos
    public List<OlympicGamesModel> getOlympicGames() {
        return this.olympicGamesRepository.findAll();
    }

    //Método u operación que permite obtener la información de unos Juegos Olímpicos que tienen un determinado identificador
    public Optional<OlympicGamesModel> getOlympicGamesById(Long idOlympicGames) {
        return this.olympicGamesRepository.findById(idOlympicGames);
    }

    //Método u operación que permite obtener la información de unos Juegos Olímpicos que se celebraron en un determinado año
    //Hay que tener en cuenta que el año en el que se celebraron unos Juegos Olímpicos es único (es decir, no puede haber dos Juegos Olímpicos con el mismo año)
    public OlympicGamesModel getOlympicGamesByYear(Integer olympicGamesYear) {
        OlympicGamesModel olympicGamesSearched = null;
        for (OlympicGamesModel olympicGames : this.olympicGamesRepository.findAll()) {
            if (olympicGames.getOlympicGamesYear().equals(olympicGamesYear)) {
                olympicGamesSearched = olympicGames;
                break;
            }
        }
        return olympicGamesSearched;
    }

    //Método u operación que permite obtener una lista con la información de todos los Juegos Olímpicos que se celebraron en un determinado lugar
    public List<OlympicGamesModel> getOlympicGamesByPlace(String olympicGamesPlace) {
        List<OlympicGamesModel> olympicGamesSearched = new ArrayList<>();
        for (OlympicGamesModel olympicGames : this.olympicGamesRepository.findAll()) {
            if (olympicGames.getOlympicGamesPlace().equals(olympicGamesPlace)) {
                olympicGamesSearched.add(olympicGames);
            }
        }
        return olympicGamesSearched;
    }

    //Método u operación que permite guardar la información de unos Juegos Olímpicos dados el año y el lugar en el que se celebraron
    public OlympicGamesModel saveOlympicGames(int olympicGamesYear, String olympicGamesPlace) {
        return this.olympicGamesRepository.save(new OlympicGamesModel(olympicGamesYear, olympicGamesPlace));
    }

    //Método u operación que permite actualizar la información de unos Juegos Olímpicos que tienen un determinado identificador con el nuevo año y/o el nuevo lugar en el que se celebraron
    //Si el año o el lugar en el que se celebraon los Juegos Olímpicos no se quieren modificar se pasarán valores nulos (para el lugar) o 0 (para el año)
    public OlympicGamesModel updateOlympicGamesById(OlympicGamesModel oldOlympicGames, int olympicGamesYear, String olympicGamesPlace) {
        if (olympicGamesYear != 0) {
            oldOlympicGames.setOlympicGamesYear(olympicGamesYear);
        }
        if (olympicGamesPlace != null) {
            oldOlympicGames.setOlympicGamesPlace(olympicGamesPlace);
        }
        return this.olympicGamesRepository.save(oldOlympicGames);
    }

    //Método u operación que permite eliminar la información de unos Juegos Olímpicos que tienen un determinado identificador
    public void deleteOlympicGames(Long idOlympicGames) {
        this.olympicGamesRepository.deleteById(idOlympicGames);
    }

}