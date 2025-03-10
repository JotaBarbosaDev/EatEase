package com.eatease.eatease.model;

import jakarta.persistence.*;

@Entity
@Table(name = "menu")

public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long pratos_id[];
    private long items_id[];
    private long tipoMenu[];

    public Menu(long pratos_id[], long items_id[], long tipoMenu[]){
        this.pratos_id = pratos_id;
        this.items_id = items_id;
        this.tipoMenu = tipoMenu;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long[] getPratos_id() {
        return pratos_id;
    }

    public void setPratos_id(long[] pratos_id) {
        this.pratos_id = pratos_id;
    }

    public long[] getItems_id() {
        return items_id;
    }

    public void setItems_id(long[] items_id) {
        this.items_id = items_id;
    }

    public long[] getTipoMenu() {
        return tipoMenu;
    }

    public void setTipoMenu(long[] tipoMenu) {
        this.tipoMenu = tipoMenu;
    }

    
}
