package com.eatease.eatease.model;

import jakarta.persistence.*;

@Entity
@Table(name = "pedido")

public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long prato_id;
    private long estadoPedido_id;
    private long mesa_id;
    private long funcionario_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPrato_id() {
        return prato_id;
    }

    public void setPrato_id(long prato_id) {
        this.prato_id = prato_id;
    }

    public long getEstadoPedido_id() {
        return estadoPedido_id;
    }

    public void setEstadoPedido_id(long estadoPedido_id) {
        this.estadoPedido_id = estadoPedido_id;
    }

    public long getMesa_id() {
        return mesa_id;
    }

    public void setMesa_id(long mesa_id) {
        this.mesa_id = mesa_id;
    }

    public long getFuncionario_id() {
        return funcionario_id;
    }

    public void setFuncionario_id(long funcionario_id) {
        this.funcionario_id = funcionario_id;
    }   
    
}
