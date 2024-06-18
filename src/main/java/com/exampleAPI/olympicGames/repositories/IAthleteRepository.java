package com.exampleAPI.olympicGames.repositories;

import com.exampleAPI.olympicGames.models.AthleteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAthleteRepository extends JpaRepository<AthleteModel, Long> {
}