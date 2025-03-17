package com.eatease.eatease.repository;

import com.eatease.eatease.model.Pedido;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    //Optional<Pedido> findByNome(String nome);
}
