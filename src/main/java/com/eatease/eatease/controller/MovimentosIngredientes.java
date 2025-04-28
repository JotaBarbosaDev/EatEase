package com.eatease.eatease.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.eatease.eatease.service.FuncionarioService;
import com.eatease.eatease.service.IngredientesService;
import com.eatease.eatease.service.Login;
import com.eatease.eatease.service.MovimentosIngredientesService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import io.swagger.v3.oas.annotations.Parameter; // springdoc-openapi

@RestController
@RequestMapping("/movimentosIngredientes")
@Validated
public class MovimentosIngredientes {
    private final IngredientesService ingredientesService;
    private final MovimentosIngredientesService movimentosIngredientesService;

    public MovimentosIngredientes(IngredientesService ingredientesService, FuncionarioService funcionarioService,
            MovimentosIngredientesService movimentosIngredientesService) {
        this.ingredientesService = ingredientesService;
        this.movimentosIngredientesService = movimentosIngredientesService;
    }

    @PostMapping("/movStock")
    public ResponseEntity<String> addStock(
            @RequestParam long id_ingrediente,
            @RequestParam int quantidade,
            @Parameter(hidden = true) HttpServletRequest request) {

        // Verifica se o utilizador está autenticado
        String validUsername = Login.checkLoginWithCargos(request, "GERENTE", "COZINHEIRO");
        if (validUsername == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Não autenticado");
        }

        boolean res = movimentosIngredientesService.createMovimentoIngrediente(id_ingrediente, quantidade);
        if (res) {
            return ResponseEntity.ok("Movimento de adição de stock criado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Não foi possível criar o movimento de adição de stock.");
        }
    }
}
