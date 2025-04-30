package com.eatease.eatease.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ItemRequestDTO {
    @NotBlank(message = "O nome não pode estar vazio")
    private String nome;

    @NotNull(message = "O ID do tipo de prato é obrigatório")
    private Long tipoPrato_id;

    @NotNull(message = "O preço é obrigatório")
    @Min(value = 0, message = "O preço não pode ser negativo")
    private Float preco;

    @NotNull(message = "A lista de ingredientes é obrigatória")
    private long[] ingredientes_id;

    private boolean eCpmposto;

    @NotNull(message = "O stock atual é obrigatório")
    @Min(value = 0, message = "O stock não pode ser negativo")
    private Integer stockAtual;

    // Getters and Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getTipoPrato_id() {
        return tipoPrato_id;
    }

    public void setTipoPrato_id(Long tipoPrato_id) {
        this.tipoPrato_id = tipoPrato_id;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public long[] getIngredientes_id() {
        return ingredientes_id;
    }

    public void setIngredientes_id(long[] ingredientes_id) {
        this.ingredientes_id = ingredientes_id;
    }

    public boolean isECpmposto() {
        return eCpmposto;
    }

    public void setECpmposto(boolean eCpmposto) {
        this.eCpmposto = eCpmposto;
    }

    public Integer getStockAtual() {
        return stockAtual;
    }

    public void setStockAtual(Integer stockAtual) {
        this.stockAtual = stockAtual;
    }
}