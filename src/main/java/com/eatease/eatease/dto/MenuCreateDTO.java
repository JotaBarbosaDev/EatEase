package com.eatease.eatease.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Data Transfer Object for creating and updating menus.
 * Handles all validations for menu-related operations.
 */
@Schema(description = "DTO para criação e atualização de menus")
public class MenuCreateDTO {

    @NotNull(message = "A lista de IDs de itens não pode ser nula")
    @NotEmpty(message = "A lista de IDs de itens não pode estar vazia")
    @JsonProperty("itemsIds")
    @Schema(description = "Lista de IDs dos itens que compõem o menu", required = true)
    private List<Long> itemsIds = new ArrayList<>();

    @NotNull(message = "O tipo de menu é obrigatório")
    @Schema(description = "ID do tipo de menu", required = true, example = "1")
    private Long tipoMenuId;

    @NotBlank(message = "O nome do menu é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ0-9\\s\\-_]+$", message = "O nome deve conter apenas letras, números, espaços, hífens e sublinhados")
    @Schema(description = "Nome do menu", required = true, example = "Menu Executivo")
    private String nome;

    @Size(max = 500, message = "A descrição não pode ter mais de 500 caracteres")
    @Schema(description = "Descrição do menu", example = "Menu especial com entrada, prato principal e sobremesa")
    private String descricao;

    /**
     * Construtor padrão
     */
    public MenuCreateDTO() {
    }

    /**
     * Construtor com todos os campos
     * 
     * @param itemsIds   Lista de IDs dos itens que compõem o menu
     * @param tipoMenuId ID do tipo de menu
     * @param nome       Nome do menu
     * @param descricao  Descrição do menu
     */
    public MenuCreateDTO(List<Long> itemsIds, Long tipoMenuId, String nome, String descricao) {
        this.itemsIds = itemsIds != null ? new ArrayList<>(itemsIds) : new ArrayList<>();
        this.tipoMenuId = tipoMenuId;
        this.nome = nome;
        this.descricao = descricao;
    }

    /**
     * Obtém a lista de IDs dos itens que compõem o menu
     * 
     * @return Lista de IDs dos itens
     */
    public List<Long> getItemsIds() {
        return new ArrayList<>(itemsIds);
    }

    /**
     * Define a lista de IDs dos itens que compõem o menu
     * 
     * @param itemsIds Lista de IDs dos itens
     */
    public void setItemsIds(List<Long> itemsIds) {
        this.itemsIds = itemsIds != null ? new ArrayList<>(itemsIds) : new ArrayList<>();
    }

    /**
     * Obtém o ID do tipo de menu
     * 
     * @return ID do tipo de menu
     */
    public Long getTipoMenuId() {
        return tipoMenuId;
    }

    /**
     * Define o ID do tipo de menu
     * 
     * @param tipoMenuId ID do tipo de menu
     */
    public void setTipoMenuId(Long tipoMenuId) {
        this.tipoMenuId = tipoMenuId;
    }

    /**
     * Obtém o nome do menu
     * 
     * @return Nome do menu
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do menu
     * 
     * @param nome Nome do menu
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém a descrição do menu
     * 
     * @return Descrição do menu
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a descrição do menu
     * 
     * @param descricao Descrição do menu
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Adiciona um item à lista de itens do menu
     * 
     * @param itemId ID do item a ser adicionado
     * @return true se o item foi adicionado, false caso contrário
     */
    public boolean addItem(Long itemId) {
        if (itemId != null && !itemsIds.contains(itemId)) {
            return itemsIds.add(itemId);
        }
        return false;
    }

    /**
     * Remove um item da lista de itens do menu
     * 
     * @param itemId ID do item a ser removido
     * @return true se o item foi removido, false caso contrário
     */
    public boolean removeItem(Long itemId) {
        return itemsIds.remove(itemId);
    }

    @Override
    public String toString() {
        return "MenuCreateDTO{" +
                "itemsIds=" + itemsIds +
                ", tipoMenuId=" + tipoMenuId +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
