package com.eatease.eatease.model;

import jakarta.persistence.*;

@Entity
@Table(name = "movimentosIngredientes")

public class MovimentosIngredientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long id_ingrediente;
    private int quantidade_anterior;
    private int quantidade_atualizada;
    private String data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getId_ingrediente() {
        return id_ingrediente;
    }

    public void setId_ingrediente(long id_ingrediente) {
        this.id_ingrediente = id_ingrediente;
    }

    public int getQuantidade_anterior() {
        return quantidade_anterior;
    }

    public void setQuantidade_anterior(int quantidade_anterior) {
        this.quantidade_anterior = quantidade_anterior;
    }

    public int getQuantidade_atualizada() {
        return quantidade_atualizada;
    }

    public void setQuantidade_atualizada(int quantidade_atualizada) {
        this.quantidade_atualizada = quantidade_atualizada;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
