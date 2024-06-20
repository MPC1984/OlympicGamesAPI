package com.exampleAPI.olympicGames.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

//Hay que crear previamente la siguiente tabla en MySQL para poder utilizar esta aplicación
//CREATE TABLE db.olympic_games (
//        olympic_games_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
//        olympic_games_year INT NOT NULL UNIQUE,
//        olympic_games_place VARCHAR(250) NOT NULL
//);

//Clase que permite almacenar el modelo de datos de información de unos Juegos Olímpicos
@Entity
@Table(name = "olympic_games")
@Schema(name = "Olympic Games", description = "Olympic Games information")
public class OlympicGamesModel {

    //Variable o campo que almacena el identificador de los Juegos Olímpicos
    @Id
    @Column(name = "olympic_games_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Olympic Games identifier", example = "1")
    private Long id;

    //Variable o campo que almacena el año de los Juegos Olímpicos
    @Column(name = "olympic_games_year", nullable = false, unique = true)
    @Schema(description = "Olympic Games year", example = "2020")
    private Integer olympicGamesYear;

    //Variable o campo que almacena el lugar de los Juegos Olímpicos
    @Column(name = "olympic_games_place", length = 250, nullable = false)
    @Schema(description = "Olympic Games place", example = "Tokyo")
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