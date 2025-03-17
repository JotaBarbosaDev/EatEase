package com.eatease.eatease;

import com.eatease.eatease.service.CargoService;
import com.eatease.eatease.service.EstadoPedidoService;
import com.eatease.eatease.service.FuncionarioService;
import com.eatease.eatease.service.IngredientesService;

import org.springframework.stereotype.Component;

@Component
public class Testing {

    private final CargoService cargoService;
    private final EstadoPedidoService estadoPedidoService;
    private final FuncionarioService funcionarioService;
    private final IngredientesService ingredientesService;

    public Testing(CargoService cargoService, EstadoPedidoService estadoPedidoService, FuncionarioService funcionarioService, IngredientesService ingredientesService) {
        this.cargoService = cargoService;
        this.estadoPedidoService = estadoPedidoService;
        this.funcionarioService = funcionarioService;
        this.ingredientesService = ingredientesService;
    }

    public void criar() {
        cargoService.createCargo("Funcionario");
        cargoService.createCargo("Gerente");
        cargoService.createCargo("Cozinheiro");
        cargoService.createCargo("Limpeza");
        estadoPedidoService.createEstadoPedido("PENDDING");
        estadoPedidoService.createEstadoPedido("READY");
        estadoPedidoService.createEstadoPedido("IN_PREPARATION");
        estadoPedidoService.createEstadoPedido("SERVED");
        funcionarioService.createFuncionario("Marques", "1");
        funcionarioService.createFuncionario("Jota", "2");
        funcionarioService.createFuncionario("Rafa", "1");
        ingredientesService.createIngredientes("Arroz", 10000, 1000,  "1");
        ingredientesService.createIngredientes("Massa", 20000, 1000,  "1");
        ingredientesService.createIngredientes("Batata", 35000, 5000,  "1");
        //Limpar dados da bd e testar com casos reais
        //Marques verifica esta merda toda mas acho que há alguns muitos ajustes a fazer
        //Falta adicionar validacoes e outras funcoes
    }
}
