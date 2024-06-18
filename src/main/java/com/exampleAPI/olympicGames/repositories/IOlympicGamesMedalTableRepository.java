package com.exampleAPI.olympicGames.repositories;

import com.exampleAPI.olympicGames.models.OlympicGamesMedalTableModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOlympicGamesMedalTableRepository extends JpaRepository<OlympicGamesMedalTableModel, Long> {
}