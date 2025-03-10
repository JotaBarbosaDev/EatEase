package com.eatease.eatease.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cargo")

public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome; //Gerente, Funcionario

    public Cargo(String nome){
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
