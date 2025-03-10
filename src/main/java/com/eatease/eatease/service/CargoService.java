package com.eatease.eatease.service;

import com.eatease.eatease.model.Cargo;
import com.eatease.eatease.repository.CargoRepository;
import org.springframework.stereotype.Service;

@Service
public class CargoService {

    private final CargoRepository cargoRepository;

    public CargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public Cargo createCargo(String nome) {
        Cargo cargo = new Cargo(nome);
        return cargoRepository.save(cargo);
    }
}
