package com.exampleAPI.olympicGames.models;

import jakarta.persistence.*;

@Entity
@Table (name = "olympic_games_medal_table")
public class OlympicGamesMedalTableModel {

    @Id
    @Column (name = "olympic_games_medal_table_id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn (name = "olympic_games_id", referencedColumnName = "olympic_games_id", nullable = false)
    private OlympicGamesModel olympicGames;

    @OneToOne
    @JoinColumn (name = "sport_id", referencedColumnName = "sport_id", nullable = false)
    private SportModel sport;

    @OneToOne
    @JoinColumn (name = "metal_id", referencedColumnName = "metal_id", nullable = false)
    private MetalModel metal;

    @OneToOne
    @JoinColumn (name = "athlete_id", referencedColumnName = "athlete_id", nullable = false)
    private AthleteModel athlete;

    public OlympicGamesMedalTableModel() {
    }

    public OlympicGamesMedalTableModel(OlympicGamesModel olympicGames, SportModel sport, MetalModel metal, AthleteModel athlete) {
        this.olympicGames = olympicGames;
        this.sport = sport;
        this.metal = metal;
        this.athlete = athlete;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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