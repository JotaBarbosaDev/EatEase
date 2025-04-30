package com.eatease.eatease.dto;

import jakarta.validation.constraints.NotNull;

public class MovimentosIngredientesRequestDTO {
    @NotNull(message = "A quantidade é obrigatória")
    private Integer quantidade;

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}