package com.exampleAPI.olympicGames.models;

import jakarta.persistence.*;

@Entity
@Table (name = "sport")
public class SportModel {

    @Id
    @Column  (name = "sport_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "sport_name", length = 250, nullable = false)
    private String sportName;

    @Column (name = "sport_category_name", length = 250, nullable = true)
    private String sportCategoryName;

    public SportModel() {
    }

    public SportModel(String sportName) {
        this.sportName = sportName;
    }

    public SportModel(String sportName, String sportCategoryName) {
        this.sportName = sportName;
        this.sportCategoryName = sportCategoryName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public String getSportCategoryName() {
        return sportCategoryName;
    }

    public void setSportCategoryName(String sportCategoryName) {
        this.sportCategoryName = sportCategoryName;
    }

}