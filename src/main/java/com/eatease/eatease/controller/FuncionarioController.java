// src/main/java/com/eatease/eatease/controller/FuncionarioController.java
package com.eatease.eatease.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import io.swagger.v3.oas.annotations.Parameter; // springdoc-openapi

import com.eatease.eatease.dto.FuncionarioDTO;
import com.eatease.eatease.service.FuncionarioService;
import com.eatease.eatease.service.Login;

@RestController
@RequestMapping("/auth")
@Validated
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
        Login.setFuncionarioService(funcionarioService);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody FuncionarioDTO dto,
            @Parameter(hidden = true) HttpServletRequest request) {
       
        // Verifica se o utilizador está autenticado
        String validUsername = Login.checkLogin(request);
        if (validUsername == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Não autenticado");
        }

        // Gerente pode criar outros funcionários
        String username = Login.getUsername(request);
        if (funcionarioService.checkCargoByUsernameAndName(username, "GERENTE") == false) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Não tem permissão para aceder a esta área");
        }

        boolean res= funcionarioService.createFuncionario(dto.getNome(), dto.getCargoId(), dto.getUsername(),
                dto.getPassword(), dto.getEmail(), dto.getTelefone());
        if(res) {
            return ResponseEntity.ok("Funcionário adicionado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O funcionário já existe.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestParam String username,
            @RequestParam String password,
            @Parameter(hidden = true) // esconder do Swagger
            HttpServletResponse response) {

        String validUsername = Login.login(username, password, response);
        return validUsername == null
                ? ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas")
                : ResponseEntity.ok("Login bem-sucedido");
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(@Parameter(hidden = true) HttpServletRequest request) {

        String validUsername = Login.checkLogin(request);
        return validUsername == null
                ? ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Não autenticado")
                : ResponseEntity.ok("Autenticado");
    }
}
