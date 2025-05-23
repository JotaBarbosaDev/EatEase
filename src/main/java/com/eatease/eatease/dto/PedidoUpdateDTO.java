package com.eatease.eatease.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * DTO for updating existing pedidos
 */
public class PedidoUpdateDTO {

    @NotNull(message = "O ID do pedido é obrigatório")
    @Min(value = 1, message = "O ID do pedido deve ser positivo")
    private Long id;

    @NotNull(message = "O ID do prato é obrigatório")
    @Min(value = 1, message = "O ID do prato deve ser positivo")
    private Long pratoId;

    @NotNull(message = "O ID do estado do pedido é obrigatório")
    @Min(value = 1, message = "O ID do estado do pedido deve ser positivo")
    private Long estadoPedidoId;

    @NotNull(message = "O ID da mesa é obrigatório")
    @Min(value = 1, message = "O ID da mesa deve ser positivo")
    private Long mesaId;

    @NotNull(message = "O ID do funcionário é obrigatório")
    @Min(value = 1, message = "O ID do funcionário deve ser positivo")
    private Long funcionarioId;

    private String observacao;

    // Constructors
    public PedidoUpdateDTO() {
    }

    public PedidoUpdateDTO(Long id, Long pratoId, Long estadoPedidoId, Long mesaId, Long funcionarioId,
            String observacao) {
        this.id = id;
        this.pratoId = pratoId;
        this.estadoPedidoId = estadoPedidoId;
        this.mesaId = mesaId;
        this.funcionarioId = funcionarioId;
        this.observacao = observacao;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPratoId() {
        return pratoId;
    }

    public void setPratoId(Long pratoId) {
        this.pratoId = pratoId;
    }

    public Long getEstadoPedidoId() {
        return estadoPedidoId;
    }

    public void setEstadoPedidoId(Long estadoPedidoId) {
        this.estadoPedidoId = estadoPedidoId;
    }

    public Long getMesaId() {
        return mesaId;
    }

    public void setMesaId(Long mesaId) {
        this.mesaId = mesaId;
    }

    public Long getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(Long funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}