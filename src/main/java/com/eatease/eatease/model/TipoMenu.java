package com.eatease.eatease.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tipoMenu")

public class TipoMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome; //PRINCIPAL, SNACKS, SOBREMESAS, BEBIDAS

    public TipoMenu(String nome){
        this.nome = nome;
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

    
}
