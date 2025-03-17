package com.eatease.eatease.model;

import jakarta.persistence.*;


@Entity
@Table(name = "ingredientes")

public class Ingredientes {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private int stock;
    private int stock_min;
    private String unidade_id;

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

    public String getUnidade_id() {
        return unidade_id;
    }

    public void setUnidade_id(String unidade_id) {
        this.unidade_id = unidade_id;
    }

}
