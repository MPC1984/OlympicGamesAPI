package com.exampleAPI.olympicGames.models;

import jakarta.persistence.*;

@Entity
@Table (name = "olympic_games")
public class OlympicGamesModel {

    @Id
    @Column  (name = "olympic_games_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "olympic_games_year", nullable = false, unique = true)
    private Integer olympicGamesYear;

    @Column (name = "olympic_games_place", length = 250, nullable = false)
    private String olympicGamesPlace;

    public OlympicGamesModel() {
    }

    public OlympicGamesModel(Integer olympicGamesYear, String olympicGamesPlace) {
        this.olympicGamesYear = olympicGamesYear;
        this.olympicGamesPlace = olympicGamesPlace;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOlympicGamesYear() {
        return olympicGamesYear;
    }

    public void setOlympicGamesYear(Integer olympicGamesYear) {
        this.olympicGamesYear = olympicGamesYear;
    }

    public String getOlympicGamesPlace() {
        return olympicGamesPlace;
    }

    public void setOlympicGamesPlace(String olympicGamesPlace) {
        this.olympicGamesPlace = olympicGamesPlace;
    }

}