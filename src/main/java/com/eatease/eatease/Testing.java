package com.eatease.eatease;

import com.eatease.eatease.service.CargoService;
import com.eatease.eatease.service.EstadoPedidoService;
import com.eatease.eatease.service.FuncionarioService;
import com.eatease.eatease.service.IngredientesService;
import com.eatease.eatease.service.UnidadeMedidaService;
import org.springframework.stereotype.Component;

@Component
public class Testing {

        private final UnidadeMedidaService unidadeMedidaService;
        private final CargoService cargoService;
        private final EstadoPedidoService estadoPedidoService;
        private final FuncionarioService funcionarioService;
        private final IngredientesService ingredientesService;

        public Testing(CargoService cargoService, EstadoPedidoService estadoPedidoService,
                        FuncionarioService funcionarioService, IngredientesService ingredientesService,
                        UnidadeMedidaService unidadeMedidaService) {
                this.cargoService = cargoService;
                this.estadoPedidoService = estadoPedidoService;
                this.funcionarioService = funcionarioService;
                this.unidadeMedidaService = unidadeMedidaService;
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

                unidadeMedidaService.createUnidadeMedida("quilos");
                unidadeMedidaService.createUnidadeMedida("gramas");
                unidadeMedidaService.createUnidadeMedida("litros");
                unidadeMedidaService.createUnidadeMedida("mililitros");
                unidadeMedidaService.createUnidadeMedida("unidades");
                unidadeMedidaService.createUnidadeMedida("doses");
                unidadeMedidaService.createUnidadeMedida("caixas");

                funcionarioService.createFuncionario("Administrador", Long.parseLong("2"), "admin",
                                "admin", "admin@email.com", "213123123");
                funcionarioService.createFuncionario("Jota", Long.parseLong("1"), "jota", "jota",
                                "jota@email.pt", "213123123");
                funcionarioService.createFuncionario("Rafa", Long.parseLong("1"), "rafa", "rafa",
                                "rafa@email.fr", "213123123");
                ingredientesService.createIngredientes("Arroz", 10000, 1000, "gramas");
                ingredientesService.createIngredientes("Massa", 20000, 1000, "quilos");
                ingredientesService.createIngredientes("Batata", 35000, 5000, "unidades");

        }
}
