package com.eatease.eatease;

import com.eatease.eatease.service.CargoService;
import org.springframework.stereotype.Component;

@Component
public class Testing {

    private final CargoService cargoService;

    public Testing(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    public void criar() {
        cargoService.createCargo("Funcionario");
        cargoService.createCargo("Gerente");
        System.out.println("Cargos criados com sucesso!");
    }
}
