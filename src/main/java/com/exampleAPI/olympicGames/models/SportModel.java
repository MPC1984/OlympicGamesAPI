package com.exampleAPI.olympicGames.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

//Hay que crear previamente la siguiente tabla en MySQL para poder utilizar esta aplicación
//CREATE TABLE db.sport (
//        sport_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
//        sport_name VARCHAR(250) NOT NULL,
//        sport_category_name VARCHAR(250)
//);

//Clase que permite almacenar el modelo de datos de información de un deporte
@Entity
@Table(name = "sport")
@Schema(name = "Sport", description = "Sport information")
public class SportModel {

    //Variable o campo que almacena el identificador del deporte
    @Id
    @Column(name = "sport_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Sport identifier", example = "1")
    private Long id;

    //Variable o campo que almacena el nombre del deporte
    @Column(name = "sport_name", length = 250, nullable = false)
    @Schema(description = "Sport name", example = "Kárate")
    private String sportName;

    //Variable o campo que almacena el nombre de la categoría del deporte
    @Column(name = "sport_category_name", length = 250, nullable = true)
    @Schema(description = "Sport category name", example = "Kata")
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