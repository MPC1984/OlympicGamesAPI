package com.exampleAPI.olympicGames.repositories;

import com.exampleAPI.olympicGames.models.OlympicGamesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Interfaz que permite interactuar con los datos del modelo de datos OlympicGamesModel
@Repository
public interface IOlympicGamesRepository extends JpaRepository<OlympicGamesModel, Long> {
}