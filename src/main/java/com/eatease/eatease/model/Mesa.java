package com.eatease.eatease.model;

import jakarta.persistence.*;

@Entity
@Table(name = "mesa")

public class Mesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int numero;
    private boolean estadoLivre; //true=livre, false=ocupado
    
    public Mesa(int numero, boolean estadoLivre) {
        this.numero = numero;
        this.estadoLivre = estadoLivre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean isEstadoLivre() {
        return estadoLivre;
    }

    public void setEstadoLivre(boolean estadoLivre) {
        this.estadoLivre = estadoLivre;
    }
    
    
    
}
