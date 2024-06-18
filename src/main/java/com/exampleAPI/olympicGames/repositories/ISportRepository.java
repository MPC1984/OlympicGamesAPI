package com.exampleAPI.olympicGames.repositories;

import com.exampleAPI.olympicGames.models.SportModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISportRepository extends JpaRepository<SportModel, Long> {
}