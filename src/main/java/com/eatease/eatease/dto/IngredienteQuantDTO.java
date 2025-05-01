// src/main/java/com/eatease/eatease/dto/IngredienteQuantDTO.java
package com.eatease.eatease.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

public class IngredienteQuantDTO {

    @Schema(description = "ID do ingrediente", example = "1")
    @NotNull(message = "O ID do ingrediente é obrigatório")
    private Long ingredienteId;

    @Schema(description = "Quantidade do ingrediente", example = "2.5")
    @NotNull(message = "A quantidade é obrigatória")
    @Min(value = 0, message = "A quantidade não pode ser negativa")
    private Float quantidade;

    /* getters & setters */
    public Long getIngredienteId() {
        return ingredienteId;
    }

    public void setIngredienteId(Long ingredienteId) {
        this.ingredienteId = ingredienteId;
    }

    public Float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Float quantidade) {
        this.quantidade = quantidade;
    }
}
