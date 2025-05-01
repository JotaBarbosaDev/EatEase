package com.eatease.eatease.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import com.eatease.eatease.dto.MenuCreateDTO;
import com.eatease.eatease.service.*;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;

public class MenuController {

    private final MenuService menuService;
    private final IngredientesService ingredientesService;
    private final TipoPratoService tipoPratoService;
    private final TipoMenuService tipoMenuService;

    public MenuController(MenuService menuService, IngredientesService ingredientesService,
            TipoPratoService tipoPratoService, TipoMenuService tipoMenuService) {
        this.menuService = menuService;
        this.ingredientesService = ingredientesService;
        this.tipoPratoService = tipoPratoService;
        this.tipoMenuService = tipoMenuService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createMenu(MenuCreateDTO menuCreateDTO,
            @Parameter(hidden = true) HttpServletRequest request) {
        // Verifica se o utilizador está autenticado
        String validUsername = Login.checkLoginWithCargos(request, "GERENTE", "COZINHEIRO");
        if (validUsername == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Não autenticado");
        }

        String res = menuService.createMenu(
                menuCreateDTO.getNome(),
                menuCreateDTO.getDescricao(),
                menuCreateDTO.getItemsIds(),
                menuCreateDTO.getTipoMenuIds());
        if (res != null) {
            return ResponseEntity.ok("Item adicionado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(res);
        }
    }

}
