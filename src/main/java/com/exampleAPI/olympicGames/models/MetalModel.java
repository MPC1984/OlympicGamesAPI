package com.exampleAPI.olympicGames.models;

import jakarta.persistence.*;

@Entity
@Table (name = "metal")
public class MetalModel {

    @Id
    @Column  (name = "metal_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "metal_type", length = 10, nullable = false, unique = true)
    private String metalType;

    public MetalModel(){
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