package com.eatease.eatease.service;

import com.eatease.eatease.model.Pedido;
import com.eatease.eatease.repository.PedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository PedidoRepository) {
        this.pedidoRepository = PedidoRepository;
    }

    public boolean createPedido(long prato_id, long estadoPedido_id, long mesa_id, long funcionario_id) {
        
            Pedido Pedido = new Pedido();
            Pedido.setPrato_id(prato_id);
            Pedido.setEstadoPedido_id(estadoPedido_id);
            Pedido.setMesa_id(mesa_id);
            Pedido.setFuncionario_id(funcionario_id);
            pedidoRepository.save(Pedido);
            System.err.println("Pedido adicionado com sucesso.");
            return true;
    }
}
