package com.exampleAPI.olympicGames.services;

import com.exampleAPI.olympicGames.models.MetalModel;
import com.exampleAPI.olympicGames.repositories.IMetalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Clase en la que se definen las diferentes operaciones que se pueden realizar sobre el modelo de datos que tiene la información de un metal
@Service
public class MetalService {

    @Autowired
    IMetalRepository metalRepository;

    //Método u operación que permite obtener una lista con la información de todos los metales
    public List<MetalModel> getMetal() {
        return this.metalRepository.findAll();
    }

    //Método u operación que permite obtener la información de un metal que tiene un determinado identificador
    public Optional<MetalModel> getMetalById(Long idMetal) {
        return this.metalRepository.findById(idMetal);
    }

    //Método u operación que permite obtener la información de un metal que tiene un determinado tipo
    //Hay que tener en cuenta que el tipo de un metal es único (es decir, no puede haber dos metales con el mismo tipo)
    public MetalModel getMetalByType(String metalType) {
        MetalModel metalSearched = null;
        for (MetalModel metal : this.metalRepository.findAll()) {
            if (metal.getMetalType().equals(metalType)) {
                metalSearched = metal;
                break;
            }
        }
        return metalSearched;
    }

    //Método u operación que permite guardar la información de un metal dado el tipo
    public MetalModel saveMetal(String metalType) {
        return this.metalRepository.save(new MetalModel(metalType));
    }

    //Método u operación que permite actualizar la información de un metal que tiene un determinado identificador con el nuevo tipo
    public MetalModel updateMetalById(MetalModel oldMetal, String metalType) {
        oldMetal.setMetalType(metalType);
        return this.metalRepository.save(oldMetal);
    }

    //Método u operación que permite eliminar la información de un metal que tiene un determinado identificador
    public void deleteMetal(Long idMetal) {
        this.metalRepository.deleteById(idMetal);
    }

}