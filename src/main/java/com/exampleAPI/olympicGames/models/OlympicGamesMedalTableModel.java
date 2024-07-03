package com.exampleAPI.olympicGames.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

//Hay que crear previamente la siguiente tabla en MySQL para poder utilizar esta aplicación
//CREATE TABLE db.olympic_games_medal_table (
//        olympic_games_medal_table_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
//        olympic_games_id INT UNSIGNED NOT NULL,
//        sport_id INT UNSIGNED NOT NULL,
//        metal_id INT UNSIGNED NOT NULL,
//        athlete_id INT UNSIGNED NOT NULL,
//        CONSTRAINT FK_OLYMPICGAMES FOREIGN KEY (olympic_games_id)
//        REFERENCES db.olympic_games (olympic_games_id),
//        CONSTRAINT FK_SPORT FOREIGN KEY (sport_id)
//        REFERENCES db.sport (sport_id),
//        CONSTRAINT FK_METAL FOREIGN KEY (metal_id)
//        REFERENCES db.metal (metal_id),
//        CONSTRAINT FK_ATHLETE FOREIGN KEY (athlete_id)
//        REFERENCES db.athlete (athlete_id)
//);

//Clase que permite almacenar el modelo de datos de información del medallero olímpico de todos los Juegos Olímpicos
@Entity
@Table(name = "olympic_games_medal_table")
@Schema(name = "Olympic Games medal table", description = "Olympic Games medal table information")
public class OlympicGamesMedalTableModel {

    //Variable o campo que almacena el identificador de un registro del medallero olímpico
    @Id
    @Column(name = "olympic_games_medal_table_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Olympic Games medal table identifier", example = "1")
    private Long olympicGamesMedalTableId;

    //Variable o campo que almacena los Juegos Olímpicos de un registro del medallero olímpico
    @OneToOne
    @JoinColumn(name = "olympic_games_id", referencedColumnName = "olympic_games_id", nullable = false)
    @Schema(description = "Olympic Games identifier", implementation = OlympicGamesModel.class)
    private OlympicGamesModel olympicGames;

    //Variable o campo que almacena el deporte de un registro del medallero olímpico
    @OneToOne
    @JoinColumn(name = "sport_id", referencedColumnName = "sport_id", nullable = false)
    @Schema(description = "Sport identifier", implementation = SportModel.class)
    private SportModel sport;

    //Variable o campo que almacena el metal de un registro del medallero olímpico
    @OneToOne
    @JoinColumn(name = "metal_id", referencedColumnName = "metal_id", nullable = false)
    @Schema(description = "Metal identifier", implementation = MetalModel.class)
    private MetalModel metal;

    //Variable o campo que almacena el atleta de un registro del medallero olímpico
    @OneToOne
    @JoinColumn(name = "athlete_id", referencedColumnName = "athlete_id", nullable = false)
    @Schema(description = "Athlete identifier", implementation = AthleteModel.class)
    private AthleteModel athlete;

    public OlympicGamesMedalTableModel() {
    }

    public OlympicGamesMedalTableModel(OlympicGamesModel olympicGames, SportModel sport, MetalModel metal, AthleteModel athlete) {
        this.olympicGames = olympicGames;
        this.sport = sport;
        this.metal = metal;
        this.athlete = athlete;
    }

    public Long getOlympicGamesMedalTableId() {
        return olympicGamesMedalTableId;
    }

    public void setOlympicGamesMedalTableId(Long olympicGamesMedalTableId) {
        this.olympicGamesMedalTableId = olympicGamesMedalTableId;
    }

    public OlympicGamesModel getOlympicGames() {
        return olympicGames;
    }

    public void setOlympicGames(OlympicGamesModel olympicGames) {
        this.olympicGames = olympicGames;
    }

    public SportModel getSport() {
        return sport;
    }

    public void setSport(SportModel sport) {
        this.sport = sport;
    }

    public MetalModel getMetal() {
        return metal;
    }

    public void setMetal(MetalModel metal) {
        this.metal = metal;
    }

    public AthleteModel getAthlete() {
        return athlete;
    }

    public void setAthlete(AthleteModel athlete) {
        this.athlete = athlete;
    }

}