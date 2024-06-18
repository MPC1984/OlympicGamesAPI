package com.exampleAPI.olympicGames.services;

import com.exampleAPI.olympicGames.models.MetalModel;
import com.exampleAPI.olympicGames.repositories.IMetalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MetalService {

    @Autowired
    IMetalRepository metalRepository;

    public void deleteMetal(Long idMetal) {
        this.metalRepository.deleteById(idMetal);
    }

    public List<MetalModel> getMetal() {
        return this.metalRepository.findAll();
    }

    public Optional<MetalModel> getMetalById(Long idMetal) {
        return this.metalRepository.findById(idMetal);
    }

    public MetalModel getMetalByType(String metalType) {
        MetalModel metalSearched = null;
        for (MetalModel metal : this.metalRepository.findAll()){
            if(metal.getMetalType().equals(metalType)) {
                metalSearched = metal;
                break;
            }
        }
        return metalSearched;
    }

    public MetalModel saveMetal(String metalType) {
        return this.metalRepository.save(new MetalModel(metalType));
    }

    public MetalModel updateMetalById(MetalModel oldMetal, String metalType) {
        oldMetal.setMetalType(metalType);
        return this.metalRepository.save(oldMetal);
    }

}