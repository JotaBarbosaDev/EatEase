package com.eatease.eatease.service;

import com.eatease.eatease.model.Cargo;
import com.eatease.eatease.model.Funcionario;
import com.eatease.eatease.repository.CargoRepository;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class CargoService {

    private final CargoRepository cargoRepository;

    public CargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public boolean createCargo(String nome) {
        if (cargoRepository.findByNome(nome).isEmpty()) {
            Cargo cargo = new Cargo();
            cargo.setNome(nome);
            cargoRepository.save(cargo);
            System.err.println("Cargo adicionado com sucesso.");
            return true;
        }else{
            System.err.println("O cargo já existe.");
            return false;
        }
    }

    public boolean adicionarFuncionarioAoCargo(Long cargoId, Funcionario funcionario) {
    Optional<Cargo> cargoOpt = cargoRepository.findById(cargoId);
    if (cargoOpt.isPresent()) {
        Cargo cargo = cargoOpt.get();
        cargo.getFuncionarios().add(funcionario);
        cargoRepository.save(cargo);
        System.out.println("Funcionário adicionado ao cargo com sucesso.");
        return true;
    }
    System.out.println("Cargo não encontrado.");
    return false;
}


public Optional<Cargo> findById(Long id) {
    return cargoRepository.findById(id);
}


}
