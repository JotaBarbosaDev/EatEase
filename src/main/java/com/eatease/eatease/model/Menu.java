package com.eatease.eatease.model;

import jakarta.persistence.*;

@Entity
@Table(name = "menu")

public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private String descricao;
    private long items_id[];
    private long tipoMenu;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long[] getItems_id() {
        return items_id;
    }

    public void setItems_id(long[] items_id) {
        this.items_id = items_id;
    }

    public long getTipoMenu() {
        return tipoMenu;
    }

    public void setTipoMenu(long tipoMenu) {
        this.tipoMenu = tipoMenu;
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
