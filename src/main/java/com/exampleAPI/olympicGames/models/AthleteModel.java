package com.exampleAPI.olympicGames.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

//Hay que crear previamente la siguiente tabla en MySQL para poder utilizar esta aplicación
//CREATE TABLE db.athlete (
//        athlete_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
//        athlete_name VARCHAR(250) NOT NULL,
//        athlete_surname VARCHAR(250) NOT NULL,
//        athlete_country VARCHAR(250) NOT NULL
//);

//Clase que permite almacenar el modelo de datos de información de un atleta
@Entity
@Table(name = "athlete")
@Schema(name = "Athlete", description = "Athlete information")
public class AthleteModel {

    //Variable o campo que almacena el identificador del atleta
    @Id
    @Column(name = "athlete_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Athlete identifier", example = "1")
    private Long athleteId;

    //Variable o campo que almacena el nombre del atleta
    @Column(name = "athlete_name", length = 250, nullable = false)
    @Schema(description = "Athlete name", example = "Sandra")
    private String athleteName;

    //Variable o campo que almacena los apellidos del atleta
    @Column(name = "athlete_surname", length = 250, nullable = false)
    @Schema(description = "Athlete surname", example = "Sánchez Jaime")
    private String athleteSurname;

    //Variable o campo que almacena el país al que representa el atleta
    @Column(name = "athlete_country", length = 250, nullable = false)
    @Schema(description = "Athlete country", example = "España")
    private String athleteCountry;

    public AthleteModel() {
    }

    public AthleteModel(String athleteName, String athleteSurname, String athleteCountry) {
        this.athleteName = athleteName;
        this.athleteSurname = athleteSurname;
        this.athleteCountry = athleteCountry;
    }

    public Long getAthleteId() {
        return athleteId;
    }

    public void setAthleteId(Long athleteId) {
        this.athleteId = athleteId;
    }

    public String getAthleteName() {
        return athleteName;
    }

    public void setAthleteName(String athleteName) {
        this.athleteName = athleteName;
    }

    public String getAthleteSurname() {
        return athleteSurname;
    }

    public void setAthleteSurname(String athleteSurname) {
        this.athleteSurname = athleteSurname;
    }

    public String getAthleteCountry() {
        return athleteCountry;
    }

    public void setAthleteCountry(String athleteCountry) {
        this.athleteCountry = athleteCountry;
    }

}