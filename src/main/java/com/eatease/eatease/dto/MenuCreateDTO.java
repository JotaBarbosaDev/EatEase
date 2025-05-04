package com.eatease.eatease.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object for creating a new Menu.
 * A menu consists of arrays of item IDs and menu type IDs.
 */
public class MenuCreateDTO {
    @NotNull(message = "A lista de IDs de itens não pode ser nula")
    @NotEmpty(message = "A lista de IDs de itens não pode estar vazia")
    private long[] itemsIds;

    @NotNull(message = "A lista de IDs de tipos de menu não pode ser nula")
    @NotEmpty(message = "A lista de IDs de tipos de menu não pode estar vazia")
    private long tipoMenuId;

    // Nome do menu (opcional)
    private String nome;

    // Descrição do menu (opcional)
    private String descricao;

    // Constructors
    public MenuCreateDTO() {
    }

    public MenuCreateDTO(long[] itemsIds, long tipoMenuIds, String nome, String descricao) {
        this.itemsIds = itemsIds;
        this.tipoMenuId = tipoMenuIds;
        this.nome = nome;
        this.descricao = descricao;
    }

    // Getters and Setters
    public long[] getItemsIds() {
        return itemsIds;
    }

    public void setItemsIds(long[] itemsIds) {
        this.itemsIds = itemsIds;
    }

    public long getTipoMenuId() {
        return tipoMenuId;
    }

    public void setTipoMenuId(long tipoMenuId) {
        this.tipoMenuId = tipoMenuId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
