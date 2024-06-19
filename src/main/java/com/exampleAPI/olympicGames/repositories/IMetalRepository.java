package com.exampleAPI.olympicGames.repositories;

import com.exampleAPI.olympicGames.models.MetalModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Interfaz que permite interactuar con los datos del modelo de datos MetalModel
@Repository
public interface IMetalRepository extends JpaRepository<MetalModel, Long> {
}