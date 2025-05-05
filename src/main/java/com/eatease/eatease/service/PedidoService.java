package com.eatease.eatease.service;

import com.eatease.eatease.dto.IngredienteQuantDTO;
import com.eatease.eatease.model.Funcionario;
import com.eatease.eatease.model.Item;
import com.eatease.eatease.model.Mesa;
import com.eatease.eatease.model.Pedido;
import com.eatease.eatease.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ItemService itemService;
    private final MesaService mesaService;
    private final FuncionarioService funcionarioService;
    private final EstadoPedidoService estadoPedidoService;
    private final IngredientesService ingredientesService;

    public PedidoService(PedidoRepository PedidoRepository, ItemService itemService,
            MesaService mesaService, FuncionarioService funcionarioService,
            IngredientesService ingredientesService,
            EstadoPedidoService estadoPedidoService) {
        this.pedidoRepository = PedidoRepository;
        this.itemService = itemService;
        this.mesaService = mesaService;
        this.funcionarioService = funcionarioService;
        this.ingredientesService = ingredientesService;
        this.estadoPedidoService = estadoPedidoService;
    }

    public String checkAllInfo(long prato_id, long estadoPedido_id, long mesa_id, long funcionario_id) {
        // Verifica se o prato/estadoPedido/mesa/funcionario existe
        Optional<Item> pratoOpt = itemService.getItemById(prato_id);
        if (pratoOpt.isEmpty()) {
            System.err.println("O prato não existe.");
            return "O prato não existe.";
        }
        Optional<Mesa> mesaOpt = mesaService.getMesaById(mesa_id);
        if (mesaOpt.isEmpty()) {
            System.err.println("A mesa não existe.");
            return "A mesa não existe.";
        }

        if (!estadoPedidoService.existsById(estadoPedido_id)) {
            System.err.println("O estado do pedido não existe.");
            return "O estado do pedido não existe.";
        }

        Optional<Funcionario> funcionarioOpt = funcionarioService.getFuncionarioById(funcionario_id);
        if (funcionarioOpt.isEmpty()) {
            System.err.println("O funcionário não existe.");
            return "O funcionário não existe.";
        }
        return null;
    }

    /**
     * Checks if there's enough stock for all ingredients in a dish
     * Used within the transaction to ensure consistency
     */
    @Transactional(isolation = Isolation.REPEATABLE_READ, readOnly = true)
    public List<Long> temIngredientesSuficientes(Item prato, List<IngredienteQuantDTO> ingredientes) {
        List<Long> naoHaStockSuficiente = new java.util.ArrayList<>();
        for (IngredienteQuantDTO ingredienteQuant : ingredientes) {
            if (!ingredientesService.temStockSuficiente(ingredienteQuant.getIngredienteId(),
                    ingredienteQuant.getQuantidade())) {
                System.err.println("Não há stock suficiente do ingrediente " + ingredienteQuant.getIngredienteId());
                naoHaStockSuficiente.add(ingredienteQuant.getIngredienteId());
            }
        }
        return naoHaStockSuficiente;
    }

    /**
     * Verifies if there's enough stock for all ingredients and removes them
     * atomically
     * Using REPEATABLE_READ to prevent other transactions from changing the stock
     * during the operation
     */
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public boolean alterarStockItem(long prato_id) {
        Optional<Item> pratoOpt = itemService.getItemById(prato_id);
        if (pratoOpt.isPresent()) {
            Item prato = pratoOpt.get();
            List<IngredienteQuantDTO> ingredientes = itemService.getIngredientesByItemId(prato.getId());
            if (ingredientes == null || ingredientes.isEmpty()) {
                System.err.println("O prato não tem ingredientes.");
                return false;
            }

            if (temIngredientesSuficientes(prato, ingredientes).size() > 0) {
                System.err.println("Não há stock suficiente para o prato " + prato.getNome());
                return false;
            }

            for (IngredienteQuantDTO ingredienteQuant : ingredientes) {
                ingredientesService.removeStock(ingredienteQuant.getIngredienteId(), ingredienteQuant.getQuantidade());
            }
            return true;
        } else {
            System.err.println("O prato não existe.");
            return false;
        }
    }

    public Pedido createPedido(long prato_id, long estadoPedido_id, long mesa_id, long funcionario_id,
            String observacao) throws Exception {

        String error = checkAllInfo(prato_id, estadoPedido_id, mesa_id, funcionario_id);
        if (error != null) {
            throw new Exception(error);
        }

        if (alterarStockItem(prato_id) == false) {
            throw new Exception("Não há stock suficiente para o prato " + prato_id);
        }

        Pedido Pedido = new Pedido();
        Pedido.setPrato_id(prato_id);
        Pedido.setEstadoPedido_id(estadoPedido_id);
        Pedido.setMesa_id(mesa_id);
        Pedido.setFuncionario_id(funcionario_id);
        Pedido.setDataHora(java.time.LocalDateTime.now().toString());
        Pedido.setObservacao(observacao);
        pedidoRepository.save(Pedido);
        System.err.println("Pedido adicionado com sucesso.");
        return Pedido; // sucesso
    }

    // public String updatePedido(long id, long prato_id, long estadoPedido_id, long
    // mesa_id, long funcionario_id,
    // String observacao) {

    // String error = checkAllInfo(prato_id, estadoPedido_id, mesa_id,
    // funcionario_id);
    // if (error != null) {
    // return error;
    // }

    // if (alterarStockItem(prato_id) == false) {
    // return "Não há stock suficiente para o prato " + prato_id;
    // }
    // Optional<Pedido> pedidoOpt = pedidoRepository.findById(id);
    // if (pedidoOpt.isPresent()) {
    // Pedido pedido = pedidoOpt.get();
    // pedido.setPrato_id(prato_id);
    // pedido.setEstadoPedido_id(estadoPedido_id);
    // pedido.setMesa_id(mesa_id);
    // pedido.setFuncionario_id(funcionario_id);
    // pedido.setDataHora(java.time.LocalDateTime.now().toString());
    // pedido.setObservacao(observacao);
    // pedidoRepository.save(pedido);
    // System.err.println("Pedido atualizado com sucesso.");
    // return null; // sucesso
    // } else {
    // System.err.println("O pedido não existe.");
    // return "O pedido não existe.";
    // }
    // }

    public boolean deletePedido(long id) {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);
            System.err.println("Pedido removido com sucesso.");
            return true;
        } else {
            System.err.println("O pedido não existe.");
            return false;
        }
    }

    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> getPedidoById(long id) {
        return pedidoRepository.findById(id);
    }

    public String setEstadoPedido(long id, long estadoPedido_id) {
        Optional<Pedido> pedidoOpt = pedidoRepository.findById(id);
        if (pedidoOpt.isPresent()) {
            Pedido pedido = pedidoOpt.get();
            pedido.setEstadoPedido_id(estadoPedido_id);
            pedidoRepository.save(pedido);
            System.err.println("Estado do pedido atualizado com sucesso.");
            return null; // sucesso
        } else {
            System.err.println("O pedido não existe.");
            return "O pedido não existe.";
        }
    }
}
