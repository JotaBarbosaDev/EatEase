package com.eatease.eatease.model;

import jakarta.persistence.*;

@Entity
@Table(name = "item")

public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private long tipoPrato_id;
    private float preco;
    private long ingrediente_id[];
    private boolean eCpmposto;
    private int stockAtual; 

    public Item(String nome, long tipoPrato_id, float preco, long ingrediente_id[], boolean eCpmposto, int stockAtual){
        this.nome = nome;
        this.tipoPrato_id = tipoPrato_id;
        this.preco = preco;
        this.ingrediente_id = ingrediente_id;
        this.eCpmposto = eCpmposto;
        this.stockAtual = stockAtual;
    }

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

    public long getTipoPrato_id() {
        return tipoPrato_id;
    }

    public void setTipoPrato_id(long tipoPrato_id) {
        this.tipoPrato_id = tipoPrato_id;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public long[] getIngrediente_id() {
        return ingrediente_id;
    }

    public void setIngrediente_id(long[] ingrediente_id) {
        this.ingrediente_id = ingrediente_id;
    }

    public boolean iseCpmposto() {
        return eCpmposto;
    }

    public void seteCpmposto(boolean eCpmposto) {
        this.eCpmposto = eCpmposto;
    }

    public int getStockAtual() {
        return stockAtual;
    }

    public void setStockAtual(int stockAtual) {
        this.stockAtual = stockAtual;
    }

}
