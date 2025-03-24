package com.eatease.eatease.controller;

import com.eatease.eatease.model.Cargo;
import com.eatease.eatease.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/cargos")
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @PostMapping
    public ResponseEntity<String> createCargo(@RequestParam String nome) {
        boolean created = cargoService.createCargo(nome);
        if (created) {
            return ResponseEntity.ok("Cargo criado com sucesso!");
        }
        return ResponseEntity.badRequest().body("Erro: Cargo já existe.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cargo> getCargo(@PathVariable Long id) {
        Optional<Cargo> cargoOpt = cargoService.findById(id); // Se implementares esse método no serviço
        return cargoOpt.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }
    
    // Podes adicionar endpoints para atualização e eliminação
}
