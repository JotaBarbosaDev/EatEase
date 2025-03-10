package com.eatease.eatease.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tipoPrato")

public class TipoPrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome; //CARNE, PEIXE SOBREMESAS, BEBIDA, SNACK

    public TipoPrato(String nome){
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
