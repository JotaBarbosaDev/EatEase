package com.eatease.eatease.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.eatease.eatease.model.TipoMenu;
import com.eatease.eatease.service.TipoMenuService;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/tipomenu")
@Validated
public class TipoMenuController {

    private final TipoMenuService tipoMenuService;

    public TipoMenuController(TipoMenuService tipoMenuService) {
        this.tipoMenuService = tipoMenuService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllTipoMenus(@Parameter(hidden = true) HttpServletRequest request) {
        List<TipoMenu> tipoMenus = tipoMenuService.getAllTipoMenus();
        return ResponseEntity.ok(tipoMenus);
    }
}
