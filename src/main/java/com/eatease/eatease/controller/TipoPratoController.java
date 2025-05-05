package com.eatease.eatease.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.eatease.eatease.model.TipoPrato;
import com.eatease.eatease.service.TipoPratoService;
import com.eatease.eatease.service.Login;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/tipoprato")
@Validated
public class TipoPratoController {

    private final TipoPratoService tipoPratoService;

    public TipoPratoController(TipoPratoService tipoPratoService) {
        this.tipoPratoService = tipoPratoService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllTipoPratos(@Parameter(hidden = true) HttpServletRequest request) {
        List<TipoPrato> tipoPratos = tipoPratoService.getAllTipoPratos();
        return ResponseEntity.ok(tipoPratos);
    }
}
