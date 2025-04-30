package com.eatease.eatease.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class IngredientesRequestDTO {

    @NotBlank(message = "O nome não pode estar em branco")
    private String nome;

    @NotNull(message = "O stock não pode ser nulo")
    @Min(value = 0, message = "O stock não pode ser negativo")
    private Integer stock;

    @NotNull(message = "O stock mínimo não pode ser nulo")
    @Min(value = 0, message = "O stock mínimo não pode ser negativo")
    private Integer stock_min;

    @NotBlank(message = "A unidade de medida não pode estar em branco")
    private String unidadeMedida;

    // Constructors
    public IngredientesRequestDTO() {
    }

    public IngredientesRequestDTO(String nome, Integer stock, Integer stock_min, String unidadeMedida) {
        this.nome = nome;
        this.stock = stock;
        this.stock_min = stock_min;
        this.unidadeMedida = unidadeMedida;
    }

    // Getters and Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getStock_min() {
        return stock_min;
    }

    public void setStock_min(Integer stock_min) {
        this.stock_min = stock_min;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }
}