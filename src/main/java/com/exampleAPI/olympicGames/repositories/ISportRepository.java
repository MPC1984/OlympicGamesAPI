package com.exampleAPI.olympicGames.repositories;

import com.exampleAPI.olympicGames.models.SportModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Interfaz que permite interactuar con los datos del modelo de datos SportModel
@Repository
public interface ISportRepository extends JpaRepository<SportModel, Long> {
}