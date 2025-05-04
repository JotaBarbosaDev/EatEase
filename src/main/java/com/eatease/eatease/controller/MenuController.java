package com.eatease.eatease.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.eatease.eatease.dto.MenuCreateDTO;
import com.eatease.eatease.service.*;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;

public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
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
                menuCreateDTO.getTipoMenuId());
        if (res == null) {
            return ResponseEntity.ok("Item adicionado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(res);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteMenu(Long id,
            @Parameter(hidden = true) HttpServletRequest request) {
        // Verifica se o utilizador está autenticado
        String validUsername = Login.checkLoginWithCargos(request, "GERENTE", "COZINHEIRO");
        if (validUsername == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Não autenticado");
        }

        if (menuService.deleteMenu(id)) {
            return ResponseEntity.ok("Menu eliminado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("O menu não existe.");
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllMenus(@Parameter(hidden = true) HttpServletRequest request) {
        // Verifica se o utilizador está autenticado
        String validUsername = Login.checkLoginWithCargos(request, "GERENTE", "COZINHEIRO");
        if (validUsername == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Não autenticado");
        }

        return ResponseEntity.ok(menuService.getAllMenus());
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateMenu(Long id, MenuCreateDTO menuCreateDTO,
            @Parameter(hidden = true) HttpServletRequest request) {
        // Verifica se o utilizador está autenticado
        String validUsername = Login.checkLoginWithCargos(request, "GERENTE", "COZINHEIRO");
        if (validUsername == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Não autenticado");
        }

        String res = menuService.updateMenu(
                id,
                menuCreateDTO.getNome(),
                menuCreateDTO.getDescricao(),
                menuCreateDTO.getItemsIds(),
                menuCreateDTO.getTipoMenuId());
        if (res != null) {
            return ResponseEntity.ok("Item editado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(res);
        }
    }

}
