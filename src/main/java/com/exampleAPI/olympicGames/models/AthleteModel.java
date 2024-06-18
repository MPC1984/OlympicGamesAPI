package com.exampleAPI.olympicGames.models;

import jakarta.persistence.*;

@Entity
@Table (name = "athlete")
public class AthleteModel {

    @Id
    @Column (name = "athlete_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "athlete_name", length = 250, nullable = false)
    private String athleteName;

    @Column (name = "athlete_surname", length = 250, nullable = false)
    private String athleteSurname;

    @Column (name = "athlete_country", length = 250, nullable = false)
    private String athleteCountry;

    public AthleteModel() {
    }

    public AthleteModel(String athleteName, String athleteSurname, String athleteCountry) {
        this.athleteName = athleteName;
        this.athleteSurname = athleteSurname;
        this.athleteCountry = athleteCountry;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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