package com.eatease.eatease;

import com.eatease.eatease.service.CargoService;
import com.eatease.eatease.service.EstadoPedidoService;
import com.eatease.eatease.service.FuncionarioService;
import com.eatease.eatease.service.IngredientesService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Testing {

    private final CargoService cargoService;
    private final EstadoPedidoService estadoPedidoService;
    private final FuncionarioService funcionarioService;
    private final IngredientesService ingredientesService;
        private final PasswordEncoder passwordEncoder;


    public Testing(CargoService cargoService, EstadoPedidoService estadoPedidoService, FuncionarioService funcionarioService, IngredientesService ingredientesService, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.cargoService = cargoService;
        this.estadoPedidoService = estadoPedidoService;
        this.funcionarioService = funcionarioService;
        this.ingredientesService = ingredientesService;
    }

    public void criar() {
        cargoService.createCargo("FUNCIONARIO");
        cargoService.createCargo("GERENTE");
        cargoService.createCargo("COZINHEIRO");
        cargoService.createCargo("LIMPEZA");
        estadoPedidoService.createEstadoPedido("PENDDING");
        estadoPedidoService.createEstadoPedido("READY");
        estadoPedidoService.createEstadoPedido("IN_PREPARATION");
        estadoPedidoService.createEstadoPedido("SERVED");
        funcionarioService.createFuncionario("Administrador", Long.parseLong("2"), "admin", passwordEncoder.encode("admin"), "admin@email.com", "213123123");
        funcionarioService.createFuncionario("Jota", Long.parseLong("1"), "jota", passwordEncoder.encode("jota"), "jota@email.pt", "213123123");
        funcionarioService.createFuncionario("Rafa", Long.parseLong("1"), "rafa", passwordEncoder.encode("rafa"), "rafa@email.fr", "213123123");
        ingredientesService.createIngredientes("Arroz", 10000, 1000,  "1");
        ingredientesService.createIngredientes("Massa", 20000, 1000,  "1");
        ingredientesService.createIngredientes("Batata", 35000, 5000,  "1");

    }
}
