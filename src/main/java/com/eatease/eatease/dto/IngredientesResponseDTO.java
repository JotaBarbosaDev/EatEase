package com.eatease.eatease.dto;

import com.eatease.eatease.model.Ingredientes;

public class IngredientesResponseDTO {

    private long id;
    private String nome;
    private int stock;
    private int stock_min;
    private Long unidade_id;
    private String unidadeMedidaNome;

    // Constructors
    public IngredientesResponseDTO() {
    }

    public IngredientesResponseDTO(long id, String nome, int stock, int stock_min, Long unidade_id) {
        this.id = id;
        this.nome = nome;
        this.stock = stock;
        this.stock_min = stock_min;
        this.unidade_id = unidade_id;
    }

    // Factory method to create DTO from entity
    public static IngredientesResponseDTO fromEntity(Ingredientes ingredientes) {
        IngredientesResponseDTO dto = new IngredientesResponseDTO();
        dto.setId(ingredientes.getId());
        dto.setNome(ingredientes.getNome());
        dto.setStock(ingredientes.getStock());
        dto.setStock_min(ingredientes.getStock_min());
        dto.setUnidade_id(ingredientes.getUnidade_id());
        return dto;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStock_min() {
        return stock_min;
    }

    public void setStock_min(int stock_min) {
        this.stock_min = stock_min;
    }

    public Long getUnidade_id() {
        return unidade_id;
    }

    public void setUnidade_id(Long unidade_id) {
        this.unidade_id = unidade_id;
    }

    public String getUnidadeMedidaNome() {
        return unidadeMedidaNome;
    }

    public void setUnidadeMedidaNome(String unidadeMedidaNome) {
        this.unidadeMedidaNome = unidadeMedidaNome;
    }
}