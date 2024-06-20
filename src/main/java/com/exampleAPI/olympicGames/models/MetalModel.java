package com.exampleAPI.olympicGames.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

//Hay que crear previamente la siguiente tabla en MySQL para poder utilizar esta aplicación
//CREATE TABLE db.metal (
//        metal_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
//        metal_type VARCHAR(10) NOT NULL UNIQUE
//);

//Clase que permite almacenar el modelo de datos de información de un metal
@Entity
@Table(name = "metal")
@Schema(name = "Metal", description = "Metal information")
public class MetalModel {

    //Variable o campo que almacena el identificador del metal
    @Id
    @Column(name = "metal_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Metal identifier", example = "1")
    private Long id;

    //Variable o campo que almacena el tipo del metal
    @Column(name = "metal_type", length = 10, nullable = false, unique = true)
    @Schema(description = "Metal type", example = "Oro")
    private String metalType;

    public MetalModel() {
    }

    public MetalModel(String metalType) {
        this.metalType = metalType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMetalType() {
        return metalType;
    }

    public void setMetalType(String metalType) {
        this.metalType = metalType;
    }

}