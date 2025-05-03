package com.eatease.eatease.controller;

import com.eatease.eatease.model.Pedido;
import com.eatease.eatease.dto.PedidoRequestDTO;
import com.eatease.eatease.dto.PedidoUpdateDTO;
import com.eatease.eatease.service.PedidoService;
import com.eatease.eatease.service.Login;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

/**
 * Controller para gerenciar pedidos do restaurante
 */
@RestController
@RequestMapping("/pedido")
@Validated
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    /**
     * Cria um novo pedido
     */
    @PostMapping("/create")
    public ResponseEntity<String> createPedido(
            @Valid @RequestBody PedidoRequestDTO pedidoDTO,
            @Parameter(hidden = true) HttpServletRequest request) {

        // Verificação de autenticação - GERENTE, COZINHEIRO e FUNCIONARIO podem criar
        // pedidos
        String validUsername = Login.checkLoginWithCargos(request, "GERENTE", "COZINHEIRO", "FUNCIONARIO");
        if (validUsername == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Não autenticado ou sem permissões");
        }

        String result = pedidoService.createPedido(
                pedidoDTO.getPratoId(),
                pedidoDTO.getEstadoPedidoId(),
                pedidoDTO.getMesaId(),
                pedidoDTO.getFuncionarioId(),
                pedidoDTO.getObservacao());

        if (result == null) {
            return ResponseEntity.ok("Pedido criado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(result);
        }
    }

    /**
     * Busca todos os pedidos
     */
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllPedidos(@Parameter(hidden = true) HttpServletRequest request) {
        // Verificação de autenticação - todos os funcionários podem ver os pedidos
        String validUsername = Login.checkLoginWithCargos(request, "GERENTE", "COZINHEIRO", "FUNCIONARIO");
        if (validUsername == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Não autenticado");
        }

        List<Pedido> pedidos = pedidoService.getAllPedidos();
        return ResponseEntity.ok(pedidos);
    }

    /**
     * Busca um pedido pelo ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getPedidoById(
            @PathVariable long id,
            @Parameter(hidden = true) HttpServletRequest request) {

        // Verificação de autenticação - todos os funcionários podem ver os pedidos
        String validUsername = Login.checkLoginWithCargos(request, "GERENTE", "COZINHEIRO", "FUNCIONARIO");
        if (validUsername == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Não autenticado");
        }

        Optional<Pedido> pedido = pedidoService.getPedidoById(id);
        if (pedido.isPresent()) {
            return ResponseEntity.ok(pedido.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado");
        }
    }

    /**
     * Atualiza um pedido existente
     */
    @PostMapping("/edit")
    public ResponseEntity<String> updatePedido(
            @Valid @RequestBody PedidoUpdateDTO pedidoDTO,
            @Parameter(hidden = true) HttpServletRequest request) {

        // Verificação de autenticação - GERENTE, COZINHEIRO e FUNCIONARIO podem editar
        // pedidos
        String validUsername = Login.checkLoginWithCargos(request, "GERENTE", "COZINHEIRO", "FUNCIONARIO");
        if (validUsername == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Não autenticado ou sem permissões");
        }

        String result = pedidoService.updatePedido(
                pedidoDTO.getId(),
                pedidoDTO.getPratoId(),
                pedidoDTO.getEstadoPedidoId(),
                pedidoDTO.getMesaId(),
                pedidoDTO.getFuncionarioId(),
                pedidoDTO.getObservacao());

        if (result == null) {
            return ResponseEntity.ok("Pedido atualizado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
    }

    /**
     * Remove um pedido
     */
    @DeleteMapping("/delete")
    public ResponseEntity<String> deletePedido(
            @RequestParam long id,
            @Parameter(hidden = true) HttpServletRequest request) {

        // Verificação de autenticação - apenas GERENTE pode remover pedidos
        String validUsername = Login.checkLoginWithCargos(request, "GERENTE");
        if (validUsername == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Não autenticado ou sem permissões");
        }

        boolean result = pedidoService.deletePedido(id);
        if (result) {
            return ResponseEntity.ok("Pedido removido com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado");
        }
    }

    @PostMapping("/setEstado")
    public ResponseEntity<String> setEstado(
            @RequestParam long id,
            @RequestParam long estadoPedido_id,
            @Parameter(hidden = true) HttpServletRequest request) {

        // Verificação de autenticação - GERENTE, COZINHEIRO e FUNCIONARIO podem editar
        // pedidos
        String validUsername = Login.checkLoginWithCargos(request, "GERENTE", "COZINHEIRO", "FUNCIONARIO");
        if (validUsername == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Não autenticado ou sem permissões");
        }

        String result = pedidoService.setEstadoPedido(id, estadoPedido_id);
        if (result == null) {
            return ResponseEntity.ok("Estado do pedido atualizado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
    }
}
