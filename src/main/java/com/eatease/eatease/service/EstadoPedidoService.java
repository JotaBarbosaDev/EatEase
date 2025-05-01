package com.eatease.eatease.service;

import com.eatease.eatease.model.EstadoPedido;
import com.eatease.eatease.repository.EstadoPedidoRepository;

import org.springframework.stereotype.Service;

@Service
public class EstadoPedidoService {

    private final EstadoPedidoRepository estadoPedidoRepository;

    public EstadoPedidoService(EstadoPedidoRepository estadoPedidoRepository) {
        this.estadoPedidoRepository = estadoPedidoRepository;
    }

    public boolean createEstadoPedido(String nome) {
        if (estadoPedidoRepository.findByNome(nome).isEmpty()) {
            EstadoPedido estadoPedido = new EstadoPedido();
            estadoPedido.setNome(nome);
            estadoPedidoRepository.save(estadoPedido);
            System.err.println("Estado de pedido adicionado com sucesso.");
            return true;
        } else {
            System.err.println("O estado de pedido já existe.");
            return false;
        }
    }

    public Long getEstadoPedidoId(String nome) {
        return estadoPedidoRepository.findByNome(nome)
                .map(EstadoPedido::getId)
                .orElse(null);
    }
}
