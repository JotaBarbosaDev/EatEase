package com.eatease.eatease.model;

import jakarta.persistence.*;

@Entity
@Table(name = "movimentosIngredientes")

public class MovimentosIngredientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long id_ingrediente;
    private long tipoMovimento_id;
    private int quantidade;
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

    public long getTipoMovimento_id() {
        return tipoMovimento_id;
    }

    public void setTipoMovimento_id(long tipoMovimento_id) {
        this.tipoMovimento_id = tipoMovimento_id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
