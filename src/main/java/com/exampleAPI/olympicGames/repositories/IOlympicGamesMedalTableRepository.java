package com.exampleAPI.olympicGames.repositories;

import com.exampleAPI.olympicGames.models.OlympicGamesMedalTableModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Interfaz que permite interactuar con los datos del modelo de datos OlympicGamesMedalTableModel
@Repository
public interface IOlympicGamesMedalTableRepository extends JpaRepository<OlympicGamesMedalTableModel, Long> {
}