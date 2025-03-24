package com.eatease.eatease.controller;

import com.eatease.eatease.dto.FuncionarioDTO;
import com.eatease.eatease.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping
    public ResponseEntity<String> createFuncionario(@Valid @RequestBody FuncionarioDTO funcionarioDTO) {
        boolean created = funcionarioService.createFuncionario(
            funcionarioDTO.getNome(),
            funcionarioDTO.getCargoId(),
            funcionarioDTO.getUsername(),
            funcionarioDTO.getPassword(),
            funcionarioDTO.getEmail(),
            funcionarioDTO.getTelefone()
        );
        if (created) {
            return ResponseEntity.ok("Funcionário criado com sucesso!");
        }
        return ResponseEntity.badRequest().body("Erro: Funcionário já existe ou cargo não encontrado.");
    }
    
    // Podes adicionar outros endpoints (GET, PUT, DELETE)
}
