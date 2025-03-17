package com.eatease.eatease.model;

import jakarta.persistence.*;

@Entity
@Table(name = "funcionario")

public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private String cargo_id;

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

    public String getCargo_id() {
        return cargo_id;
    }

    public void setCargo_id(String cargo_id) {
        this.cargo_id = cargo_id;
    }

    
    
}
