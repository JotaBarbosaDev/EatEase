package com.eatease.eatease.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * DTO for receiving pedido creation requests
 */
public class PedidoRequestDTO {

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

    @Valid
    private List<IngredienteQuantDTO> ingredientesRemover;

    private String observacao;

    // Constructors
    public PedidoRequestDTO() {
    }

    public PedidoRequestDTO(Long pratoId, Long estadoPedidoId, Long mesaId, Long funcionarioId, String observacao) {
        this.pratoId = pratoId;
        this.estadoPedidoId = estadoPedidoId;
        this.mesaId = mesaId;
        this.funcionarioId = funcionarioId;
        this.observacao = observacao;
    }

    // Getters and Setters
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

    public List<IngredienteQuantDTO> getIngredientesRemover() {
        return ingredientesRemover;
    }

    public void setIngredientesRemover(List<IngredienteQuantDTO> ingredientes) {
        this.ingredientesRemover = ingredientes;
    }
}