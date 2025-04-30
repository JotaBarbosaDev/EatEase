package com.eatease.eatease.controller;

import org.springframework.http.ResponseEntity;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.eatease.eatease.model.Item;
import com.eatease.eatease.service.FuncionarioService;
import com.eatease.eatease.service.IngredientesService;
import com.eatease.eatease.service.ItemService;
import com.eatease.eatease.service.Login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import io.swagger.v3.oas.annotations.Parameter; // springdoc-openapi

@RestController
@RequestMapping("/item")
@Validated
public class ItemController {
    private final ItemService itemService;
    private final FuncionarioService funcionarioService;

    public ItemController(ItemService itemService, FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
        this.itemService = itemService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createItem(
            @RequestBody String nome,
            @RequestBody long tipoPratoId,
            @RequestBody float preco,
            @RequestBody long ingredientes_id[],
            @RequestBody boolean eCpmposto,
            @RequestBody int stockAtual,
            @Parameter(hidden = true) HttpServletRequest request) {

        // Verifica se o utilizador está autenticado
        String validUsername = Login.checkLoginWithCargos(request, "GERENTE", "COZINHEIRO");
        if (validUsername == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Não autenticado");
        }

        boolean res = itemService.createItem(nome, tipoPratoId, preco, ingredientes_id, eCpmposto, stockAtual);
        if (res) {
            return ResponseEntity.ok("Item adicionado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Não foi possível adicionar o item nome repetido ou tipo de prato não existente.");
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllItems(@Parameter(hidden = true) HttpServletRequest request) {
        // Verifica se o utilizador está autenticado
        String validUsername = Login.checkLoginWithCargos(request, "GERENTE", "COZINHEIRO");
        if (validUsername == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Não autenticado");
        }

        return ResponseEntity.ok(itemService.getAllItems());
    }

    @GetMapping("/getByPratoId")
    public ResponseEntity<?> getByPratoId(@RequestParam long pratoId,
            @Parameter(hidden = true) HttpServletRequest request) {
        // Verifica se o utilizador está autenticado
        String validUsername = Login.checkLoginWithCargos(request, "GERENTE", "COZINHEIRO");
        if (validUsername == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Não autenticado");
        }

        return ResponseEntity.ok(itemService.getByPratoId(pratoId));
    }

    @PostMapping("/edit")
    public ResponseEntity<?> editItem(
            @RequestParam long id,
            @RequestBody String nome,
            @RequestBody long tipoPratoId,
            @RequestBody float preco,
            @RequestBody long ingredientes_id[],
            @RequestBody boolean eCpmposto,
            @RequestBody int stockAtual,
            @Parameter(hidden = true) HttpServletRequest request) {

        // Verifica se o utilizador está autenticado
        String validUsername = Login.checkLoginWithCargos(request, "GERENTE", "COZINHEIRO");
        if (validUsername == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Não autenticado");
        }

        Optional<Item> res = itemService.editItem(id, nome, tipoPratoId, preco, ingredientes_id, eCpmposto, stockAtual);
        if (res != null && res.isPresent()) {
            return ResponseEntity.ok(res.get());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Não foi possível editar o item nome repetido ou tipo de prato não existente.");
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteItem(
            @RequestParam long id,
            @Parameter(hidden = true) HttpServletRequest request) {

        // Verifica se o utilizador está autenticado
        String validUsername = Login.checkLoginWithCargos(request, "GERENTE", "COZINHEIRO");
        if (validUsername == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Não autenticado");
        }

        boolean res = itemService.deleteItem(id);
        if (res) {
            return ResponseEntity.ok("Item eliminado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível eliminar o item.");
        }
    }
}
