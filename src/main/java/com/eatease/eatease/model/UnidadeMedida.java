package com.eatease.eatease.model;

import jakarta.persistence.*;

@Entity
@Table(name = "unidadeMedida") // Define a tabela no PostgreSQL
public class UnidadeMedida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private long ingrediente_id;

    public UnidadeMedida(String nome, long ingrediente_id) {
        this.nome = nome;
        this.ingrediente_id = ingrediente_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getIngrediente_id() {
        return ingrediente_id;
    }

    public void setIngrediente_id(long ingrediente_id) {
        this.ingrediente_id = ingrediente_id;
    }
    
}
