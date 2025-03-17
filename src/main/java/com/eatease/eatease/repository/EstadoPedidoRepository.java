package com.eatease.eatease.repository;

import com.eatease.eatease.model.EstadoPedido;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoPedidoRepository extends JpaRepository<EstadoPedido, Long> {

    Optional<EstadoPedido> findByNome(String nome);
}
