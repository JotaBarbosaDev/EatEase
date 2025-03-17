package com.eatease.eatease.model;

import jakarta.persistence.*;

@Entity
@Table(name = "unidadeMedida")

public class ItemIngrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long ingredientes_id;
    private int quantidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getIngredientes_id() {
        return ingredientes_id;
    }

    public void setIngredientes_id(long ingredientes_id) {
        this.ingredientes_id = ingredientes_id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
}
