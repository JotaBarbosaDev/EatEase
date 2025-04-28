package com.eatease.eatease.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.eatease.eatease.service.FuncionarioService;
import com.eatease.eatease.service.IngredientesService;
import com.eatease.eatease.service.Login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import io.swagger.v3.oas.annotations.Parameter; // springdoc-openapi

@RestController
@RequestMapping("/ingredientes")
@Validated
public class IngredientesController {
    private final IngredientesService ingredientesService;
    private final FuncionarioService funcionarioService;

    public IngredientesController(IngredientesService ingredientesService, FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
        this.ingredientesService = ingredientesService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createIngrediente(
            @RequestParam String nome,
            @RequestParam int stock,
            @RequestParam int stock_min,
            @RequestParam String unidadeMedida,
            @Parameter(hidden = true) HttpServletRequest request) {

        // Verifica se o utilizador está autenticado
        String validUsername = Login.checkLoginWithCargos(request, "GERENTE", "COZINHEIRO");
        if (validUsername == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Não autenticado");
        }

        boolean res = ingredientesService.createIngredientes(nome, stock, stock_min, unidadeMedida);
        if (res) {
            return ResponseEntity.ok("Ingrediente adicionado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Não foi possível adicionar o ingrediente nome repetido ou unidade de medida não existente.");
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllIngredientes(@Parameter(hidden = true) HttpServletRequest request) {
        // Verifica se o utilizador está autenticado
        String validUsername = Login.checkLoginWithCargos(request, "GERENTE", "COZINHEIRO");
        if (validUsername == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Não autenticado");
        }

        return ResponseEntity.ok(ingredientesService.getAllIngredientes());
    }

    @PostMapping("/edit")
    public ResponseEntity<String> editIngrediente(
            @RequestParam long id,
            @RequestParam String nome,
            @RequestParam int stock,
            @RequestParam int stock_min,
            @RequestParam String unidadeMedida,
            @Parameter(hidden = true) HttpServletRequest request) {

        // Verifica se o utilizador está autenticado
        String validUsername = Login.checkLoginWithCargos(request, "GERENTE", "COZINHEIRO");
        if (validUsername == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Não autenticado");
        }

        boolean res = ingredientesService.updateIngredientes(id, nome, stock, stock_min, unidadeMedida);
        if (res) {
            return ResponseEntity.ok("Ingrediente editado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Não foi possível editar o ingrediente nome repetido ou unidade de medida não existente.");
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteIngrediente(
            @RequestParam long id,
            @Parameter(hidden = true) HttpServletRequest request) {

        // Verifica se o utilizador está autenticado
        String validUsername = Login.checkLoginWithCargos(request, "GERENTE", "COZINHEIRO");
        if (validUsername == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Não autenticado");
        }

        boolean res = ingredientesService.deleteIngredientes(id);
        if (res) {
            return ResponseEntity.ok("Ingrediente removido com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Não foi possível remover o ingrediente.");
        }
    }
}
